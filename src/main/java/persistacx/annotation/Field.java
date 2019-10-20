package persistacx.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Field {
    String name();
    boolean isPrimaryKey() default false;
}
