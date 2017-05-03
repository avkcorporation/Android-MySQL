package ua.avk.entitys;

import java.io.Serializable;

/**
 * @author Alexander Kononenko
 * @version 1.0.0
 * @date 01.05.2017.
 */
public class Account implements Serializable {
    public static final String GET_ALL_ACCOUNTS = "select * from Account";
    public static final String INSERT_ACCOUNTS = "INSERT INTO Account";

    private Long id = null;
    private String logIn = null;
    private String password = null;
    private int role = 0;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogIn() {
        return logIn;
    }

    public void setLogIn(String logIn) {
        this.logIn = logIn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return id + ", " + logIn + ", " + password + ", " + role + ";";
    }
}
