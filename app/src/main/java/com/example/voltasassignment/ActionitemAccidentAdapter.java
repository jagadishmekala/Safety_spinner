package com.example.voltasassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ActionitemAccidentAdapter extends BaseAdapter {
    private Context context;
    private List<ItemsActionDetailsModel> injuredDetailsModelaction;

    public ActionitemAccidentAdapter(Context context, List<ItemsActionDetailsModel> injuredDetailsModel) {
        this.context = context;
        this.injuredDetailsModelaction = injuredDetailsModel;
    }



    @Override
    public int getCount() {
        return injuredDetailsModelaction.size();
    }

    @Override
    public Object getItem(int position) {
        return injuredDetailsModelaction.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.accident_actionitems, parent, false);
        }

        // Get the data item for this position
        ItemsActionDetailsModel item =injuredDetailsModelaction.get(position);

        // Find the views in your layout for every TextView
        //  TextView titleTextView = convertView.findViewById(R.id.title);
        TextView actionTaken1TextView = convertView.findViewById(R.id.textviewactiontaken1nearmiss);
        TextView description1TextView = convertView.findViewById(R.id.textviewdescriptionofaction1nearmiss);
        TextView correctiveAction1TextView = convertView.findViewById(R.id.textViewcorrectiveactionandpreventive1nearmiss);
        TextView actionStatus1TextView = convertView.findViewById(R.id.textactionstatus1nearmiss);
        //  TextView actionItem2TextView = convertView.findViewById(R.id.textnearmissaction2);
//        TextView actionTaken2TextView = convertView.findViewById(R.id.textviewactiontaken2nearmiss);
//        TextView description2TextView = convertView.findViewById(R.id.textviewdescriptionofaction2nearmiss);
//        TextView correctiveAction2TextView = convertView.findViewById(R.id.textViewcorrectiveactionandpreventive2nearmiss);
//        TextView actionStatus2TextView = convertView.findViewById(R.id.textactionstatus2nearmiss);

        // Populate the TextViews with data from the item
        // titleTextView.setText(item.getActionName());
        actionTaken1TextView.setText(item.getActionName());
        description1TextView.setText(item.getDescription());
        correctiveAction1TextView.setText(item.getCpRemarks());
        actionStatus1TextView.setText(item.getStatus());
        //  actionItem2TextView.setText(item.getActionName());
//        actionTaken2TextView.setText(item.getActionName());
//        description2TextView.setText(item.getDescription());
//        correctiveAction2TextView.setText(item.getCpRemarks());
//        actionStatus2TextView.setText(item.getStatus());

        return convertView;
    }


}



