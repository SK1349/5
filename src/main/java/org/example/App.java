package org.example;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, IllegalAccessException, InstantiationException {
        SomeBean sb = (new Injector<SomeBean>("src/main/resources/s.properties").inject(new SomeBean()));
        sb.foo();
        System.out.println( "Hello World!" );
    }
}
