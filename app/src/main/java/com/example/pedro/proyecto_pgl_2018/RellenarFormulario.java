package com.example.pedro.proyecto_pgl_2018;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;

import java.io.File;
import java.util.Map;


public class RellenarFormulario extends AppCompatActivity  {




    EditText editTextNombre, editTextDNI, editTextEmail, editTextTelefono, editTextQueja;
    CheckBox checkboxEducation,checkboxScience, checkboxUrbanismy ;

    Button EnviarFormulario, CancelarFormulario, button_captura;
    ImageView imagen_formulario;


    private final String CARPETA_RAIZ="misImagenesSolicitud/";
    private final String RUTA_IMAGEN=CARPETA_RAIZ+"misFotos";

    final int COD_SELECCIONA = 10;
    final int COD_FOTO= 20;
    String path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rellenar_formulario);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imagen_formulario =(ImageView) findViewById(R.id.imagen_captura);
        button_captura = (Button) findViewById(R.id.button_captura);
        button_captura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarImagen();
            }
        });
        editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        editTextDNI = (EditText) findViewById(R.id.editTextDNI);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextTelefono = (EditText) findViewById(R.id.editTextTelefono);
        editTextQueja = (EditText) findViewById(R.id.editTextQueja);
        checkboxScience = (CheckBox) findViewById(R.id.checkboxScience);
        checkboxEducation = (CheckBox) findViewById(R.id.checkboxEducation);
        checkboxUrbanismy = (CheckBox) findViewById(R.id.checkboxUrbanismy);

        Button EnviarFormulario = (Button) findViewById(R.id.EnviarFormulario);
        EnviarFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviar();

            }
        });
    }


    private void cargarImagen() {

        final CharSequence[] opciones = {"Sacar Foto","Cargar Imagen","Cancelar"};
        final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(RellenarFormulario.this);
        alertOpciones.setTitle("Seleccione una Opción");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Sacar Foto")){

                      tomarFotografia();
                }else{

                    if (opciones[i].equals("Cargar Imagen")){
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicación"),COD_SELECCIONA);
                    }else{
                        dialogInterface.dismiss();
                    }
                }
            }
        });

        alertOpciones.show();

    }

    private void tomarFotografia(){

        File fileImagen = new File(Environment.getExternalStorageDirectory(), RUTA_IMAGEN);
        boolean isCreada = fileImagen.exists();
        String nombreImagen = "";

        if(isCreada==false){

            isCreada=fileImagen.mkdirs();

        }

        if (isCreada==true){

            nombreImagen = (System.currentTimeMillis()/100)+".jpg";
        }

        path= Environment.getExternalStorageDirectory()+File.separator+RUTA_IMAGEN+File.separator+nombreImagen;

        File imagen = new File(path);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
        {
            String authorities = getApplicationContext().getPackageName()+".provider";
            Uri imageUri = FileProvider.getUriForFile(this,authorities,imagen);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        }else
        {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen));
        }

        startActivityForResult(intent,COD_FOTO);

    }


   // @Override

   // public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,  @NonNull int[] grantResults){

    //   Map<String, Boolean> map = magicalPermissions.permissionResult(requestCode, permissions, grantResults);
    //   for (String permission : map.keySet()){

     //      Log.d("PERMISSIONS", permission + "was:" + map.get(permission));
   //    }

   // }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode,resultCode, data);

        if (resultCode==RESULT_OK){

            switch (requestCode){

                case COD_SELECCIONA:
                    Uri miPath = data.getData();
                    imagen_formulario.setImageURI(miPath);
                    break;

                case COD_FOTO:

                    MediaScannerConnection.scanFile(this, new String[]{path}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String s, Uri uri) {
                                    Log.i("Ruta de Almacenamiento", "Path:" + path);
                                }
                            });

                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    imagen_formulario.setImageBitmap(bitmap);

                    break;

            }
        }
    }



    private void enviar() {


        editTextNombre.setError(null);
        editTextDNI.setError(null);
        editTextEmail.setError(null);
        editTextTelefono.setError(null);
        editTextQueja.setError(null);

        String nombre = editTextNombre.getText().toString().trim();
        String dni = editTextDNI.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        //String telefono = editTextTelefono.getText().toString().trim();
        String queja = editTextQueja.getText().toString().trim();

        //String checkbox_1 = checkboxEducation.getText().toString();
        //String checkbox_2 = checkboxScience.getText().toString();
       // String checkbox_3 = checkboxUrbanismy.getText().toString();

        if(checkboxScience.isChecked()){

        }else if (checkboxEducation.isChecked()){


        }else if(checkboxUrbanismy.isChecked()){


        }

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



      //  int dniint = Integer.parseInt(dni);

      //  if(dniint != 9){

        //    editTextDNI.setError(getString(R.string.error_valor_emtre_0_9));
       //     editTextDNI.requestFocus();
       //     return;
       // }

      //  int tlf = Integer.parseInt(telefono);

        //if(tlf<9 || tlf>10){

        //    editTextTelefono.setError(getString(R.string.error_valor_emtre_0_9));
        //    editTextTelefono.requestFocus();
            //return;
       // }

        Toast.makeText(getApplicationContext(),"Se ha enviado su solicitud correctamente", Toast.LENGTH_LONG).show();
    }
}
