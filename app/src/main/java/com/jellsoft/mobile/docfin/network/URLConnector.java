package com.jellsoft.mobile.docfin.network;

import android.content.Context;
import android.util.Log;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.util.StreamReader;

import java.io.IOException;
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

    public String doGet(String urlStr)
    {
        InputStream is = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(DEBUG_TAG, "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = StreamReader.readStream(is);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }catch (Exception e)
        {
            e.printStackTrace();
            return context.getResources().getString(R.string.network_conn_error);
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
