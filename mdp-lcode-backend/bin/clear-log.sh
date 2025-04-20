#!/bin/bash
######################################################################################
###          sh clear-log.sh
###          cyc 20190221
###    使用：crontab -e 打开定时任务编辑器 crontab -l 查看定时任务
###    0 1 * * * /bin/bash /home/mall/m1/bin/clear-log.sh
###
###
#####################################################################################
# 备份日期
yesterday=$(date -d yesterday +%Y-%m-%d) 
#yesterday=$(date +%Y-%m-%d)
current_datetime=$(date +"%Y%m%d%H%M%S")

# 备份路径
backup_dir="/home/mall/m1/backup_log"
# 日志路径
log_dir="/home/mall/m1/logs"
log_file="*${yesterday}*.log"
 
mkdir -p $backup_dir
cd $log_dir
# 备份日志文件到日志中并压缩
tar -czvf $backup_dir/${yesterday}_${current_datetime}_log_backup.log.tar.gz $log_file
 
# 日志文件清空
rm -f $log_file
 
# 清空7天前的日志文件
find $backup_dir -type f -mtime +7 -delete;
