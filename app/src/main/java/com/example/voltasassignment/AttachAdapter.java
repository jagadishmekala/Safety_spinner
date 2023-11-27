package com.example.voltasassignment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AttachAdapter extends BaseAdapter {
    private Context context;
    private List<ObservationAttachmentModel> attachment;

    public AttachAdapter(Context context, List<ObservationAttachmentModel> attachment) {
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

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.attachments, parent, false);
        }
        ImageView attachmentImageView = convertView.findViewById(R.id.imageViewAttachment);
//

        ///

        ObservationAttachmentModel attachmentUrl = (ObservationAttachmentModel) getItem(position);



        attachmentImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showImagePopup(attachmentUrl.getUrl(), v);

            }
            ///


            ///
        });
        return convertView;
    }

    @SuppressLint("ResourceAsColor")
    private void showImagePopup(final String imageUrl, View anchorView) {
        View popupView = LayoutInflater.from(context).inflate(R.layout.attachments, null);
        ImageView popupImageView = popupView.findViewById(R.id.imageViewAttachment);
        final PopupWindow popupWindow = new PopupWindow(
                popupView, ViewGroup.LayoutParams.MATCH_PARENT, 1600, true
        );

        Picasso.get().load(imageUrl).into(popupImageView);
        ImageButton closeButton = new ImageButton(context);

        closeButton.setBackgroundResource(R.drawable.baseline_close_24); // Set background color

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();
            }
        });
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        params.setMargins(0, 0, 0, 0); // Adjust margins as needed

        ((RelativeLayout) popupView).addView(closeButton, params);
        popupWindow.showAsDropDown(anchorView, 0, 0);
    }
}
