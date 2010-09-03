%%%-------------------------------------------------------------------
%%% @author Ammen <>
%%% @copyright (C) 2010, Ammen
%%% @doc
%%%
%%% @end
%%% Created :  5 Aug 2010 by Ammen <>
%%%-------------------------------------------------------------------
-module(client_handler).

-import(xmerl_scan).

-behaviour(gen_fsm).

-include_lib("xmerl/include/xmerl.hrl").

%% API
-export([start_link/1]).
%% FSM States
-export([wait_for_handshake/2,wait_for_data/2,wait_for_login/2]).
-export([get_msg/1,login/1]).

%% gen_fsm callbacks
-export([init/1, handle_event/3,handle_sync_event/4, handle_info/3, terminate/3, code_change/4]).

-record(state, {socket,addr,group}).

-define(HAND_SHAKE,"HTTP/1.1 101 Web Socket Protocol Handshake\r\nUpgrade: WebSocket\r\nConnection: Upgrade\r\nWebSocket-Origin: http://localhost:8080\r\nWebSocket-Location: ws://localhost:9000/chat\r\n\r\n").

%%%===================================================================
%%% API
%%%===================================================================

%%--------------------------------------------------------------------
%% @doc
%% Creates a gen_fsm process which calls Module:init/1 to
%% initialize. To ensure a synchronized start-up procedure, this
%% function does not return until Module:init/1 has returned.
%%
%% @spec start_link() -> {ok, Pid} | ignore | {error, Error}
%% @end
%%--------------------------------------------------------------------
start_link(Args) ->
    gen_fsm:start_link(?MODULE, [Args], []).

%%%===================================================================
%%% gen_fsm callbacks
%%%===================================================================

