package ua.avk.dao;

import android.os.AsyncTask;
import android.util.Log;
import ua.avk.entitys.Account;

import java.sql.*;
import java.util.List;

/**
 * Created by Галя on 21.04.2017.
 */

/**
 * @author Alexander Kononenko
 * @version 1.0.0
 * @date 19.04.2017.
 */
abstract class MySQLConnect extends AsyncTask<String, Void, Void> {
    protected final String TAG = this.getClass().getSimpleName();
    private static final String URL = "jdbc:mysql://host:port/db";
    private static final String USER = "user";
    private static final String PSWD = "password";

    // JDBC variables for opening and managing connection
    protected static Connection con;
    protected static Statement stmt;

    /**
     * Constructor
     */
    public MySQLConnect() {
    }

    @Override
    public Void doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(URL, USER, PSWD);
            stmt = con.createStatement();
        } catch (Exception e) {
            Log.e(TAG, "MySQLConnection doInBackground: ", e);
        }
        return null;
    }


    /**
     * Open connection
     */
    public void openConnection() {

        doInBackground();

    }

    /**
     * Close connection and stmt
     */
    public void closeAllConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            Log.e(TAG, "Получено исключение", ex);
        }
        try {
            stmt.close();
        } catch (SQLException ex) {
            Log.e(TAG, "Получено исключение", ex);
        }
    }

    /**
     * Close connection and stmt
     */
    public void closeAllConnection(ResultSet rs) {
        try {
            con.close();
        } catch (SQLException ex) {
            Log.e(TAG, "Получено исключение", ex);
        }
        try {
            stmt.close();
        } catch (SQLException ex) {
            Log.e(TAG, "Получено исключение", ex);
        }
        try {
            rs.close();
        } catch (SQLException ex) {
            //System.out.print("SQL-CLOSE-RESULTSET-Exception: " + ex.getMessage());
            Log.e(TAG, "Получено исключение", ex);
        }
    }

    /**
     * Close resultset here
     */
    public void closeResultSetOfConnection(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            Log.e(TAG, "Получено исключение", ex);
        }
    }

    /**
     * Created sql query only for create table
     *
     * @param query string
     * @return boolean
     */
    public Boolean createTable(String query) {
        Boolean res = null;
        try {
            res = stmt.execute(query);

        } catch (SQLException ex) {
            Log.e(TAG, "Получено исключение", ex);

        } finally {
            return res;
        }
    }

    /**
     * Created statement sql query only for select
     *
     * @param query string
     * @return resultset object
     */
    public ResultSet createSelect(String query) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            Log.e(TAG, "Получено исключение", ex);
        }

        return rs;
    }

    /**
     * Created statement sql query only for insert, delete, update
     *
     * @param query string
     * @return Integer
     */
    public Integer createOtherQuery(String query) {
        int res = 0;
        try {
            res = stmt.executeUpdate(query);

        } catch (SQLException ex) {
            Log.e(TAG, "Получено исключение", ex);
        }

        return res;
    }

    /**
     * Convert ResultSet To List
     *
     * @param resultSet object
     * @return list object
     */
    abstract public List<Account> convertResultSetToList(ResultSet resultSet);

}

