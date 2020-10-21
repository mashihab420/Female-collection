package com.codian.femalecollection.UI.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cartitem")
public class ModelCartRoom {

    @PrimaryKey(autoGenerate = true)
    int id;
    String p_name;
    String p_price;
    String quantity;
    String url;
    String size;

    public ModelCartRoom(){

    }

    public ModelCartRoom(String p_name, String p_price, String quantity, String url, String size) {
        this.p_name = p_name;
        this.p_price = p_price;
        this.quantity = quantity;
        this.url = url;
        this.size = size;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_price() {
        return p_price;
    }

    public void setP_price(String p_price) {
        this.p_price = p_price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
