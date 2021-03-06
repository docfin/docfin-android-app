package com.jellsoft.mobile.docfin.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.util.StreamReader;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by atulanand on 8/17/16.
 */
public class URLConnector {

    private static final String DEBUG_TAG = "URLConnector";

    Context context;

    public URLConnector(Context context) {
        this.context = context;
    }

    public String doGet(String urlStr) {
        InputStream is = null;
        try {
            URL url = new URL(urlStr);
            ConnectivityManager connMgr = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                conn.connect();
                int response = conn.getResponseCode();
                Log.d(DEBUG_TAG, "The response is: " + response);
                is = conn.getInputStream();

                return StreamReader.readStream(is);
            }
            return context.getResources().getString(R.string.no_network_connection);
        } catch (Exception e) {
            Log.e(DEBUG_TAG, e.getMessage());
            return context.getResources().getString(R.string.network_call_error);
        }
    }
}
