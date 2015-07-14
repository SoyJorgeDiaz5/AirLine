package com.joluditru.airline;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Servicios extends Activity {

    Button btnConsultarVuelos;
    Button btnVuelosGuardados;
    Button btnVuelosReservados;
    Button btnCheckin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);

        //Captura objetos desde la vista
        btnConsultarVuelos = (Button) findViewById(R.id.btnConsultarVuelos);
        btnVuelosGuardados = (Button) findViewById(R.id.btnVuelosGuardados);
        btnVuelosReservados = (Button) findViewById(R.id.btnVuelosReservados);
        btnCheckin = (Button) findViewById(R.id.btnCheckin);

        //Métodos onClick

        //Button CONSULTAR VUELOS
        btnConsultarVuelos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), ConsultarVuelos.class);
                startActivity(intent);
            }
        });

        //Button VUELOS GUARDADOS
        btnVuelosGuardados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), VuelosGuardados.class);
                startActivity(intent);
            }
        });

        //Button VUELOS RESERVADOS
        btnVuelosReservados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), VuelosReservados.class);
                startActivity(intent);
            }
        });

        //Button CHECKIN
        btnCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Checkin.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_servicios, menu);
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
