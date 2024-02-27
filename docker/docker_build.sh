#!/bin/sh

# Setting Versions
VERSION='0.0.1'

cd ..
./gradlew clean build -x test
ROOT_PATH=`pwd`
echo $ROOT_PATH

echo 'api docker image Build...'
cd $ROOT_PATH/api && docker build -t api:$VERSION .
echo 'api docker image Build... Done'

echo 'consumer docker image build...'
cd $ROOT_PATH/consumer && docker build -t consumer:$VERSION .
echo 'consumer docker image build... Done'

echo 'css docker image build...'
cd $ROOT_PATH/css && docker build -t css:$VERSION .
echo 'css docker image Build... Done'

echo 'nginx docker image Build...'
cd $ROOT_PATH/nginx  && docker build -t nginx:$VERSION .
echo 'nginx docker image Build... Done'
