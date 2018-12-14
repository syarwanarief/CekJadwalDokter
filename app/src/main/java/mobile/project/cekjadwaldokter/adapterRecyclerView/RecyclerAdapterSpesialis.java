package mobile.project.cekjadwaldokter.adapterRecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import mobile.project.cekjadwaldokter.R;
import mobile.project.cekjadwaldokter.paket.firebase.FirebaseModelListSpesialis;


public class RecyclerAdapterSpesialis extends RecyclerView.Adapter<RecyclerAdapterSpesialis.ViewHolder> {

    List<FirebaseModelListSpesialis> list;
    Context context;
    String warna;

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public RecyclerAdapterSpesialis(List<FirebaseModelListSpesialis> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_list_spesialis, parent, false);
        ViewHolder myHoder = new ViewHolder(view);

        return myHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        FirebaseModelListSpesialis mylist = list.get(position);
        holder.spesial.setText(mylist.getSpesialis());
        Picasso.with(context).load(mylist.getImage()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.getPosition(position);
            }
        });
        if (warna.equals("advent")) {
            if (position == 0) {
                holder.view.setBackgroundColor(Color.parseColor("#99ffbb"));
            } else {
                holder.view.setBackgroundColor(Color.parseColor("#99ffbb"));
            }
        } else if (warna.equals("bumiwaras")) {
            if (position == 0) {
                holder.view.setBackgroundColor(Color.parseColor("#99ffff"));
            } else {
                holder.view.setBackgroundColor(Color.parseColor("#99ffff"));
            }
        }else if (warna.equals("dkt")) {
            if (position == 0) {
                holder.view.setBackgroundColor(Color.parseColor("#adebad"));
            } else {
                holder.view.setBackgroundColor(Color.parseColor("#adebad"));
            }
        }else if (warna.equals("imanuel")) {
            if (position == 0) {
                holder.view.setBackgroundColor(Color.parseColor("#99ccff"));
            } else {
                holder.view.setBackgroundColor(Color.parseColor("#99ccff"));
            }
        }else{
            if (position == 0) {
                holder.view.setBackgroundColor(Color.parseColor("#ffffb3"));
            } else {
                holder.view.setBackgroundColor(Color.parseColor("#ffffb3"));
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView spesial;
        ImageView imageView;
        LinearLayout view;

        public ViewHolder(View itemView) {
            super(itemView);
            spesial = (TextView) itemView.findViewById(R.id.Vspesialis);
            imageView = (ImageView) itemView.findViewById(R.id.imV);
            view = (LinearLayout) itemView.findViewById(R.id.cardView);

        }
    }

    @Override
    public int getItemCount() {
        int arr = 0;

        try {
            if (list.size() == 0) {
                arr = 0;
            } else {

                arr = list.size();
            }
        } catch (Exception e) {
        }
        return arr;
    }

    OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void getPosition(int pos); //pass any things
    }
}
