package com.example.guia.trip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Registro extends AppCompatActivity {

    EditText txtNombre, txtClave;
    TextView lblMensaje;
    Button btnRegistrar;
    Integer cont=1;
    String usuario, clave;
    private DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        txtNombre = findViewById(R.id.txtNombre);
        txtClave = findViewById(R.id.txtClave);
        lblMensaje = findViewById(R.id.lblMensaje);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        db = new DB(this);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = txtNombre.getText().toString();
                String clave = txtClave.getText().toString();
                String c = (cont).toString();

                if(usuario.isEmpty()|| clave.isEmpty()){
                    lblMensaje.setText("Ingrese datos ");
                }else{
                    Usuarios user = new Usuarios(c,usuario,clave);
                    db.GuardarOActualizar(user);
                    finish();
                    lblMensaje.setText("");
                }
            }
        });
    }
}
