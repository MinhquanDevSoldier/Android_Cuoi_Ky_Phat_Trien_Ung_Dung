package com.example.appqlquanoc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class modifydatabase extends AppCompatActivity {
    Button bntmodify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifydatabase);

        bntmodify =(Button) findViewById(R.id.btnmodify);
        bntmodify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DatabaseQuanOc databaseQuanOc = new DatabaseQuanOc(getApplicationContext());
                    databaseQuanOc.xoadatabase();
                }catch (Exception ex)
                {
                    Toast.makeText(modifydatabase.this, ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}