%%--------------------------------------------------------------------
%% @private
%% @doc
%% Whenever a gen_fsm is started using gen_fsm:start/[3,4] or
%% gen_fsm:start_link/[3,4], this function is called by the new
%% process to initialize.
%%
%% @spec init(Args) -> {ok, StateName, State} |
%%                     {ok, StateName, State, Timeout} |
%%                     ignore |
%%                     {stop, StopReason}
%% @end
%%--------------------------------------------------------------------
init([Args]) ->
    {ok, {IP, _Port}} = inet:peername(Args),
    {ok, wait_for_handshake, #state{socket=Args,addr=IP}}.

%%--------------------------------------------------------------------
%% @private
%% @doc
%% There should be one instance of this function for each possible
%% state name. Whenever a gen_fsm receives an event sent using
%% gen_fsm:send_event/2, the instance of this function with the same
%% name as the current state name StateName is called to handle
%% the event. It is also called if a timeout occurs.
%%
%% @spec wait_for_data(Event, State) ->
%%                   {next_state, NextStateName, NextState} |
%%                   {next_state, NextStateName, NextState, Timeout} |
%%                   {stop, Reason, NewState}
%% @end
%%--------------------------------------------------------------------

wait_for_handshake({data,Bin},#state{socket=Socket} = State) ->
    io:format("SRECV:~p~n",[Bin]),
    io:format("Write:~p~n",[?HAND_SHAKE]),
    gen_tcp:send(Socket,?HAND_SHAKE),
    {next_state, wait_for_login, State};
wait_for_handshake(Other, State) ->
    error_logger:error_msg("State: wait_for_data. Unexpected message: ~p\n", [Other]),
    {next_state, wait_for_handshake, State}.

wait_for_login({data,Bin},#state{socket=Socket,addr=Addr}=State) ->
    LoginMsg = get_msg(Bin),
    io:format("Login Msg:~p~n",[LoginMsg]),
    {ok,[UserName,Password,Group]} = login(LoginMsg),
    register(list_to_atom(UserName),self()),
    groupmanager:join_group(Group,self()),
    NewState = #state{socket=Socket,addr=Addr,group=Group},
    io:format("login success~n"),
    {next_state, wait_for_data, NewState};
wait_for_login(Other,State) ->
    {next_state, wait_for_data, State}.

wait_for_data({data,Bin}, #state{socket=Socket,group=Group} = State) ->
    Msg = get_msg(Bin),
    io:format("RECV:~p~n",[Msg]),
    groupmanager:send_msg(Group,Msg),
    {next_state, wait_for_data, State};
wait_for_data({send_msg,Msg}, #state{socket=Socket} = State) ->
    Message = [0,Msg,255],
    io:format("Send message to client:~p by:~p~n",[list_to_binary(Message),Socket]),
    gen_tcp:send(Socket,Message),
    {next_state, wait_for_data, State}.
%%--------------------------------------------------------------------
%% @private
%% @doc
%% Whenever a gen_fsm receives an event sent using
%% gen_fsm:send_all_state_event/2, this function is called to handle
%% the event.
%%
%% @spec handle_event(Event, StateName, State) ->
%%                   {next_state, NextStateName, NextState} |
%%                   {next_state, NextStateName, NextState, Timeout} |
%%                   {stop, Reason, NewState}
%% @end
%%--------------------------------------------------------------------
handle_event(_Event, StateName, State) ->
    {next_state, StateName, State}.

%%--------------------------------------------------------------------
%% @private
%% @doc
%% Whenever a gen_fsm receives an event sent using
%% gen_fsm:sync_send_all_state_event/[2,3], this function is called
%% to handle the event.
%%
%% @spec handle_sync_event(Event, From, StateName, State) ->
%%                   {next_state, NextStateName, NextState} |
%%                   {next_state, NextStateName, NextState, Timeout} |
%%                   {reply, Reply, NextStateName, NextState} |
%%                   {reply, Reply, NextStateName, NextState, Timeout} |
%%                   {stop, Reason, NewState} |
%%                   {stop, Reason, Reply, NewState}
%% @end
%%--------------------------------------------------------------------
handle_sync_event(_Event, _From, StateName, State) ->
    Reply = ok,
    {reply, Reply, StateName, State}.

%%--------------------------------------------------------------------
%% @private
%% @doc
%% This function is called by a gen_fsm when it receives any
%% message other than a synchronous or asynchronous event
%% (or a system message).
%%
%% @spec handle_info(Info,StateName,State)->
%%                   {next_state, NextStateName, NextState} |
%%                   {next_state, NextStateName, NextState, Timeout} |
%%                   {stop, Reason, NewState}
%% @end
%%--------------------------------------------------------------------

%handle_info({tcp,Socket,Bin},StateName,State)->
%    inet:setopts(Socket, ?SOCKET_OPTS),
%    ?MODULE:StateName({data,Bin},StateData);
%
%handle_info({tcp_closed,Socket},StateName,State) ->
%    io:format("Connection colosed.~n"),
%    {stop,normal,StateData};
handle_info({tcp,Socket,Bin},StateName, State)->
    inet:setopts(Socket, [{active, once}]),
    ?MODULE:StateName({data, Bin}, State);
handle_info({tcp_closed,Socket},StateName,State) ->
    io:format("Socket closed.~n"),
    {stop, normal, State};
handle_info(_Info, StateName, State) ->
    {next_state, StateName, State}.

%%--------------------------------------------------------------------
%% @private
%% @doc
%% This function is called by a gen_fsm when it is about to
%% terminate. It should be the opposite of Module:init/1 and do any
%% necessary cleaning up. When it returns, the gen_fsm terminates with
%% Reason. The return value is ignored.
%%
%% @spec terminate(Reason, StateName, State) -> void()
%% @end
%%--------------------------------------------------------------------
terminate(_Reason, _StateName, _State) ->
    ok.

%%--------------------------------------------------------------------
%% @private
%% @doc
%% Convert process state when code is changed
%%
%% @spec code_change(OldVsn, StateName, State, Extra) ->
%%                   {ok, StateName, NewState}
%% @end
%%--------------------------------------------------------------------
code_change(_OldVsn, StateName, State, _Extra) ->
    {ok, StateName, State}.

%%%===================================================================
%%% Internal functions
%%%===================================================================
get_msg(Bin)->
    BinLen = byte_size(Bin),
    MsgLen = BinLen - 2,
    <<Begin:8,Msg:MsgLen/binary-unit:8,End/binary>> = Bin,
    binary_to_list(Msg).
    
login(Msg) ->
    %try xmerl_scan:string(Msg)
    %catch
    %   error:X ->
    %	    io:format("Error is:~p~n",[X])
    %end.
    {Doc,Misc} = xmerl_scan:string(Msg),
    #xmlElement{name=Xmlname} = Doc,
    Result = if
	Xmlname =/="auth" ->
	    [#xmlAttribute{value=UserName}] = xmerl_xpath:string("//@username", Doc),
	    [#xmlAttribute{value=Password}] = xmerl_xpath:string("//@password", Doc),
	    [#xmlAttribute{value=Group}] = xmerl_xpath:string("//@group", Doc),
	    {ok,[UserName,Password,Group]};
	true ->
	    {stop,"It's not a login msg~n"}
    end,
    io:format("XMl is:~p~n",[Result]),
    Result.
