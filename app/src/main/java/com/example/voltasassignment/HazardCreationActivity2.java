package com.example.voltasassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.voltasassignment.retrofitinterface.RetrofitConnect;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HazardCreationActivity2 extends AppCompatActivity {
    ArrayAdapter verticalAdapter;
    ArrayList<String> verticalList;
    TextView uploaddatas,cancel_txt_back;
    ImageView uploaddatashow;

    String verticalId;
    List<EntityObservationResult> EntityNamesArrayList;
    Spinner spin_vertical_create, spin_hazardtype_create, spin_risktype_create, spin_category_create;
    int SELECT_PICTURE = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hazard_creation2);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(HazardCreationActivity2.this, R.color.DashBoardTextColor));
        }
        cancel_txt_back = findViewById(R.id.cancel_txt_back);
        spin_vertical_create = findViewById(R.id.spin_vertical_create);
        spin_hazardtype_create = findViewById(R.id.spin_hazardtype_create);
        spin_risktype_create = findViewById(R.id.spin_risktype_create);
        spin_category_create = findViewById(R.id.spin_category_create);
        uploaddatas =findViewById(R.id.uploaddatas);
        uploaddatashow = findViewById(R.id.uploaddatashow);
        uploaddatas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadpicker();
            }
        });
        cancel_txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HazardCreationActivity2.this,HazardCreationActivity.class));
            }
        });
        spin_vertical_create.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                verticalcreate();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                verticalcreate();

            }
        });

    }
    void uploadpicker() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    uploaddatashow.setImageURI(selectedImageUri);
                }
            }
        }
    }



    public void verticalcreate(){
        SharedPreferences sharedPreferences = getSharedPreferences("My Prefs", Context.MODE_PRIVATE);
        RetrofitConnect retrofitConnect = new RetrofitConnect();
        String token = sharedPreferences.getString("token","");
        DataAPi dataAPi= retrofitConnect.getLink().create(DataAPi.class);
        Call<EntityObservationResponse>call = dataAPi.getEntitynames("Bearer" + token,null);
        call.enqueue(new Callback<EntityObservationResponse>() {
            @Override
            public void onResponse(Call<EntityObservationResponse> call, Response<EntityObservationResponse> response) {
//                if(response.isSuccessful()){
//                    if(response.body().getResult()!=null){
//                        EntityObservationResponse  entityObservationResponse=response.body();
//                        if(entityObservationResponse.getSuccess()){
//                            EntityNamesArrayList = new ArrayList<>();
//                            EntityNamesArrayList.addAll(response.body().getResult());
//                            verticalAdapter = new ArrayAdapter<>(HazardCreationActivity2.this,android.R.layout.simple_list_item_1,EntityNamesArrayList);
//                    verticalAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
//                    spin_vertical_create.setAdapter(verticalAdapter);
////
//                        }
//                    }
//                }


                verticalList.add("Select Vertical*");

                if(response.isSuccessful()){
                    for(int i=0; i < response.body().getResult().size();i++){
                        verticalList.add(response.body().getResult().get(i).getEntityName());
                    }
                    verticalAdapter = new ArrayAdapter<>(HazardCreationActivity2.this,android.R.layout.simple_list_item_1,verticalList);
                    verticalAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
                    spin_vertical_create.setAdapter(verticalAdapter);

                }
            }
            @Override
            public void onFailure(Call<EntityObservationResponse> call, Throwable t) {

            }
        });
    }


}