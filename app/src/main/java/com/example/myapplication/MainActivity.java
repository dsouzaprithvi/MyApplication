package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {



    RecyclerView recyclerView;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        error = findViewById(R.id.error);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        


        processdata1();
        search_sms_by_number("+911234567890");

        // Inside OnCreate Method





// Add this inside your class



    }

//    private void processdata() {
//
//        Call<List<responsemodel>> call = apicontroller
//                .getInstance()
//                .getapi()
//                .getdata();
//
//        call.enqueue(new Callback<List<responsemodel>>() {
//            @Override
//            public void onResponse(Call<List<responsemodel>> call, Response<List<responsemodel>> response) {
//
//                List<responsemodel> data = response.body();
//                smsadapter adapter = new smsadapter(data);
//                recyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<responsemodel>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
//
//            }
//        });
//    }

    void processdata1(){
        String device_id = "+919901469554";

        Call<List<responsemodel>> call = apicontroller
                .getInstance()
                .getapi()
                .getdata(device_id);

        call.enqueue(new Callback<List<responsemodel>>() {
            @Override
            public void onResponse(Call<List<responsemodel>> call, Response<List<responsemodel>> response) {
                List<responsemodel> data = response.body();


                smsadapter adapter = new smsadapter(data);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<responsemodel>> call, Throwable t) {
                error.setText(t.toString());
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    void processdata() {



        Call<List<responsemodel>> call2 = apicontroller
                .getInstance()
                .getapi()
                .get();

        call2.enqueue(new Callback<List<responsemodel>>() {
            @Override
            public void onResponse(Call<List<responsemodel>> call, Response<List<responsemodel>> response) {
                List<responsemodel> data = response.body();


                smsadapter adapter = new smsadapter(data);
                recyclerView.setAdapter(adapter);




            }

            @Override
            public void onFailure(Call<List<responsemodel>> call, Throwable t) {
                error.setText(t.toString());
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });



    }

    public void search_sms_by_number(String device){

        ContentResolver cResolver = getContentResolver();
        Cursor smsInboxCursor = cResolver.query(Uri.parse("content://sms/inbox"),
                null ,null, null, null);


        int indexBody = smsInboxCursor.getColumnIndex("body");
        int indexAddress = smsInboxCursor.getColumnIndex("address");
        int indexDate = smsInboxCursor.getColumnIndex("date");

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat tf = new SimpleDateFormat("HH:mm");



        if(indexBody <0 || !smsInboxCursor.moveToFirst())
            return;



        do {

            String date, time, body, sender;
            sender = smsInboxCursor.getString(indexAddress);
            body = smsInboxCursor.getString(indexBody).toString();
            date = df.format(smsInboxCursor.getLong(indexDate));
            time = tf.format(smsInboxCursor.getLong(indexDate));


            if (device.equals(sender)){

                String[] smsbody = body.split("\n");
                if(smsbody[0].matches("ON") || smsbody[0].matches("OFF")
                        || smsbody[0].matches("TON") || smsbody[0].matches("FAULTY")){
                    String trip_id = smsbody[8];

                    Log.i("message searching -->", body);
                    storesms(sender, body);

                }

            }
        }while (smsInboxCursor.moveToNext());



    }

    private void storesms(String sender, String body) {

        Call<responsemodel> call3 = apicontroller
                .getInstance()
                .getapi()
                .getmessage(sender, body);

        call3.enqueue(new Callback<responsemodel>() {
            @Override
            public void onResponse(Call<responsemodel> call, Response<responsemodel> response) {
                responsemodel mess = response.body();
                if (mess.getMessage().equals("inserted")){
                    Toast.makeText(getApplicationContext(), "sms inserted", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<responsemodel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }




}
