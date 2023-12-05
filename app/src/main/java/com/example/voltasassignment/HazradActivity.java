package com.example.voltasassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voltasassignment.retrofitinterface.RetrofitConnect;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HazradActivity extends AppCompatActivity {
    private ListView listView;
    private Hazradcustomlistadapter customAdapter;
    private List<HazardResult> hazardResults = new ArrayList<>();
  //  private List<HazardResult> allHazardResults = new ArrayList<>();
    Retrofit retrofit;
 TextView  listviewmainheading;
    EditText searchEditText;
ImageButton imagebuttonsearch;
    String type, subType, searchkey;
FloatingActionButton floating_btn_hazard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hazrad);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(HazradActivity.this,R.color.DashBoardTextColor));
        }
        String listViewMainHeadingText = getIntent().getStringExtra("listViewMainHeading");
        String strSubType = getIntent().getStringExtra("strSubType");
        String text = getIntent().getStringExtra("text");

        listView = findViewById(R.id.listview);
        searchEditText = findViewById(R.id.editTextSearchbar);
        imagebuttonsearch = findViewById(R.id.imageViewSearchbar);
        listviewmainheading = findViewById(R.id.listviewmainheading);
        floating_btn_hazard = findViewById(R.id.floating_btn_hazard);
        listviewmainheading.setText(listViewMainHeadingText);
        customAdapter = new Hazradcustomlistadapter(this, hazardResults);
        listView.setAdapter(customAdapter);
       listView.setTextFilterEnabled(true);
        floating_btn_hazard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   startActivity( new Intent(HazradActivity.this, HazardCreationActivity.class));
            }
        });
        ///
        String authToken = "Bearer " + getTokenFromSharedPreferences();
        if (strSubType.equalsIgnoreCase("Near Miss")  ) {
            type = "Incident";
            subType = "Near Miss";
            searchkey = "All";
            listviewmainheading.setText("NEAR MISS");

        } else if (strSubType.equalsIgnoreCase("Accident") ) {
            type = "Incident";
            subType = "Accident";
            searchkey = "All";
            listviewmainheading.setText( "ACCIDENT");


        }
        else {
            type = "hazard";
            subType = "hazard";
            searchkey = "All";
            listviewmainheading.setText( "HAZARD");
        }

        getAllObservatins(authToken, type, subType);
        ImageView backbtn = findViewById(R.id.backbutton);
        Button button1all = findViewById(R.id.customButtonAll);
        Button button2open = findViewById(R.id.button3Open);
        Button button3assigned = findViewById(R.id.button4Assigned);
        Button button4closed = findViewById(R.id.button5Closed);
        button1all.setTextColor(getResources().getColor(R.color.white));



        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HazradActivity.this, Dashboard.class);
                startActivity(i);
                finish();
            }
        });
        button1all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                button1all.setBackgroundResource(R.drawable.hazaradbutton);
                button1all.setTextColor(getResources().getColor(R.color.white));
                button2open.setBackgroundResource(R.drawable.hazarad2button);
                button2open.setTextColor(getResources().getColor(R.color.open_text));
                button3assigned.setBackgroundResource(R.drawable.hazarad3button);
                button3assigned.setTextColor(getResources().getColor(R.color.assigned_text));
                button4closed.setBackgroundResource(R.drawable.hazarad4button);
                button4closed.setTextColor(getResources().getColor(R.color.close_text));

                if (strSubType.equalsIgnoreCase("Near Miss")) {
                    type = "Incident";
                    subType = "Near Miss";
                    searchkey = "All";
                }
                else if (strSubType.equalsIgnoreCase("Accident")) {
                    type = "Incident";
                    subType = "Accident";
                    searchkey = "All";
                }
                else {
                    type = "hazard";
                    subType = "hazard";
                    searchkey = "All";
                }
                getAllObservatins(authToken, type, subType);
            }
        });

        button2open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button2open.setBackgroundResource(R.drawable.hazarad2button1);
                button2open.setTextColor(getResources().getColor(R.color.white));
                button1all.setBackgroundResource(R.drawable.hazardbutton2);
                button1all.setTextColor(getResources().getColor(R.color.DashBoardTextColor));
                button3assigned.setBackgroundResource(R.drawable.hazarad3button);
                button3assigned.setTextColor(getResources().getColor(R.color.assigned_text));
                button4closed.setBackgroundResource(R.drawable.hazarad4button);
                button4closed.setTextColor(getResources().getColor(R.color.close_text));

                if (strSubType.equalsIgnoreCase("Near Miss")) {
                    type = "Incident";
                    subType = "Near Miss";
                    searchkey = "Open";
                }
                else if (strSubType.equalsIgnoreCase("Accident")) {
                    type = "Incident";
                    subType = "Accident";
                    searchkey = "Open";
                }
                else {
                    type = "hazard";
                    subType = "hazard";
                    searchkey = "Open";
                }
                getObservatins(authToken, searchkey, type, subType);
            }
        });
        button3assigned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button3assigned.setBackgroundResource(R.drawable.hazarad3button1);
                button3assigned.setTextColor(getResources().getColor(R.color.white));
                button1all.setBackgroundResource(R.drawable.hazardbutton2);
                button1all.setTextColor(getResources().getColor(R.color.DashBoardTextColor));
                button2open.setBackgroundResource(R.drawable.hazarad2button);
                button2open.setTextColor(getResources().getColor(R.color.open_text));
                button4closed.setBackgroundResource(R.drawable.hazarad4button);
                button4closed.setTextColor(getResources().getColor(R.color.close_text));
                if (strSubType.equalsIgnoreCase("Near Miss")) {
                    type = "Incident";
                    subType = "Near Miss";
                    searchkey = "Assigned";
                } else if (strSubType.equalsIgnoreCase("Accident")) {
                    type = "Incident";
                    subType = "Accident";
                    searchkey = "Assigned";
                } else {
                    type = "hazard";
                    subType = "hazard";
                    searchkey = "Assigned";
                }
                getObservatins(authToken, searchkey, type, subType);

            }
        });
        button4closed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button4closed.setBackgroundResource(R.drawable.hazarad4button1);
                button4closed.setTextColor(getResources().getColor(R.color.white));
                button1all.setBackgroundResource(R.drawable.hazardbutton2);
                button1all.setTextColor(getResources().getColor(R.color.DashBoardTextColor));
                button2open.setBackgroundResource(R.drawable.hazarad2button);
                button2open.setTextColor(getResources().getColor(R.color.open_text));
                button3assigned.setBackgroundResource(R.drawable.hazarad3button);
                button3assigned.setTextColor(getResources().getColor(R.color.assigned_text));
                if (strSubType.equalsIgnoreCase("Near Miss")) {
                    type = "Incident";
                    subType = "Near Miss";
                    searchkey = "Closed";
                } else if (strSubType.equalsIgnoreCase("Accident")) {
                    type = "Incident";
                    subType = "Accident";
                    searchkey = "Closed";
                } else {
                    type = "hazard";
                    subType = "hazard";
                    searchkey = "Closed";
                }
                getObservatins(authToken, searchkey, type, subType);
            }
        });
        // Inside your onCreate method:
        imagebuttonsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = searchEditText.getText().toString().trim().toLowerCase().toUpperCase();

                    if (searchText.length() == 0) {
                        getAllObservatins(authToken, type, subType);
                    } else {
                        getObservatins(authToken, searchText, type, subType);
                    }
            }
        });

    }

    private void getAllObservatins(String authToken, String type, String subType) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
