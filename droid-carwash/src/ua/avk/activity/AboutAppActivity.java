package ua.avk.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import ua.avk.R;
import ua.avk.services.Session;

/**
 * @author Alexander Kononenko
 * @version 1.0.0
 * @date 02.05.2017.
 */
public class AboutAppActivity extends Activity {
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
        /*
        if (!Session.userSession) {
            Intent intent = new Intent(AboutAppActivity.this, AuthorizationActivity.class);
            startActivity(intent);
        }
        */
        setContentView(R.layout.activity_about_app);
    }

}
