package com.kh_sof_dev.kzajeeh;

import org.json.JSONException;
import org.json.JSONObject;

public class Request {

    private String name,status;
private Double price;
private int id;

    public Request(String name, String status, Double price) {
        this.name = name;
        this.status = status;
        this.price = price;
    }
    public Request(JSONObject jsonObject) throws JSONException {
        if(jsonObject == null){
            return;
        }
        id = jsonObject.optInt("ID");
        price = jsonObject.getDouble("Price");
        name = jsonObject.optString("Name");
        status = jsonObject.optString("Status");

    }

    public Request() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
