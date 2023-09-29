package com.example.tourism_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class DisAdapter extends RecyclerView.Adapter<DisAdapter.ViewHolder> {

    ArrayList<bus> mList;
    private DisAdapter.RecyclerViewClickListener listener;

    public DisAdapter(ArrayList<bus> mList, RecyclerViewClickListener listener) {
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
        holder.txtbname.setText("Destination-"+vacancy1.getDestination());
        holder.txtaddress.setText("Flight Name- "+vacancy1.getBusno());
        holder.txttype.setText("Amount -"+vacancy1.getAmount());
        holder.txtseat.setText("Flight Date/Time -"+vacancy1.getSeats());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtbname,txtaddress,txttype,txtseat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtbname = itemView.findViewById(R.id.nametext);
            txtaddress = itemView.findViewById(R.id.coursetext);
            txttype = itemView.findViewById(R.id.emailtext);
            txtseat = itemView.findViewById(R.id.seattext);
//            itemView.setOnClickListener(this);
        }
    }
}
