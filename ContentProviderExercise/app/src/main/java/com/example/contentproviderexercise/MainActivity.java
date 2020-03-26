package com.example.contentproviderexercise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataRetriever dataRetriever;
    ListView contactList;

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    private void showContacts() {
        //Check SDK version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            loadContacts();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactList = findViewById(R.id.contact_list);
        showContacts();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if(requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                showContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we cannot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopDataRetriever();
    }

    private void loadContacts(){
        stopDataRetriever();
        dataRetriever = new DataRetriever();
        dataRetriever.execute();

    }

    private void stopDataRetriever(){
        if(dataRetriever != null){
            dataRetriever.cancel(false);
        }
    }

    @SuppressLint("StaticFieldLeak")
    class DataRetriever extends AsyncTask<Void,Void, Cursor>{

        @Override
        protected Cursor doInBackground(Void... voids) {
            Cursor cursor = null;

            ContentResolver cr = getContentResolver();

            String[] mProjection = {
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.LOOKUP_KEY,
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            };

            Uri uri = ContactsContract.Contacts.CONTENT_URI;

            return cr.query(uri,mProjection,null,null,null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            String[] cursorColumns = {
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.LOOKUP_KEY,
            };

            int[] viewIds = {R.id.tvColumn1, R.id.tvColumn2,R.id.tvColumn3};
            SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
                    MainActivity.this,
                    R.layout.contect_list_item,
                    cursor,
                    cursorColumns,
                    viewIds,
                    0);

            contactList.setAdapter(simpleCursorAdapter);

        }
    }
}
