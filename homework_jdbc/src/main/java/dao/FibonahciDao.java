package dao;

import java.util.List;

public interface FibonahciDao {
    void addFibonachi(List<Integer> list);
    List<Integer> findFibonachi(int n);

}
