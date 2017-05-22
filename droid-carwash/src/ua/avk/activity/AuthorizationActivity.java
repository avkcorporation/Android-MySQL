package ua.avk.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import ua.avk.R;
import ua.avk.dao.CallbackDAO;
import ua.avk.dao.IAccountDAO;
import ua.avk.entitys.Account;
import ua.avk.services.CallbackServices;
import ua.avk.services.IAccountSrv;
import ua.avk.services.Session;

/**
 * @author Alexander Kononenko
 * @version 1.0.0
 * @date 02.05.2017.
 */
public class AuthorizationActivity extends Activity {
    protected final String TAG = this.getClass().getSimpleName();
    public static final String APP_PREFERENCES = "mysettings";
    private static final String APP_PREFERENCES_LOGIN = "login";
    private static final String APP_PREFERENCES_PASSWORD = "pswd";
    private static final String APP_PREFERENCES_ROLE = "role";
    private SharedPreferences mSettings;
    private Session session = null;
    protected Account account = null;
    protected String logInStr = null;
    protected String pswdStr = null;
    private final Context context = this;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        session = new Session(this);

        final Button cancelBtn = (Button) findViewById(R.id.sigInBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.GINGERBREAD)
            public void onClick(View v) {

                EditText logInEditText = (EditText) findViewById(R.id.logInEditText);
                logInStr = logInEditText.getText().toString();
                EditText pswdEditText = (EditText) findViewById(R.id.pswdEditText);
                pswdStr = pswdEditText.getText().toString();

                if (!pswdStr.isEmpty() & !logInStr.isEmpty()) {
                    final String[] param = {logInStr, pswdStr};
                    Connect task = new Connect();
                    task.execute(param);
                } else {
                    Toast.makeText(context, "Вы не ввели данные", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private class Connect extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... param) {
            IAccountDAO accountDAO = CallbackDAO.getAccountDAO();
            account = accountDAO.getByLogInAndPassword(param[0], param[1]);
            if (account != null) {
                session = new Session((Activity) context);
                session.createUserSession(account);
                Log.i(TAG, "Сессия пользлвателя - открыта");
                Intent intent = new Intent(AuthorizationActivity.this, MainActivity.class);
                startActivity(intent);
                return "Доступ получен!";

            } else {

                return "Message: Нет доступа";
            }

        }

        @Override
        protected void onPostExecute(String result) {
            Log.i(TAG, result);
        }
    }

}
