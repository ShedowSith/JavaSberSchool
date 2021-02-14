package service;

import java.util.List;

public interface Source {
    List<Integer> getListFibonachi(int i);
    void saveListFibonachi(List<Integer> list);
}
