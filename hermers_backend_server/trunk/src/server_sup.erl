%%%-------------------------------------------------------------------
%%% @author Ammen <>
%%% @copyright (C) 2010, Ammen
%%% @doc
%%%
%%% @end
%%% Created :  5 Aug 2010 by Ammen <>
%%%-------------------------------------------------------------------
-module(server_sup).

-behaviour(supervisor).

%% API
-export([start_link/0]).

%% Supervisor callbacks
-export([init/1]).

-define(SERVER, ?MODULE).

%%%===================================================================
%%% API functions
%%%===================================================================

%%--------------------------------------------------------------------
%% @doc
%% Starts the supervisor
%%
%% @spec start_link() -> {ok, Pid} | ignore | {error, Error}
%% @end
%%--------------------------------------------------------------------
start_link() ->
    {ok,Pid} = supervisor:start_link({local, ?SERVER}, ?MODULE, []),
    unlink(Pid),
    {ok,Pid}.


%%%===================================================================
%%% Supervisor callbacks
%%%===================================================================

%%--------------------------------------------------------------------
%% @private
%% @doc
%% Whenever a supervisor is started using supervisor:start_link/[2,3],
%% this function is called by the new process to find out about
%% restart strategy, maximum restart frequency and child
%% specifications.
%%
%% @spec init(Args) -> {ok, {SupFlags, [ChildSpec]}} |
%%                     ignore |
%%                     {error, Reason}
%% @end
%%--------------------------------------------------------------------
init([]) ->
    RestartStrategy = one_for_one,
    MaxRestarts = 1000,
    MaxSecondsBetweenRestarts = 3600,

    SupFlags = {RestartStrategy, MaxRestarts, MaxSecondsBetweenRestarts},

    Restart = permanent,
    Shutdown = 2000,
    Type = worker,

    ServerSpec = {server, {server, start_link, []},
	      Restart, Shutdown, Type, [server]},

    ClientHandlerSpec = {client_handler_sup, {client_handler_sup, start_link, []},
	      Restart, infinity, supervisor, [client_handler_sup]},

    GroupManagerSpec = {groupmanager, {groupmanager, start_link, []},
	      Restart, Shutdown, Type, [groupmanager]},


    {ok, {SupFlags, [ServerSpec,ClientHandlerSpec,GroupManagerSpec]}}.

%%%===================================================================
%%% Internal functions
%%%===================================================================
