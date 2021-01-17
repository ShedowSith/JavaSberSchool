package ru.sbt.proxy;

import ru.sbt.annotation.Cache;
import ru.sbt.annotation.CacheType;
import ru.sbt.object.MyMethod;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class CacheInvocationHandler implements InvocationHandler {
    private Map<Object, Object> cacheMap =new HashMap<>();
    private final Object delegate;
    private String folder;
    private int listList;
    private boolean zip;
    private String fileNamePrefix;
    private Class[] identityBy;
    private CacheType cacheType;

    public CacheInvocationHandler(Object delegate) {
        this.delegate = delegate;
    }
    public CacheInvocationHandler(Object delegate,
                                  String folder,
                                  String fileNamePrefix,
                                  int listList,
                                  boolean zip,
                                  Class[] identityBy,
                                  CacheType cacheType) {
        this.delegate = delegate;
        this.folder = folder;
        this.fileNamePrefix = fileNamePrefix;
        this.listList = listList;
        this.zip = zip;
        this.identityBy = identityBy;
        this.cacheType = cacheType;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String path = folder+"\\"+fileNamePrefix+method.getName();
        if(cacheType == CacheType.IN_FILE){
            if (zip)cacheMap = readCacheZip(path);
            else cacheMap = readCache(path);
        }
        if(!method.isAnnotationPresent(Cache.class)) return invoke(method,args);
        Object o = containsKey(cacheMap, key(method, args), identityBy);
        if(o != null ) return o;
        Object invoke = invoke(method,args);

        if(method.getReturnType().getTypeName().equals("java.util.List")){
            ArrayList res = (ArrayList) invoke;
            if(res.size()>listList){
                System.out.println("В кеше можно хранить List размером в "+listList+" элементов");
                res = new ArrayList<>(res.subList(0,listList));
            }
            invoke = res;
        }
        cacheMap.put(key(method,args), invoke);
        if(cacheType == CacheType.IN_FILE){
            if (zip)writeCacheZip(path);
            else writeCache(path);
        }
        return invoke;
    }
    private Object invoke(Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(delegate,args);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Impossible",e);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
    private Object containsKey(Map<Object, Object> container, Object key, Object[] param){
        if(param.length == 0) {
            if(container.containsKey(key)){
                return container.get(key);
            } else return null;
        }
        List<Object> keyList = getListFilter((List<Object>)key, param);
        for (Map.Entry<Object, Object> entry : container.entrySet()) {
            List<Object> tempKey = getListFilter(new ArrayList<>((List<Object>)entry.getKey()), param);
            if(tempKey.equals(keyList)){
                return entry.getValue();
            }
        }
        return null;
    }
    private List<Object> getListFilter(List<Object> list, Object[] param){
        List<Object> newList= new ArrayList<>();
        for (Object o : list) {
            String type = o.getClass().toString();
            for (Object o1 : param) {
                String o1Type = o1.toString();
                if(type.equals(o1Type)){
                    newList.add(o);
                }
            }
        }
        return newList;
    }
    private Object key(Method method , Object[] args){
        List<Object> key = new ArrayList<>();
        key.add(method);
        key.addAll(Arrays.asList(args));
        return key;
    }
    private void writeCacheZip(String file){
        List<MyMethod> list = new ArrayList<>();
        for (Map.Entry<Object, Object> entry : cacheMap.entrySet()) {
            List<Object> key =(List<Object>) entry.getKey();
            Method method =(Method) key.get(0);
            Object[] args = key.subList(1, key.size()).toArray();
            MyMethod methodObject = new MyMethod(method.getName(), method.getParameterTypes(), args,entry.getValue());
            list.add(methodObject);
        }
        try (FileOutputStream fos = new FileOutputStream(file+".zip");
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            ZipEntry ze;
            ObjectOutputStream oos;

            ze = new ZipEntry(file);
            zos.putNextEntry(ze);
            oos = new ObjectOutputStream(zos);
            oos.writeObject(list);
            zos.closeEntry();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeCache(String file){
        List<MyMethod> list = new ArrayList<>();
        for (Map.Entry<Object, Object> entry : cacheMap.entrySet()) {
            List<Object> key =(List<Object>) entry.getKey();
            Method method =(Method) key.get(0);
            Object[] args = key.subList(1, key.size()).toArray();
            MyMethod methodObject = new MyMethod(method.getName(), method.getParameterTypes(), args,entry.getValue());
            list.add(methodObject);
        }
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Map<Object, Object> readCacheZip(String file) throws NoSuchMethodException {
        List<MyMethod> list = new ArrayList<>();
        Map<Object, Object> res=new HashMap<>();
        ZipEntry entry;
        try (FileInputStream fis = new FileInputStream(file+".zip");
             ZipInputStream zis = new ZipInputStream(fis)) {
            zis.getNextEntry();
            ObjectInputStream ois = new ObjectInputStream(zis);
            list = (List<MyMethod>) ois.readObject();
        } catch (FileNotFoundException e) {
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (MyMethod myMethod : list) {
            Method method = delegate.getClass().getMethod(myMethod.getName(), myMethod.getParams());
            res.put(key(method, myMethod.getArgs()), myMethod.getResult());
        }
        return res;
    }
    private Map<Object, Object> readCache(String file) throws NoSuchMethodException {
        List<MyMethod> list = new ArrayList<>();
        Map<Object, Object> res=new HashMap<>();
        try(FileInputStream fis = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fis)
        ){
            list = (List<MyMethod>) in.readObject();
        } catch (FileNotFoundException e) {
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (MyMethod myMethod : list) {
            Method method = delegate.getClass().getMethod(myMethod.getName(), myMethod.getParams());
            res.put(key(method, myMethod.getArgs()), myMethod.getResult());
        }
        return res;
    }

}
