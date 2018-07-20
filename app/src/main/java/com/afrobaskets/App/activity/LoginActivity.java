package com.afrobaskets.App.activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.afrobaskets.App.interfaces.Constant;
import com.afrobaskets.App.interfaces.SavePref;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.webistrasoft.org.ecommerce.R;
import com.webistrasoft.org.ecommerce.databinding.LoginActivityBinding;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by HP-PC on 11/13/2017.
 */




public class LoginActivity extends AppCompatActivity {
LoginActivityBinding loginActivityBinding;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    JSONObject sendJson;
    String regId;
    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginActivityBinding = DataBindingUtil.setContentView(this, R.layout.login_activity);

loginActivityBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       // startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        login();
    }
});

        displayFirebaseRegId();

    }



    // Fetches reg id from shared preferences
    // and displays on the screen
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
         regId = pref.getString("regId", null);

           }


    @Override
    protected void onResume() {
        super.onResume();
        if (!SavePref.get_credential(LoginActivity.this,SavePref.Email).equalsIgnoreCase("0")) {
            loginActivityBinding.txtName.setText(SavePref.get_credential(LoginActivity.this,
                    SavePref.Email));
            loginActivityBinding.password.setText(SavePref.get_credential(LoginActivity.this,
                    SavePref.Password));
        }
    }

    public  void login()
    {
        String email = loginActivityBinding.txtName.getText().toString();
        if (!Constant.isEmpty(email)) {
            loginActivityBinding.txtName.setError("Enter userName");
            return;
        }

        final String password = loginActivityBinding.password.getText().toString();
        if (!Constant.isEmpty(password)) {
            loginActivityBinding.password.setError("Enter Password");
            return;
        }

        if (loginActivityBinding.checkBox.isChecked()) {
        SavePref.save_credential(LoginActivity.this,SavePref.Email,email);
        SavePref.save_credential(LoginActivity.this,SavePref.Password,password);
    }pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        try {
            sendJson = new JSONObject();
            sendJson.put("method", "riderlogin");
            sendJson.put("email",loginActivityBinding.txtName.getText().toString());
            sendJson.put("password", loginActivityBinding.password.getText().toString());
            sendJson.put("fcm_reg_id",regId);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,Constant.BASE_URL+"application",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObject = new JSONObject(response);
                            if (jObject.getString("status").equalsIgnoreCase("success")) {
                                JSONObject Object = jObject.getJSONObject("data");
                                Iterator<String> keys = Object.keys();
                                while (keys.hasNext()) {
                                    String key = keys.next();
                                    //  JSONObject innerJObject = jObject.getJSONObject(key);
                                    JSONObject attributeObject = new JSONObject(Object.getString(key));
                                    SavePref.saveStringPref(LoginActivity.this, SavePref.User_id, attributeObject.getString("id"));
                                    SavePref.saveStringPref(LoginActivity.this, SavePref.is_loogedin,"true");
                         SavePref.saveStringPref(LoginActivity.this, SavePref.Email, attributeObject.getString("email"));
                                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                    finish();
                                pDialog.dismiss();}
                            }
                            else
                            {
                                pDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Login Invalid", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            pDialog.dismiss();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(LoginActivity.this, "Communication Error!", Toast.LENGTH_SHORT).show();

                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(LoginActivity.this, "Authentication Error!", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(LoginActivity.this, "Server Side Error!", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(LoginActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(LoginActivity.this, "Parse Error!", Toast.LENGTH_SHORT).show();
                }
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String,String>();
                params.put("parameters",sendJson.toString());
                params.put("rqid",Constant.get_SHA_512_SecurePassword(Constant.salt+sendJson.toString()));

                return params;
            }
        };
        queue.add(stringRequest);
    }
    }
