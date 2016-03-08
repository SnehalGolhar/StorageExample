package com.example.vaibhav.storageexample;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SqliteActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        ed1=(EditText)findViewById(R.id.txtDesc);
        final String tablename = "MYTable";
        final String TEXT_TYPE = " TEXT";
        final String COMMA_SEP = ",";
        final String strid= "entryid";
        final String strdesc = "description";
        final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + tablename + " (" +
                       // strid + " INTEGER PRIMARY KEY," +
                        strdesc+ TEXT_TYPE + " )";
        //TO save information in Sqlite class
        final Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

              /*  String strdescr  = ed1.getText().toString();

// Gets the data repository in write mode
                SQLiteDatabase db ;
// Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(strdesc, strdescr);

// Insert the new row, returning the primary key value of the new row
                 db.insert(
                        tablename,
                        values);*/
            }
        });


        //TO save information in Sqlite class
        final Button btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });


    }
}
