package sbt.proxy;

import org.jetbrains.annotations.Nullable;
import sbt.annotation.CacheType;
import sbt.api.Service;

import java.lang.reflect.Proxy;
import java.util.Optional;

public class CacheProxy {
    private String folder;
    private int listList;
    private boolean zip;
    private String fileNamePrefix;
    private Class[] identityBy;
    private CacheType cacheType;

    public CacheProxy(String folder,
                      @Nullable String fileNamePrefix,
                      @Nullable int listList,
                      @Nullable boolean zip,
                      @Nullable Class[] identityBy,
                      @Nullable CacheType cacheType) {
        this.folder = folder;
        this.listList = Optional.ofNullable(listList).orElse(100_000);
        this.zip = Optional.ofNullable(zip).orElse(false);
        this.fileNamePrefix = Optional.ofNullable(fileNamePrefix).orElse("");
        this.identityBy = Optional.ofNullable(identityBy).orElse(new Class[]{});
        this.cacheType = Optional.ofNullable(cacheType).orElse(CacheType.IN_FILE);
    }


    public Service cache(Service service){
        CacheInvocationHandler handler = new CacheInvocationHandler(service,folder, fileNamePrefix, listList, zip,  identityBy, cacheType);

        return (Service) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{Service.class},
                handler);
    }

}
/*

int listList() default 100_000;

    boolean zip() default false;

    String fileNamePrefix() default "";

    Class[] identityBy() default {};

    CacheType cacheType() default CacheType.IN_FILE;
 */