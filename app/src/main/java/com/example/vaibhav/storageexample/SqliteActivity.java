package com.example.vaibhav.storageexample;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SqliteActivity extends AppCompatActivity {
    EditText ed1;
    public int counter=0;
    private SimpleDateFormat s=new SimpleDateFormat("MM/dd/yyyy-hh:mm a");
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        final Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ed1=(EditText)findViewById(R.id.txtdescsqlite);
                String strdescval=ed1.getText().toString();

                if(!strdescval.isEmpty())
                {
                    DBHandler dataController = new DBHandler(getBaseContext());
                    dataController.open();
                    long retValue = dataController.insert(strdescval);
                    dataController.close();
                    if (retValue != -1) {
                        context = getApplicationContext();
                        CharSequence text = "Data saved successfully";
                        int duration = Toast.LENGTH_LONG;
                        Toast.makeText(context, text, duration).show();

                        try {
                            counter += 1;
                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("SQL_COUNTER", counter);
                            editor.commit();
                            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(PreferenceActivity.STORE_PREFERENCES, MODE_APPEND));
                            out.write("\nSQLite " + counter + ", " + s.format(new Date()));
                            out.close();
                            Intent intent = new Intent(context, MainActivity.class);
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                }
                else
                {

                    AlertDialog alertDialog = new AlertDialog.Builder(SqliteActivity.this).create();
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
                context = getApplicationContext();
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
        counter=sharedPrefs.getInt("SQL_COUNTER", 0);
    }
}
