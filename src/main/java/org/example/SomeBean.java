package org.example;
import reflection.SomeOtherInterface;
import reflection.SomeInterface;

/**
 * Класс, в который внедряются зависимости
 */
public class SomeBean {
    @AutoInjectable
    private SomeInterface field1;

    @AutoInjectable
    private SomeOtherInterface field2;

    /**
     * метод демонстрации
     */
    public void foo() {
        field1.doSomething();
        field2.doSomething();
    }

}
