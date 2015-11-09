.PHONY:all
all:server mysqlclient epoll_udp_server
server:
	gcc main.c -o server
mysqlclient:
	gcc -I/usr/include/mysql mysql_conn.c -o mysqlclient -L/usr/lib64/mysql -lmysqlclient
epoll_udp_server:
	gcc epoll_udp_server.c -pthread -o epoll_udp_server -lpthread
