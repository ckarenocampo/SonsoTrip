package com.example.guia.trip;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DB db;
    TextView lblRegistro,lblMensaje;
    EditText txtUsuario, txtClave;
    Button btnIngresar;
    private Cursor fila;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lblRegistro = findViewById(R.id.lblRegistro);
        lblMensaje = findViewById(R.id.lblMensaje);
        txtClave = findViewById(R.id.txtClave);
        txtUsuario = findViewById(R.id.txtUsuario);
        btnIngresar = findViewById(R.id.btnIngresar);
        db = new DB(this);
       // Usuarios user = new Usuarios("3","a","a");
        //db.GuardarOActualizar(user);

        lblRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent act = new Intent(MainActivity.this,Registro.class);
                startActivity(act);
                txtClave.setText("");
                txtUsuario.setText("");
                lblMensaje.setText("");
            }
        });
    }

    public void onClickIngresar(View view) {
        String usuario = txtUsuario.getText().toString();
        String clave = txtClave.getText().toString();

        if(usuario.isEmpty()|| clave.isEmpty()){
            lblMensaje.setText("Ingrese datos");
        }else{

            fila = db.validarUsuario(usuario, clave);
            if (fila.moveToFirst()){
                String user = fila.getString(0);
                String pass = fila.getString(1);
                if(usuario.equals(user) && clave.equals(pass)){
                    Intent act = new Intent(this,Principal.class);
                    startActivity(act);
                    txtClave.setText("");
                    txtUsuario.setText("");
                    lblMensaje.setText("");
                }
            }else{
                lblMensaje.setText("Datos invalidos");
            }
        }
    }


}
