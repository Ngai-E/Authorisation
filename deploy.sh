#!/bin/bash

docker container run -d  \
--network spring-cloud \
--name auth-server \
-e MYSQL_URL=jdbc:mysql://mysqlserver:3306/security_db \
-e MYSQL_USER=watchadd \
-e CONFIG_SERVER_URL=http://cloud-config-server:56030 \ 
-e EUREKA_HOST_ZONE=http://cloud-discovery-server:56031/eureka/ \ 
-e MYSQL_PASS=watchadd@2022 ngaie/authentication-service:$2
