package com.example.vaibhav.storageexample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PreferenceActivity extends MainActivity {
    EditText ed1,ed2,ed3;
    Button b1;
    public static final String MyPREFERENCES = "SharedPrefs" ;
    public static final String BookName = "nameKey";
    public static final String BookAuthor = "authorKey";
    public static final String BookDesc = "descKey";
    SharedPreferences objSharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        ed1=(EditText)findViewById(R.id.txtAuthor);
        ed2=(EditText)findViewById(R.id.txtBookname);
        ed3=(EditText)findViewById(R.id.txtDesc);
        objSharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //TO save information in Shared prefernces
        final Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String bookname  = ed1.getText().toString();
                String authorename  = ed2.getText().toString();
                String description  = ed3.getText().toString();

                SharedPreferences.Editor editor = objSharedpreferences.edit();

                editor.putString(BookName, bookname);
                editor.putString(BookAuthor, authorename);
                editor.putString(BookDesc, description);
                editor.commit();
                Toast.makeText(PreferenceActivity.this,"The data is saved ", Toast.LENGTH_LONG).show();
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

