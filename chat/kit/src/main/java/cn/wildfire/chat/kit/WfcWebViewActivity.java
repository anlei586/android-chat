package cn.wildfire.chat.kit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import cn.wildfirechat.chat.R;

public class WfcWebViewActivity extends WfcBaseActivity {
    private String url;

    @BindView(R.id.webview)
    WebView webView;

    public static void loadUrl(Context context, String title, String url) {
        Intent intent = new Intent(context, WfcWebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    public static void loadHtmlContent(Context context, String title, String htmlContent) {
        Intent intent = new Intent(context, WfcWebViewActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", htmlContent);
        context.startActivity(intent);
    }

    @Override
    protected int contentLayout() {
        return R.layout.activity_webview;
    }

    @Override
    protected void afterViews() {

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        // 设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDatabaseEnabled(true);
        // 使用localStorage则必须打开
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);

        url = getIntent().getStringExtra("url");

        String htmlContent = getIntent().getStringExtra("content");
        if (!TextUtils.isEmpty(htmlContent)) {
            webView.loadDataWithBaseURL("", htmlContent, "text/html", "UTF-8", "");
        } else {
            webView.loadUrl(url);
        }

        String title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
        }

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String webTitle = view.getTitle();
                if (!TextUtils.isEmpty(webTitle)) {
                    if (TextUtils.isEmpty(title) || !TextUtils.equals(webTitle, "about:blank")) {
                        setTitle(webTitle);
                    }
                }
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //使用WebView加载显示url
                view.loadUrl(url);
                //返回true
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //handler.proceed();// 接受证书
                super.onReceivedSslError(view, handler, error);
            }
/*
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                try {
                    if (url.startsWith("weixin://") || url.startsWith("alipays://")) {
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }

                if (url.contains("https://wx.tenpay.com")) {
                    return false;
                }
                return true;
            }*/
        });
    }
}
