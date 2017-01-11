
package inc.gpss.applocker.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import inc.gpss.applocker.R;
import inc.gpss.applocker.Utils.PreferenceContract;
import inc.gpss.applocker.Utils.PreferenceUtils;

public class GpssSplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpss_splash_screen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                navigateToNext();
            }
        }, SPLASH_TIME_OUT);
    }

    private void navigateToNext() {
        Intent intent;
        if (PreferenceUtils.getString(PreferenceContract.KEY_PATTERN_SHA1,
                PreferenceContract.DEFAULT_PATTERN_SHA1, GpssSplashScreen.this)
                .equals(PreferenceContract.DEFAULT_PATTERN_SHA1)) {
            intent = new Intent(GpssSplashScreen.this, ResetLockActivity.class);
        } else {
            intent = new Intent(GpssSplashScreen.this, LockActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
