package com.example.pedro.proyecto_pgl_2018.manager;

import android.provider.BaseColumns;

import com.example.pedro.proyecto_pgl_2018.ManagerEventApplication;

public class Manager implements BaseColumns {
    public static  String TABLE_NAME = "request";
    public static String CONTENT_URI = "content://" + ManagerEventApplication.AUTHORITY + "/" + TABLE_NAME;
    public static String TIPO = "departamento";
    public static String ID = "id";
    //Implementamos las distintas tablas que va a tener nuestra base de datos
}
