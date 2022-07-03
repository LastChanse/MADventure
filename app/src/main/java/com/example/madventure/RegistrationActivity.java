package com.example.madventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationActivity extends AppCompatActivity {

    EditText email;
    EditText name;
    EditText password;
    EditText repeatPassword;
    EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        email = (EditText) findViewById(R.id.email);
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        repeatPassword = (EditText) findViewById(R.id.repeatPassword);
        phone = (EditText) findViewById(R.id.phone);
    }

    public void login(View view) {
        startActivity(new Intent(this, AuthorizationActivity.class));
    }

    public void registerUser(View view) {
        if (validation(email.getText().toString(),name.getText().toString(),password.getText().toString(), repeatPassword.getText().toString(),phone.getText().toString())) {
            JSONObject json = new JSONObject();
            try {
                json.put("email", email.getText().toString());
                json.put("nickName", name.getText().toString());
                json.put("password", password.getText().toString());
                json.put("phone", phone.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String url = "https://virtserver.swaggerhub.com/WS-Kazan-2019-MAD/MADventure/1.0/users";

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, url, json, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(getApplicationContext(), "Response: " + response.toString(), Toast.LENGTH_SHORT).show();
                            Log.d("API", "Response: " + response.toString());
                            startActivity(new Intent(RegistrationActivity.this, AuthorizationActivity.class));
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (!error.toString().contains("JSONException")) {
                                Log.e("API", error.toString());
                            } else {
                                Toast.makeText(getApplicationContext(), "Succes", Toast.LENGTH_SHORT).show();
                                Log.d("API", "Response: OK 200 [JSONException]");
                                startActivity(new Intent(RegistrationActivity.this, AuthorizationActivity.class));
                            }

                        }
                    });

            Log.d("API", "Request: "+jsonObjectRequest.toString());
            // Access the RequestQueue through your singleton class.
            Volley.newRequestQueue(this.getApplicationContext()).add(jsonObjectRequest);
        }
    }

    private boolean validation(String email, String name, String password, String repeatPassword, String number) {
        if (email.isEmpty() || name.isEmpty() || password.isEmpty() || repeatPassword.isEmpty() || number.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Fields is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!email.matches("^.{1,64}@.{1,64}\\..{2,4}$")) {
            Toast.makeText(getApplicationContext(), "Email is wrong\nexample: user.name@gmail.com\nexample: username@gmail.com", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(repeatPassword)) {
            Toast.makeText(getApplicationContext(), "Passwords is not equals", Toast.LENGTH_SHORT).show();
            return false;
        }
            return true;
    }
}