package com.example.blin.bennytest.SMS;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.blin.bennytest.R;
/*
所有文件夹：content://sms/all
        收件箱：content://sms/inbox
        已发送：content://sms/sent
        草稿：content://sms/draft
        发件箱：content://sms/outbox
        发送失败：content://sms/failed
        排队消息:content://sms/queued
        未送达：content://sms/undelivered

        对话：content://sms/conversations
*/
//test
public class SMSRdInbox extends Activity implements View.OnClickListener {
    private static final Uri SMS_INBOX = Uri.parse("content://sms/inbox");
    //  GUI Widget
    Button btnSent, btnInbox, btnDraft;
    TextView lblMsg, lblNo;
    ListView lvMsg;
    String TAG="SMSRdInbox";

    // Cursor Adapter
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsrd_inbox);

        // Init GUI Widget
        btnInbox = (Button) findViewById(R.id.btnInbox);
        btnInbox.setOnClickListener(this);

        btnSent = (Button)findViewById(R.id.btnSentBox);
        btnSent.setOnClickListener(this);

        btnDraft = (Button)findViewById(R.id.btnDraft);
        btnDraft.setOnClickListener(this);

        lvMsg = (ListView) findViewById(R.id.lvMsg);

    }

    @Override
    public void onClick(View v) {

        if (v == btnInbox) {

            // Create Inbox box URI
            Uri inboxURI = Uri.parse("content://sms/inbox");

            // List required columns
            String[] reqCols = new String[] { "_id", "address", "body" };

            // Get Content Resolver object, which will deal with Content Provider
            ContentResolver cr = getContentResolver();

            // Fetch Inbox SMS Message from Built-in Content Provider
            Cursor c = cr.query(inboxURI, reqCols, null, null, null);
            if(c.getCount()>0) {
                c.moveToFirst();
                Log.i(TAG, c.getString(c.getColumnIndex("address")));
            }
//            Log.i(TAG,"Test");
            // Attached Cursor with adapter and display in listview
            adapter = new SimpleCursorAdapter(this, R.layout.row, c,
                    new String[] { "body", "address" }, new int[] {
                    R.id.lblMsg, R.id.lblNumber });
            lvMsg.setAdapter(adapter);

        }

        if(v==btnSent)
        {

            // Create Sent box URI
            Uri sentURI = Uri.parse("content://sms/sent");

            // List required columns
            String[] reqCols = new String[] { "_id", "address", "body" };

            // Get Content Resolver object, which will deal with Content Provider
            ContentResolver cr = getContentResolver();

            // Fetch Sent SMS Message from Built-in Content Provider
            Cursor c = cr.query(sentURI, reqCols, null, null, null);
            // Attached Cursor with adapter and display in listview
            adapter = new SimpleCursorAdapter(this, R.layout.row, c,
                    new String[] { "body", "address" }, new int[] {
                    R.id.lblMsg, R.id.lblNumber });
            lvMsg.setAdapter(adapter);

        }

        if(v==btnDraft)
        {
            // Create Draft box URI
            Uri draftURI = Uri.parse("content://sms/draft");

            // List required columns
            String[] reqCols = new String[] { "_id", "address", "body" };

            // Get Content Resolver object, which will deal with Content Provider
            ContentResolver cr = getContentResolver();

            // Fetch Sent SMS Message from Built-in Content Provider
            Cursor c = cr.query(draftURI, reqCols, null, null, null);

            // Attached Cursor with adapter and display in listview
            adapter = new SimpleCursorAdapter(this, R.layout.row, c,
                    new String[] { "body", "address" }, new int[] {
                    R.id.lblMsg, R.id.lblNumber });
            lvMsg.setAdapter(adapter);

        }

    }
    // 通过address手机号关联Contacts联系人的显示名字
    private String getPeopleNameFromPerson(String address){
        if(address == null || address == ""){
            return "( no address )\n";
        }

        String strPerson = "null";
        String[] projection = new String[] {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};

        Uri uri_Person = Uri.withAppendedPath(ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI, address);	// address 手机号过滤
        Cursor cursor = getContentResolver().query(uri_Person, projection, null, null, null);

        if(cursor.moveToFirst()){
            int index_PeopleName = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            String strPeopleName = cursor.getString(index_PeopleName);
            strPerson = strPeopleName;
        }
        cursor.close();

        return strPerson;
    }
}
