package org.example;

import java.lang.annotation.*;

/**
 * Аннотация для пометки полей
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoInjectable {
}
