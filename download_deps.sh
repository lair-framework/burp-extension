#!/bin/bash

if [ $# -eq 0 ]; then
    echo "Usage: $0 DESTINATION_DIRECTORY"
    echo
    exit 1
fi

if [ ! -d "$1" ]; then
    echo "[!] Directory did not exist, created."
    mkdir -p $1/lib
    if [ ! -d "$1/lib" ]; then
        echo "[+] Could not create directory ..."
        exit 1
    fi
fi

echo "[+] Downloading files to $1 ..."
pushd . &>/dev/null
cd $1
wget http://mirrors.koehn.com/apache//httpcomponents/httpclient/binary/httpcomponents-client-4.5.3-bin.tar.gz -O - | \
    tar -zxf - --strip-components=1 -K lib
wget -q http://search.maven.org/remotecontent?filepath=com/google/code/gson/gson/2.3.1/gson-2.3.1.jar -O lib/gson-2.3.1.jar
popd &>/dev/null
echo "[+] Complete. Add `(cd $1 && pwd)`/lib to your Java CLASSPATH or Burp Extender options"
exit 0
