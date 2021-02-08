package sbt.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceImpl implements Service {
    @Override
    public List<String> run(String item, double value, Date date) {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i<item.length(); i++){
            result.add(item+ " "+i+" "+value +"   "+ date.toString());
        }

        return result;
    }

    @Override
    public List<String> work(String item) {
//        try {
//            Thread.sleep(6000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i<item.length(); i++){
            result.add(item+ " "+i+" "+ new Date().toString());
        }

        return result;
    }
}
