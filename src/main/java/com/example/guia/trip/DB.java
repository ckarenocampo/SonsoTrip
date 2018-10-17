package com.example.guia.trip;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DB {
    private DBHelper dbHelper;
    public DB(Context context) {
        //definimos el nombre de la BD
        dbHelper = new DBHelper(context, "SonsoTrip", null, 1);
    }
    //consulta usuario
    public Cursor validarUsuario(String nombre, String contra){
        return dbHelper.getReadableDatabase().rawQuery(
                "select usuario, clave from usuarios where usuario='"+nombre+"' and " +
                        "clave='"+contra+"'",null);
    }

    public boolean GuardarOActualizar(Usuarios us) {


        ContentValues initialValues = new ContentValues();
        Log.d("Usuarios","id "+us.getId());
        Log.d("Usuarios","usuario "+us.getUsuario());
        Log.d("Usuarios","clave "+us.getClave());
        if(!us.getId().isEmpty())
            initialValues.put("id", Integer.parseInt(us.getId()));
        initialValues.put("usuario",us.getUsuario());
        initialValues.put("clave",us.getClave());
        int id = (int) dbHelper.getWritableDatabase().insertWithOnConflict(
                "usuarios",
                null,
                initialValues,
                SQLiteDatabase.CONFLICT_REPLACE);
        return id>0;
    }
}
