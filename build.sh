#!/bin/bash
set -e

find src -name "*.java" -print >javafiles
if [ ! -d bin ]; then
    mkdir bin
fi
javac -d bin -source 1.8 -target 1.8 -cp bin:lib/dpprocessor.jar @javafiles
jar -cfe lensunit.jar org.lensunit.Runner  -C bin/ .
