#!bin/sh

#ip=123.57.37.50
ip=120.27.227.32
local=D:/Biye/fd-binwang/fd-binwang
#local=/Users/owen/Documents/binwang158/fd-binwang
remote=/root/fd-binwang
remote_sh=/root/sh/binwang-management-sh
appName=fd-binwang-0.0.1-SNAPSHOT.jar

#cd ~
#source .bash_profile
#cd ${local}
#netstat -nltp |grep
#打包
#mvn clean package -Dmaven.test.skip=true

ssh root@${ip} <<EOF
sh ${remote_sh}/kill.sh
rm -rf ${remote}
mkdir -p ${remote}
EOF
echo "done for delete original folder"

scp -r ${local}/target/${appName} root@${ip}:${remote}/
scp ${local}/src/main/resources/application.properties root@${ip}:${remote}/
scp ${local}/src/main/resources/application-online.properties root@${ip}:${remote}/
echo "done for send"


ssh root@${ip} <<EOF
sh ${remote_sh}/start.sh
EOF
echo "done for restart"
