#!/bin/bash

if [ ! -d "$1" ]; then
    echo "[!] Directory does not exist"
    echo "Usage: $0 DESTINATION_DIRECTORY"
    echo
    exit 1
fi

mkdir $1/lib

wget http://mirrors.koehn.com/apache//httpcomponents/httpclient/binary/httpcomponents-client-4.5.3-bin.tar.gz -O $1/httpcomponents-client-4.5.3-bin.tar.gz
wget http://search.maven.org/remotecontent?filepath=com/google/code/gson/gson/2.3.1/gson-2.3.1.jar -O $1/lib/gson-2.3.1.jar

cd $1
tar -xzvf httpcomponents-client-4.5.3-bin.tar.gz
mv httpcomponents-client-4.5.3/lib/*jar $1/lib

rm $1/httpcomponents-client-4.5.3-bin.tar.gz
rm -R $1/httpcomponents-client-4.5.3

echo "[+] Complete. Add $1/lib to your Java CLASSPATH or Burp Extender options"
exit 0

