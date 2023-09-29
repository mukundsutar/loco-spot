package com.example.tourism_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterReview extends RecyclerView.Adapter<AdapterReview.ViewHolder>{

    ArrayList<Userreview> mList;
    private final AdapterReview.RecyclerViewClickListener listener;

    public AdapterReview(ArrayList<Userreview> mList, AdapterReview.RecyclerViewClickListener listener) {
        this.mList = mList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Userreview items = mList.get(position);
        holder.txtbname.setText("User Name "+items.getUsername());
        holder.txtaddress.setText("Type "+items.getType());
        holder.txttype.setText("FeddBack "+items.getFeedback());





        Glide.with(holder.img1.getContext()).load(items.getImageurl()).into(holder.img1);



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img1;
        TextView txtbname,txtaddress,txttype,txtplace4,txtcityname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img1 =itemView.findViewById(R.id.img1);
            txtbname = itemView.findViewById(R.id.nametext);
            txtaddress = itemView.findViewById(R.id.coursetext);
            txttype = itemView.findViewById(R.id.emailtext);
            txtplace4 = itemView.findViewById(R.id.text);
            txtcityname = itemView.findViewById(R.id.text1);
        }
    }
}
