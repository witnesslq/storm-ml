#include <sys/types.h>
#include <sys/socket.h>
#include <string.h>
#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <sys/time.h>
#include <time.h>
#include <signal.h>
#include "timer.h"


#define MAXLEN 1024
#define SERV_PORT 8888



//static int count=0;
//static struct itimerval oldtv;

void do_cli(int sockfd,struct sockaddr *pservaddr,socklen_t servlen)
{
   int n;
   char sendline[MAXLEN],recvline[MAXLEN+1];
   if(connect(sockfd,(struct sockaddr *)pservaddr,servlen)==-1)
   {
     perror("connect error");
     exit(1);
   }
  // while(fgets(sendline,MAXLEN,fp)!=NULL)

    char heart_msg[] = "hello";

    strcpy(sendline,heart_msg);
  // {
      printf("%s\n",sendline);
     /*read a line and send to server */
     write(sockfd,sendline,strlen(sendline));
     /*receive data from server*/
     n=read(sockfd,recvline,MAXLEN);
     if(n==-1)
     {
       perror("read error");
       exit(1);
     }
     recvline[n]='\0';
     printf("receive:%s\n",recvline);
     fflush(stdout);

 //  }

}

void signal_headler(int m)
{
   heart_beat("127.0.0.1");
}

void heart_beat(char * ip_addr)
{
  int sockfd;
  struct sockaddr_in servaddr;
  
  /*init servaddr*/
  bzero(&servaddr,sizeof(servaddr));
  servaddr.sin_family=AF_INET;
  servaddr.sin_port=htons(SERV_PORT);
  if(inet_pton(AF_INET,ip_addr,&servaddr.sin_addr)<=0)
  {
    printf("[%s] is not a valid IPaddress\n",ip_addr);
    exit(1);
  }
  sockfd=socket(AF_INET,SOCK_DGRAM,0);
  do_cli(sockfd,(struct sockaddr *)&servaddr,sizeof(servaddr));
}

int main(int argc,char **argv)
{
  int sockfd;
  struct sockaddr_in servaddr;
  if(argc!=2)
  {
     printf("usage: udpcl ient <IPaddress>\n");
     exit(1);
  }
  
  signal(SIGALRM,signal_headler);
  set_timer();
 //while(1);
  //while(1);
  //exit(1);
   //heart_beat("127.0.0.1");
   int ex=0;
//   scanf("%d",&ex);
   while(1){
    scanf("%d",&ex);
    if(ex==9){
      exit(1);
     }
   }

  /*init servaddr*/ 
  /*bzero(&servaddr,sizeof(servaddr));
  servaddr.sin_family=AF_INET;
  servaddr.sin_port=htons(SERV_PORT);
  if(inet_pton(AF_INET,argv[1],&servaddr.sin_addr)<=0)
  {
    printf("[%s] is not a valid IPaddress\n",argv[1]);
    exit(1);
  }
  sockfd=socket(AF_INET,SOCK_DGRAM,0);
  do_cli(sockfd,(struct sockaddr *)&servaddr,sizeof(servaddr));*/
  return 0;
}
