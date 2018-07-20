package com.afrobaskets.App.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afrobaskets.App.activity.CollectionDetailActivity;
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

public class CollectionHistoryAdapter extends RecyclerView.Adapter<CollectionHistoryAdapter.MyViewHolder> {
    Context context;
    RecyclerView recyclerView;

    ArrayList<OrderCollectionBean> orderCollectionBeen;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date, merchent_name, merchent_address, total_item;
        ImageView image;

        public MyViewHolder(View view) {
            super(view);
            merchent_name = (TextView) view.findViewById(R.id.merchent_name);
            merchent_address = (TextView) view.findViewById(R.id.merchent_address);
            total_item = (TextView) view.findViewById(R.id.total_item);
            date = (TextView) view.findViewById(R.id.txt_p_date);

        }

    }

    public CollectionHistoryAdapter(Context context, ArrayList<OrderCollectionBean> orderCollectionBeen, RecyclerView recyclerView) {
        this.orderCollectionBeen = orderCollectionBeen;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    public CollectionHistoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.collectionfragmentadapter, parent, false);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<OrderCollectionBean>>() {
                }.getType();
                String json = gson.toJson(orderCollectionBeen, type);
                Intent intent = new Intent(context, CollectionDetailActivity.class);
                intent.putExtra("data", json);
                intent.putExtra("type", "history");
                intent.putExtra("position", "" + recyclerView.getChildAdapterPosition(itemView));
                context.startActivity(intent);
            }
        });
        return new CollectionHistoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CollectionHistoryAdapter.MyViewHolder holder, int position) {
        holder.merchent_address.setText(orderCollectionBeen.get(position).getStoreListBeansArrayList().get(0).getAddress());
        holder.merchent_name.setText(orderCollectionBeen.get(position).getStoreListBeansArrayList().get(0).getStore_name());
        holder.date.setText(orderCollectionBeen.get(position).getCreated_date());
        holder.total_item.setText("" + orderCollectionBeen.get(position).getOrderItemListBeansArrayList().size()+"Items");
    }

    @Override
    public int getItemCount() {
        return orderCollectionBeen.size();
    }
}