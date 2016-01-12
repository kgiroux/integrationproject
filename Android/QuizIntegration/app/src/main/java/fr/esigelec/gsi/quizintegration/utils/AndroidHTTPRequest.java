package fr.esigelec.gsi.quizintegration.utils;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Kevin PACE
 */

public class AndroidHTTPRequest extends AsyncTask<String, Void, JSONObject>
{
    public JSONObject executeRequest(String url, String method, String paramsStr)
    {
        URL uri;
        HttpURLConnection urlConnection = null;
        String str;
        JSONObject json = null;

        try
        {
            //Client to execute request on the server
            uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();

            //Set execute parameters
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setDoInput(true);

            //Send parameters to the request
            if("POST".equals(method))
            {
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);

                //Write params to the request
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(paramsStr);
                writer.flush();
                writer.close();
                os.close();
            }
            else
                urlConnection.setRequestMethod("GET");

            //Retrive information from the URL
            urlConnection.connect();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            str = streamToString(in);

            json = new JSONObject(str);

        }catch(Exception ex){ex.printStackTrace();}
        finally {

            if(urlConnection != null)
                urlConnection.disconnect();
        }
        return json;
    }

    public static String createParamString(Map<String,String> params)
    {
        StringBuilder result = new StringBuilder();
        boolean isFirst = true;

        for(Map.Entry<String, String> entry : params.entrySet())
        {
            if(!isFirst)
                result.append("&");
            else
                isFirst = false;

            try {
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }catch(Exception ex){ex.printStackTrace();}
        }
        return result.toString();
    }

    private String streamToString(InputStream in)
    {
        InputStreamReader rd = new InputStreamReader(in);
        BufferedReader buffer = new BufferedReader(rd);
        StringBuilder strb = new StringBuilder();

        try
        {
            String str;
            while((str = buffer.readLine()) != null)
                strb.append(str);

        }catch(Exception ex){ ex.printStackTrace(); }

        return strb.toString();
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        return executeRequest(params[0],params[1],params[2]);
    }
}
