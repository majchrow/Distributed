#!/bin/bash

if [ "$#" -ne 1 ]; then
  echo "Expected 1 arg: language"
  exit 1
fi

docker run --rm -w /data -v $(pwd):/data thrift bash generate.sh "$1"
