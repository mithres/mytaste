%%%-------------------------------------------------------------------
%%% @author Ammen <>
%%% @copyright (C) 2010, Ammen
%%% @doc
%%%
%%% @end
%%% Created :  5 Aug 2010 by Ammen <>
%%%-------------------------------------------------------------------
-module(server).

-behaviour(gen_server).

%% API
-export([start_link/0]).
-export([accept/1]).
%% gen_server callbacks
-export([init/1, handle_call/3, handle_cast/2, handle_info/2,
	 terminate/2, code_change/3]).

-define(SERVER, ?MODULE). 
-define(DEFAULT_PORT,9000).
-define(SOCKET_OPTS,[binary,{packet,0},{active,once},{reuseaddr,true},{nodelay,true}]).

-record(state, {lsocket,port,acceptor}).

%%%===================================================================
%%% API
%%%===================================================================

%%--------------------------------------------------------------------
%% @doc
%% Starts the server
%%
%% @spec start_link() -> {ok, Pid} | ignore | {error, Error}
%% @end
%%--------------------------------------------------------------------
start_link() ->
    io:format("Server starting..."),
    gen_server:start_link({local, ?SERVER}, ?MODULE, [?DEFAULT_PORT], []).

%%%===================================================================
%%% gen_server callbacks
%%%===================================================================

%%--------------------------------------------------------------------
%% @private
%% @doc
%% Initializes the server
%%
%% @spec init(Args) -> {ok, State} |
%%                     {ok, State, Timeout} |
%%                     ignore |
%%                     {stop, Reason}
%% @end
%%--------------------------------------------------------------------
init([Port]) ->
    case gen_tcp:listen(Port,?SOCKET_OPTS) of
	{ok , Listener} ->
	    io:format("Server listen on:~p~n",[Port]),
	    Pid = spawn_link(?SERVER,accept,[Listener]),
	    {ok, #state{port=Port,lsocket=Listener,acceptor=Pid}};
	{error, Reason} ->
	    error_logger:error_msg("Listen on port:~p error:~p.~n",[Port,Reason]),
	    {stop, Reason}
    end.

%%--------------------------------------------------------------------
%% @private
%% @doc
%% Handling call messages
%%
%% @spec handle_call(Request, From, State) ->
%%                                   {reply, Reply, State} |
%%                                   {reply, Reply, State, Timeout} |
%%                                   {noreply, State} |
%%                                   {noreply, State, Timeout} |
%%                                   {stop, Reason, Reply, State} |
%%                                   {stop, Reason, State}
%% @end
%%--------------------------------------------------------------------
handle_call(_Request, _From, State) ->
    Reply = ok,
    {reply, Reply, State}.

%%--------------------------------------------------------------------
%% @private
%% @doc
%% Handling cast messages
%%
%% @spec handle_cast(Msg, State) -> {noreply, State} |
%%                                  {noreply, State, Timeout} |
%%                                  {stop, Reason, State}
%% @end
%%--------------------------------------------------------------------
handle_cast(_Msg, State) ->
    {noreply, State}.

%%--------------------------------------------------------------------
%% @private
%% @doc
%% Handling all non call/cast messages
%%
%% @spec handle_info(Info, State) -> {noreply, State} |
%%                                   {noreply, State, Timeout} |
%%                                   {stop, Reason, State}
%% @end
%%--------------------------------------------------------------------
handle_info(_Info, State) ->
    {noreply, State}.

%%--------------------------------------------------------------------
%% @private
%% @doc
%% This function is called by a gen_server when it is about to
%% terminate. It should be the opposite of Module:init/1 and do any
%% necessary cleaning up. When it returns, the gen_server terminates
%% with Reason. The return value is ignored.
%%
%% @spec terminate(Reason, State) -> void()
%% @end
%%--------------------------------------------------------------------
terminate(_Reason, _State) ->
    ok.

%%--------------------------------------------------------------------
%% @private
%% @doc
%% Convert process state when code is changed
%%
%% @spec code_change(OldVsn, State, Extra) -> {ok, NewState}
%% @end
%%--------------------------------------------------------------------
code_change(_OldVsn, State, _Extra) ->
    {ok, State}.

%%%===================================================================
%%% Internal functions
%%%===================================================================

accept(LSocket) ->
    case gen_tcp:accept(LSocket) of
	{ok,Socket} ->
	    {ok,ChildHandler} = supervisor:start_child(client_handler_sup,[Socket]),
	    gen_tcp:controlling_process(Socket,ChildHandler),
	    %gen_fsm:send_event(ChildHandler,{receive_data,Socket}),
	    accept(LSocket);
	{error,Reason} ->
	    {stop,Reason}
    end.

