

package com.example.voltasassignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.example.voltasassignment.retrofitinterface. RetrofitConnect;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
   List<Autotext_Result> autoTextUserArrayList,ConcernedEngArrayList;
    ArrayAdapter zoneAdapter, branchadapter;
    AutoCompleteTextView edt_Incharge_Name_create, edt_supervisorname_create;
    String zoneid;
    HazardResult hazardResult;


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
        edt_Factor_Incharge_Id_create=findViewById(R.id.edt_Factor_Incharge_Id_create);
        edt_Factor_Incharge_Phone_create = findViewById(R.id.edt_Factor_Incharge_Phone_create);
        edt_supervisorname_create = findViewById(R.id.edt_supervisorname_create);
        edt_suervisoremail_create = findViewById(R.id.edt_suervisoremail_create);
        edt_otheremail_id_create = findViewById(R.id.edt_otheremail_id_create);
        edt_target_date_to_close_create = findViewById(R.id.edt_target_date_to_close_create);

        txt_Next_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validatefields()){
                    String customername= edt_customername_create.getText().toString();
                    Intent i = new Intent(getApplicationContext(),HazardCreationActivity2.class);
//                    i.putExtra(customername);
                     startActivity(i);

//                edt_customername_create.getText(getIntent().putExtra());

                }
            }
        });

//   if(hazardResult.getReason().equalsIgnoreCase("Hazard")){
//       Mainheading.setText("HAZARDS");
//   }
//   else{
       Mainheading.setText("HAZARD");
