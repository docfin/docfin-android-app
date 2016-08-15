package com.jellsoft.mobile.docfin.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by atulanand on 8/14/16.
 */
public class StreamReader {

    public static String readStream(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        try {
            byte[] buffer = new byte[1024];
            int size;
            while ((size = bis.read(buffer, 0, 1024)) != -1) {
                sb.append(new String(buffer, 0, size, "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (inputStream != null) try {
                bis.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
