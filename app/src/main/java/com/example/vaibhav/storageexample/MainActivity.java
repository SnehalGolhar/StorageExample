package com.example.vaibhav.storageexample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btnPreferences = (Button) findViewById(R.id.btnPref);
        btnPreferences.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, PreferenceActivity.class);
                startActivity(intent);
            }
        });


        final Button btnSqlite = (Button) findViewById(R.id.btnSql);
        btnSqlite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent(context, SqliteActivity.class);
                startActivity(intent1);
            }
        });

        final Button btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            System.exit(0);
            }
        });
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        try
        {
            InputStream in=openFileInput(PreferenceActivity.STORE_PREFERENCES);
            if(in!=null)
            {
                InputStreamReader tmp=new InputStreamReader(in);
                BufferedReader reader=new BufferedReader(tmp);
                String str;
                StringBuilder buf=new StringBuilder();
                while((str=reader.readLine())!=null)
                {
                    buf.append(str + "\n");
                }
                in.close();
                TextView savedData=(TextView)findViewById(R.id.txtData);
                savedData.setText(buf.toString());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
