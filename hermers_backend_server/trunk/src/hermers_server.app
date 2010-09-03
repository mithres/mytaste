{application, hermers_server, 
[{description, "The demo tcp server"}, 
{vsn, "1.0"}, 
{modules, [hermers_app, server_sup,server,client_handler_sup,client_handler]}, 
{registered,[server_sup,server,client_handler_sup]}, 
{applications, [kernel,stdlib]}, 
{mod, {hermers_app,[]}}, 
{start_phases, []} 
]}.