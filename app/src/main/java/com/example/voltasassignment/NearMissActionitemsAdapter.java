package com.example.voltasassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class NearMissActionitemsAdapter extends BaseAdapter {

    private Context context;
    private List<ItemsActionDetailsModel> attachmentnearmiss;

    public NearMissActionitemsAdapter(Context context, List<ItemsActionDetailsModel> attachment) {
        this.context = context;
        this.attachmentnearmiss = attachment;
    }


    public int getCount() {
        return attachmentnearmiss.size();
    }


    public Object getItem(int position) {
        return attachmentnearmiss.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.nearmissactionview, parent, false);
        }

        // Get the data item for this position
        ItemsActionDetailsModel item = attachmentnearmiss.get(position);

        // Find the views in your layout for every TextView
      TextView titleTextView = convertView.findViewById(R.id.title);
        TextView actionTaken1TextView = convertView.findViewById(R.id.textviewactiontaken1);
        TextView description1TextView = convertView.findViewById(R.id.textviewdescriptionofaction1);
        TextView correctiveAction1TextView = convertView.findViewById(R.id.textViewcorrectiveactionandpreventive);
        TextView actionStatus1TextView = convertView.findViewById(R.id.textactionstatus1);

        actionTaken1TextView.setText(item.getActionName());
        description1TextView.setText(item.getDescription());
        correctiveAction1TextView.setText(item.getCpRemarks());
        actionStatus1TextView.setText(item.getStatus());


        return convertView;
    }


}