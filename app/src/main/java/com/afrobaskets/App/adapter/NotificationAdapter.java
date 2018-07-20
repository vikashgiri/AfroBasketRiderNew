package com.afrobaskets.App.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afrobaskets.App.bean.NotificationBean;
import com.webistrasoft.org.ecommerce.R;

import java.util.List;

/**
 * Created by asdfgh on 10/13/2017.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    private List<NotificationBean> notificationBeanList;
    Context context;
    RecyclerView recyclerView;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date,heading;
       public TextView msg;

        public MyViewHolder(View view) {
            super(view);
            date=(TextView)view.findViewById(R.id.date);
            heading=(TextView)view.findViewById(R.id.heading);
            msg=(TextView)view.findViewById(R.id.msg);
        }
    }
    public NotificationAdapter(Context context, List<NotificationBean> notificationBeen, RecyclerView recyclerView) {
        this.notificationBeanList = notificationBeen;
        this.context=context;
        this.recyclerView=recyclerView;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_adapter, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.date.setText(notificationBeanList.get(position).getCreated_date());
        holder.heading.setText(notificationBeanList.get(position).getSubject());
        holder.msg.setText(notificationBeanList.get(position).getMsg());


          }
    @Override
    public int getItemCount() {
        return notificationBeanList.size();
    }

}
