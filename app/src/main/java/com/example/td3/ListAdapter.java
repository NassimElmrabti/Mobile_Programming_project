package com.example.td3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<FinalFantasy> values;
    private static OnFFListener OnFFListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnFFListener OnFFListener;
        ImageView mImg = itemView.findViewById(R.id.icon);
        TextView txtHeader;
        TextView txtFooter;
        View layout;

        public ViewHolder(View v, OnFFListener onFFListener) {
            super(v);
            layout = v;
            txtHeader = v.findViewById(R.id.firstLine);
            txtFooter = v.findViewById(R.id.secondLine);
            this.OnFFListener = onFFListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            OnFFListener.OnFFClick(getAdapterPosition());
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
    public ListAdapter(List<FinalFantasy> myDataset, OnFFListener OnFFListener) {
        values = myDataset;
        ListAdapter.OnFFListener = OnFFListener;
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
        ViewHolder vh = new ViewHolder(v, OnFFListener);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final FinalFantasy currentFinalFantasy = values.get(position);
        holder.txtHeader.setText(currentFinalFantasy.getName());

        Picasso.get().load(currentFinalFantasy.getImageUrl()).resize(300,300).into(holder.mImg);

        holder.txtFooter.setText(currentFinalFantasy.getAnnee());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

    public interface OnFFListener{
        void OnFFClick(int position);
    }

}