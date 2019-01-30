package org.lensunit;

/**
 * Generic representation of a method to be called to test that it throws an
 * exception.
 * 
 * There might be a better way to do it. Simply works for now.
 * 
 * @author leberre
 *
 */
@FunctionalInterface
public interface MethodHandle {

    void doSomething() throws Throwable;
}
