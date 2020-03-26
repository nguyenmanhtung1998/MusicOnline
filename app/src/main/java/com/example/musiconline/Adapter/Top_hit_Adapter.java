package com.example.musiconline.Adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musiconline.ManActivity.PlaynhacActivity;
import com.example.musiconline.Model.Baihat;
import com.example.musiconline.R;
import com.example.musiconline.Service.MusicService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.musiconline.Service.APIService.getService;

public class Top_hit_Adapter extends RecyclerView.Adapter<Top_hit_Adapter.Viewholder> {
    Context context;
    ArrayList<Baihat> baihatArrayList;
    public Top_hit_Adapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_top_hit,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
       Baihat baihat=baihatArrayList.get(position);
       holder.textViewtenbaihat.setText(baihat.getTenbaihat());
       holder.textViewtencasi.setText(baihat.getCasi());
        Picasso.with(context).load(baihat.getHinhbaihat()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textViewtencasi,textViewtenbaihat;
        public Viewholder(@NonNull final View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imagetophit);
            textViewtenbaihat=itemView.findViewById(R.id.tenbaihathit);
            textViewtencasi=itemView.findViewById(R.id.tencasi);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            GetPosison(getAdapterPosition());
            Intent intent=new Intent(context, PlaynhacActivity.class);
            Bundle bundle=new Bundle();
            bundle.putInt("int",GetPosison(getAdapterPosition()));
            bundle.putParcelableArrayList("danhsachbaihat",baihatArrayList);
           intent.putExtra("dulieu",bundle);
            context.startActivity(intent);
        }

        private int GetPosison(int cout) {
            int posison=cout;
            return posison;
        }
    }

}


