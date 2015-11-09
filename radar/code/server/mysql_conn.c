#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>
#include "cJSON.h"

MYSQL *mysql_conn;
MYSQL_RES *mysql_res;
MYSQL_ROW mysql_row;

#define MAX_BUF_SIZE 1024

const char *mysql_host_name="10.0.224.68";
const char *mysql_user_name="root";
const char *mysql_password="root";
const char *mysql_db_name="linux-test";
const unsigned int mysql_db_port=3306;


void print_mysql_error(const char *msg)
{
   if(msg)
     printf("%s:%s\n",msg,mysql_error(mysql_conn));
   else
     puts(mysql_error(mysql_conn));
}

int executesql(const char * sql)
{
    if(mysql_real_query(mysql_conn,sql,strlen(sql)))
     {
       return -1;
     }else{
       return 0;
     }
}

int init_mysql()
{
   mysql_conn=mysql_init(NULL);
   if(!mysql_real_connect(mysql_conn,mysql_host_name,mysql_user_name,mysql_password,mysql_db_name,mysql_db_port,NULL,0))
   {
     return 0;
   }
   printf("conn.....\n");
   if(executesql("set names utf8"))
   {
     return 1;
   }

   return 0;

}


int main(int argc, char ** argv)
{
   puts("!!!Hello World!!!");
   
   if(init_mysql()){

     }else{
        print_mysql_error(NULL);
     }
    char sql[MAX_BUF_SIZE];

    sprintf(sql,"INSERT INTO t_test(myname) values('testname')");
    if(executesql(sql)){
       print_mysql_error(NULL);
     }  
    if(executesql("select * from t_test")){
       print_mysql_error(NULL);
     }
    mysql_res=mysql_store_result(mysql_conn);
    int iNum_rows=mysql_num_rows(mysql_res);
    int iNum_fields=mysql_num_fields(mysql_res);

    printf("rows:%d,fields:%d\n",iNum_rows,iNum_fields);
    puts("id\tname\n");
    
    while((mysql_row=mysql_fetch_row(mysql_res)))
    {
       printf("%s\t%s\n",mysql_row[0],mysql_row[1]);
    }
    mysql_free_result(mysql_res);
    mysql_close(mysql_conn);

   return EXIT_SUCCESS;

}
