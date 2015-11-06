#ifndef _UDP_CLIENT__
#define _UDP_CLIENT__

#include <sys/types.h>
#include <sys/socket.h>
#include <string.h>
#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <arpa/inet.h>
#include <unistd.h>

#define MAXLEN 1024
#define SERV_PORT 8888

void do_cli(FILE *fp,int sockfd,struct sockaddr *pservaddr,sockelen_t servlen);

void hear_beat(char * ip_addr);

#endif
