package com.example.blin.bennytest.SMS;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.example.blin.benlib.BenGps;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lin on 2015/3/14.
 */
public class SmsReceiver extends BroadcastReceiver  {
    Context mcontext;
    @Override
    public void onReceive(Context context, Intent intent) {
        mcontext=context;
        // 判断是系统短信；
        if (intent.getAction()
                .equals("android.provider.Telephony.SMS_RECEIVED")) {
            // 不再往下传播；
            this.abortBroadcast();
            StringBuffer sb = new StringBuffer();
            String sender = null;
            String content = null;
            String sendtime = null;
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                // 通过pdus获得接收到的所有短信消息，获取短信内容；
                Object[] pdus = (Object[]) bundle.get("pdus");
                // 构建短信对象数组；
                SmsMessage[] mges = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    // 获取单条短信内容，以pdu格式存,并生成短信对象；
                    mges[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                for (SmsMessage mge : mges) {
                    sb.append("短信来自：" + mge.getDisplayOriginatingAddress()
                            + "\n");
                    sb.append("短信内容：" + mge.getMessageBody());

                    sender = mge.getDisplayOriginatingAddress();// 获取短信的发送者
                    content = mge.getMessageBody();// 获取短信的内容
                    Date date = new Date(mge.getTimestampMillis());
                    SimpleDateFormat format = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss");
                    sendtime = format.format(date);// 获取短信发送时间；
                    SmsManager manager = SmsManager.getDefault();
                    /*manager.sendTextMessage(sender,
                            null,"发送人:"+sender+"-----发送时间:"+sendtime+"----内容:"+content,
                            null, null);*/

                   /* manager.sendTextMessage(sender,
                            null,"Sender:"+sender+"-----Time:"+sendtime+"----Content:"+content,
                            null, null);*/
                    try {
                        String s1 = "发送人:" + sender + "-----发送时间:" + sendtime + "----内容:" + content;
                        /*byte ptext[] = s1.getBytes("ISO-8859-1");
                        String value = new String(ptext, "UTF_8");*/
                       /* ArrayList<String> parts = manager.divideMessage(s1);
                        manager.sendMultipartTextMessage(sender, null, parts, null, null);
*/
                           manager.sendTextMessage(sender,
                            null,"Sender:"+sender+"-----Time:"+sendtime+"----Content:"+content,
                            null, null);

                        Log.i("Test","Done!");
                    }
                    catch (Exception E)
                    {
                        Log.i("Test",E.toString());
                    }
                    //把拦截到的短信发送到指定的手机，此处为5556;
                    // if ("5556".equals(sender)){
                    // //屏蔽手机号为5556的短信，这里还可以时行一些处理，如把该信息发送到第三人的手机等等。
                    // abortBroadcast();
                    // }
                }

                Toast.makeText(context, sb.toString()+"\n"+GetGPSStr(), Toast.LENGTH_LONG)
                        .show();
            }


        }

    }
    private String GetGPSStr()
    {
        BenGps.GPSService mGPSService = new BenGps.GPSService(mcontext);
        mGPSService.getLocation();
        double latitude = mGPSService.getLatitude();
        double longitude = mGPSService.getLongitude();
        if (mGPSService.isGPSEnabled== false) {
         return "GPS Service not Available!";
        }
        else
        {
        return "Latitude: " + latitude + " \nLongitude: " + longitude;}
    }
}
