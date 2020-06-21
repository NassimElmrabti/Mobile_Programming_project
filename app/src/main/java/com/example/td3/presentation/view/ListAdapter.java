package com.example.td3.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.td3.R;
import com.example.td3.presentation.model.FinalFantasy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<FinalFantasy> values;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(FinalFantasy item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg = (ImageView) itemView.findViewById(R.id.icon);
        TextView txtHeader;
        TextView txtFooter;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }

    public void add(int position, FinalFantasy item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    private void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(List<FinalFantasy> myDataset, OnItemClickListener listener) {
        this.values = myDataset;
        this.listener = listener;
    }

    public void setListener(OnItemClickListener listener){
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final FinalFantasy currentFinalFantasy = values.get(position);
        holder.txtHeader.setText(currentFinalFantasy.getName());
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position);
            }
        });

        Picasso.get().load(currentFinalFantasy.getImageUrl()).resize(300,300).into(holder.mImg);

        holder.txtFooter.setText(currentFinalFantasy.getAnnee());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
           @Override public void onClick(View v){
               listener.onItemClick(currentFinalFantasy);
           }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}