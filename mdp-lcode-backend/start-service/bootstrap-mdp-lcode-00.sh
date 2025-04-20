#!/bin/bash
######################################################################################
###          sh bootstrap-mdp-lcode-${GROUP}${NODE}.sh
###          cyc 20190221
###
###
#####################################################################################
cd ..

APP_HOME=$(pwd)
JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64

# 服务名称，一般指jar包除了版本号之外的名称
SERVICE_NAME=mdp-lcode

# 微服务名称，一般与服务名称一致，有些微服务名称会比服务名称短，或者重新编码。一般取spring配置文件中的applicationName
MICRO_SERVICE=lcode

# CLOUD属性 如果是cloud版本，填写-cloud,否则填写空值,单体应用一般不填写
CLOUD=

# jar主版本号，暂时为空，如有需要按 {版本号}. 格式填写，版本号后面带一个 .
MVERSION=

# jar子版本号
SVERSION="3.0.0-RELEASE"

# 群组编号，默认0，用于区分多个微服务组合，比如0={arc+xm+lcode}，从0开始，按0，1，2，3等进行编号，用于区分同一机器，同一个微服务多个实例时的进程编号 bootstrop-xxxx-${GROUP}${NODE}.sh
GROUP=0

# 节点编号 默认0，按0，1，2，3等进行编号,用于区分同一机器，同一个微服务多个实例时的进程编号 bootstrop-xxxx-${GROUP}${NODE}.sh
NODE=0

# 引用的配置文件
PROFILES=${MICRO_SERVICE}.prod.${GROUP}.${NODE}



# 配置文件外置的情况下，请将配置文件放到 ./config/目录下,此目录不能修改
CONFIG_PATH=${APP_HOME}/config


# 记录当前进程编号
PID_FILE_NAME=${APP_HOME}/start-service/pid/${SERVICE_NAME}-${GROUP}-${NODE}.pid


echo "应用主目录为 ${APP_HOME}"

echo "将启动${SERVICE_NAME} 主版本${MVERSION} 子版本号${SVERSION} 配置文件${PROFILES}"


if [ ! -d "./start-service/pid" ];then
  mkdir ./start-service/pid
fi

# 清理进程
if [ -f "${PID_FILE_NAME}" ];then
	PID=$(cat "${PID_FILE_NAME}")
	kill -9 ${PID}
	echo "成功停止服务${SERVICE_NAME}"
fi



# 执行启动程序
echo "启动服务${SERVICE_NAME}"

nohup ${JAVA_HOME}/bin/java -server -Xms512m -Xmx512m -Dfile.encoding=UTF-8 -jar ./lib/${SERVICE_NAME}${CLOUD}-bootstrap-${MVERSION}${SVERSION}.jar --spring.profiles.active=${PROFILES} >/dev/null 2>&1 &

# 记录进程号
echo $! > ${PID_FILE_NAME}

echo "启动服务成功!!!!!!!! pid $!"
