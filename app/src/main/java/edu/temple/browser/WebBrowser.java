package edu.temple.browser;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebBrowser extends Fragment {


    WebView webView;
    View v;

    public WebBrowser() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        if(v == null) {
            v = inflater.inflate(R.layout.fragment_web_browser, container, false);
        }


        webView = (WebView) v.findViewById(R.id.browserWebView);


        return v;
    }

    public void loadWebPage(String url)
    {

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
