package com.example.tourism_app;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowpAdapter extends RecyclerView.Adapter<ShowpAdapter.ViewHolder> {


    ArrayList<bus> mList;
    private ShowpAdapter.RecyclerViewClickListener listener;

    public ShowpAdapter(ArrayList<bus> mList, ShowpAdapter.RecyclerViewClickListener listener) {
        this.mList = mList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.useritem,parent,false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        bus vacancy1 = mList.get(position);
        holder.txtbname.setText("Destination -"+vacancy1.getDestination());
        holder.txtaddress.setText("Bus Name - "+vacancy1.getBusno());
        holder.txttype.setText("Amount "+vacancy1.getAmount());
        holder.txtseat.setText("Bus Date/Time -"+vacancy1.getSeats());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView txtbname,txtaddress,txttype,txtseat;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtbname = itemView.findViewById(R.id.nametext);
            txtaddress = itemView.findViewById(R.id.coursetext);
            txttype = itemView.findViewById(R.id.emailtext);
            txtseat = itemView.findViewById(R.id.seattext);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listener.onClick(v,getAdapterPosition());
        }
    }
}
