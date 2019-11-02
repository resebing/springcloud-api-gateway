# jdk的来源openjdk及版本号（openjdk默认是官方仓库的)。设置继承自拿个基础镜像
FROM hub.c.163.com/library/java:8-alpine

# 构建的镜像所有者名称及练习方式
MAINTAINER lishoubi xxx@163.com

# 构建的镜像要拷贝到哪个目录下运行。如果用tomcat运行到话
#COPY eureka-0.0.1-SNAPSHOT.jar /usr/local/tomcat/webapps

# 指定的jar包路径和名称
ADD target/*.jar app.jar

# 设置端口号
EXPOSE 11000

# 启动命令
ENTRYPOINT ["java","-jar","-Xms128m","-Xmx256m","/app.jar"]