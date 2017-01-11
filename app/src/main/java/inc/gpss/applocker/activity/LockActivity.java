
package inc.gpss.applocker.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import inc.gpss.applocker.R;
import inc.gpss.applocker.Utils.LockView;
import inc.gpss.applocker.Utils.PreferenceContract;
import inc.gpss.applocker.Utils.PreferenceUtils;

/**
 * An example full-screen activity that shows and hides the system UI (i.e. status bar and
 * navigation/system bar) with user interaction.
 */
public class LockActivity extends Activity {
    private LockView lock9View;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);

        lock9View = (LockView) findViewById(R.id.lock_9_view);
        if (lock9View != null) {
            lock9View.setCallBack(new LockView.CallBack() {

                @Override
                public void onFinish(String password) {
                    String patternKey = PreferenceUtils.getString(
                            PreferenceContract.KEY_PATTERN_SHA1,
                            PreferenceContract.DEFAULT_PATTERN_SHA1, LockActivity.this);
                    if (password.equals(patternKey)) {
                        Intent intent = new Intent(LockActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(LockActivity.this, "Login success!", Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        Toast.makeText(LockActivity.this, "Pattern incorrect!",
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            });
        }
    }
}
