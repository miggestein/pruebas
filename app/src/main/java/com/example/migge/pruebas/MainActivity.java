package com.example.migge.pruebas;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {
    Button enviar;
    EditText datos;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        request= Volley.newRequestQueue(getApplicationContext());
        datos = findViewById(R.id.etDatos);
        enviar = findViewById(R.id.btnEnviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarWebService();
            }
        });


    }

    private void cargarWebService() {
       progreso=new ProgressDialog(MainActivity.this);
       progreso.setMessage("Carganding...");
       progreso.show();
        String url="http://192.168.100.159/pruebabdremota/wsJSONPrueba.php?datos="+datos.getText().toString();
        url=url.replace(" ", "$20");
         jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "No funcó :( "+error.toString(), Toast.LENGTH_SHORT).show();
        progreso.hide();
        datos.setText("");
        Log.i("ERROR", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this, "Funcó perro", Toast.LENGTH_SHORT).show();
        progreso.hide();
        datos.setText("");

    }
}
