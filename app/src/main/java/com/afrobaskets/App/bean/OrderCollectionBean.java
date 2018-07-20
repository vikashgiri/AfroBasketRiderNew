package com.afrobaskets.App.bean;

import java.util.ArrayList;

/**
 * Created by HP-PC on 12/25/2017.
 */

public class OrderCollectionBean {
    String id;
    String rider_id;
    String order_id;
    String status;
    String created_date;
    String store_id;
    String shipping_address_id;
    String order_status;
    String imageRootPath;
    ArrayList<ShippingAddressListBeans>shippingAddressListBeansArrayList;
    ArrayList<OrderItemListBeans>orderItemListBeansArrayList;
    ArrayList<StoreListBeans>storeListBeansArrayList;
    ArrayList<UserDetail>userDetailArrayList;
String payable_amount;

    public String getPayable_amount() {
        return payable_amount;
    }

    public void setPayable_amount(String payable_amount) {
        this.payable_amount = payable_amount;
    }

    public ArrayList<UserDetail> getUserDetailArrayList() {
        return userDetailArrayList;
    }

    public void setUserDetailArrayList(ArrayList<UserDetail> userDetailArrayList) {
        this.userDetailArrayList = userDetailArrayList;
    }

    public String getImageRootPath() {
        return imageRootPath;
    }

    public void setImageRootPath(String imageRootPath) {
        this.imageRootPath = imageRootPath;
    }

    public ArrayList<ShippingAddressListBeans> getShippingAddressListBeansArrayList() {
        return shippingAddressListBeansArrayList;
    }

    public void setShippingAddressListBeansArrayList(ArrayList<ShippingAddressListBeans> shippingAddressListBeansArrayList) {
        this.shippingAddressListBeansArrayList = shippingAddressListBeansArrayList;
    }

    public ArrayList<OrderItemListBeans> getOrderItemListBeansArrayList() {
        return orderItemListBeansArrayList;
    }

    public void setOrderItemListBeansArrayList(ArrayList<OrderItemListBeans> orderItemListBeansArrayList) {
        this.orderItemListBeansArrayList = orderItemListBeansArrayList;
    }

    public ArrayList<StoreListBeans> getStoreListBeansArrayList() {
        return storeListBeansArrayList;
    }

    public void setStoreListBeansArrayList(ArrayList<StoreListBeans> storeListBeansArrayList) {
        this.storeListBeansArrayList = storeListBeansArrayList;
    }
String payment_status;

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }
    String delivery_date;

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRider_id() {
        return rider_id;
    }

    public void setRider_id(String rider_id) {
        this.rider_id = rider_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_date()
    {
        return created_date;
    }

    public void setCreated_date(String created_date)
    {
        this.created_date = created_date;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getShipping_address_id() {
        return shipping_address_id;
    }

    public void setShipping_address_id(String shipping_address_id) {
        this.shipping_address_id = shipping_address_id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}