//        retrofit = new Retrofit.Builder().baseUrl("https://vserveq.voltasworld.com/").addConverterFactory(GsonConverterFactory.create()).build();
//        DataAPi dataAPi = retrofit.create(DataAPi.class);

        RetrofitConnect retrofitConnect = new RetrofitConnect();
        DataAPi dataApi = retrofitConnect.getLink().create(DataAPi.class);
        Call<Example> call = dataApi.getAllObservations(authToken, type, subType);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                if (response.isSuccessful()) {

                    Example example = response.body();
                    hazardResults.clear();
                    if (example.getHazardResult() != null) {

                        hazardResults.addAll(example.getHazardResult());

                    }
                    customAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d("",t.getMessage());

            }
        });
   }

    private void getObservatins(String authToken, String searchkey, String type, String subType) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
//        retrofit = new Retrofit.Builder().baseUrl("https://vserveq.voltasworld.com/").addConverterFactory(GsonConverterFactory.create()).build();

//        DataAPi dataAPi = retrofit.create(DataAPi.class);
        RetrofitConnect retrofitConnect = new RetrofitConnect();
        DataAPi dataApi = retrofitConnect.getLink().create(DataAPi.class);

        Call<Example> call = dataApi.getUserObservations(authToken, searchkey, type, subType);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                if (response.isSuccessful()) {

                    Example example = response.body();
                    hazardResults.clear();
                    if (example.getHazardResult() != null) {

                        hazardResults.addAll(example.getHazardResult());

                    }
                    customAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }

    private String getTokenFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("My Prefs", Context.MODE_PRIVATE);
        return sharedPreferences.getString("token", "");
    }


}

