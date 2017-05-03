package ua.avk.services;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import ua.avk.entitys.Account;

/**
 * @author Alexander Kononenko
 * @version 1.0.0
 * @date 02.05.2017.
 */
public class Session {

    public static final String APP_PREFERENCES = "mysettings";
    private static final String APP_PREFERENCES_LOGIN = "login";
    private static final String APP_PREFERENCES_PASSWORD = "pswd";
    private static final String APP_PREFERENCES_ROLE = "role";
    private SharedPreferences mSettings;

    public static Boolean userSession = false;

    public Session(Activity activity) {
        mSettings = activity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void createUserSession(Account account) {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_LOGIN, account.getLogIn());
        editor.putString(APP_PREFERENCES_PASSWORD, account.getPassword());
        editor.putInt(APP_PREFERENCES_ROLE, account.getRole());
        editor.apply();
        userSession = true;
    }

}
