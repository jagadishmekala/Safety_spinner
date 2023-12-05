

package com.example.voltasassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.voltasassignment.retrofitinterface.RetrofitConnect;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HazardCreationActivity extends AppCompatActivity {
    private EditText datecreate, timecreate, edt_customername_create, edt_Address_create,
            edt_Factor_Incharge_Id_create, edt_Emailid_factory_create, edt_Factor_Incharge_Phone_create, edt_suervisoremail_create, edt_otheremail_id_create, edt_target_date_to_close_create;
    Spinner spin_zone_create, spin_branch_create;
    TextView cancel_txt_back, txt_Next_create, Mainheading;
    ArrayList<String> zonesList, zonesbranchList, zoneIdList;
    DatePickerDialog datePickerDialog;
    ArrayAdapter zoneAdapter, branchadapter;
    AutoCompleteTextView edt_Incharge_Name_create, edt_supervisorname_create;
    String zoneid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hazard_creation);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(HazardCreationActivity.this, R.color.DashBoardTextColor));
        }
//        String zoneid = getIntent().getStringExtra("zoneid");
        // on below line we are initializing our variables.
        Mainheading = findViewById(R.id.Mainheading);
        datecreate = findViewById(R.id.datecreate);
        timecreate = findViewById(R.id.timecreate);
        cancel_txt_back = findViewById(R.id.cancel_txt_back);
        txt_Next_create = findViewById(R.id.txt_Next_create);
        edt_Address_create = findViewById(R.id.edt_Address_create);
        edt_customername_create = findViewById(R.id.edt_customername_create);
        spin_zone_create = findViewById(R.id.spin_zone_create);
        spin_branch_create = findViewById(R.id.spin_branch_create);
        edt_Incharge_Name_create = findViewById(R.id.edt_Incharge_Name_create);
        edt_Emailid_factory_create = findViewById(R.id.edt_Emailid_factory_create);
        edt_Factor_Incharge_Phone_create = findViewById(R.id.edt_Factor_Incharge_Phone_create);
        edt_supervisorname_create = findViewById(R.id.edt_supervisorname_create);
        edt_suervisoremail_create = findViewById(R.id.edt_suervisoremail_create);
        edt_otheremail_id_create = findViewById(R.id.edt_otheremail_id_create);
        edt_target_date_to_close_create = findViewById(R.id.edt_target_date_to_close_create);


        //calling Methods
        currenttimedate(); //calling current timeand date method
        Timepicker(); // calling date picker when click edittext date
        datepicker();
        zonespinner();// calling time picker when click edittext time
        spin_zone_create.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (zoneIdList.size() > position) {
                    zoneid = zoneIdList.get(position);
                    ZoneIdbranch(zoneid);
                    zonesList.remove("Select");
                } else {
                    Toast.makeText(HazardCreationActivity.this, "Please select zone", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        autotextfactory();

        cancel_txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HazardCreationActivity.this, HazradActivity.class));
            }
        });
        edt_target_date_to_close_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(HazardCreationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        edt_target_date_to_close_create.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    public void Timepicker() {
        timecreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(HazardCreationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timecreate.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }

    public void datepicker() {
        datecreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(HazardCreationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        datecreate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    public void currenttimedate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
        // for current date and time and calling a simple date format in it.
        String currentDateAndTime = sdf.format(new Date());
        String[] dateAndTimeComponents = currentDateAndTime.split(" ");
        String date = dateAndTimeComponents[0];
        String time = dateAndTimeComponents[1];
        time = time.substring(0, 5);
        datecreate.setText(date);
        timecreate.setText(time);
    }

    public void zonespinner() {

        SharedPreferences sharedPreferences = getSharedPreferences("My Prefs", Context.MODE_PRIVATE);
        RetrofitConnect retrofitConnect = new RetrofitConnect();
        String token = sharedPreferences.getString("token", "");
        DataAPi dataApi = retrofitConnect.getLink().create(DataAPi.class);
        Call<ZoneResponseCreate> call = dataApi.getzones("Bearer " + token);
        call.enqueue(new Callback<ZoneResponseCreate>() {
            @Override
            public void onResponse(Call<ZoneResponseCreate> call, Response<ZoneResponseCreate> response) {
                zonesList = new ArrayList<>();
                zoneIdList = new ArrayList<>();
                zonesList.add("Select");
                if (response.isSuccessful()) {
                    if (response.body().getResult() != null) {
                        for (int i = 0; i < response.body().getResult().size(); i++) {

                            zonesList.add(response.body().getResult().get(i).getZoneName());
                            zoneIdList.add(response.body().getResult().get(i).getZoneId());
                        }
                        zoneAdapter = new ArrayAdapter(HazardCreationActivity.this, android.R.layout.simple_list_item_1, zonesList);
                        zoneAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
                        if(zonesList.size()>0){
                            spin_zone_create.setAdapter(zoneAdapter);
                        }


//
//                        branchadapter = new ArrayAdapter<>(HazardCreationActivity.this, android.R.layout.simple_list_item_1, zonesbranchList);
//                        branchadapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
//                        spin_branch_create.setAdapter(branchadapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ZoneResponseCreate> call, Throwable t) {

            }
        });
    }

    public void ZoneIdbranch(String zoneId) {
        SharedPreferences sharedPreferences = getSharedPreferences("My Prefs", Context.MODE_PRIVATE);
        RetrofitConnect retrofitConnect = new RetrofitConnect();
        String token = sharedPreferences.getString("token", "");
        DataAPi dataApi = retrofitConnect.getLink().create(DataAPi.class);
        Call<ZoneId_Response> call = dataApi.getbranch("Bearer " + token,zoneId);
        call.enqueue(new Callback<ZoneId_Response>() {
            @Override
            public void onResponse(Call<ZoneId_Response> call, Response<ZoneId_Response> response) {
                zonesbranchList = new ArrayList<>();

                zonesbranchList.add("Select");
                if (response.isSuccessful()) {
                    if (response.body().getResult() != null) {
                        for (int j = 0; j < response.body().getResult().size(); j++) {
                            zonesbranchList.add(response.body().getResult().get(j).getBranchName());

                        }
                        branchadapter = new ArrayAdapter<>(HazardCreationActivity.this, android.R.layout.simple_list_item_1, zonesbranchList);
                        branchadapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
                        spin_branch_create.setAdapter(branchadapter);
                    }
                }

            }

            @Override
            public void onFailure(Call<ZoneId_Response> call, Throwable t) {
                Toast.makeText(HazardCreationActivity.this, "Please check internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
    String[] countryNameList = {"India", "China", "Australia", "New Zealand", "England", "Pakistan"};
    public void autotextfactory(){
        SharedPreferences sharedPreferences = getSharedPreferences("My Prefs", Context.MODE_PRIVATE);
        RetrofitConnect retrofitConnect = new RetrofitConnect();
        String token = sharedPreferences.getString("token", "");
        DataAPi dataApi = retrofitConnect.getLink().create(DataAPi.class);
        Call<Autotext_Responsee> call = dataApi.getsearchusers("Bearer " + token);
        call.enqueue(new Callback<Autotext_Responsee>() {

            @Override
            public void onResponse(Call<Autotext_Responsee> call, Response<Autotext_Responsee> response) {
                if(response.isSuccessful()){
                    Autotext_Result autotextResult = (Autotext_Result) response.body().getResult();
                    if(autotextResult != null ){
//                        AutoCompleteTextView simpleAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.edt_Incharge_Name_create);
//                        ArrayAdapter adapter = new ArrayAdapter(HazardCreationActivity.this, android.R.layout.simple_list_item_1, countryNameList);
//
//                        simpleAutoCompleteTextView.setAdapter(adapter);
//                        simpleAutoCompleteTextView.setThreshold(1);
//                        simpleAutoCompleteTextView.setAdapter(adapter);
                        edt_Incharge_Name_create.setText(autotextResult.getUserName().toString());
                        edt_Factor_Incharge_Id_create.setText(autotextResult.getUserId().toString());
                        edt_Emailid_factory_create.setText(autotextResult.getUserMail().toString());
                        edt_Factor_Incharge_Phone_create.setText(autotextResult.getUserPhone().toString());
                    }
                }

//
//                Autotext_Result autotextResult= (Autotext_Result)
            }

            @Override
            public void onFailure(Call<Autotext_Responsee> call, Throwable t) {

            }
        });
    }

}

