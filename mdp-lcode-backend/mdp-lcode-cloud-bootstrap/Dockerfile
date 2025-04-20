## AdoptOpenJDK 停止发布 OpenJDK 二进制，而 Eclipse Temurin 是它的延伸，提供更好的稳定性
FROM eclipse-temurin:8-jre

## 创建目录，并使用它作为工作目录
RUN mkdir -p /app

## 将后端项目的 Jar 文件，复制到镜像中
COPY /target/mdp-lcode-bootstrap-3.0.0-RELEASE.jar /app/app.jar

WORKDIR /app

## 设置 TZ 时区
ENV TZ=Asia/Shanghai

## 设置 JAVA_OPTS 环境变量，可通过 docker run -e "JAVA_OPTS=" 进行覆盖
ENV JAVA_OPTS="-Xms512m -Xmx512m -Dfile.encoding=UTF-8"

## 应用参数
ENV ARGS=""

## 暴露后端项目的 7014 端口
EXPOSE 7014

## 启动后端项目
CMD java -server ${JAVA_OPTS} -jar app.jar $ARGS
