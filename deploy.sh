#!/bin/bash

docker container run -d  \
--network spring-cloud \
--name auth-server \
 ngaie/authentication-service:"$2"
