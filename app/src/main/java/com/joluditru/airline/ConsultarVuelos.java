package com.joluditru.airline;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.IllegalFormatFlagsException;
import java.util.List;


public class ConsultarVuelos extends Activity {

    EditText txtFecha;
    Button btnConsultar;
    Spinner spinOrigen;
    Spinner spinDestino;
    Conexion conexion;
    String origen = "";
    String destino = "";



    String fecha =  "";

    private static final String CONSULTA_URL = "ConsultarVuelos";
    private ProgressDialog pDialog;
    private String resultado = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_vuelos);

        spinOrigen = (Spinner) findViewById(R.id.spinnerOrigen);


        spinDestino = (Spinner) findViewById(R.id.spinnerDestino);


        txtFecha =(EditText)findViewById(R.id.txtFecha);

        txtFecha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DateDialog dialog = new DateDialog(view);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialog.show(ft, "Selección de fecha");

            }

        });


        btnConsultar = (Button)findViewById(R.id.btnConsultar);
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                origen = spinOrigen.getSelectedItem().toString();
                destino = spinDestino.getSelectedItem().toString();

                Log.d("Origen", origen);
                Log.d("Destino", destino);

                fecha = txtFecha.getText().toString();

                Log.d("FECHA", fecha);


                if (origen.equals(destino) || fecha.equals("") ){
                    Toast.makeText(getApplicationContext(), "Verifique los campos de Origen, Destino y Fecha.", Toast.LENGTH_SHORT).show();
                }else{
                    conexion = Conexion.getInstance("192.168.1.64");

                    ConsVuelos consulta =  new ConsVuelos();
                    consulta.execute();
                    Log.d("MENSAJE", resultado);
                }
            }
        });
    }



    class ConsVuelos extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... params) {



            // Building Parameters
            List parametros = new ArrayList();
            parametros.add(new BasicNameValuePair("origen",origen));
            parametros.add(new BasicNameValuePair("destino",destino));
            parametros.add(new BasicNameValuePair("fecha", fecha));

            // getting product details by making HTTP request
            String busqueda = conexion.buscar(CONSULTA_URL, parametros);

            // check your log for json response
            Log.d("Login attempt", busqueda);

            resultado = busqueda.trim();

                Log.d("RESULTADO", resultado);

                Log.d("MENSAJE", "ENTRÓ");
                Intent intent = new Intent(getApplicationContext(),ListaVuelos.class);
                intent.putExtra("parametros", resultado);
                startActivity(intent);

            return null;
        }




        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null) {
                Toast.makeText(ConsultarVuelos.this, file_url, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ConsultarVuelos.this);
            pDialog.setMessage("Consultando vuelos disponibles...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_consultar_vuelos, menu);
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













}
