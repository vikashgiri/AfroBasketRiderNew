package com.afrobaskets.App.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.afrobaskets.App.adapter.CollectionDetailAdapter;
import com.afrobaskets.App.bean.CartBean;
import com.afrobaskets.App.bean.OrderCollectionBean;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.webistrasoft.org.ecommerce.R;
import com.webistrasoft.org.ecommerce.databinding.CollectiondetailactivityBinding;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HP-PC on 11/20/2017.
 */

public class CollectionDetailActivity extends AppCompatActivity {
    private List<CartBean> foodList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CollectionDetailAdapter fAdapter;
    ProgressDialog pDialog;
    ArrayList<OrderCollectionBean>orderCollectionBeen;
    JSONObject sendJson;
    CollectiondetailactivityBinding collectiondetailactivityBinding;
    int position;
    ImageView back;
    public void onCall() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    123);
        } else {
            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+orderCollectionBeen.get(position).getUserDetailArrayList().get(0).getMobile_number().toString())));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         collectiondetailactivityBinding= DataBindingUtil.setContentView(this,R.layout.collectiondetailactivity);
        Gson gson = new Gson();
        collectiondetailactivityBinding.phoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCall();
            }
        });
        Type type = new TypeToken<List<OrderCollectionBean>>() {}.getType();
         orderCollectionBeen = gson.fromJson(getIntent().getStringExtra("data"), type);
         position=Integer.parseInt(getIntent().getStringExtra("position"));
        collectiondetailactivityBinding.phoneNumber.setText(orderCollectionBeen.get(position).getUserDetailArrayList().get(0).getMobile_number());
        collectiondetailactivityBinding.shippingDetails.setText(Html.fromHtml("<b>Delivery Address</b><br>"+orderCollectionBeen.get(position).getShippingAddressListBeansArrayList().get(0).getAddress_nickname()+"<br>"+orderCollectionBeen.get(position).getShippingAddressListBeansArrayList().get(0).getHouse_number()+","+orderCollectionBeen.get(position).getShippingAddressListBeansArrayList().get(0).getStreet_detail()+","+orderCollectionBeen.get(position).getShippingAddressListBeansArrayList().get(0).getCity_name()));
        String emailString =orderCollectionBeen.get(position).getUserDetailArrayList().get(0).getEmail();
        collectiondetailactivityBinding.mail.setText(Html.fromHtml(emailString));

        if(getIntent().hasExtra("type"))
        {
            collectiondetailactivityBinding.orderDeliverd.setVisibility(View.GONE);
        }
        String deliverySourceStrings = "<b>Delivery Time</b> \n"+orderCollectionBeen.get(position).getDelivery_date();
        collectiondetailactivityBinding.deliveryTime.setText(Html.fromHtml(deliverySourceStrings));

       /* collectiondetailactivityBinding.deliveryTime.setText("Delivery Time:\n" + orderCollectionBeen.get(position).getDelivery_date());*/
        if(orderCollectionBeen.get(position).getPayment_status().equalsIgnoreCase("unpaid")) {
            //collectiondetailactivityBinding.paymentMode.setText("Cash");
            String sourceString = "<b>Payment Mode</b>    Cash";
            collectiondetailactivityBinding.paymentMode.setText(Html.fromHtml(sourceString));

        }
        else
        {
            String sourceString = "<b>Payment Mode</b>    Razorpay";
            collectiondetailactivityBinding.paymentMode.setText(Html.fromHtml(sourceString));

            //collectiondetailactivityBinding.paymentMode.setText("Razorpay");

        }


        collectiondetailactivityBinding.merchentDetails.setText(Html.fromHtml("<b>Merchant Store Address</b>   <br>"+orderCollectionBeen.get(position).getStoreListBeansArrayList().get(0).getStore_name()+"<br>"+orderCollectionBeen.get(position).getStoreListBeansArrayList().get(0).getAddress()));

        String sourceStrings = "<b>order id: </b>"+orderCollectionBeen.get(position).getOrder_id();
        collectiondetailactivityBinding.orderId.setText(Html.fromHtml(sourceStrings));

       // collectiondetailactivityBinding.orderId.setText("order id:"+orderCollectionBeen.get(position).getOrder_id());
        collectiondetailactivityBinding.date.setText(orderCollectionBeen.get(position).getCreated_date());
         recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        back = (ImageView) findViewById(R.id.toolbar_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fAdapter = new CollectionDetailAdapter(CollectionDetailActivity.this, orderCollectionBeen.get(position).getOrderItemListBeansArrayList(),orderCollectionBeen.get(position).getImageRootPath());
        String cghStrings = "<b>GHC </b>"+orderCollectionBeen.get(position).getPayable_amount();
        collectiondetailactivityBinding.payable.setText(Html.fromHtml(cghStrings));

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(fAdapter);
        Button order_deliver=(Button)findViewById(R.id.order_deliverd);

        order_deliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 deliverd(CollectionDetailActivity.this);
            }
        });
    }


    void deliverd(final Context context)
    {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        try {
            sendJson = new JSONObject();
            sendJson.put("method", "updateorderbyrider");
            sendJson.put("rider_id", SavePref.getPref(CollectionDetailActivity.this,SavePref.User_id));
            sendJson.put("order_id", orderCollectionBeen.get(position).getOrder_id());
            sendJson.put("order_status", "dispatched");

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.BASE_URL+"application/customer",

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                                                try {
                                JSONObject Object = new JSONObject(response);
                                if (Object.getString("status").equalsIgnoreCase("success")) {
                                    startActivity(new Intent(CollectionDetailActivity.this, HomeActivity.class));
                                    Toast.makeText(getApplicationContext(),"Status  Updated",Toast.LENGTH_SHORT).show();
                                    finish();
                                    HomeActivity.activity.finish();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(),"Status Not Updated",Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                                    e.printStackTrace();
                            }
                        pDialog.dismiss();

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(context, "Communication Error!", Toast.LENGTH_SHORT).show();

                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(context, "Authentication Error!", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(context, "Server Side Error!", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(context, "Network Error!", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(context, "Parse Error!", Toast.LENGTH_SHORT).show();
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