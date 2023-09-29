package com.example.tourism_app;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

public class AdapterVideo extends RecyclerView.Adapter<AdapterVideo.HolderVideo>{

    private Context context;
    private ArrayList<member> videoArrayList;

    public AdapterVideo(Context context, ArrayList<member> videoArrayList) {
        this.context = context;
        this.videoArrayList = videoArrayList;
    }

    @NonNull
    @Override
    public HolderVideo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_video,parent,false);

        return new HolderVideo(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HolderVideo holder, int position) {

        member member = videoArrayList.get(position);
        String id = member.getId();
        String title = member.getTitle();
        String timestamp = member.getTimestamp();
        String videouri = member.getVideoUrl();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(timestamp));
        String formattime = DateFormat.format("dd/MM/yyyy K:MM a",calendar).toString();


        holder.txttitle.setText(title);
        holder.timeTv.setText(formattime);


        setVideoUrl(member,holder);


    }

    private void setVideoUrl(member member, HolderVideo holder) {

        holder.progressBar.setVisibility(View.VISIBLE);
        String videoUrl = member.getVideoUrl();
        MediaController mediaController = new MediaController(context);
        mediaController.setAnchorView(holder.videoView);

        Uri videoUri = Uri.parse(videoUrl);
        holder.videoView.setMediaController(mediaController);
        holder.videoView.setVideoURI(videoUri);



        holder.videoView.requestFocus();
        holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        holder.videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {

                switch (what)
                {
                    case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                    {
                        holder.progressBar.setVisibility(View.VISIBLE);
                        return true;
                    }
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                    {
                        holder.progressBar.setVisibility(View.INVISIBLE);
                        return true;
                    }
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:{
                        holder.progressBar.setVisibility(View.GONE);

                        return true;
                    }
                }


                return false;
            }
        });

        holder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoArrayList.size();
    }

    class HolderVideo extends RecyclerView.ViewHolder{

        VideoView videoView;
        TextView txttitle,timeTv;
        ProgressBar progressBar;


        public HolderVideo(@NonNull View itemView) {
            super(itemView);

            videoView = itemView.findViewById(R.id.videoview);
            txttitle = itemView.findViewById(R.id.titleTv);
            timeTv = itemView.findViewById(R.id.timeTv);
            progressBar = itemView.findViewById(R.id.progressbar);

        }
    }
}
