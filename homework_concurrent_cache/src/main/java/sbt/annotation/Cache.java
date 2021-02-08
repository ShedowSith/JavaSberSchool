package sbt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {
    int listList() default 100_000;

    boolean zip() default false;

    String fileNamePrefix() default "";

    Class[] identityBy() default {};

    CacheType cacheType() default CacheType.IN_FILE;
}
