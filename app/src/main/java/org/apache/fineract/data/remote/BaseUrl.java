/*
 * This project is licensed under the open source MPL V2.
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */

package org.apache.fineract.data.remote;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Rajan Maurya
 */
public class BaseUrl {

    public static final String PROTOCOL_HTTPS = "https://";
    public static final String API_ENDPOINT = "pilot.kuelap.io";
    public static final String PORT = "80";
    // "/" in the last of the base url always

    public String getName() {
        return "fineract";
    }

    public static String getDefaultBaseUrl() {
        return PROTOCOL_HTTPS + API_ENDPOINT;
    }

    public static String getConfiguredBaseUrl(Context context){
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = context.getAssets().open("application.properties");

            prop.load(input);

            String protocol = prop.getProperty("protocol");
            String api_endpoint = prop.getProperty("api_endpoint");
            //String port = prop.getProperty("port");

            return protocol + api_endpoint;

        } catch (Exception ex) {
            Log.e("BaseUrl", ex.getMessage());
            return getDefaultBaseUrl();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    Log.e("BaseUrl", e.getMessage());
                }
            }
        }
    }
}