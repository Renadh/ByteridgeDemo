package com.jindagi.byteridge.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.jindagi.byteridge.R;

public class CustomViewHolders extends RecyclerView.ViewHolder  {

    public ImageView galleryPhoto;

    public CustomViewHolders(View itemView) {
        super(itemView);
        galleryPhoto = (ImageView) itemView.findViewById(R.id.galleryImage);
    }
}
