package com.iuea.networkoperations;

import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText name;
    EditText course;
    Button btnSubmit;
    TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting refference
        name = findViewById(R.id.editName);
        course = findViewById(R.id.editCourse);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        results = (TextView) findViewById(R.id.results);



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // declaring data types
                String Name = name.getText().toString();
                String Course = course.getText().toString();
                String url = "https://prosystemsug.com/iuea/easy.php?";
                //    name=Batama&course=Mobile
                // https://prosystemsug.com/iuea/easy.php?name=Batama&course=Mobile
                // http://127.0.0.1:8084/iuea/view-users.php


                  results.setText("Hello there ss" + Name + Course);

                    if(TextUtils.isEmpty(Name)){
                        Toast.makeText(MainActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                        name.requestFocus();
                        return;
                    }

                    if(TextUtils.isEmpty(Course)){
                        Toast.makeText(MainActivity.this, "Enter Course", Toast.LENGTH_SHORT).show();
                        course.requestFocus();
                        return;
                    }

                    try {

                    String n1 = "name=" + Name;
                    String n2 = "&course=" + Course;
                    Integer responseCode = 1;


                    HttpsURLConnection conn = (HttpsURLConnection) new URL(url).openConnection();
                    String data = n1 + n2;
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                    responseCode = conn.getResponseCode();

                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();

                    String line;
                    while ((line = rd.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    rd.close();
                    // return stringBuffer.toString();
                    Toast.makeText(getApplicationContext(), "User Addition Success: ", Toast.LENGTH_LONG).show();

                    results.setText("Name : "+Name+" Course : "+Course+" Response Code: "+responseCode);


                }
                catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error Message occurs " +e.getMessage(), Toast.LENGTH_LONG).show();
                }
                    }



        });

        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }
}