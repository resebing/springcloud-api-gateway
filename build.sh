#! /usr/bin/env bash
# 先打包文件.并重新加载jar包 -U表示重新加载jar包
mvn clean package -Dmaven.test.skip=true -U

# docker打包
docker build -t registry.cn-hangzhou.aliyuncs.com/shoubi/springcloud:zuul .

# docker push到阿里云镜像仓库
docker push registry.cn-hangzhou.aliyuncs.com/shoubi/springcloud:zuul