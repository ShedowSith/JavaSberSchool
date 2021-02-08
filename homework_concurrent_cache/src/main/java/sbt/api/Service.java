package sbt.api;

import sbt.annotation.Cache;
import sbt.annotation.CacheType;

import java.util.Date;
import java.util.List;

public interface Service {
    @Cache(cacheType = CacheType.IN_FILE, fileNamePrefix = "data", zip = true, identityBy = {String.class, double.class})
    List<String> run(String item, double value, Date date);

    @Cache(cacheType = CacheType.IN_MEMORY, listList = 100_000)
    List<String> work(String item);

}
