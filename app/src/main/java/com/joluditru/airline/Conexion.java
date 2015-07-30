package com.joluditru.airline;

/**
 * Created by jorgel.diaz on 18/07/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Conexion
{

    //private  String ip;
    private String url;
    private AsyncHttpClient client;
    private static Conexion singleton = null;
    private  String resultado = "";
    private InputStream is;


    private Conexion(String ip)
    {
        //this.ip = ip;
        url = "http://"+ip+":8080/ServicioWebAerolineaUdeA/webresources/ServicioWeb/";
        client = new AsyncHttpClient();
    }

    public static Conexion getInstance(String ip)
    {
        if(singleton==null){
            singleton = new Conexion(ip);
        }
        return singleton;
    }
    //metodo que consume el servicio web
    public String buscar(String ruta,List parametros){
            // Haciendo la Peticion HTTP
            try {
                    // request method is GET
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    String paramString = URLEncodedUtils.format(parametros, "utf-8");
                    ruta = url + ruta  + "?" + paramString;
                    HttpGet httpGet = new HttpGet(ruta);
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    is = httpEntity.getContent();


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                            is, "utf-8"), 8);
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    is.close();
                    resultado = sb.toString();
                } catch (Exception e) {
                    Log.e("Buffer Error", "Error converting result " + e.toString());
                }

                return resultado;

    }

    public boolean iniciarSesion(String usuario,String contrasena)
    {
        boolean retorno = false;
        RequestParams parametros = new RequestParams();
        parametros.add("usuario", usuario);
        parametros.add("contrasena", contrasena);
        String datos = this.buscar(url+"/IniciarSesion",null);
        if(datos.equals("true")){
            retorno = true;
        }
        return retorno;
    }



    public String getCiudades()
    {
        //RequestParams parametros = new RequestParams();
        String datos = this.buscar(url+"/ConsultarCiudades", null);
        return datos;
    }

}
