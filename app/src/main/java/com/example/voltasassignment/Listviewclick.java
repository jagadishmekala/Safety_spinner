package com.example.voltasassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.voltasassignment.retrofitinterface.RetrofitConnect;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.lang.StringBuilder;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Listviewclick extends AppCompatActivity {
    TextView customernametxtview,customeraddresstxtview,customerzonetxtview,customerbrancttxtview,hazardidtxtview,
            hazardmnthyeartxtview, hazardhoursmintxtview,employeenametxtview,employeeidtxtview,employeemailidtxtview,
            employeephonenumbertxtview,mainheadingtxtview,textviewprjtinchargename,textviewinchargeid,textviewinchargephnenumbe,
    supervisornameconcerned,supervisoremailconcerned,supervisorotheremailconcerned;
    AppCompatButton button_name_heading;
    ImageView imageViewback;
    ListView listViewww;
    AppCompatButton button;
    private Observation_ItemsAdapter customadapter1;
    private List<ObservationItemsDetailsModel> hazardItems = new ArrayList<>();
    private List<ObservationAttachmentModel__1> accidentitem = new ArrayList<>();
    private List<ActionAdapter> actionItems = new ArrayList<>();
//    private List<AttachAdapter> attachment=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewclick);
       getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow()
            .setStatusBarColor(ContextCompat.getColor(Listviewclick.this,R.color.DashBoardTextColor));
        }
        String observationId = getIntent().getStringExtra("ObservationID");
        String mainheading = getIntent().getStringExtra("ObservationID");
        String observationType = getIntent().getStringExtra("observationType");
        String type = getIntent().getType();



        listViewww = findViewById(R.id.listviewww);
        customernametxtview = findViewById(R.id.textViewcustomername);
        customeraddresstxtview = findViewById(R.id.textViewaddress);
        button_name_heading= findViewById(R.id.button_name_heading);
         customerzonetxtview = findViewById(R.id.textViewzone);
         TextView info_txt = findViewById(R.id.textView_info);
         customerbrancttxtview = findViewById(R.id.textViewbranch);
        hazardidtxtview = findViewById(R.id.textViewhazardid);
        hazardmnthyeartxtview =findViewById(R.id.textViewdatemnthyear);
        hazardhoursmintxtview = findViewById(R.id.textViewhrmin);
        employeeidtxtview = findViewById(R.id.txtemployeeid);
        employeenametxtview = findViewById(R.id.txtemployeename);
        employeemailidtxtview= findViewById(R.id.txtemployeeemailid);
        employeephonenumbertxtview=findViewById(R.id.txtemployeephonenumber);
        textviewinchargeid = findViewById(R.id.textViewinchargeid);
        textviewinchargephnenumbe= findViewById(R.id.textViewinchargephnenumber);
        textviewprjtinchargename= findViewById(R.id.textViewprjtinchargename);
        imageViewback = findViewById(R.id.imageViewback);
        mainheadingtxtview = findViewById(R.id.textViewmainheading);
//        button_name_heading = findViewById(R.id.button_name_heading);

        supervisornameconcerned = findViewById(R.id.supervisornameconcerned);
        supervisoremailconcerned = findViewById(R.id.supervisoremailconcerned);
        supervisorotheremailconcerned = findViewById(R.id.supervisorotheremailconcerned);

        //


        //
        if (observationType != null) {
            if (observationType.equals("Hazard")) {
                String fullHeading = "HAZARD DETAILS (" +mainheading+ ")";
                mainheadingtxtview.setText(fullHeading.toString());
            } else if (observationType.equalsIgnoreCase("Near Miss")) {
                mainheadingtxtview.setText("NEAR MISS DETAILS");
            } else if (observationType.equalsIgnoreCase("Accident")) {
                mainheadingtxtview.setText("ACCIDENT DETAILS");
            }
        } else {
            mainheadingtxtview.setText("DETAILS OF ( " +observationId+" )");
        }
        ///


        ///

        customadapter1 = new Observation_ItemsAdapter(this, hazardItems);
        listViewww.setAdapter(customadapter1);
