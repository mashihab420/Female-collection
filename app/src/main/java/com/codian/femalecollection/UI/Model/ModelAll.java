package com.codian.femalecollection.UI.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelAll {
    @SerializedName("id")
    @Expose
    private String id;


    @SerializedName("p_name")
    @Expose
    private String pName;

    @SerializedName("product_name")
    @Expose
    private String product_name;


    @SerializedName("p_price")
    @Expose
    private String pPrice;


    @SerializedName("product_price")
    @Expose
    private String product_price;


    @SerializedName("quantity")
    @Expose
    private String quantity;

    @SerializedName("invoice_number")
    @Expose
    private String invoice_number;

    @SerializedName("user_phone")
    @Expose
    private String user_phone;

    @SerializedName("subtotal")
    @Expose
    private String subtotal;

    @SerializedName("total")
    @Expose
    private String total;

    @SerializedName("size")
    @Expose
    private String size;

    @SerializedName("description")
    @Expose
    private String description;


    @SerializedName("email")
    @Expose
    private String email;


    @SerializedName("category")
    @Expose
    private String categories;


    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("name")
    @Expose
    private String Name;



    @SerializedName("phone")
    @Expose
    private String Phone;


    @SerializedName("date")
    @Expose
    private String date;




    @SerializedName("password")
    @Expose
    private String Password;


    @SerializedName("address")
    @Expose
    private String Address;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpPrice() {
        return pPrice;
    }

    public void setpPrice(String pPrice) {
        this.pPrice = pPrice;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    public String getPPrice() {
        return pPrice;
    }

    public void setPPrice(String pPrice) {
        this.pPrice = pPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
}
