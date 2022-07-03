package com.example.madventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AuthorizationActivity extends AppCompatActivity {

    TextView email;
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void register(View view) {
        startActivity(new Intent(this, RegistrationActivity.class));
    }

    public void authUser(View view) {
        String url = "https://virtserver.swaggerhub.com/WS-Kazan-2019-MAD/MADventure/1.0/user/login";

        if (validation(email.getText().toString(), password.getText().toString())) {
            JSONObject json = new JSONObject();
            try {
                json.put("email", email.getText().toString());
                json.put("password", password.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getApplicationContext(), "Response: " + response.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("API", "Response: " + response.toString());
                    startActivity(new Intent(AuthorizationActivity.this, MainActivity.class));
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("API", "Error: " + error.toString());
                }
            });
            Log.d("API", "Request: "+request.toString());
            Volley.newRequestQueue(getApplicationContext()).add(request);
        }
    }

    private boolean validation(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Fields is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!email.matches("^.{1,64}@.{1,64}\\..{2,4}$")) {
            Toast.makeText(getApplicationContext(), "Wrong email\nexample: user.name@gmail.com\nexample: username@gmail.com", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}