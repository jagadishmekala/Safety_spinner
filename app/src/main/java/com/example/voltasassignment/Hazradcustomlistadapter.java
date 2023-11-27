package com.example.voltasassignment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Hazradcustomlistadapter extends BaseAdapter {
    private Context context;
    private List<HazardResult> hazardResults;
    private List<HazardResult> filteredResults;


    public Hazradcustomlistadapter(@NonNull Context context, List<HazardResult> dataList) {
        this.context = context;
        this.hazardResults = dataList;
        this.filteredResults = new ArrayList<>(dataList);
    }

    @Override
    public int getCount() {
        return hazardResults.size();
    }

    @Nullable
    @Override
    public HazardResult getItem(int position) {
        return hazardResults.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.buttonlayouthazard, parent, false);
        }
        TextView observationIdTextView = convertView.findViewById(R.id.txtObservationid);
        TextView customerNameTextView = convertView.findViewById(R.id.txtname);
        TextView hazarddateandtime = convertView.findViewById(R.id.txthzrddateandtime);
        TextView StatusTextView = convertView.findViewById(R.id.txtstatus);
        TextView createdontime = convertView.findViewById(R.id.txtcreatedtimeanddate);
        TextView typename = convertView.findViewById(R.id.editTextTextPersonName9);
        TextView location = convertView.findViewById(R.id.location_convertview);

        HazardResult hazardResult = (HazardResult) getItem(position);

        customerNameTextView.setText(hazardResult.getCustomerName());
        observationIdTextView.setText(hazardResult.getObservationId());
        StatusTextView.setText(hazardResult.getField1());
        createdontime.setText(hazardResult.getCreatedOn());
        hazarddateandtime.setText(hazardResult.getDateOfIncidence());
         typename.setText(hazardResult.getReason());
        location.setText(hazardResult.getLocation());

        String status = hazardResult.getStatus();
        StatusTextView.setText(status);
        if ("Open".equalsIgnoreCase(status)) {

            StatusTextView.setTextColor(ContextCompat.getColor(context, R.color.open_text));
        } else if ("Closed".equalsIgnoreCase(status)) {

            StatusTextView.setTextColor(ContextCompat.getColor(context, R.color.close_text));
        } else if ("Assigned".equalsIgnoreCase(status)) {

            StatusTextView.setTextColor(ContextCompat.getColor(context, R.color.assigned_text));
        } else {

            StatusTextView.setTextColor(ContextCompat.getColor(context, android.R.color.black));
        }

        String createdOn = formatDate(hazardResult.getCreatedOn());
        createdontime.setText(createdOn);


        String dateOfIncidence = formatDate(hazardResult.getDateOfIncidence());
        hazarddateandtime.setText(dateOfIncidence);
        ////



        ////

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HazardResult hazardResult = getItem(position); // Get the clicked item
                String observationId = hazardResult.getObservationId();
                Intent intent = new Intent(context,Listviewclick.class);
                intent.putExtra("ObservationID",observationId);
                context.startActivity(intent);
            }
        });
        return convertView;

    }
    private String formatDate(String inputDate) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'  'HH:mm:ss");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            Date date = inputFormat.parse(inputDate);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return inputDate;
        }
    }
    private void listmainHeading (String ClickedHazardid){
        Intent i = new Intent (context,Listviewclick.class);
        i.putExtra("ObservationID",ClickedHazardid);
        context.startActivity(i);
    }
}





