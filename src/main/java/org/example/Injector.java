package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 *  Класс для внедрения зависимостей
 * @param <T> тип объекта в который внедряют зависимости
 */
public class Injector<T> {

    private final Properties p;

    /**
     * Конструктор класса
     * @param pathP путь к файлу свойств
     * @throws IOException в случае ошибки ввода-вывода
     */
    Injector(String pathP) throws IOException {
        p = new Properties();
        p.load(new FileInputStream(new File(pathP)));
    }

    /**
     * Метод внедрения зависимостей в объект
     * @param obj объект в который внедряются зависимости
     * @return объект с внедрёнными зависимостями
     * @throws IllegalAccessException в случает недопустимого доступа
     * @throws InstantiationException в случае ошибки создания экземпляра
     */
    T inject(T obj) throws IllegalAccessException, InstantiationException {

        Class dependency;
        Class cl = obj.getClass();

        Field[] fields = cl.getDeclaredFields();
        for (Field field: fields){

            Annotation a = field.getAnnotation(AutoInjectable.class);
            if (a != null){

                String[] fieldType = field.getType().toString().split(" ");
                String equalsClassName = p.getProperty(fieldType[1], null);

                if (equalsClassName != null){

                    try {
                        dependency = Class.forName(equalsClassName);

                    } catch (ClassNotFoundException e){
                        System.out.println("Не найден класс для " + equalsClassName);
                        continue;
                    }

                    field.setAccessible(true);
                    field.set(obj, dependency.newInstance());
                }
                else
                    System.out.println("Не найдены свойства для типа поля " + fieldType[1]);
            }
        }


        return obj;
    }
}
