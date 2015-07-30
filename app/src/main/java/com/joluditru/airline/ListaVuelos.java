package com.joluditru.airline;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ListaVuelos extends Activity {

    String vuelos = "";
    String[] listaVuelos;
    ListView lista;
    List<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vuelos);
        list = new ArrayList<>();
        vuelos = getIntent().getStringExtra("parametros");

        Log.d("SplitVuelos",vuelos);
        listaVuelos = vuelos.split("&");

        for (int i=0; i<listaVuelos.length; i++){
            String[] vuelo = listaVuelos[i].split(",");
            String cadena = "";
            for (int j=0; j < vuelo.length;j++){
                cadena = cadena + vuelo[j] + "\n";
            }
            list.add(cadena);
            Log.d("CADENA", cadena);
        }
        lista = (ListView) findViewById(R.id.listaConsultaVuelos);
        lista.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list));

    }










    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_vuelos, menu);
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
