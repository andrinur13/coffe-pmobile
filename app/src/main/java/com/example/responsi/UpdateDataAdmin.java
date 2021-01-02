package com.example.responsi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class UpdateDataAdmin extends AppCompatActivity {

    int levelOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_admin);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UpdateDataAdmin.this, ListKaryawanAdmin.class);
        startActivity(intent);
        finish();
    }

    public void updateData(View view) {

        EditText id = (EditText)findViewById(R.id.id);
        EditText nama = (EditText)findViewById(R.id.name);
        EditText alamat = (EditText)findViewById(R.id.alamat);
        EditText email = (EditText)findViewById(R.id.email);
        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);
        RadioGroup level = (RadioGroup)findViewById(R.id.level);
        RadioButton admin = (RadioButton)findViewById(R.id.admin);
        RadioButton bukanadmin = (RadioButton)findViewById(R.id.bukanadmin);

        if(admin.isChecked()) {
            levelOption = 1;
        } else if(bukanadmin.isChecked()) {
            levelOption = 2;
        }


        String id_txt = id.getText().toString();
        String nama_txt = nama.getText().toString();
        String alamat_txt = alamat.getText().toString();
        String email_txt = email.getText().toString();
        String username_txt = username.getText().toString();
        String password_txt = password.getText().toString();
        String level_txt = String.valueOf(new Integer(levelOption));

        // Toast.makeText(this, id_txt + nama_txt + alamat_txt + email_txt + email_txt + username_txt + password_txt + level_txt, Toast.LENGTH_SHORT).show();

        prosesAPI(id_txt, nama_txt, alamat_txt, email_txt, username_txt, password_txt, level_txt);

    }

    public void prosesAPI(String id, String nama, String alamat, String email, String username, String password, String level) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://www.futsaloka.my.id/pmobile/index.php/pegawai/editpegawai";
        JSONObject postData = new JSONObject();


        try {
            postData.put("id", id);
            postData.put("name", nama);
            postData.put("alamat", alamat);
            postData.put("email", email);
            postData.put("username", username);
            postData.put("password", password);
            postData.put("level", level);
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
                    String status = jsonObject.getString("status");

                    if(!status.equals("success")) {
                        Toast.makeText(UpdateDataAdmin.this, "gagal load!", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(UpdateDataAdmin.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(UpdateDataAdmin.this, ListKaryawanAdmin.class);
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
}