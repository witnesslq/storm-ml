#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>
#include <sys/epoll.h>

#define BUFFER_SIZE 40
#define MAX_EVENTS 40

int main(int argc,char * argv[])
{
  int server_sockfd;
  int client_sockfd;
  int len;
  struct sockaddr_in my_addr;
  struct sockaddr_in remote_addr;
  int sin_size;
  char buf[BUFFER_SIZE];
  memset(&my_addr,0,sizeof(my_addr));
  my_addr.sin_family=AF_INET;
  my_addr.sin_addr.s_addr=INADDR_ANY;
  my_addr.sin_port=htons(8000);
  if((server_sockfd=socket(PF_INET,SOCK_STREAM,0))<0)
  {
    perror("socket");
    return 1;
  }
  if(bind(server_sockfd,(struct sockaddr *)&my_addr,sizeof(struct sockaddr))<0)
  {
    perror("bind");
    return 1;
  }
  listen(server_sockfd,5);
  sin_size=sizeof(struct sockaddr_in);
  
  int epoll_fd;
  epoll_fd=epoll_create(MAX_EVENTS);
  if(epoll_fd==-1)
  {
    perror("epoll_create failed");
    exit(EXIT_FAILURE);
  }

  struct epoll_event ev;
  struct epoll_event events[MAX_EVENTS];
  ev.events=EPOLLIN;
  ev.data.fd=server_sockfd;
  
  if(epoll_ctl(epoll_fd,EPOLL_CTL_ADD,server_sockfd,&ev)==-1)
  {
    perror("epll_ctl:server_sockfd register failed");
    exit(EXIT_FAILURE);
  }

  int nfds;
  
   while(1)
   {
     nfds=epoll_wait(epoll_fd,events,MAX_EVENTS,-1);
     if(nfds==-1)
     {
        perror("start poll_wait failed");
        exit(EXIT_FAILURE);
     }
     int i;
     for(i=0;i<nfds;i++)
     {
         if(events[i].data.fd==server_sockfd)
         {
           if((client_sockfd=accept(server_sockfd,(struct sockaddr *)&remote_addr,&sin_size))<0) 
           {
             perror("accept client_sockfd failed");
             exit(EXIT_FAILURE);
            }
           ev.events=EPOLLIN;
           ev.data.fd=client_sockfd;
           if(epoll_ctl(epoll_fd,EPOLL_CTL_ADD,client_sockfd,&ev)==-1)
           {
             perror("epoll_ctl:client sockfd register failed");
             exit(EXIT_FAILURE);
           }
           printf("accept client %s\n",inet_ntoa(remote_addr.sin_addr));
      
         }else{

          len=recv(client_sockfd,buf,BUFFER_SIZE,0);
          if(len<0)
          {
           perror("receive from client failed");
           exit(EXIT_FAILURE);
          }
          printf("receive from client:%s\n",buf);
          send(client_sockfd,"I have received you message.",30,0);
         }
        
     }

   }
   return 0;
}
