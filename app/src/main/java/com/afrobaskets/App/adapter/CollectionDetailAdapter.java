package com.afrobaskets.App.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afrobaskets.App.bean.OrderItemListBeans;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.webistrasoft.org.ecommerce.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asdfgh on 10/13/2017.
 */

public class CollectionDetailAdapter extends RecyclerView.Adapter<CollectionDetailAdapter.MyViewHolder> {
    private List<OrderItemListBeans> OrderItemListBeansArrayList;
    Context context;
    String image_url;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView items;
       public TextView quantity;
        public TextView price;
        public TextView name;

        ImageView product_image;

        public MyViewHolder(View view) {
            super(view);
            name=(TextView)  view.findViewById(R.id.txt_name);
            product_image=(ImageView)  view.findViewById(R.id.product_image);
            price=(TextView)  view.findViewById(R.id.price);
            quantity = (TextView) view.findViewById(R.id.quantity);
            items = (TextView) view.findViewById(R.id.items);


        }

    }
    public CollectionDetailAdapter(Context context, ArrayList<OrderItemListBeans> OrderItemListBeansArrayList,String image_url) {
        this.OrderItemListBeansArrayList = OrderItemListBeansArrayList;
        this.context=context;
    this.image_url=image_url;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.collectiondetailadapters, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        try {
      holder.name.setText(OrderItemListBeansArrayList.get(position).getProductDetailBeansArrayList().get(0).getProduct_name());
        holder.price.setText("GHC "+OrderItemListBeansArrayList.get(position).getAmount());
        holder.items.setText(OrderItemListBeansArrayList.get(position).getNumber_of_item()+" Items");
        holder.quantity.setText(OrderItemListBeansArrayList.get(position).getProductDetailBeansArrayList().get(0).getQuantity()+""+OrderItemListBeansArrayList.get(position).getProductDetailBeansArrayList().get(0).getUnit());
        Glide.with(context).load(image_url+"/"+OrderItemListBeansArrayList.get(position).getProductImageBeanArrayList().get(0).getType()+"/"+OrderItemListBeansArrayList.get(position).getProductImageBeanArrayList().get(0).getImage_id()+"/"+OrderItemListBeansArrayList.get(position).getProductImageBeanArrayList().get(0).getImage_name())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.product_image);}catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return OrderItemListBeansArrayList.size();
    }

}
