package com.example.vaibhav.storageexample;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PreferenceActivity extends MainActivity {
    public final static String STORE_PREFERENCES="PreferenceExample.txt";
    public int counter=0;
    private SimpleDateFormat s=new SimpleDateFormat("MM/dd/yyyy-hh:mm a");
    EditText ed1,ed2,ed3;
    public static final String MyPREFERENCES = "SharedPrefs" ;
    public static final String BookName = "nameKey";
    public static final String BookAuthor = "authorKey";
    public static final String BookDesc = "descKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        ed1=(EditText)findViewById(R.id.txtAuthor);
        ed2=(EditText)findViewById(R.id.txtBookname);
        ed3=(EditText)findViewById(R.id.txtDesc);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        counter=sharedPrefs.getInt("COUNTER", 0);

        //TO save information in Shared prefernces
        final Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String bookname  = ed1.getText().toString();
                String authorename  = ed2.getText().toString();
                String description  = ed3.getText().toString();

                SharedPreferences objSharedpreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = objSharedpreferences.edit();

                editor.putString(BookName, bookname);
                String booknameval = objSharedpreferences.getString(BookName, "");
                editor.putString(BookAuthor, authorename);
                String authornameval = objSharedpreferences.getString(BookAuthor, "");
                editor.putString(BookDesc, description);
                String descriptionval = objSharedpreferences.getString(BookDesc, "");
                editor.apply();
                Toast.makeText(PreferenceActivity.this,"The data is saved ", Toast.LENGTH_LONG).show();


                if (booknameval != null && authornameval != null && descriptionval != null) {
                    try {
                        counter += 1;

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                        SharedPreferences.Editor editor1 = sharedPreferences.edit();
                        editor1.putInt("COUNTER", counter);
                        editor1.commit();

                        OutputStreamWriter out = new OutputStreamWriter(openFileOutput(STORE_PREFERENCES, MODE_APPEND));
                        String message = "\nPreference " + counter + "," + s.format(new Date());
                        out.write(message);
                        out.close();
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                else
                {

                    AlertDialog alertDialog = new AlertDialog.Builder(PreferenceActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Please enter values in required field");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }


            }
        });

        //TO save information in Sqlite class
        final Button btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(context,MainActivity.class);
                startActivity(intent);

            }
        });



    }

    @Override
    public void onResume()
    {
        super.onResume();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        counter=sharedPrefs.getInt("COUNTER", 0);
    }

}

