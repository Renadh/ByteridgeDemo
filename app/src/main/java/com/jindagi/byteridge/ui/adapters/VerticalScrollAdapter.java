package com.jindagi.byteridge.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.JsonObject;
import com.jindagi.byteridge.R;

import java.util.List;
/**
 * Created by Jindagi on 11/18/2017.
 */


public class VerticalScrollAdapter extends RecyclerView.Adapter<CustomViewHolders> {

    private List<JsonObject> itemList;
    private Context context;

    public VerticalScrollAdapter(Context context, List<JsonObject> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public CustomViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_list, null);
        return new CustomViewHolders(layoutView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolders holder, int position) {

        RequestOptions options = new RequestOptions();
        options.centerInside();

        holder.itemView.layout(0,0,0,0);
        Glide.with(context)
                .load(itemList.get(position).get("kids_url").getAsString())
                .apply(options)
                .into(holder.galleryPhoto);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}

