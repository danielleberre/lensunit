# Simple unit testing framework for educational purpose

[![Lens Unit own CI](https://github.com/danielleberre/lensunit/actions/workflows/main.yml/badge.svg)](https://github.com/danielleberre/lensunit/actions/workflows/main.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=danielleberre_lensunit&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=danielleberre_lensunit)

## Goal

The aim of this simple unit testing framework in Java is to illustrate the use of various
design patterns.

The design of the testing framework is inspired by the [original JUnit 3 design](http://junit.sourceforge.net/doc/cookstour/cookstour.htm).

That framework is not meant to be used in production. It is just an educational exercise.

The framework does not use any difficult Java constructs since it is aimed at
entry level Java programmers and OO designers.

Feel free to create issues if you have ideas how to add new functionalities using new design patterns.

## How to give it a try?

```
$ ./build.sh
$ ./runtests.sh org.lensunit.Basic
testTrivialOk OK
testEasyCases OK
testFailingTest FAILURE
java.lang.AssertionError
testFailingEquals FAILURE
java.lang.AssertionError: Expected 2 but got 4
Total 4 test(s) run, status is FAILURE
```

## Design patterns used so far

+ [strategy](https://en.wikipedia.org/wiki/Strategy_pattern)
+ [decorator](https://en.wikipedia.org/wiki/Decorator_pattern)
+ [template](https://en.wikipedia.org/wiki/Template_method_pattern)
+ [pluggable selector](http://junit.sourceforge.net/doc/cookstour/cookstour.htm)
+ [composite](https://en.wikipedia.org/wiki/Composite_pattern)
