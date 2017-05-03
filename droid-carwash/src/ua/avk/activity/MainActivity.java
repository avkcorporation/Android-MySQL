package ua.avk.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import ua.avk.R;
import ua.avk.services.Session;


public class MainActivity extends Activity {
    protected final String TAG = this.getClass().getSimpleName();
    public static final String APP_PREFERENCES = "mysettings";
    private static final String APP_PREFERENCES_LOGIN = "login";
    private static final String APP_PREFERENCES_PASSWORD = "pswd";
    private static final String APP_PREFERENCES_ROLE = "role";
    private SharedPreferences mSettings;

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

        if (!Session.userSession) {
            Intent intent = new Intent(MainActivity.this, AuthorizationActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_main);

        final Button debitBtn = (Button) findViewById(R.id.debit_btn);
        debitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutAppActivity.class);
                startActivity(intent);
            }
        });

        final Button creditBtn = (Button) findViewById(R.id.credit_btn);
        creditBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutAppActivity.class);
                //startActivity(intent);
            }
        });

        final Button totalBtn = (Button) findViewById(R.id.total_btn);
        totalBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutAppActivity.class);
                //startActivity(intent);
            }
        });
    }
}

