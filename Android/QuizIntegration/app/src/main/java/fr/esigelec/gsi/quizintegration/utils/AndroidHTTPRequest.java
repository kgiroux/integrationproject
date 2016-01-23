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
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Kevin PACE
 */

public class AndroidHTTPRequest extends AsyncTask<String[], Void, JSONObject>
{
    public JSONObject executeRequest(String url, String method, String paramsStr)
    {
        URL uri;
        HttpURLConnection urlConnection = null;
        String str;
        JSONObject json = null;
        OutputStream os = null;
        InputStream in = null;

        try
        {
            //Création de la connection et de l'url de la requête
            uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();

            //Définition des paramètres de connexion
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setDoInput(true);

            //Envoie des paramètre dans la requête au serveur
            if("POST".equals(method) && paramsStr != null) {
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);

                //Ecriture dans le flux de la chaîne des paramètres
                os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(paramsStr);
                writer.flush();
                writer.close();
                os.close();
            }
            else
                urlConnection.setRequestMethod("GET");

            //Récupération des iformations de retour du serveur
            urlConnection.connect();
            in = new BufferedInputStream(urlConnection.getInputStream());
            str = streamToString(in);
            in.close();

            json = new JSONObject(str);

        }catch(Exception ex){ex.printStackTrace();}
        finally {

            if(urlConnection != null)
                urlConnection.disconnect();
        }
        return json;
    }

    //Fonction permettant de parser une HashMap en châine de caractère pour la requête
    public static String createParamString(Map<String,String> params)
    {
        StringBuilder result = new StringBuilder();
        boolean isFirst = true;

        for(Map.Entry<String, String> entry : params.entrySet())
        {
            //Si premier element ne pas ajouter le signe &
            if(!isFirst)
                result.append("&");
            else
                isFirst = false;

            //Encodade des informations du paramètre en UTF (clé, valeur)
            try {
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }catch(Exception ex){ex.printStackTrace();}
        }
        return result.toString();
    }

    //Fonction permettant la conversion en chaîne de caractère du flux reçu en retour de requête
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
    protected JSONObject doInBackground(String[]... params) {
        return executeRequest(params[0][0],params[0][1],params[0][2]);
    }
}
