#!/bin/bash

BUILD_PATH=$(pwd)/out
STARTING_CLASS_PACKAGE=principal
STARTING_CLASS_NAME=Principal

if [ -d "$BUILD_PATH" ];
then
    rm -r "$BUILD_PATH"
fi

cd src
javac -d ../out "$STARTING_CLASS_PACKAGE/$STARTING_CLASS_NAME".java

if [ -d "$BUILD_PATH" ];
then
    java -classpath ../out "$STARTING_CLASS_PACKAGE.$STARTING_CLASS_NAME"
fi