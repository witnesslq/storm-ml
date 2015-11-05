#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <sys/wait.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <fcntl.h>
#include <sys/epoll.h>
#include <sys/time.h>
#include <sys/resource.h>
#include <pthread.h>
#include <assert.h>

#define MAXBUF 1024
#define MAXEPOLLSIZE 100

int setnonblocking(int sockfd)
{
   if(fcntl(sockfd,F_SETFL,fcntl(sockfd,F_GETFD,0)|O_NONBLOCK)==-1)
   {
     return -1;
   }
   return 0;
}
void * pthread_handle_message(int * sock_fd)
{
   char recvbuf[MAXBUF+1];
   char sendbuf[MAXBUF+1];
   
   int ret;
   int new_fd;

   struct  sockaddr_in client_addr;
   socklen_t cli_len=sizeof(client_addr);

   new_fd=* sock_fd;
   bzero(recvbuf,MAXBUF+1);
   bzero(sendbuf,MAXBUF+1);

   ret= recvfrom(new_fd,recvbuf,MAXBUF,0,(struct sockaddr *)&client_addr,&cli_len);
   if(ret>0)
   {
     printf("socket %d :%s:%d message:%s :%d\n",new_fd,inet_ntoa(client_addr.sin_addr),ntohs(client_addr.sin_port),recvbuf,ret);

    ret=sendto(new_fd,sendbuf,strlen(sendbuf),0,(struct sockaddr *)&client_addr,cli_len);
    if(ret<0)
    {
      //printf("message send error %s,error msg:%s\n",errno,strerror(errno));
    }
   }else{
     printf("message recv faild ");
   }
   fflush(stdout);

   pthread_exit(NULL);
}

int main(int argc,char **argv)
{
   int listener,kdpfd,nfds,n,curfds;
   socklen_t len;
   struct sockaddr_in my_addr,their_addr;
   unsigned int myport;
   struct epoll_event ev;
   struct epoll_event events[MAXEPOLLSIZE];
   struct rlimit rt;
    myport=8888;
   pthread_t thread;
   pthread_attr_t attr;


   rt.rlim_max=rt.rlim_cur=MAXEPOLLSIZE;
   if(setrlimit(RLIMIT_NOFILE,&rt)==-1)
   {
      perror("setrlimit");
      exit(1);
   }else{
    printf("set rlim_max successed!\n");
   }
   
   /*start socket listen*/
   if((listener = socket(PF_INET,SOCK_DGRAM,0))==-1)
   {
      perror("create socket faild!\n");
      exit(1);
   }else{
      printf("create socket successed!\n");
   }

   /*set socket attr,port */
   int opt=SO_REUSEADDR;
   setsockopt(listener,SOL_SOCKET,SO_REUSEADDR,&opt,sizeof(opt));
  
   setnonblocking(listener);
   
   bzero(&my_addr,sizeof(my_addr));
   
   my_addr.sin_family=PF_INET;
   my_addr.sin_port=htons(myport);
   my_addr.sin_addr.s_addr=INADDR_ANY;
   if(bind(listener,(struct sockaddr *)&my_addr,sizeof(struct sockaddr))==-1)
   {
     perror("bind");
     exit(1);
   }else{
     printf("IP address and port bind successed!\n");
   }

   kdpfd=epoll_create(MAXEPOLLSIZE);
   len=sizeof(struct sockaddr_in);
   ev.events=EPOLLIN|EPOLLET;
   ev.data.fd=listener;
   if(epoll_ctl(kdpfd,EPOLL_CTL_ADD,listener,&ev)<0)
   {
     fprintf(stderr,"epoll set insertion error: fd=%d\n",listener);
     return -1;
   }else{
      printf("listen socket add epoll successed!\n");
   }


   while(1)
   {
     nfds=epoll_wait(kdpfd,events,10000,-1);
     if(nfds==-1)
     {
      perror("epoll_wait");
      break;
     }
     for(n=0;n<nfds;++n)
     {
       if(events[n].data.fd==listener)
       {
          pthread_attr_init(&attr);
          pthread_attr_setscope(&attr,PTHREAD_SCOPE_SYSTEM);
          
          pthread_attr_setdetachstate(&attr,PTHREAD_CREATE_DETACHED);

          if(pthread_create(&thread,&attr,(void *)pthread_handle_message,(void *)&(events[n].data.fd)))
          {
            perror("pthread_create error!\n");
            exit(-1);
          }
       }
     }
   }
   close(listener);
   return 0;

}





