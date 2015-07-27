package com.joluditru.airline;

/**
 * Created by jorgel.diaz on 18/07/15.
 */

import android.app.Activity;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;

public class Conexion extends Activity
{

    //private  String ip;
    private String url;
    private AsyncHttpClient client;
    private static Conexion singleton = null;
    private static String retorno;


    private Conexion(String ip)
    {
        //this.ip = ip;
        url = "http://"+ip+":8080/ServicioWebAerolineaUdeA/webresources/ServicioWeb";//192.168.1.64
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
    public String buscar(String url,RequestParams parametros){
        client.get(getApplicationContext(), url, parametros, new AsyncHttpResponseHandler() {


            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try{
                    retorno = new String(bytes,"UTF-8");
                }catch(UnsupportedEncodingException uee){
                    uee.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                // TODO Auto-generated method stub
                retorno="error";
                Log.d("TAG", "ERROR DE CONEXIÓN");
            }
        });
        return retorno;
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

    //solo se puede reservar de a una boleta
    public boolean realizarReserva(String boleta,String usuario){
        boolean retorno = false;
        RequestParams parametros = new RequestParams();
        parametros.add("id_boleta", boleta);
        parametros.add("id_usuario", usuario);
        String datos = this.buscar(url+"/realizarReserva", parametros);
        if(datos.equals("true")){
            retorno = true;
        }
        return retorno;
    }
}
