package com.example.courseapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4,ed5;
    AppCompatButton b1;
    String getTitle,getDesc,getVenue,getDate,getDuration;
    String apiUrl="http://mountzioncollege.herokuapp.com/addcourse";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText) findViewById(R.id.title);
        ed2=(EditText) findViewById(R.id.desc);
        ed3=(EditText) findViewById(R.id.venue);
        ed4=(EditText) findViewById(R.id.date);
        ed5=(EditText) findViewById(R.id.duration);
        b1=(AppCompatButton) findViewById(R.id.submit);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getTitle=ed1.getText().toString();
               getDesc=ed2.getText().toString();
               getVenue=ed3.getText().toString();
               getDate=ed4.getText().toString();
               getDuration=ed5.getText().toString();

                StringRequest sr=new StringRequest(Request.Method.POST,
                        apiUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                ed1.setText("");
                                ed2.setText("");
                                ed3.setText("");
                                ed4.setText("");
                                ed5.setText("");

                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();

                            }
                        })
                {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        HashMap<String,String> params=new HashMap<>();
                        params.put("courseDuration",getDuration);
                        params.put("courseTitle",getTitle);
                        params.put("courseVenue",getVenue);
                        params.put("courseDate",getDate);
                        params.put("courseDescription",getDesc);
                        return params;
                    }
                };
                RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
                rq.add(sr);

            }
        });
    }
}