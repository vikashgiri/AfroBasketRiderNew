package com.afrobaskets.App.bean;

import java.util.ArrayList;

/**
 * Created by HP-PC on 12/25/2017.
 */

public class OrderItemListBeans {
    String id;
    String order_id;
    String merchant_product_id;
    String number_of_item;
    String amount;
    String commission_amount;
    String  discount_amount;
    String  tax_amount;
    String status;
    String created_by;
    String updated_by;
ArrayList<ProductDetailBeans>productDetailBeansArrayList;
ArrayList<ProductImageBean>productImageBeanArrayList;

    public ArrayList<ProductImageBean> getProductImageBeanArrayList() {
        return productImageBeanArrayList;
    }

    public void setProductImageBeanArrayList(ArrayList<ProductImageBean> productImageBeanArrayList) {
        this.productImageBeanArrayList = productImageBeanArrayList;
    }

    public ArrayList<ProductDetailBeans> getProductDetailBeansArrayList() {
        return productDetailBeansArrayList;
    }

    public void setProductDetailBeansArrayList(ArrayList<ProductDetailBeans> productDetailBeansArrayList) {
        this.productDetailBeansArrayList = productDetailBeansArrayList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getMerchant_product_id() {
        return merchant_product_id;
    }

    public void setMerchant_product_id(String merchant_product_id) {
        this.merchant_product_id = merchant_product_id;
    }

    public String getNumber_of_item() {
        return number_of_item;
    }

    public void setNumber_of_item(String number_of_item) {
        this.number_of_item = number_of_item;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCommission_amount() {
        return commission_amount;
    }

    public void setCommission_amount(String commission_amount) {
        this.commission_amount = commission_amount;
    }

    public String getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(String discount_amount) {
        this.discount_amount = discount_amount;
    }

    public String getTax_amount() {
        return tax_amount;
    }

    public void setTax_amount(String tax_amount) {
        this.tax_amount = tax_amount;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }
}
