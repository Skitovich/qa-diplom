package ru.netology.sql;

import lombok.Value;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.*;

public class SqlMethods {
    public SqlMethods() {
    }

    @Value
    public static class StatusInfo {
        String status;
    }

    public static StatusInfo getStatusForCredit() {
        val getCode = "SELECT status FROM credit_request_entity ORDER BY created desc LIMIT 1;";
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

    public static StatusInfo getStatusForCard() {
        val getCode = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
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


    public static int getResultSetRowCountForCredit() {

        val getRows = "select * from credit_request_entity";
        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass")
        ) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(getRows);
            rs.last();
            return rs.getRow();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }

    public static int getResultSetRowCountForCard() {

        val getRows = "select * from payment_entity";
        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass")
        ) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(getRows);
            rs.last();
            return rs.getRow();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }

}
