package com.joluditru.airline;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class InicioSesion extends Activity {

    TextView lblOlvidoContraseña;
    EditText txtUsuario;
    EditText txtContraseña;
    Button btnEntrar;
    Button btnCrearCuenta;
    Conexion conexion;
    private static final String LOGIN_URL = "IniciarSesion";
    private ProgressDialog pDialog;
    private String resultado = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        //Captura objetos desde la vista
        btnCrearCuenta = (Button) findViewById(R.id.btnCrearCuenta);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        lblOlvidoContraseña = (TextView) findViewById(R.id.lblOlvidoContraseña);

        //Métodos onClick

        //Button ENTRAR
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Captura objetos desde la vista
                txtUsuario = (EditText) findViewById(R.id.txtUsuario);
                txtContraseña = (EditText) findViewById(R.id.txtContraseña);

                //Asignación de campos de textos
                String usuario = txtUsuario.getText().toString().trim();
                String contraseña = txtContraseña.getText().toString();

                conexion = Conexion.getInstance("192.168.193.25");

                IniSesion login =  new IniSesion();
                login.execute();
                Log.d("MENSAJE", resultado);

            }
        });


        //Button CREAR CUENTA
        btnCrearCuenta.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(),NuevaCuenta.class);
                startActivity(intent);
            }
        });

        //TextView OLVIDO CONTRASEÑA
        lblOlvidoContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(),RecuperacionContrasena.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicio_sesion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





    class IniSesion extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... params) {
            int success;
            String username = txtUsuario.getText().toString();
            String password = txtContraseña.getText().toString();

            // Building Parameters
            List parametros = new ArrayList();
            parametros.add(new BasicNameValuePair("usuario", username));
            parametros.add(new BasicNameValuePair("contrasena", password));

            Log.d("request!", "starting");
            // getting product details by making HTTP request
            String busqueda = conexion.buscar(LOGIN_URL, parametros);

            // check your log for json response
            Log.d("Login attempt", busqueda);

            resultado = busqueda.trim();
            Log.d("RESULTADO", resultado);

            //Inicia una nueva actividad si retorna True
            if (resultado.equals("true")) {
                Log.d("MENSAJE", "ENTRÓ");
                Intent intent = new Intent(getApplicationContext(), Servicios.class);
                startActivity(intent);
            }
                return null;
        }



        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null) {
                Toast.makeText(InicioSesion.this, file_url, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(InicioSesion.this);
            pDialog.setMessage("Iniciando sesión...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
    }
}
