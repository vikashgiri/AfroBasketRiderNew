package com.afrobaskets.App.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
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
import com.webistrasoft.org.ecommerce.databinding.SettingActivityBinding;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by HP-PC on 11/24/2017.
 */

public class SettingActivity  extends AppCompatActivity {
JSONObject sendJson;
    ProgressDialog pDialog;
    SettingActivityBinding settingActivityBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         settingActivityBinding= DataBindingUtil.setContentView(this,R.layout.setting_activity);
         ImageView back_btn=(ImageView)findViewById(R.id.toolbar_back);

        settingActivityBinding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final String old_password = settingActivityBinding.oldPassword.getText().toString();
                if (!Constant.isEmpty(old_password)) {
                    settingActivityBinding.oldPassword.setError("Enter Password");
                    return;
                }
                final String password = settingActivityBinding.txtPassword.getText().toString();
                if (!Constant.isEmpty(password)) {
                    settingActivityBinding.txtPassword.setError("Enter Password");
                    return;
                }

                if (!old_password.equalsIgnoreCase(SavePref.get_credential(SettingActivity.this,
                        SavePref.Password))) {
                    settingActivityBinding.oldPassword.setError("Enter correct old Password");
                    settingActivityBinding.oldPassword.setText(" ");
                    return;
                }

                final String cfm_password = settingActivityBinding.cfmPasword.getText().toString();
                if (!Constant.isEmpty(cfm_password)) {
                    settingActivityBinding.cfmPasword.setError("Enter Password");
                    return;
                }

                if (!password.equalsIgnoreCase(cfm_password)) {

                    settingActivityBinding.cfmPasword.setError("Password Not Matched");
                    settingActivityBinding.cfmPasword.setText("");
                    settingActivityBinding.txtPassword.setText("");
                    return;
                }
                change_password();
            }
        });
        ImageView toolbar_back=(ImageView)findViewById(R.id.toolbar_back);
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public  void change_password()
    {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        try
        {
            sendJson = new JSONObject();
            sendJson.put("method", "addEditRider");
            sendJson.put("id",SavePref.getPref(SettingActivity.this,
                  SavePref.User_id));
            sendJson.put("password",settingActivityBinding.txtPassword.getText().toString());
            /*  sendJson.put("mobile_number","");
            sendJson.put("location_id","");
            sendJson.put("fcm_reg_id","");*/
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.BASE_URL+"application/index",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObject = new JSONObject(response);
                            if (jObject.getString("status").equalsIgnoreCase("success"))
                            {
                                JSONObject Object = jObject.getJSONObject("data");
                                Iterator<String> keys = Object.keys();
                                while (keys.hasNext())
                                {
                                    SavePref.removePref(SettingActivity.this);
                                    Intent intent=new Intent(SettingActivity.this,LoginActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                pDialog.dismiss();
                                }
                            }
                            else
                            {
                                pDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(SettingActivity.this, "Communication Error!", Toast.LENGTH_SHORT).show();

                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(SettingActivity.this, "Authentication Error!", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(SettingActivity.this, "Server Side Error!", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(SettingActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(SettingActivity.this, "Parse Error!", Toast.LENGTH_SHORT).show();
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