//   }
        //calling Methods
        currenttimedate(); //calling current timeand date method
        Timepicker(); // calling date picker when click edittext date
        datepicker();
        zonespinner();// calling time picker when click edittext time
        edt_supervisorname_create.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=1){
                    concernSuperVisor( s.toString());
                }
                else{
                    edt_suervisoremail_create.setText("");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
                edt_supervisorname_create.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Autotext_Result selectedUser = (Autotext_Result) parent.getItemAtPosition(position);
                        if(selectedUser!=null){
                        // Update the corresponding fields with data from the selected suggestion
                        edt_supervisorname_create.setText(selectedUser.getUserName());
                        edt_suervisoremail_create.setText(selectedUser.getUserMail());
                        }
                    }
                });
        edt_Incharge_Name_create.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>=1){
                    factoryProject( s.toString());
                }
                else {
                    edt_Factor_Incharge_Id_create.setText("");
                    edt_Emailid_factory_create.setText("");
                    edt_Factor_Incharge_Phone_create.setText("");
                }
            }
        });
        edt_Incharge_Name_create.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Autotext_Result selectedUser = (Autotext_Result) parent.getItemAtPosition(position);

                if(selectedUser!=null){
                // Update the corresponding fields with data from the selected suggestion
                edt_Incharge_Name_create.setText(selectedUser.getUserName());
                edt_Factor_Incharge_Id_create.setText(selectedUser.getUserId());
                edt_Emailid_factory_create.setText(selectedUser.getUserMail());
                edt_Factor_Incharge_Phone_create.setText(selectedUser.getUserPhone());
                }
            }
        });
        spin_zone_create.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {

                if (adapterView.getSelectedItem().toString().equalsIgnoreCase("Select")) {
//                    zonesList.remove("Select");
                    spin_branch_create.setAdapter(null);

                }
               else if (zoneIdList.size() >i) {
                    zoneid = zoneIdList.get(i);
                    ZoneIdbranch(zoneid);
//                    zonesList.remove("Select");
                }
                else {
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
        edt_target_date_to_close_create.setText(date);
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
                zoneIdList.add("Select");
                if (response.isSuccessful()) {
                    if (response.body().getResult() != null) {
                        for (int i = 0; i < response.body().getResult().size(); i++) {

                            zonesList.add(response.body().getResult().get(i).getZoneName());
                            zoneIdList.add(response.body().getResult().get(i).getZoneId());
                        }
                        zoneAdapter = new ArrayAdapter(HazardCreationActivity.this, android.R.layout.simple_list_item_1, zonesList);
                        zoneAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
                        if (zonesList.size() > 0 && zoneIdList.size()>0) {
                            spin_zone_create.setAdapter(zoneAdapter);
                        }

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
        Call<ZoneId_Response> call = dataApi.getbranch("Bearer " + token, zoneId);
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
//                        if(zonesbranchList.size()>1){
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



    public void factoryProject(String searchKey){
        SharedPreferences sharedPreferences = getSharedPreferences("My Prefs", Context.MODE_PRIVATE);
        RetrofitConnect retrofitConnect = new RetrofitConnect();
        String token = sharedPreferences.getString("token", "");
        DataAPi dataApi = retrofitConnect.getLink().create(DataAPi.class);
        Call<Autotext_Responsee> call = dataApi.getsearchusers("Bearer " + token,searchKey);
        call.enqueue(new Callback<Autotext_Responsee>() {
            @Override
            public void onResponse(Call<Autotext_Responsee> call, Response<Autotext_Responsee> response) {
                autoTextUserArrayList = new ArrayList<>();
                if (response.isSuccessful()) {
                    autoTextUserArrayList.addAll(response.body().getResult());

                    AutoTextProj_Adapter autoCompleteAdapter = new AutoTextProj_Adapter(HazardCreationActivity.this, R.layout.searchtext, R.id.searchItemNames, autoTextUserArrayList);
                    edt_Incharge_Name_create.setAdapter(autoCompleteAdapter);
                    autoCompleteAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<Autotext_Responsee> call, Throwable t) {

            }
        });
    }
    public void concernSuperVisor(String searchKey){
        SharedPreferences sharedPreferences = getSharedPreferences("My Prefs", Context.MODE_PRIVATE);
        RetrofitConnect retrofitConnect = new RetrofitConnect();
        String token = sharedPreferences.getString("token", "");
        DataAPi dataApi = retrofitConnect.getLink().create(DataAPi.class);
        Call<Autotext_Responsee> call = dataApi.getsearchusers("Bearer " + token,searchKey);
        call.enqueue(new Callback<Autotext_Responsee>() {
            @Override
            public void onResponse(Call<Autotext_Responsee> call, Response<Autotext_Responsee> response) {
                ConcernedEngArrayList = new ArrayList<>();
                if (response.isSuccessful()) {
                    ConcernedEngArrayList.addAll(response.body().getResult());
                    AutoTextProj_Adapter autoCompleteAdapter = new AutoTextProj_Adapter(HazardCreationActivity.this, R.layout.searchtext, R.id.searchItemNames, ConcernedEngArrayList);
                    edt_supervisorname_create.setAdapter(autoCompleteAdapter);
                    autoCompleteAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<Autotext_Responsee> call, Throwable t) {
            }
        });
    }
    private boolean validatefields(){
        String customername = edt_customername_create.getText().toString().trim();
        String address = edt_Address_create.getText().toString().trim();
//        Spinner zone= findViewById(R.id.spin_zone_create);
//        Spinner branch= findViewById(R.id.spin_branch_create);
        String inchargenamefactory=edt_Incharge_Name_create.getText().toString().trim();
        String inchargeidfactory=edt_Factor_Incharge_Id_create.getText().toString().trim();
        String inchargeemailfactory=edt_Emailid_factory_create.getText().toString().trim();
        String phonenumberfactory = edt_Factor_Incharge_Phone_create.getText().toString().trim();
//        TextView errorTextview = (TextView) spin_zone_create.getSelectedView();
//        errorTextview.setError("Your Error Message here");
//        boolean isValid = true;


        if(customername.isEmpty()){
            edt_customername_create.setError("Please Enter Customer name");
            edt_customername_create.requestFocus();
            edt_customername_create.performClick();
            return false;
        }
        if(address.isEmpty()){
            edt_Address_create.setError("Please Enter Address");
            edt_Address_create.requestFocus();
            edt_Address_create.performClick();

        return false;
    }
        if(spin_zone_create.getSelectedItemPosition()==0){
            ((TextView)spin_zone_create.getSelectedView()).setError("Error message");
            Toast.makeText(HazardCreationActivity.this,"Please Select Zone.....",Toast.LENGTH_SHORT).show();
            showZoneAlertDialog();
            spin_zone_create.requestFocus();
            spin_zone_create.performClick();

            return false;
        }
        if(spin_branch_create.getSelectedItemPosition()==0){
            ((TextView) spin_branch_create.getSelectedView()).setError("Branch selection is required");
            Toast.makeText(HazardCreationActivity.this,"Please Select Branch....",Toast.LENGTH_SHORT).show();
            showbranchAlertDialog();
            spin_branch_create.requestFocus();
            spin_branch_create.performClick();
            return false;
        }
//        if (spin_branch_create.getSelectedItem().toString().equalsIgnoreCase("Select")) {
//            ( (TextView) spin_branch_create.getSelectedItem()).setError("Please Select Zone");
//            return false;
//        }
//        if (spin_zone_create.getSelectedItem().toString().equalsIgnoreCase("Select")) {
//            ( (TextView) spin_zone_create.getSelectedItem()).setError("Please Select Branch");
//            return false;
//        }
        if(inchargenamefactory.isEmpty()){
            edt_Incharge_Name_create.setError("Please Enter Incharge Name");
            edt_Incharge_Name_create.requestFocus();
            return false;
        }
        if(inchargeidfactory.isEmpty()){
            edt_Factor_Incharge_Id_create.setError("Please Enter Incharge ID");
            edt_Factor_Incharge_Id_create.requestFocus();
            return false;
        }
        if(inchargeemailfactory.isEmpty()){
            edt_Emailid_factory_create.setError("Please Enter Incharge Email Id");
            edt_Emailid_factory_create.requestFocus();
            return false;
        }

        if(phonenumberfactory.isEmpty()){
            edt_Factor_Incharge_Phone_create.setError("Please Enter Incharge Phone Number");
            edt_Factor_Incharge_Phone_create.requestFocus();
            return false;
        }


        return true;
    }
    private void showZoneAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Zone")
                .setMessage("Please select a ZONE")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle positive button click
                        dialog.dismiss();
                    }
                })
                .show();
    }
    private void showbranchAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Branch")
                .setMessage("Please select BRANCH")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle positive button click
                        dialog.dismiss();
                    }
                })
                .show();
    }
}

