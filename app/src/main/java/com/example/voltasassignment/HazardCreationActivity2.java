package com.example.voltasassignment;

import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class HazardCreationActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hazard_creation2);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(HazardCreationActivity2.this, R.color.DashBoardTextColor));
        }
    }
}
