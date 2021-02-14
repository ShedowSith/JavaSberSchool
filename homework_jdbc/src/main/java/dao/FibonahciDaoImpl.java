package dao;

import connection.DataSoursHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FibonahciDaoImpl implements FibonahciDao {
    @Override
    public void addFibonachi(List<Integer> list) {
        try(Statement statement = DataSoursHelper.connection().createStatement()) {
            statement.executeUpdate("truncate table FIBONACHI");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try(PreparedStatement statement = DataSoursHelper.connection().prepareStatement("INSERT INTO FIBONACHI (n, number) values(?,?)")) {
            System.out.println("Пишем в БД");
            for (int i=1; i<= list.size(); i++){
                insert(statement, i, list.get(i-1));
                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<Integer> findFibonachi(int n) {
        List<Integer> list = new ArrayList<>();
        try(Statement statement = DataSoursHelper.connection().createStatement()) {
            statement.execute("select * from FIBONACHI where N="+n);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()){
                System.out.println("Получаем результат из БД");
                statement.execute("select * from FIBONACHI where N<="+n);
                resultSet  = statement.getResultSet();
                while (resultSet.next()){
                    list.add(resultSet.getInt("NUMBER"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    private void insert(PreparedStatement statement, int n, Integer number) throws SQLException {
        statement.setInt(1, n);
        statement.setInt(2, number);
    }
}
