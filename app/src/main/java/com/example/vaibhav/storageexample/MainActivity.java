package com.example.vaibhav.storageexample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
}
