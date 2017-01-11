
package inc.gpss.applocker.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import inc.gpss.applocker.R;
import inc.gpss.applocker.Utils.LockView;
import inc.gpss.applocker.Utils.PreferenceContract;
import inc.gpss.applocker.Utils.PreferenceUtils;

public class ResetLockActivity extends Activity {

    private FrameLayout mEnterPatternContainer;
    private FrameLayout mConfirmPatternContainer;
    private LockView mLockViewFirstTry;
    private LockView mLockViewConfirm;
    private static String PATTERN_KEY;
    private TextView mMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_lock);

        mMessageTextView = (TextView) findViewById(R.id.tvMsg);
        mMessageTextView.setText(getResources().getString(R.string.draw_pattern_msg));

        mEnterPatternContainer = (FrameLayout) findViewById(R.id.enterPattern);
        mConfirmPatternContainer = (FrameLayout) findViewById(R.id.confirmPattern);

        mLockViewFirstTry = (LockView) findViewById(R.id.lock_viewFirstTry);
        mLockViewConfirm = (LockView) findViewById(R.id.lock_viewConfirm);
        // we can get a call back string when ever user interacts
        // with the pattern lock view
        mLockViewFirstTry.setCallBack(new LockView.CallBack() {

            @Override
            public void onFinish(String password) {
                PATTERN_KEY = password;
                mEnterPatternContainer.setVisibility(View.GONE);
                mMessageTextView
                        .setText(getResources().getString(R.string.redraw_confirm_pattern_msg));
                mConfirmPatternContainer.setVisibility(View.VISIBLE);
            }
        });

        mLockViewConfirm.setCallBack(new LockView.CallBack() {

            @Override
            public void onFinish(String password) {

                if (password.equals(PATTERN_KEY)) {
                    Toast.makeText(getApplicationContext(),
                            "Pattern created successfully!",
                            Toast.LENGTH_SHORT).show();
                    PreferenceUtils.putString(PreferenceContract.KEY_PATTERN_SHA1, PATTERN_KEY,
                            ResetLockActivity.this);
                    Intent intent = new Intent(ResetLockActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "You have drawn the wrong Pattern", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
