package com.example.voltasassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

public class InjuredPersonDetails extends BaseAdapter {
    private Context context;

   private List<InjuredDetailsModel> attachment;

    public InjuredPersonDetails(Context context, List<InjuredDetailsModel> attachment) {
        this.context = context;
        this.attachment = attachment;
    }
    @Override
    public int getCount() {
        return attachment.size();
    }

    @Override
    public Object getItem(int position) {
        return attachment.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ObservationItemsDetailsModel observationItemsDetailsModel = (ObservationItemsDetailsModel) getItem(position);
        InjuredDetailsModel injuredDetailsModel =(InjuredDetailsModel) getItem(position);
     //   InjuredDetailsModel injuredDetailsModel1=new InjuredDetailsModel(context, injuredDetailsModel.getObservationAttachmentModels());

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.accident_incident, parent, false);
            TextView txtname = convertView.findViewById(R.id.name_accident_txt);
            TextView txtdesignation = convertView.findViewById(R.id.designation_accident_txt);
            TextView actiongender = convertView.findViewById(R.id.gender_accident_txt);
            TextView txtage = convertView.findViewById(R.id.age_accident_txt);
            TextView txtincidenttime = convertView.findViewById(R.id.incidenttime_accident_txt);
            TextView txtemptype= convertView.findViewById(R.id.emptype_accident_txt);
            TextView txttreatment = convertView.findViewById(R.id.treatment_accident_txt);
            GridView gv_accident_photos =convertView.findViewById(R.id.gv_accident_photos);



            txtname.setText(injuredDetailsModel.getName());
            txtdesignation.setText(injuredDetailsModel.getDesignation());
            actiongender.setText(injuredDetailsModel.getGender());
             txtage.setText(injuredDetailsModel.getAge().toString());
            txtemptype.setText(injuredDetailsModel.getEmployeeType());
            txtincidenttime.setText(injuredDetailsModel.getInjuredDateTime());
            txttreatment.setText(injuredDetailsModel.getTreatment());
       //     gv_accident_photos.setAdapter(injuredDetailsModel1);
//            Accident_GridAdapter accidentGridAdapter = (Accident_GridAdapter) getItem(position);
//            Accident_GridAdapter
//            gv_accident_photos.setAdapter(accidentGridAdapter);

//            Accident_GridAdapter accidentGridAdapter=new Accident_GridAdapter(context,injuredDetailsModel.getObservationAttachmentModels());
//            gridaccident_image.setAdapter(accidentGridAdapter);

        }
        return convertView;

    }
}
