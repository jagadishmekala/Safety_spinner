package com.example.voltasassignment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
public class Observation_ItemsAdapter extends BaseAdapter {
    private Context context;
    private ListView listViewaction;

    private List<ObservationItemsDetailsModel> hazardItems;
//    private List<ObservationAttachmentModel> attachment = new ArrayList<>();
//    private List<ItemsActionDetailsModel> actionitems = new ArrayList<>();

    public Observation_ItemsAdapter(Context context, List<ObservationItemsDetailsModel> hazardItems) {
        this.context = context;
        this.hazardItems = hazardItems;
    }

    @Override
    public int getCount() {
        return hazardItems.size();
    }

    @Override
    public Object getItem(int position) {
        return hazardItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("LongLogTag")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ObservationItemsDetailsModel observationItemsDetailsModel = (ObservationItemsDetailsModel) getItem(position);
        String observationType = observationItemsDetailsModel.getReason();

        if (convertView == null) {
//            ObservationItemsDetailsModel observationItemsDetailsModel = (ObservationItemsDetailsModel) getItem(position);
//            String observationType = observationItemsDetailsModel.getReason();
            if ("Hazard".equalsIgnoreCase(observationType)) {
//                 Log.d("Observation_ItemsAdapter", "Observation Type: " + observationType);
                convertView = LayoutInflater.from(context).inflate(R.layout.observation_itemsadapter_activity, parent, false);
                TextView VerticalTextview = convertView.findViewById(R.id.textViewvertical);
                TextView Hazardtpyetextview = convertView.findViewById(R.id.textViewhazardtype);
                TextView Risktypetextview = convertView.findViewById(R.id.textViewrisk);
                TextView Categorytextview = convertView.findViewById(R.id.textViewcategory);
                TextView Briefdescriptiontxtview = convertView.findViewById(R.id.textViewbriefdescription);
                TextView subcontractartxtview = convertView.findViewById(R.id.textViewsubcontractor);
                GridView attachgridviewhazard = convertView.findViewById(R.id.gv);
                TextView textViewinjury = convertView.findViewById(R.id.textViewinjury);
                TextView textViewcorrectiveaction = convertView.findViewById(R.id.textViewcorrectiveaction);
                TextView textViewsubcontractor = convertView.findViewById(R.id.textViewsubcontractor);
                TextView textViewprevention = convertView.findViewById(R.id.textViewprevention);
                ListView listViewaction = convertView.findViewById(R.id.listaction);

//                ObservationItemsDetailsModel observationItemsDetailsModel1 = (ObservationItemsDetailsModel) getItem(position);
                VerticalTextview.setText(observationItemsDetailsModel.getVertical());
                Hazardtpyetextview.setText(observationItemsDetailsModel.getHazardType());
                Risktypetextview.setText(observationItemsDetailsModel.getRisk());
                Categorytextview.setText(observationItemsDetailsModel.getCategory());
                Briefdescriptiontxtview.setText(observationItemsDetailsModel.getBriefDescription());
                subcontractartxtview.setText(observationItemsDetailsModel.getSubcontractor());
                textViewinjury.setText(observationItemsDetailsModel.getDescription2());
                textViewprevention.setText(observationItemsDetailsModel.getPreventiveAction());
                textViewsubcontractor.setText(observationItemsDetailsModel.getSubcontractor());
                textViewcorrectiveaction.setText(observationItemsDetailsModel.getCorrectiveAction());


//calling attach
                if (observationItemsDetailsModel.getObservationAttachmentModels() != null) {
                    AttachAdapter attachAdapter = new AttachAdapter(context, observationItemsDetailsModel.getObservationAttachmentModels());
                    attachgridviewhazard.setAdapter(attachAdapter);
                }
                //calling action
                if (observationItemsDetailsModel.getItemsActionDetailsModels() != null) {
                    ActionAdapter actionAdapter = new ActionAdapter(context, observationItemsDetailsModel.getItemsActionDetailsModels());
                    listViewaction.setAdapter(actionAdapter);
                }


            }
            else if ("Near Miss".equalsIgnoreCase(observationType)) {

                convertView = LayoutInflater.from(context).inflate(R.layout.nearmissdetails, parent, false);

                TextView VerticalTextviewnearmiss = convertView.findViewById(R.id.textViewverticalnearmiss);
                TextView Hazardtpyetextviewnearmiss = convertView.findViewById(R.id.textViewhazardtypenearmiss);
                TextView Risktypetextviewnearmiss = convertView.findViewById(R.id.textViewrisknearmiss);
                TextView Categorytextviewnearmiss = convertView.findViewById(R.id.textViewcategorynearmiss);
                TextView Briefdescriptiontxtviewnearmiss = convertView.findViewById(R.id.textViewbriefdescriptionnearmiss);
                TextView Injurythatcouldhappendnearmiss = convertView.findViewById(R.id.textViewinjurynearmiss);
                TextView Correctiveactionnearmiss = convertView.findViewById(R.id.textViewcorrectiveactionnearmiss);
                TextView Preventactionnearmiss = convertView.findViewById(R.id.textViewprventiveactionnearmiss);
                TextView Rootcausenearmiss = convertView.findViewById(R.id.textViewrootcausenearmiss);
                TextView Analysisnearmiss = convertView.findViewById(R.id.textViewanalysisnearmiss);
                TextView Classificationnearmiss = convertView.findViewById(R.id.textViewclassificationnearmiss);
                TextView Remarksreasonwhynearmiss = convertView.findViewById(R.id.textViewremarkswhynearmiss);
                TextView subcontractartxtview = convertView.findViewById(R.id.textViewSubContractorNearMiss);
                GridView attachgridviewnearmiss = convertView.findViewById(R.id.gv_attachmentsnearmiss);
                ListView listViewactionnearmiss = convertView.findViewById(R.id.listactionnearmiss);


                VerticalTextviewnearmiss.setText(observationItemsDetailsModel.getVertical());
                Hazardtpyetextviewnearmiss.setText(observationItemsDetailsModel.getHazardType());
                Risktypetextviewnearmiss.setText(observationItemsDetailsModel.getRisk());
                Categorytextviewnearmiss.setText(observationItemsDetailsModel.getCategory());
                Briefdescriptiontxtviewnearmiss.setText(observationItemsDetailsModel.getBriefDescription());
                Injurythatcouldhappendnearmiss.setText(observationItemsDetailsModel.getInjured());
                Correctiveactionnearmiss.setText(observationItemsDetailsModel.getCorrectiveAction());
                Preventactionnearmiss.setText(observationItemsDetailsModel.getPreventiveAction());
                Rootcausenearmiss.setText(observationItemsDetailsModel.getRouteCause());
                Analysisnearmiss.setText(observationItemsDetailsModel.getAnalysis());
                Classificationnearmiss.setText(observationItemsDetailsModel.getClassification());
                subcontractartxtview.setText(observationItemsDetailsModel.getSubcontractor());
                Remarksreasonwhynearmiss.setText(observationItemsDetailsModel.getReason());


                Gridnearmissadapter gridnearmissadapter = new Gridnearmissadapter(context, observationItemsDetailsModel.getObservationAttachmentModels());
                attachgridviewnearmiss.setAdapter(gridnearmissadapter);
                attachgridviewnearmiss.setTextFilterEnabled(true);

                NearMissActionitemsAdapter nearMissActionitemsAdapter = new NearMissActionitemsAdapter(context, observationItemsDetailsModel.getItemsActionDetailsModels());
                listViewactionnearmiss.setAdapter(nearMissActionitemsAdapter);
                listViewactionnearmiss.setTextFilterEnabled(true);
            }
            else{
                convertView = LayoutInflater.from(context).inflate(R.layout.accident_item_details, parent, false);
                TextView Incidenttypeaccident = convertView.findViewById(R.id.tv_incident);
                TextView Risktypeaccident = convertView.findViewById(R.id.tv_risk);
                TextView Isanyinjuredaccident = convertView.findViewById(R.id.tv_injured);
                TextView InjuredBreifdescriptionofincident = convertView.findViewById(R.id.tv_description);
                TextView InjuredDirectcause = convertView.findViewById(R.id.tv_directcause);
                TextView injuredUnderLyingcause = convertView.findViewById(R.id.tv_underlaying_cause);
                TextView Injuredrootcause = convertView.findViewById(R.id.tv_rootcause);
                TextView Injuredcorrectiveaction = convertView.findViewById(R.id.tv_Corrective_Action);
                TextView Injuredpreventiveaction = convertView.findViewById(R.id.tv_Preventive_Action);
                TextView Injuredsubcontractor = convertView.findViewById(R.id.tv_Subcontractor);
                TextView Injuredclassification = convertView.findViewById(R.id.tv_Classification);
                TextView Analysis=convertView.findViewById(R.id.tv_Analysis);
                TextView Injuredremarkreasonwhy = convertView.findViewById(R.id.tv_Remark_reason);
                ListView listaccidentinjuredpresondetails = convertView.findViewById(R.id.listview_accident_actionitems);
                ListView listaccidentaction = convertView.findViewById(R.id.incidentItem_List);
                GridView gridaccident_image = convertView.findViewById(R.id.gv_accident);

                Incidenttypeaccident.setText(observationItemsDetailsModel.getIncidentType());
                Risktypeaccident.setText(observationItemsDetailsModel.getRisk());
                Isanyinjuredaccident.setText(observationItemsDetailsModel.getInjured());
                InjuredBreifdescriptionofincident.setText(observationItemsDetailsModel.getBriefDescription());
                InjuredDirectcause.setText(observationItemsDetailsModel.getDirectCause());
                injuredUnderLyingcause.setText(observationItemsDetailsModel.getUnderlayingCause());
                Injuredrootcause.setText(observationItemsDetailsModel.getRouteCause());
                Injuredcorrectiveaction.setText(observationItemsDetailsModel.getCorrectiveAction());
                Injuredpreventiveaction.setText(observationItemsDetailsModel.getPreventiveAction());
                Injuredsubcontractor.setText(observationItemsDetailsModel.getSubcontractor());
                Injuredclassification.setText(observationItemsDetailsModel.getClassification());
                Analysis.setText(observationItemsDetailsModel.getAnalysis());
                Injuredremarkreasonwhy.setText(observationItemsDetailsModel.getRemarkreason());

                ActionitemAccidentAdapter actionitemAccidentAdapter = new ActionitemAccidentAdapter(context, observationItemsDetailsModel.getItemsActionDetailsModels());
                listaccidentinjuredpresondetails.setAdapter(actionitemAccidentAdapter);

                InjuredPersonDetails injuredPersonDetails= new InjuredPersonDetails(context, observationItemsDetailsModel.getInjuredDetailsModels());
                listaccidentaction.setAdapter(injuredPersonDetails);
//
             Accident_GridAdapter accidentGridAdapter=new Accident_GridAdapter(context, observationItemsDetailsModel.getObservationAttachmentModels());
                gridaccident_image.setAdapter(accidentGridAdapter);
//                Gridnearmissadapter gridnearmissadapter = new Gridnearmissadapter(context, observationItemsDetailsModel.getObservationAttachmentModels());
//                gridaccident_image.setAdapter(gridnearmissadapter);
            }
            } return convertView;

        }
    }




