package com.example.tourism_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowsAdapter extends RecyclerView.Adapter<ShowsAdapter.ViewHolder> {

    ArrayList<food> mList;
    private ShowsAdapter.RecyclerViewClickListener listener;

    public ShowsAdapter(ArrayList<food> mList, ShowsAdapter.RecyclerViewClickListener listener) {
        this.mList = mList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_list,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        food vacancy1 = mList.get(position);
        holder.txtbname.setText(vacancy1.getCityname());
        holder.txtaddress.setText(vacancy1.getFood1());
        holder.txttype.setText(vacancy1.getFood2());
        holder.txtfood3.setText(vacancy1.getFood3());
        holder.txtfood4.setText(vacancy1.getFood4());





    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView txtbname,txtaddress,txttype,txtfood3,txtfood4;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtbname = itemView.findViewById(R.id.txtbname);
            txtaddress = itemView.findViewById(R.id.txtprofile);
            txttype = itemView.findViewById(R.id.txtaddress);
            txtfood3 = itemView.findViewById(R.id.txtfood3);
            txtfood4 = itemView.findViewById(R.id.txtfood4);


        }
    }
}
