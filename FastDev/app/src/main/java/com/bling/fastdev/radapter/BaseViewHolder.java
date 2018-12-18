package com.bling.fastdev.radapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

public class BaseViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> views;
    private View convertView;
    private int viewType;
    private int position;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.convertView=itemView;
        this.views=new SparseArray<>();
    }
    public BaseViewHolder(View itemView,int viewType) {
         this(itemView);
         this.viewType=viewType;
    }
    public  <T extends View> T get( int id) {
        View childView = views.get(id);
        if (childView == null) {
            childView = convertView.findViewById(id);
            views.put(id, childView);
        }
        return (T) childView;
    }


    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public int getCurPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
