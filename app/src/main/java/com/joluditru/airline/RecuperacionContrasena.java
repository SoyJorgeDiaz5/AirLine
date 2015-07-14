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


public class RecuperacionContrasena extends Activity {

    EditText txtCorreo;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperacion_contrasena);

        //Captura objetos desde la vista
        btnEnviar = (Button) findViewById(R.id.btnEnviar);

        //Métodos onClick

        //Button ENTRAR
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String correo;
                txtCorreo = (EditText) findViewById(R.id.txtCorreo);
                correo = txtCorreo.getText().toString();

                if (correo.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Por favor ingrese su correo electrónico", Toast.LENGTH_LONG).show();
                }else{
                    if (correo.contains("@")){
                        Toast.makeText(getApplicationContext(),"Su contraseña ha sido enviada", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Por favor ingrese un correo válido", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recuperacion_contrasena, menu);
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
