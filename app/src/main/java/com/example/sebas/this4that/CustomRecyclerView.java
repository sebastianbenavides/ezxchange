package com.example.sebas.this4that;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sebas on 4/26/2017.
 */

public class CustomRecyclerView extends RecyclerView.Adapter<CustomRecyclerView.ViewHolder> {

    Context context;
    String[] titles;
    View view;
    ViewHolder viewHolder;

    public CustomRecyclerView(Context context, String[] titles) {
        this.context = context;
        this.titles = titles;
    }

    @Override
    public CustomRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.card_listview_layout, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomRecyclerView.ViewHolder holder, int position) {

        holder.textView.setText(titles[position]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        String title;
        String image;

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.cardText);
        }
    }

}
