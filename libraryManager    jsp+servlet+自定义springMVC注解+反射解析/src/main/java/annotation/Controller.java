package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  //在运行时起作用
@Target({ElementType.TYPE,ElementType.FIELD})  //在类上和属性上使用
public @interface Controller {
    String value() default "";
}
