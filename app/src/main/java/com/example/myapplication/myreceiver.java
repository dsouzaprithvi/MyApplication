package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.text.RelativeDateTimeFormatter;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class myreceiver extends BroadcastReceiver {

    String sendernum;
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle sms = intent.getExtras();

        if (intent.getAction().equalsIgnoreCase("android.provider.Telephony.SMS_RECEIVED")){
            Toast.makeText(context,"sms received", Toast.LENGTH_LONG).show();

            if(sms != null){
                final Object[] pdusobj = (Object[]) sms.get("pdus");
                SmsMessage[] message = new SmsMessage[pdusobj.length];
                for (int i = 0; i < message.length; i++){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        String format = sms.getString("format");
                        message[i] = SmsMessage.createFromPdu((byte[]) pdusobj[i], format);
                    }else {
                        message[i] = SmsMessage.createFromPdu((byte[]) pdusobj[i]);
                    }
                    sendernum = message[i].getOriginatingAddress();
                    String mess = message[i].getMessageBody();
                    if (sendernum == "+919901469554"){
                        Intent intent1 = new Intent("com.example.Broadcast");
                        // Data you need to pass to activity
                        intent1.putExtra("message", mess);
                        context.sendBroadcast(intent1);
                    }


                    Toast.makeText(context,sendernum +":"+ mess, Toast.LENGTH_LONG).show();
                }

            }
        }





    }

}