package com.example.myapplication;

public class responsemodel {


    String id, device_id, sms;
    String message;

    public responsemodel() {
    }


    public responsemodel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public responsemodel(String id, String device_id, String sms) {
        this.id = id;
        this.device_id = device_id;
        this.sms = sms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }
}
