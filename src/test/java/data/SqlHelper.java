package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
    public static long getRowAmountFromCreditRequestEntityTable(){
        var statusSQl = "SELECT count(*) FROM credit_request_entity;";
        return runner.query(conn, statusSQl, new ScalarHandler<>());
    }


    @SneakyThrows
    public static long getRowAmountFromOrderEntityTable(){
        var statusSQl = "SELECT count(*) FROM order_entity;";
        return runner.query(conn,statusSQl,new ScalarHandler<>());
    }
    @SneakyThrows
    public static long getRowsAmountFromPaymentEntityTable() {
        var statusSQL = "SELECT count(*) FROM payment_entity;";
        return runner.query(conn, statusSQL, new ScalarHandler<>());
    }
}

