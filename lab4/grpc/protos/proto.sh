if [ "$#" -ne 2 ]; then
    echo "Expected 2 args: language grpc_file"
    exit 1
fi

docker run -v `pwd`:/defs namely/protoc-all -f $1 -l $2
