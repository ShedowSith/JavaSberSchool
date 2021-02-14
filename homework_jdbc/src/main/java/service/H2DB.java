package service;

import dao.FibonahciDaoImpl;

import java.util.List;

public class H2DB implements Source {
    FibonahciDaoImpl fibonahciDao;
    @Override
    public List<Integer> getListFibonachi(int i) {
        if(fibonahciDao == null) fibonahciDao = new FibonahciDaoImpl();
        List<Integer> list = fibonahciDao.findFibonachi(i);
        if(list == null || list.size()<1) return null;
        return list;
    }

    @Override
    public void saveListFibonachi(List<Integer> list) {
        if(fibonahciDao == null) fibonahciDao = new FibonahciDaoImpl();
        fibonahciDao.addFibonachi(list);
    }
}
