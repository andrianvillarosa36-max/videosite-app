package com.chuck.animecorn;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inject native bridge after WebView is ready
        getBridge().getWebView().addJavascriptInterface(new NativeBridge(), "NativeBridge");
    }

    public class NativeBridge {

        @JavascriptInterface
        public void lockLandscape() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            });
        }

        @JavascriptInterface
        public void unlockOrientation() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                }
            });
        }

        @JavascriptInterface
        public void lockPortrait() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
            });
        }
    }
}
