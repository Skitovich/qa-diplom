package ru.netology.sql;

import lombok.Value;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlMethods {
    public SqlMethods() {
    }
    @Value
    public static class StatusInfo {
        String status;
    }
    public static StatusInfo getStatus() {
        val getCode = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass")
        ) {
            val status = runner.query(conn, getCode, new ScalarHandler<>());
            return new StatusInfo(status.toString());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }


}
