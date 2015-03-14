package com.example.blin.bennytest.SMS;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Lin on 2015/3/14.
 */
public class PhoneListenerService extends BroadcastReceiver {
    String TAG = "PhoneListenerService";
    private boolean isRunning  = false;
    private Context mcontext;
    @Override
    public void onReceive(Context context, Intent intent) {
        mcontext=context;
        TelephonyManager manager =
                (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        //监听电话的状态
        manager.listen(new MyListener(), PhoneStateListener.LISTEN_CALL_STATE);
        isRunning=true;
    }

    private final class MyListener extends PhoneStateListener {
        private String num;
        private MediaRecorder recorder;
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:  /* 电话进来时 */
                    num = incomingNumber;
                    Toast.makeText(mcontext, "Incoming Call",
                            Toast.LENGTH_SHORT).show();
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK: /* 接起电话时 */
                    try {
                        File file = new File(Environment.getExternalStorageDirectory(), num + "_" + System.currentTimeMillis() + ".3gp");
                        recorder = new MediaRecorder();
                        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);//声音采集来源(话筒)
                        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);//输出的格式
                        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);//音频编码方式
                        recorder.setOutputFile(file.getAbsolutePath());//输出方向
                        recorder.prepare();
                        recorder.start();
                        Toast.makeText(mcontext, "Recording Call",
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case TelephonyManager.CALL_STATE_IDLE:  /* 无任何状态时 */
                    if (recorder != null) {
                        recorder.stop();
                        recorder.release();
                        Toast.makeText(mcontext, "Stop Call",
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

}
