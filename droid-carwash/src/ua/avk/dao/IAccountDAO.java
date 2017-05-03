package ua.avk.dao;

import ua.avk.entitys.Account;

import java.util.List;

/**
 * Created by Администратор on 03.01.2017.
 */
public interface IAccountDAO {

    /**
     * Init method save or update record of the cash object in to the database
     *
     * @param account object
     */
    public void saveOrUpdate(Account account);

    /**
     * Init method save record of the cash object in to the database
     *
     * @param account object
     */
    public void save(Account account);

    /**
     * Init method delete record of the account object in to the database
     *
     * @param account object
     */
    public void delete(Account account);

    /**
     * Init method get for get all account from the database
     *
     * @return list of the account object
     */
    public List<Account> getAll();

    /**
     * User authentication by log in and password
     *
     * @param logIn    string
     * @param password string
     * @return account object
     */
    public Account getByLogInAndPassword(String logIn, String password);
}
