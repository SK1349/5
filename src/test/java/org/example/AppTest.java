package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit test для проверки Injector.
 */
public class AppTest{
    @Test
    void testSuccessfulInjection() throws Exception {

        File propertiesFile = File.createTempFile("test", ".properties");
        propertiesFile.deleteOnExit();


        Properties properties = new Properties();
        properties.setProperty("reflection.SomeInterface", "reflection.SomeImpl");
        properties.setProperty("reflection.SomeOtherInterface", "reflection.SODoer");
        try (FileOutputStream out = new FileOutputStream(propertiesFile)) {
            properties.store(out, null);
        }


        SomeBean testObject = new SomeBean();
        Injector<SomeBean> injector = new Injector<>(propertiesFile.getAbsolutePath());


        injector.inject(testObject);

        assertNotNull(getFieldValue(testObject, "field1"), "Dependency was not injected for field1");
        assertNotNull(getFieldValue(testObject, "field2"), "Dependency was not injected for field2");
    }

    // Вспомогательный метод для доступа к приватным полям
    private Object getFieldValue(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }

}
