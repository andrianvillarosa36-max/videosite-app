package com.chuck.animecorn;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {

    private volatile float topInsetDp = 0f;
    private volatile float leftInsetDp = 0f;
    private volatile float rightInsetDp = 0f;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        ViewCompat.setOnApplyWindowInsetsListener(getWindow().getDecorView(), (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            Insets cutout = insets.getInsets(WindowInsetsCompat.Type.displayCutout());
            float density = getResources().getDisplayMetrics().density;
            topInsetDp = Math.max(systemBars.top, cutout.top) / density;
            leftInsetDp = Math.max(systemBars.left, cutout.left) / density;
            rightInsetDp = Math.max(systemBars.right, cutout.right) / density;
            return insets;
        });

        getBridge().getWebView().addJavascriptInterface(new NativeBridge(), "NativeBridge");
    }

    public class NativeBridge {

        @JavascriptInterface
        public void lockLandscape() {
            runOnUiThread(() -> setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE));
        }

        @JavascriptInterface
        public void unlockOrientation() {
            runOnUiThread(() -> setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED));
        }

        @JavascriptInterface
        public void lockPortrait() {
            runOnUiThread(() -> setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));
        }

        @JavascriptInterface
        public float getTopInset() {
            return topInsetDp;
        }

        @JavascriptInterface
        public float getLeftInset() {
            return leftInsetDp;
        }

        @JavascriptInterface
        public float getRightInset() {
            return rightInsetDp;
        }
    }
}
