package jdbcTest;



import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest {
        @Test
        public void test1() throws Exception{
             Class.forName("com.mysql.jdbc.Driver");
             String url = "jdbc:mysql://localhost:3306/exam";
            String user = "root";
            String password = "admin";
            Connection conn = DriverManager.getConnection(url, user, password);

            Statement stmt = conn.createStatement();

            String sql = "select * from emp";

            boolean execute = stmt.execute(sql);

            ResultSet rs = stmt.getResultSet();

            while(rs.next()){
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                System.out.println("---------------");
            }

            stmt.close();
            stmt=null;
            conn.close();
        }

}
