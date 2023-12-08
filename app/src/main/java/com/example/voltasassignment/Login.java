package com.example.voltasassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voltasassignment.retrofitinterface.RetrofitConnect;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
   ImageButton textView;
   EditText mTextid,mTextpswrd;
   Spinner spinner;
   SharedPreferences sharedPreferences;
   ArrayAdapter adapter;
    private  final String SPINNER_PREFERENCE_KEY = "spinnerChoice";

    private  final int PERMISSION_REQUEST_CODE = 123;

    private String[] permissions = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(Login.this,R.color.DashBoardTextColor));
        }
        getSupportActionBar().hide();
      textView = findViewById(R.id.save);
        boolean isAllFieldsChecked = false;
      mTextid = findViewById(R.id.edtemployeeid);
      mTextpswrd = findViewById(R.id.edtpassword);
      spinner = findViewById(R.id.spinner_countries);
      sharedPreferences = getSharedPreferences("My Prefs", Context.MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("userName", "");
        String savedPassword = sharedPreferences.getString("Password", "");

        mTextid.setText(savedUsername);
        mTextpswrd.setText(savedPassword);

        adapter = ArrayAdapter.createFromResource(this, R.array.Coutries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       spinner.setAdapter(adapter);
        String selectedCountry = sharedPreferences.getString("selectedCountry", "");
        if (!selectedCountry.isEmpty()){
            int position = adapter.getPosition(selectedCountry);
            if (position >= 0) {
                spinner.setSelection(position);
            }
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                String newSelectedCountry = parent.getItemAtPosition(position).toString();

                // Save the newly selected country in SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("selectedCountry", newSelectedCountry);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    RetrofitData();
                }


            }


        });

        if (!arePermissionsGranted()) {
            requestPermissions();
        } else {
        }
    }

    private boolean arePermissionsGranted() {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {

            boolean allPermissionsGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false;
                    break;
                }
            }
            if (allPermissionsGranted) {

            } else {

            }
        }
    }
    public void RetrofitData(){
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://vserveq.voltasworld.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        DataAPi dataAPi = retrofit.create(DataAPi.class);
        RetrofitConnect retrofitConnect = new RetrofitConnect();
        DataAPi dataAPi = retrofitConnect.getLink().create(DataAPi.class);
        String username =mTextid.getText().toString();
        String password = mTextpswrd.getText().toString();
        LoginData loginData = new LoginData();
        loginData.setLoginName(username);
        loginData.setPassword(password);
        loginData.setImei("");
        Call<LoginResponse> call = dataAPi.Signin(loginData);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    LoginResponse loginResponse = response.body();
                    if(loginResponse!=null && loginResponse.getSuccess()) {
                        LoginResult result = loginResponse.getResult();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("userId", result.getUserId());
                        editor.putString("userName", username);
                        editor.putString("Password", password);
                        editor.putString("token", result.getToken());
                        editor.commit();
                        Intent i = new Intent(Login.this, Dashboard.class);
                        startActivity(i);

                    } else {
                            String errorMessage =  loginResponse.getErrors()[0];
                            showErrorDialog("Login Failed", errorMessage);
                        }
                    } else {
                        showErrorDialog("API Error", "API call failed.");

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                showErrorDialog("Network Error", "Network error. Please try again.");

            }
        });

    }
    private void showErrorDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private boolean validateForm() {
        String username = mTextid.getText().toString().trim();
        String password = mTextpswrd.getText().toString().trim();

        boolean isValid = true;

        if (username.isEmpty()) {
            mTextid.setError("Username is required");
            mTextid.requestFocus();
            Toast.makeText(Login.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
            return false;
        }

        else if(password.length() == 0) {
            mTextpswrd.setError("Password is required");
            mTextpswrd.requestFocus();
            Toast.makeText(Login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            return false;
        }else {

        }

        return isValid;
    }


}


