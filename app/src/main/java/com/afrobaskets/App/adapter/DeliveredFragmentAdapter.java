package com.afrobaskets.App.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afrobaskets.App.activity.DeliveryDetailsActivity;
import com.afrobaskets.App.bean.CategoriesBean;
import com.afrobaskets.App.bean.OrderCollectionBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.webistrasoft.org.ecommerce.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hh on 13-Nov-17.
 */

public class DeliveredFragmentAdapter extends RecyclerView.Adapter<DeliveredFragmentAdapter.MyViewHolder> {
    private List<CategoriesBean> foodList;
    Context context;
    ArrayList<OrderCollectionBean> orderCollectionBeen;
RecyclerView recyclerView;


        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView date,merchent_name,merchent_address,total_item;

            ImageView image;

        public MyViewHolder(View view) {
            super(view);
            merchent_name=(TextView)view.findViewById(R.id.merchent_name);
            merchent_address=(TextView)view.findViewById(R.id.merchent_address);
            total_item=(TextView)view.findViewById(R.id.total_item);
            date=(TextView)view.findViewById(R.id.txt_p_date);
        }

    }
    public DeliveredFragmentAdapter(Context context,     ArrayList<OrderCollectionBean> orderCollectionBeen,RecyclerView recyclerView) {
        this.orderCollectionBeen=orderCollectionBeen;
        this.context=context;
        this.recyclerView=recyclerView;
    }

    @Override
    public DeliveredFragmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.collectionfragmentadapter, parent, false);
       itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DeliveryDetailsActivity.class);
                Gson gson = new Gson();
                Type type = new TypeToken<List<OrderCollectionBean>>() {}.getType();
                String json = gson.toJson(orderCollectionBeen, type);
                intent.putExtra("data",json);

                intent.putExtra("position",""+recyclerView.getChildAdapterPosition(itemView));


                context.startActivity(intent);
            }
        });
        return new DeliveredFragmentAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DeliveredFragmentAdapter.MyViewHolder holder, int position) {
        holder.merchent_address.setText(orderCollectionBeen.get(position).getShippingAddressListBeansArrayList().get(0).getHouse_number()+","+orderCollectionBeen.get(position).getShippingAddressListBeansArrayList().get(0).getStreet_detail()+","
        +orderCollectionBeen.get(position).getShippingAddressListBeansArrayList().get(0).getLandmark()+","+orderCollectionBeen.get(position).getShippingAddressListBeansArrayList().get(0).getCity_name());
        holder.merchent_name.setText(orderCollectionBeen.get(position).getShippingAddressListBeansArrayList().get(0).getAddress_nickname());
        holder.date.setText(orderCollectionBeen.get(position).getCreated_date());
        holder.total_item.setText(""+orderCollectionBeen.get(position).getOrderItemListBeansArrayList().size()+" Items");
    }
    @Override
    public int getItemCount() {
        return orderCollectionBeen.size();
    }

}
