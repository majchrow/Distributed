#!/bin/bash

for files in $(find . -name '*.thrift'); do
  thrift --gen "$1" /data/"$files"
done
