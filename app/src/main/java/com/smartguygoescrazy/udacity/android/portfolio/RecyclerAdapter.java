package com.smartguygoescrazy.udacity.android.portfolio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tiago on 15/08/2017.
 */

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    private ArrayList<Item> items;
    OnItemClickListener onItemClickListener;
    private Context context;

    RecyclerAdapter(Context context, ArrayList<Item> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);

        holder.title.setText(item.getTitle());
        Picasso.with(context).load(item.getImageRes()).into(holder.bgImage);
        holder.description.setText(item.getShortDescription());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_title)
        TextView title;
        @BindView(R.id.row_desc)
        TextView description;

        @BindView(R.id.row_img)
        ImageView bgImage;

        @BindView(R.id.item_container)
        View container;

        @BindView(R.id.overlay)
        ImageView overlay;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onClick(items.get(getAdapterPosition()), bgImage, title, overlay);
                    }
                }
            });
        }
    }


    interface OnItemClickListener {
        void onClick(Item item, View... views);
    }

    void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
