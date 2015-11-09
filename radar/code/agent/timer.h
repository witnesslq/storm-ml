#ifndef _TIMER_H__
#define _TIMER_H__

#include <stdio.h>
#include <sys/time.h>
#include <time.h>


static int count=0;
static struct itimerval oldtv;

void set_timer();
void signal_headler(int m);




#endif
