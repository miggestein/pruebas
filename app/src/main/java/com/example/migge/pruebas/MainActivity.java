package com.example.migge.pruebas;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.migge.pruebas.entidades.info;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {
    Button enviar, mostrar;
    EditText datos, id;
    TextView txtviewid, txtviewdatos;
    ProgressDialog progreso;
    RequestQueue request, request2;
    JsonObjectRequest jsonObjectRequest, jsonObjectRequest2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        request= Volley.newRequestQueue(getApplicationContext());
        request2= Volley.newRequestQueue(getApplicationContext());
        datos = findViewById(R.id.etDatos);
        id = findViewById(R.id.etid);
        txtviewdatos = findViewById(R.id.txtviewdato);
        txtviewid = findViewById(R.id.txtviewid);
        mostrar= findViewById(R.id.mostrar);
        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarWebService2();
            }
        });
        enviar = findViewById(R.id.btnEnviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarWebService();
            }
        });


    }

    private void cargarWebService2() {
        progreso=new ProgressDialog(MainActivity.this);
        progreso.setMessage("Carganding...");
        progreso.show();
        String url="http://192.168.100.23/pruebabdremota/wsJSONConsultarUsuario.php?id="+id.getText().toString();
        jsonObjectRequest2=new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request2.add(jsonObjectRequest2);
    }

    private void cargarWebService() {
       progreso=new ProgressDialog(MainActivity.this);
       progreso.setMessage("Carganding...");
       progreso.show();
        String url="http://192.168.100.23/pruebabdremota/wsJSONPrueba.php?datos="+datos.getText().toString();
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
        id.setText("");

        info miinfo = new info();
        JSONArray json=response.optJSONArray("info");
        JSONObject jsonObject=null;

        try {
            jsonObject=json.getJSONObject(0);


            miinfo.setDatos(jsonObject.optString("datos"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        txtviewdatos.setText(miinfo.getDatos());
    }
}
