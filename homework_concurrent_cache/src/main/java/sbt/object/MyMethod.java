package sbt.object;

import java.io.Serializable;

public class MyMethod implements Serializable {
    private String name;
    private Class<?>[] params;
    private Object[] args;
    private Object result;

    public MyMethod(String name, Class<?>[] params, Object[] args, Object result) {
        this.name = name;
        this.params = params;
        this.args = args;
        this.result = result;
    }
    public String getName() {
        return name;
    }

    public Class<?>[] getParams() {
        return params;
    }

    public Object[] getArgs() {
        return args;
    }

    public Object getResult() {
        return result;
    }
}
