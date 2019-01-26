#!/bin/bash
set -e

find tst -name "*.java" -print >javafiles
if [ ! -d bin ]; then
    mkdir bin
fi
javac -d bin -cp bin @javafiles

java -cp lensunit.jar:bin org.lensunit.Runner $*
