package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import java.sql.Connection;
import java.sql.DriverManager;


public class SqlHelper {
    private static QueryRunner runner = new QueryRunner();
    private static String Url = System.getProperty("db.url");
    private static String User = System.getProperty("db.user");
    private static String Password = System.getProperty("db.password");
    private static Connection conn = getConn();

    @SneakyThrows
    private static Connection getConn() {

        return DriverManager.getConnection(
                Url,
                User,
                Password
        );
    }
    @SneakyThrows
    public static void clearData()  {
        runner.execute(conn, "DELETE FROM credit_request_entity;");
        runner.execute(conn, "DELETE FROM order_entity;");
        runner.execute(conn, "DELETE FROM payment_entity;");
    }


    @SneakyThrows
    public static String getCreditRequestEntityData(){
        var statusQl = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        return runner.query(conn, statusQl, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getPaymentsEntityData() {
        var statusQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        return runner.query(conn, statusQL, new ScalarHandler<>());
    }
}