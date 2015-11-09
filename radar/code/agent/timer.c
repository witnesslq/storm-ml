#include <stdio.h>
#include <time.h>
#include <sys/time.h>
#include <stdlib.h>
#include <signal.h>
#include "timer.h"


//static int count=0;
//static struct itimerval oldtv;

void set_timer()
{
   struct itimerval itv;
   itv.it_interval.tv_sec=10;
   itv.it_interval.tv_usec=0;
   itv.it_value.tv_sec=10;
   itv.it_value.tv_usec=0;
   setitimer(ITIMER_REAL,&itv,&oldtv);
}

/*void signal_headler(int m)
{
   count++;
   printf("%d\n",count);
}*/

/*
int main(){
  signal(SIGALRM,signal_headler);
  set_timer();
  while(count<10000);
  exit(1);
  return 1;

}*/
