package com.example.blin.bennytest.MEDIA;

import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.blin.bennytest.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MediaRecordActivity extends ActionBarActivity {
    MediaRecorder mediaRecorder ;
    private ArrayList recordFiles;
    private boolean sdCardExit;
    private boolean isStopRecord;
    private File myRecAudioDir;// 得到Sd卡path
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_record);
        mediaRecorder = new MediaRecorder();
        /* 判断SD Card是否插入 */
        sdCardExit = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);

    /* 取得SD Card路径作为录音的文件位置 */
        if (sdCardExit)
        {
            myRecAudioDir = Environment.getExternalStorageDirectory();
        }

//        record();
    }

    /**
     * 开始录制
     */
    private void record(){
        /**
         * mediaRecorder.setAudioSource设置声音来源。
         * MediaRecorder.AudioSource这个内部类详细的介绍了声音来源。
         * 该类中有许多音频来源，不过最主要使用的还是手机上的麦克风，MediaRecorder.AudioSource.MIC
         */
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        /**
         * mediaRecorder.setOutputFormat代表输出文件的格式。该语句必须在setAudioSource之后，在prepare之前。
         * OutputFormat内部类，定义了音频输出的格式，主要包含MPEG_4、THREE_GPP、RAW_AMR……等。
         */
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        /**
         * mediaRecorder.setAddioEncoder()方法可以设置音频的编码
         * AudioEncoder内部类详细定义了两种编码：AudioEncoder.DEFAULT、AudioEncoder.AMR_NB
         */
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        /**
         * 设置录音之后，保存音频文件的位置
         */
        mediaRecorder.setOutputFile(Environment.getExternalStorageDirectory()+ File.separator+"a.amr");

        /**
         * 调用start开始录音之前，一定要调用prepare方法。
         */
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        }
        catch (IllegalStateException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void StartRec(View V)
    {
        record();
        Toast.makeText(getApplicationContext(), "RecordStart",
                Toast.LENGTH_SHORT).show();
    }
    public void StopRec(View V)
    {
        Toast.makeText(getApplicationContext(), "RecordStop",
                Toast.LENGTH_SHORT).show();
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder=null;

    }
    public void PlayRec(View V)
    {
        String FileName=Environment.getExternalStorageDirectory()+ File.separator+"a.amr";
        try {
            File myPlayFile = new File(FileName);
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(android.content.Intent.ACTION_VIEW);
            String type = getMIMEType(myPlayFile);
            intent.setDataAndType(Uri.fromFile(myPlayFile), type);
            startActivity(intent);
        }
        catch(Exception E)
        {

        }

        Toast.makeText(getApplicationContext(), "PlayRec",
                Toast.LENGTH_SHORT).show();
    }
    // 存储一个音频文件数组到list当中
    private void getRecordFiles()
    {
        recordFiles = new ArrayList();
        if (sdCardExit)
        {
            File files[] = myRecAudioDir.listFiles();
            if (files != null)
            {
                for (int i = 0; i < files.length; i++)
                {
                    if (files[i].getName().indexOf(".") >= 0)
                    {
            /* 只取.amr文件 */
                        String fileS = files[i].getName().substring(
                                files[i].getName().indexOf("."));
                        if (fileS.toLowerCase().equals(".amr"))
                            recordFiles.add(files[i].getName());
                    }
                }
            }
        }
    }

    private String getMIMEType(File f)
    {
        String end = f
                .getName()
                .substring(f.getName().lastIndexOf(".") + 1,
                        f.getName().length()).toLowerCase();
        String type = "";
        if (end.equals("mp3") || end.equals("aac") || end.equals("aac")
                || end.equals("amr") || end.equals("mpeg")
                || end.equals("mp4"))
        {
            type = "audio";
        } else if (end.equals("jpg") || end.equals("gif")
                || end.equals("png") || end.equals("jpeg"))
        {
            type = "image";
        } else
        {
            type = "*";
        }
        type += "/*";
        return type;
    }

    /***
     * 此外，还有和MediaRecorder有关的几个参数与方法，我们一起来看一下：
     * sampleRateInHz :音频的采样频率，每秒钟能够采样的次数，采样率越高，音质越高。
     * 给出的实例是44100、22050、11025但不限于这几个参数。例如要采集低质量的音频就可以使用4000、8000等低采样率
     *
     * channelConfig ：声道设置：android支持双声道立体声和单声道。MONO单声道，STEREO立体声
     *
     * recorder.stop();停止录音
     * recorder.reset();  重置录音 ，会重置到setAudioSource这一步
     * recorder.release(); 解除对录音资源的占用
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_media_record, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
