package com.example.android.ijpinterviewassignment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class seekAdapter extends RecyclerView.Adapter<seekAdapter.seekVH> {

    ArrayList<seekModel> seekbars;
    private onItemClickListener listener;

    public interface onItemClickListener{
        void onItemClick(int position);
        void onSeekBarChanged(int position,String prog);
    }
    public void setOnItemClickListener(onItemClickListener listener)
    {
        this.listener=listener;
    }

    public seekAdapter(ArrayList<seekModel> seekbars)
    {
        this.seekbars=seekbars;
    }

    @NonNull
    @Override

    public seekAdapter.seekVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.seekbar,parent,false);

        return (new seekVH(view,listener));
    }



    @Override
    public void onBindViewHolder(@NonNull seekVH holder, int position) {
        holder.startView.setText(seekbars.get(position).getStart());
        holder.endView.setText(seekbars.get(position).getEnd());
        int start=Integer.parseInt(seekbars.get(position).getStart());
        int end=Integer.parseInt(seekbars.get(position).getEnd());
        Log.d("ved",""+seekbars.get(position).getStart());
        Log.d("ved",""+seekbars.get(position).getEnd());
        holder.seekBar.setMax(end-start);
        holder.seekBar.setProgress(holder.seekBar.getMax());

    }

    @Override
    public int getItemCount() {
        return seekbars.size();
    }


    public static class seekVH extends RecyclerView.ViewHolder{

        TextView startView,endView;
        SeekBar seekBar;
        private seekAdapter adapter;
        public seekVH(@NonNull View itemView, onItemClickListener listener) {
            super(itemView);
            startView=itemView.findViewById(R.id.start);
            endView=itemView.findViewById(R.id.end);
            seekBar =itemView.findViewById(R.id.seek);
            // this.listener=listener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        if(listener!=null)
                        {
                            int position=getAdapterPosition();
                            if(position!=RecyclerView.NO_POSITION)
                            {
                                listener.onItemClick(position);
                            }
                        }
                }
            });
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    int currentprog=seekBar.getProgress();
                    endView.setText(Integer.toString(currentprog));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                        if(seekBar.getProgress()!=seekBar.getMax()) {
                            int pos = getAdapterPosition();
                            int prog = seekBar.getProgress();
                            String prog1 = Integer.toString(prog);
                            listener.onSeekBarChanged(pos, prog1);
                        }
//                        else
//                        {
//                            Toast.makeText(seekAdapter.this, "minimum length is 2", Toast.LENGTH_SHORT).show();
//                        }

                }
            });

        }


        public seekVH linkadapter(seekAdapter adapter)
        {
            this.adapter=adapter;
            return this;
        }



    }

}


