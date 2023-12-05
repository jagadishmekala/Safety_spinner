package com.example.voltasassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.example.voltasassignment.retrofitinterface.RetrofitConnect;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback{
    private GoogleMap myMap;
    List<MapResult> gpsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(MapsActivity.this,R.color.DashBoardTextColor));
        }
        SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapview);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myMap = googleMap;
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(20.5937, 78.9629))
                .zoom(5.0f)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        locationservice();
//        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(@NonNull LatLng latLng) {
//                MarkerOptions marker  = new MarkerOptions();
//
//                marker.position(latLng);
//                marker.title(latLng.latitude+"Jags"+latLng.longitude);
//                googleMap.clear();
//                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,20));
//                googleMap.addMarker(marker);
//            }
//        });

        }


    public void locationmarkers(List<MapResult> list){
        // Create the marker options
//        MarkerOptions markerOptions = new MarkerOptions();
//
//// Get the drawable resources for each status
//        int openMarkerDrawableResId = R.drawable.open_loc_vect;
//        int assignedMarkerDrawableResId = R.drawable.assigned_loc_vector;
//        int closedMarkerDrawableResId = R.drawable.close_vector_loc;
//
//// Iterate through the list of locations
//        for (int i = 0; i < list.size(); i++) {
//            // Get the status of the current location
//            String status = list.get(i).getStatus();
//
//            // Set the marker icon based on the status
//            if (status.equalsIgnoreCase("Open")) {
//                markerOptions.icon(BitmapDescriptorFactory.fromResource(openMarkerDrawableResId));
//            } else if (status.equalsIgnoreCase("Assigned")) {
//                markerOptions.icon(BitmapDescriptorFactory.fromResource(assignedMarkerDrawableResId));
//            } else {
//                markerOptions.icon(BitmapDescriptorFactory.fromResource(closedMarkerDrawableResId));
//            }
//
//            // Add the marker to the map
//            myMap.addMarker(markerOptions);
//        }
        for (int i = 0; i < list.size(); i++) {

            double latitude = Double.parseDouble(list.get(i).getLatitude());
            double longitude = Double.parseDouble(list.get(i).getLongitude());

            LatLng latLng = new LatLng(latitude, longitude);
            MarkerOptions markerOptions = new MarkerOptions().position(latLng);

//            marker = map.addMarker(new MarkerOptions()
//                    .position(new LatLng(latitude, longitude))
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.your_image_filename)));

//            if (list.get(i).getStatus().equalsIgnoreCase("Open")) {
//                myMap.addMarker(markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.open_loc_vect)));
//
//            } else if (list.get(i).getStatus().equalsIgnoreCase("Assigned")) {
//                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.assigned_loc_vector));
//            } else {
//                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.close_vector_loc));
//            }

            if (list.get(i).getStatus().equalsIgnoreCase("Open")) {
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));}
            else if (list.get(i).getStatus().equalsIgnoreCase("Assigned")) {
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
            } else if(list.get(i).getStatus().equalsIgnoreCase("Closed")){
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            }
            else{

            }
            myMap.addMarker(markerOptions);
        }
    }
    private void locationservice(){
        SharedPreferences sharedPreferences = getSharedPreferences("My Prefs", Context.MODE_PRIVATE);
        RetrofitConnect retrofitConnect = new RetrofitConnect();
        String token = sharedPreferences.getString("token", "");
        DataAPi dataApi = retrofitConnect.getLink().create(DataAPi.class);
        Call<MapResponse> call=dataApi.getmapobservations("Bearer " +token);
        call.enqueue(new Callback<MapResponse>() {
            @Override
            public void onResponse(Call<MapResponse> call, Response<MapResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResult() != null) {
                        MapResponse mapResponse = response.body();
                        if (mapResponse.getSuccess()) {

                            gpsList = new ArrayList<>();
                            if (response.body().getResult() != null) {
                                for (MapResult mapResult : response.body().getResult()) {
                                    if (mapResult.getLatitude() != null && mapResult.getLatitude().length() > 0) {
                                        gpsList.add(mapResult);
                                    }
                                }
                                locationmarkers(gpsList);
                            }
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<MapResponse> call, Throwable t) {
                Toast.makeText(MapsActivity.this, "Internet failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}