package com.example.voltasassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class ActionAdapter extends BaseAdapter {
    private Context context;
    private List<ItemsActionDetailsModel> attachment;

    public ActionAdapter(Context context, List<ItemsActionDetailsModel> attachment) {
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.observation_actionitems, parent, false);
            TextView txtaction1 = convertView.findViewById(R.id.textviewactiontaken1);
            TextView txtactiondescription1 = convertView.findViewById(R.id.textviewdescriptionofaction1);
            TextView actionstatus1 = convertView.findViewById(R.id.textactionstatus1);
            TextView correctiveandpreventive = convertView.findViewById(R.id.textViewcorrectiveactionandpreventive);

            ItemsActionDetailsModel itemsActionDetailsModel = (ItemsActionDetailsModel) getItem(position);

            txtactiondescription1.setText(itemsActionDetailsModel.getDescription());
            txtaction1.setText(itemsActionDetailsModel.getActionName());
            actionstatus1.setText(itemsActionDetailsModel.getStatus());
            correctiveandpreventive.setText(itemsActionDetailsModel.getCpRemarks());
        }
        return convertView;

    }
}