//            listViewww.setSaveEnabled(true);
        String authToken = "Bearer " + getTokenFromSharedPreferences();

fetchdata(authToken,observationId);


imageViewback.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(Listviewclick.this,HazradActivity.class);
        startActivity(i);
        finish();

    }
});
    }
     private void fetchdata(String authToken,String observationId){
//                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://vserveq.voltasworld.com/")
//                 .addConverterFactory(GsonConverterFactory.create())
//                 .build();


         RetrofitConnect retrofitConnect = new RetrofitConnect();
         DataAPi dataApi = retrofitConnect.getLink().create(DataAPi.class);
//        Call<ObservationItemsDetailsModel> call1 = dataApi.getObservationById(authToken,observationId);

         Call<ListviewClickpojo> call = dataApi.getObservationById(authToken,observationId);
call.enqueue(new Callback<ListviewClickpojo>() {

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onResponse(Call<ListviewClickpojo> call, Response<ListviewClickpojo> response) {
        if(response.isSuccessful()){
            ObservationItemsDetailsModel observationItemsDetailsModel = new ObservationItemsDetailsModel();

           ListDetailsResult listDetailsResult = response.body().getResult();

        if(listDetailsResult != null && observationItemsDetailsModel !=null)
            customernametxtview.setText(listDetailsResult.getCustomerName());
            customeraddresstxtview.setText(listDetailsResult.getAddress());
            customerbrancttxtview.setText(listDetailsResult.getGuestBranch());
            customerzonetxtview.setText(listDetailsResult.getGuestZone());
            hazardidtxtview.setText(listDetailsResult.getObservationId());
            hazardmnthyeartxtview.setText(listDetailsResult.getDateOfIncidence());
            employeenametxtview.setText(listDetailsResult.getUserName());
            employeeidtxtview.setText(listDetailsResult.getUserId());
            employeemailidtxtview.setText(listDetailsResult.getUserEmail());
             employeephonenumbertxtview.setText(listDetailsResult.getUserPhoneNumber());
             textviewinchargeid.setText(listDetailsResult.getProjectManagerId());
             textviewinchargephnenumbe.setText(listDetailsResult.getManagerPhoneNumber());
            textviewprjtinchargename.setText(listDetailsResult.getProjectManagerName());
            supervisornameconcerned.setText(listDetailsResult.getConcernEngineerOrSupervisor());
            button_name_heading.setText(listDetailsResult.getStatus());
            supervisoremailconcerned.setText(listDetailsResult.getConcernEngineerOrSupervisorEmail());


            StringBuilder stringBuilder = new StringBuilder();
            if(listDetailsResult.getOtherResponsiblepersons() != null){
            for(int j=0;j<listDetailsResult.getOtherResponsiblepersons().size(); j++) {
                stringBuilder.append(listDetailsResult.getOtherResponsiblepersons().get(j));
                if (j == listDetailsResult.getOtherResponsiblepersons().size() - 1) {
                } else {
                    stringBuilder.append(",");
                }
            }
                supervisorotheremailconcerned.setText(stringBuilder);
            }
//            supervisorotheremailconcerned.setText(stringBuilder);
            String dateofincidence = listDetailsResult.getDateOfIncidence();
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
                Date date = dateFormat.parse(dateofincidence);
                SimpleDateFormat dateOnlyFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                SimpleDateFormat timeOnlyFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                String datePart = dateOnlyFormat.format(date);
                String timePart = timeOnlyFormat.format(date);
                // Set date and time to your TextViews
                hazardmnthyeartxtview.setText(datePart);
                hazardhoursmintxtview.setText(timePart);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            hazardItems.addAll(listDetailsResult.getObservationItemsDetailsModels());
//            actionItems.addAll();

//                    attachmentModels.addAll(ObservationItemsDetailsModel);
        }

        customadapter1.notifyDataSetChanged();
    }


    @Override
    public void onFailure(Call<ListviewClickpojo> call, Throwable t) {

    }
});

     }

    private String getTokenFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("My Prefs", Context.MODE_PRIVATE);
        return sharedPreferences.getString("token", "");
    }
}