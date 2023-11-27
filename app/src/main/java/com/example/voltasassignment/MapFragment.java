package com.example.voltasassignment;


import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.voltasassignment.retrofitinterface.RetrofitConnect;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_map,container,false);
        SupportMapFragment supportMapFragment= (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.MY_MAP);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(20.5937, 78.9629))
                        .zoom(4.6f)
                        .build();

                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                googleMap.getUiSettings().setZoomControlsEnabled(true);
                googleMap.getUiSettings().setZoomGesturesEnabled(true);
                googleMap.getUiSettings().setCompassEnabled(true);

                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                  //  CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(20.5937, 78.9629)).zoom(4.6f).build();

                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        MarkerOptions markerOptions= new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(latLng.latitude+"Jags"+latLng.longitude);
                        markerOptions.contentDescription("Jagadish");
                        googleMap.clear();
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,20));
                        googleMap.addMarker(markerOptions);

                    }
                });
            }
        });
        return view;
    }
    List<ListDetailsResult> gpsList;
//private void locationmarker(){
//
//    SharedPreferences sharedPreferences = getSharedPreferences("Bearer", MODE_PRIVATE);
//    RetrofitConnect retrofitConnect = new RetrofitConnect();
//    DataAPi dataApi = retrofitConnect.getLink().create(DataAPi.class);
//    Call<ListviewClickpojo> call=dataApi.getAllObservations("Bearer" +sharedPreferences.toString()+"Bearer Token", null);
//
//}



}

