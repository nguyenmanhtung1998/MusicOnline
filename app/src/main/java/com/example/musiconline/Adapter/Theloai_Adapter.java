package com.example.musiconline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musiconline.Model.Playlist;
import com.example.musiconline.R;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Theloai_Adapter extends RecyclerView.Adapter<Theloai_Adapter.Viewholder> {
    Context context;
    ArrayList<Playlist> arrayList;

    public Theloai_Adapter(Context context, ArrayList<Playlist> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
       View view=inflater.inflate(R.layout.dong_the_loai,parent,false);
        return new Viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
       Playlist playlist=arrayList.get(position);
        holder.textView.setText(playlist.getTen());
        Picasso.with(context).load(playlist.getHinhPlayList()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imagetheloai);
            textView=itemView.findViewById(R.id.texttentheloai);
        }
    }
}
