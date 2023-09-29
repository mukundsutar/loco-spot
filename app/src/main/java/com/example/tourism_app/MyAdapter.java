package com.example.tourism_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Items> mList;
    Context context;

    public MyAdapter(Context context, ArrayList<Items> mList){
        this.mList = mList;
        this.context = context;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Items items = mList.get(position);
        holder.txtbname.setText(items.getCityname());
        holder.txtaddress.setText(items.getPlcae1());
        holder.txttype.setText(items.getPlace3());
        holder.txtplace4.setText(items.getPlace3());
        holder.txtcityname.setText(items.getPlace4());




        Glide.with(holder.img1.getContext()).load(items.getImageurl()).into(holder.img1);
        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,userdashboard.class);
                Bundle bundle = new Bundle();
                bundle.putString("url",items.getImageurl());
                bundle.putString("cityname",items.getCityname());
                bundle.putString("place1",items.getPlcae1());
                bundle.putString("des1",items.getDescription1());
                bundle.putString("place2", items.getPlace2());
                bundle.putString("des2",items.getDescription2());
                bundle.putString("place3",items.getDescription3());
                bundle.putString("des3",items.description3);
                bundle.putString("place4",items.getPlace4());
                bundle.putString("des4",items.getDescription4());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img1;
        TextView txtbname,txtaddress,txttype,txtplace4,txtcityname;


        public MyViewHolder(@NonNull View itemView) {
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
