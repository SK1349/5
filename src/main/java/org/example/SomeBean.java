package org.example;

public class SomeBean {
    @AutoInjectable
    private SomeInterface field1;

    @AutoInjectable
    private SomeInterface field2;

    public void foo() {
        field1.doSomething();
        field2.doSomething();
    }

}
