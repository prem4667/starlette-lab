package in.co.starlette.lis;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {
    private WebView webView;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        setContentView(webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowContentAccess(false);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.addJavascriptInterface(new LISBridge(this), "AndroidLIS");
        if (savedInstanceState != null) webView.restoreState(savedInstanceState);
        else webView.loadUrl("file:///android_asset/index.html");
    }

    private class LISBridge {
        private final Context context;
        LISBridge(Context context) { this.context = context; }
        @JavascriptInterface public void printReport() {
            runOnUiThread(() -> {
                try {
                    PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
                    String jobName = "STARLETTE_LAB_Report";
                    printManager.print(jobName, webView.createPrintDocumentAdapter(jobName), new PrintAttributes.Builder().build());
                } catch (Exception e) {
                    Toast.makeText(context, "Unable to open print screen. Please try again.", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        if (webView != null) webView.saveState(outState);
        super.onSaveInstanceState(outState);
    }
    @Override public void onBackPressed() {
        if (webView != null && webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }
}
