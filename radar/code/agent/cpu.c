#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int sysCpu()
{
   const int MAX_LEN=256;
   char sInfo[MAX_LEN];
   char strInfo[1024];
   
   const int MAX_PROCESSOR=128;
   int cpuNum=0;//
   float fCpuRate;
   double cpu_idle=-1;
   double cpu_sys=-1;
   double cpu_user=-1;
   double cpu_total=-1;
   double cpu_wait=-1;
   char name[8];
   
   long int user,nice,sys,idle,iowait.irq,softirq;
   long int userNext,niceNext,sysNext,idleNext,iowaitNext,irqNext,softirqNext;

   FILE *fp;
   char buf[MAX_PROCESSOR+1][128];
   char bufNext[MAX_PROCESSOR+1][128];
   int i=0;
   memset(sInfo,0x00,sizeof(sInfo));
   memset(strInfo,0x00,sizeof(strInfo));


}
