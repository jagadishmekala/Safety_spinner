package com.example.voltasassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class  AutoTextProj_Adapter extends ArrayAdapter<Autotext_Result> {
        private Context context;
        private List<Autotext_Result> userSearchResultPojos;
        public AutoTextProj_Adapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Autotext_Result> objects) {
            super(context, resource, textViewResourceId, objects);
            this.context = context;
            this.userSearchResultPojos = objects;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = convertView;
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.searchtext,parent,false);
            }
            TextView itemName = view.findViewById(R.id.searchItemNames);

            Autotext_Result userSearchResultPojo = userSearchResultPojos.get(position);
            itemName.setText(userSearchResultPojo.getUserName());
            return view;
        }
}
