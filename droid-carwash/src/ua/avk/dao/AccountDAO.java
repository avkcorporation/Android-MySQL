package ua.avk.dao;

import android.util.Log;
import ua.avk.entitys.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Kononenko
 * @version 1.0.0
 * @date 01.05.2017.
 */
class AccountDAO extends MySQLConnect implements IAccountDAO {

    public AccountDAO() {
        super();
    }

    public void saveOrUpdate(Account account) {

    }

    public void save(Account account) {
        openConnection();
        int rs = createOtherQuery("INSERT INTO Account(id, login, pswd, role)" +
                " VALUE(NULL, '" + account.getLogIn() + "','" + account.getPassword() + "','" + account.getRole() + "');");
        closeAllConnection();
    }

    public void delete(Account account) {

    }

    public List<Account> getAll() {
        List<Account> list = new ArrayList<Account>();
        openConnection();
        ResultSet rs = createSelect(Account.GET_ALL_ACCOUNTS);
        list = convertResultSetToList(rs);
        closeAllConnection(rs);
        return list;

    }

    /**
     * User authentication by log in and password
     *
     * @param logIn    string
     * @param password string
     * @return account object
     */
    public Account getByLogInAndPassword(String logIn, String password) {
        List<Account> list = null;
        openConnection();
        ResultSet rs = createSelect("SELECT * FROM account WHERE login='" + logIn + "' AND pswd='" + password + "';");
        list = convertResultSetToList(rs);
        closeAllConnection(rs);
        return list.get(0);
    }

    /**
     * Convert ResultSet To List
     *
     * @param resultSet object
     * @return list object
     */
    public List<Account> convertResultSetToList(ResultSet resultSet) {
        List<Account> list = new ArrayList<Account>();
        try {
            while (resultSet.next()) {
                Account account = new Account();
                account.setId((long) resultSet.getInt(1));
                account.setLogIn(resultSet.getString(2));
                account.setPassword(resultSet.getString(3));
                account.setRole(resultSet.getInt(4));
                list.add(account);
            }
        } catch (SQLException e) {
            Log.e(TAG, "Получено исключение", e);
            //e.printStackTrace();
        } finally {
            closeAllConnection(resultSet);
            return list;
        }
    }
}
