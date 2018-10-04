package com.example.pedro.proyecto_pgl_2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RellenarFormulario extends AppCompatActivity {

    RellenarFormulario contexto;

    EditText editTextNombre;
    EditText editTextDNI;
    EditText editTextEmail;
    EditText editTextTelefono;
    EditText editTextDepartamento;
    EditText editTextQueja;
    EditText editTextImagen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rellenar_formulario);



        editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        editTextDNI = (EditText) findViewById(R.id.editTextDNI);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextTelefono = (EditText) findViewById(R.id.editTextTelefono);
        editTextDepartamento = (EditText) findViewById(R.id.checkbox);
        editTextQueja = (EditText) findViewById(R.id.editTextQueja);
        editTextImagen = (EditText) findViewById(R.id.editTextImagen);

        Button EnviarFormulario = (Button) findViewById(R.id.EnviarFormulario);
        EnviarFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviar();

            }
        });

        Button CancelarFormulario = (Button) findViewById(R.id.CancelarFormulario);
        CancelarFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
    }



    private void enviar(){

        editTextNombre.setError(null);
        editTextDNI.setError(null);
        editTextEmail.setError(null);
        editTextTelefono.setError(null);
        editTextDepartamento.setError(null);
        editTextQueja.setError(null);
        editTextImagen.setError(null);


        String nombre = editTextNombre.getText().toString().trim();
        String dni = editTextDNI.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String telefono = editTextTelefono.getText().toString().trim();
        String departamento = editTextDepartamento.getText().toString().trim();
        String queja = editTextQueja.getText().toString().trim();
        String imagen = editTextImagen.getText().toString().trim();

        if(TextUtils.isEmpty(nombre)){

            editTextNombre.setError(getString(R.string.error_de_campo_obligatorio));
            editTextNombre.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(dni)){

            editTextDNI.setError(getString(R.string.error_de_campo_obligatorio));
            editTextDNI.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(email)){

            editTextEmail.setError(getString(R.string.error_de_campo_obligatorio));
            editTextEmail.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(queja)){

            editTextQueja.setError(getString(R.string.error_de_campo_obligatorio));
            editTextQueja.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(departamento)){

            editTextDepartamento.setError(getString(R.string.error_de_campo_obligatorio));
            editTextDepartamento.requestFocus();
            return;
        }

        int dniint = Integer.parseInt(dni);
        int tlf = Integer.parseInt(telefono);

        if(dniint!=9){

            editTextDNI.setError(getString(R.string.error_valor_emtre_0_9));
            editTextDNI.requestFocus();
            return;
        }

        if(tlf!=9){

            editTextTelefono.setError(getString(R.string.error_valor_emtre_0_9));
            editTextTelefono.requestFocus();
            return;
        }

        Toast.makeText(getApplicationContext(),"Se ha enviado su solicitud correctamente", Toast.LENGTH_SHORT).show();
    }
}
