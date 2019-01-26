#!/bin/bash
set -e

find src -name "*.java" -print >javafiles
if [ ! -d bin ]; then
    mkdir bin
fi
javac -d bin -cp bin:lib/dpprocessor.jar @javafiles
jar -cfm lensunit.jar manifest.mf -C bin/ .
