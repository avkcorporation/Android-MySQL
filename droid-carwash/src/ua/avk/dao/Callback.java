package ua.avk.dao;

/**
 * @author Alexander Kononenko
 * @version 1.0.0
 * @date 01.05.2017.
 */
public class Callback {
    public static AccountDAO getAccountDAO() {
        return new AccountDAO();
    }
}
