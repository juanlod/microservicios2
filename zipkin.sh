#!/bin/sh
#This program returns the zipkin variable configurations
#contents of my Home folder
echo off
export RABBIT_ADDRESSES=localhost:5672
export STORAGE_TYPE=mysql
export MYSQL_USER=zipkin
export MYSQL_PASS=zipkin
java -jar ./zipkin-server-2.21.1-exec.jar --server.port=9512