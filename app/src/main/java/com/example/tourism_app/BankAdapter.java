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

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.ViewHolder> {


    ArrayList<MyPlace> mList;
    private final RecyclerViewClickListener listener;


    public BankAdapter(ArrayList<MyPlace> mList, RecyclerViewClickListener listener) {
        this.mList = mList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ouritem,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       MyPlace vacancy1 = mList.get(position);
        holder.txtbname.setText("City Name "+vacancy1.getName());
        holder.txtaddress.setText("Place "+vacancy1.getPlace1());
        holder.txttype.setText("Description "+vacancy1.getDescription1());
        holder.txtitem4.setText("PLace "+vacancy1.getPlace2());
        holder.txtitem5.setText("Description "+vacancy1.getDescription2());
        holder.txtitem6.setText("PLace "+vacancy1.getPlace3());
        holder.txtitem7.setText("Description "+vacancy1.getDescription3());

        Glide.with(holder.img1.getContext()).load(vacancy1.getImageurl()).into(holder.img1);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

    TextView txtbname,txtaddress,txttype,txtitem4,txtitem5,txtitem6,txtitem7;
    ImageView img1;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        txtbname = itemView.findViewById(R.id.nametext);
        txtaddress = itemView.findViewById(R.id.coursetext);
        txttype = itemView.findViewById(R.id.emailtext);
        txtitem4 = itemView.findViewById(R.id.text);
        txtitem5 = itemView.findViewById(R.id.text1);

        txtitem6 = itemView.findViewById(R.id.text2);
        txtitem7 = itemView.findViewById(R.id.text3);

        img1 = itemView.findViewById(R.id.img1);


    }
}
}
