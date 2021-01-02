package com.example.responsi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class Login_activity extends AppCompatActivity {

    Button lgnBtn;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        lgnBtn = (Button)findViewById(R.id.login_btn);

        lgnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameInput = findViewById(R.id.username);
                EditText passwordInput = findViewById(R.id.password);

                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                getUsername(username, password);

            }
        });

    }


    protected void getUsername(final String username, String password) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://www.futsaloka.my.id/pmobile/index.php/pegawai/login";
        JSONObject postData = new JSONObject();


        try {
            postData.put("username", username);
            postData.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);

                String respon_str = response.toString();

                try {
                    JSONObject jsonObject = new JSONObject(respon_str);
                    Boolean status = jsonObject.getBoolean("status");

                    if(status != true) {
                        Toast.makeText(Login_activity.this, "salah login", Toast.LENGTH_SHORT).show();
                    } else {

                        JSONObject dataUser = jsonObject.getJSONObject("data");
                        int user_type = dataUser.getInt("level");

                        if(user_type == 1) {
                            Intent goIntent = new Intent(Login_activity.this, HalamanAdmin.class);
                            startActivity(goIntent);
                            finish();
                        } else {
                            Intent goIntent = new Intent(Login_activity.this, HalamanPegawai.class);
                            startActivity(goIntent);
                            finish();
                            Toast.makeText(Login_activity.this, "bukan admin", Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (JSONException e) {
                    System.out.println("errorrrr broooo");
                    Toast.makeText(Login_activity.this, "Username atau sandi anda salah!", Toast.LENGTH_SHORT).show();
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

    public void pindah() {
        Intent goIntent = new Intent(this, MainActivity.class);
        startActivity(goIntent);
    }

}