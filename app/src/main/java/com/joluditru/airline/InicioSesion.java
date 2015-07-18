package com.joluditru.airline;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class InicioSesion extends Activity {

    TextView lblOlvidoContraseña;
    EditText txtUsuario;
    EditText txtContraseña;
    Button btnEntrar;
    Button btnCrearCuenta;
    Conexion conexion;


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

                conexion = Conexion.getInstance("192.168.193.26");

                //Inicia una nueva actividad si retorna True
                if (conexion.iniciarSesion(usuario, contraseña)) {
                    Intent intent = new Intent(getApplicationContext(), Servicios.class);
                    startActivity(intent);
                }else
                {
                    Toast.makeText(getApplicationContext(), "Por favor verifique su usuario y contraseña", Toast.LENGTH_LONG).show();
                }
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

}
