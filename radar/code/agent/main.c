#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <string.h>
#include <stdlib.h>

#define BUFFER_SIZE 40

int main(int argc,char *argv[])
{
  int client_sockfd;
  int len;
  struct sockaddr_in remote_addr;
  char buf[BUFFER_SIZE];
  memset(&remote_addr,0,sizeof(remote_addr));
  remote_addr.sin_family=AF_INET;
  remote_addr.sin_addr.s_addr=inet_addr("127.0.0.1");
  remote_addr.sin_port=htons(8000);
  
  if((client_sockfd=socket(PF_INET,SOCK_STREAM,0))<0)
  {
     perror("client socket creation failed");
     exit(EXIT_FAILURE);
  }
  
  if(connect(client_sockfd,(struct sockaddr *)&remote_addr,sizeof(struct sockaddr))<0)
  {
     perror("connect to server failed");
     exit(EXIT_FAILURE);
  }
  while(1)
  {
     printf("Plase input the message:");
     scanf("%s",buf);
     if(strcmp(buf,"exit")==0)
     {
       break;
     }
     send(client_sockfd,buf,BUFFER_SIZE,0);
     //
     len=recv(client_sockfd,buf,BUFFER_SIZE,0);
     printf("receive from server:%s\n",buf);
     if(len<0)
     {
       perror("receive from server failed");
       exit(EXIT_FAILURE);
     }
  }
   close(client_sockfd);
  return 0;
} 
