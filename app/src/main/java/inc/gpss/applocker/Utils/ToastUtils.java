
package inc.gpss.applocker.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by shahin on 1/10/17.
 */

public class ToastUtils {

    private ToastUtils() {
    }

    public static void show(CharSequence text, int duration, Context context) {
        Toast.makeText(context, text, duration).show();
    }

    public static void show(int resId, int duration, Context context) {
        show(context.getText(resId), duration, context);
    }

    public static void show(CharSequence text, Context context) {
        show(text, Toast.LENGTH_SHORT, context);
    }

    public static void show(int resId, Context context) {
        show(context.getText(resId), context);
    }
}
