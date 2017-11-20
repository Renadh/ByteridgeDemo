package com.jindagi.byteridge.ui.adapters;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private int spacing;

    //This is not yet done completely, just to checking working we have included a spacing.
    public DividerItemDecoration(int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.right = spacing;
    }
}