-module(myclient).
-export([test/1,start/1,loop/2,connect/2,login/3,receive_data/1,send_message/3]).

-define(SOCKET_OPTS,[binary,{packet,0},{active,false}]).

test(N) when N < 10 ->
    receive after 1000 ->
	io:format("Spawn new client~n"),
	S = connect("172.0.7.193",8000),	 
	UserName = erlang:integer_to_list(N),
	login(UserName,"password",S),
        spawn(fun() -> loop(N,S) end),
	N1 = N + 1,
	test(N1)
    end;
test(_) ->
    {stop,normal}.

start(N)  ->
   % <<I:160/integer>> = crypto:sha(term_to_binary({make_ref(), now()})),
   % UserName = lists:flatten(io_lib:fwrite("~40..0s", [erlang:integer_to_list(I, 16)])),
    UserName = erlang:integer_to_list(N),
    io:format("Client start.~p~n",[UserName]),
    S = connect("172.0.7.193",8000),
    login(UserName,"pass",S).

loop(N,Socket) ->
    receive after 2000 ->
    N1 = N + 1,
    To = erlang:integer_to_list(N1),
    Msg = "Hello world",
    send_message(To,Msg,Socket),
    loop(N,Socket)
    end.

connect(Host,Port) ->
    {ok,Socket} = gen_tcp:connect(Host,Port,?SOCKET_OPTS),
    spawn(fun() -> receive_data(Socket) end),
    Socket.


login(UserName,Password,Socket) ->
    Header = "auth",
    HeaderLen = string:len(Header),
    UserNameLen = string:len(UserName),
    PasswordLen = string:len(Password),
    AuthStr = [HeaderLen,Header,UserNameLen,UserName,PasswordLen,Password],
    gen_tcp:send(Socket,list_to_binary(AuthStr)).

send_message(To,Message,Socket) ->
    
    Header = "send_message",
    HeaderLen = string:len(Header),
    ToLen = string:len(To),
    MessageLen = string:len(Message),
    BodyLen = ToLen + MessageLen + 2,
    SendMsg = [HeaderLen,Header,BodyLen,ToLen,To,MessageLen,Message],
    gen_tcp:send(Socket,list_to_binary(SendMsg)).


receive_data(Socket) ->
    case gen_tcp:recv(Socket,0) of
	{ok, Packet} -> 
	    io:format("CRECV:~p~n",[Packet]),
	    receive_data(Socket);
	{error,Reason} ->
	    io:format("Error client~n")
    end.
    

