#!/bin/sh
log_dir="/opt/logs/nginx"
yesterday=`date +%Y%m%d -d '-1 day'`

/bin/mv ${log_dir}/fund.access.log ${log_dir}/fund.access.log.${yesterday}
/bin/mv ${log_dir}/error.log ${log_dir}/error.log.${yesterday}
kill -USR1 `cat /usr/local/nginxnew/nginx/logs/nginx.pid`