package com.example.responsi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListKaryawanAdmin extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_karyawan_admin);

        isiData();
    }

    private void isiData() {

        final TableLayout tableLayout = (TableLayout)this.findViewById(R.id.table_layout);

//        pegawaiArrayList = new ArrayList<>();

//        pegawaiArrayList.add(new Pegawai("Dimas Maulana", "1414370309", "123456789"));
//        pegawaiArrayList.add(new Pegawai("Fadly Yonk", "1214234560", "987654321"));
//        pegawaiArrayList.add(new Pegawai("Ariyandi Nugraha", "1214230345", "987648765"));
//        pegawaiArrayList.add(new Pegawai("Aham Siswana", "1214378098", "098758124"));

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://www.futsaloka.my.id/pmobile/index.php/Pegawai/getpegawai";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);

                String respon_str = response.toString();

                try {
                    JSONObject jsonObject = new JSONObject(respon_str);
                    Boolean status = jsonObject.getBoolean("status");

                    if(status == true) {
                        JSONArray dataPegawai = jsonObject.getJSONArray("data");


                        for(int i=0; i<dataPegawai.length(); i++) {
                            JSONObject pegawai = dataPegawai.getJSONObject(i);

                            TableRow row = (TableRow)getLayoutInflater().inflate(R.layout.list_karyawan, null);

                            TextView namaPegawai = (TextView)findViewById(R.id.nama);
                            TextView emailPegawai = (TextView)findViewById(R.id.email);
                            TextView alamatPegawai = (TextView)findViewById(R.id.alamat);

                            System.out.println(pegawai);
                            String email = pegawai.getString("email");
                            String nama = pegawai.getString("name");
                            String alamat = pegawai.getString("alamat");

                            ((TextView)row.findViewById(R.id.nama)).setText(nama);
                            ((TextView)row.findViewById(R.id.email)).setText(email);
                            ((TextView)row.findViewById(R.id.alamat)).setText(alamat);

                            tableLayout.addView(row);

                        }
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

//        pegawaiArrayList.add(new Pegawai("Dimas Maulana", "1414370309", "123456789"));
//        pegawaiArrayList.add(new Pegawai("Fadly Yonk", "1214234560", "987654321"));
//        pegawaiArrayList.add(new Pegawai("Ariyandi Nugraha", "1214230345", "987648765"));
//        pegawaiArrayList.add(new Pegawai("Aham Siswana", "1214378098", "098758124"));
    }


}