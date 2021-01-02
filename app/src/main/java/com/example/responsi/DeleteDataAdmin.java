package com.example.responsi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class DeleteDataAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_data_admin);
    }


    public void deleteData(View view) {
        EditText id = (EditText)findViewById(R.id.id);
        String id_txt = id.getText().toString();
        

        deleteJson(id_txt);
    }


    protected void deleteJson(String id) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://www.futsaloka.my.id/pmobile/index.php/pegawai/deletepegawai";
        JSONObject postData = new JSONObject();


        try {
            postData.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);

                String respon_str = response.toString();

                try {
                    JSONObject jsonObject = new JSONObject(respon_str);
                    Boolean status = jsonObject.getBoolean("status");

                    if(!status == true) {
                        Toast.makeText(DeleteDataAdmin.this, "gagal load!", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(DeleteDataAdmin.this, "Data Berhasil dihapus", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(DeleteDataAdmin.this, ListKaryawanAdmin.class);
                        startActivity(intent);
                        finish();

                    }

                } catch (JSONException e) {
                    System.out.println("errorrrr broooo");
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DeleteDataAdmin.this, ListKaryawanAdmin.class);
        startActivity(intent);
        finish();
    }
}