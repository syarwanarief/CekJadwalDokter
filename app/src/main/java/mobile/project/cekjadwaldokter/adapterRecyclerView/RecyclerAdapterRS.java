package mobile.project.cekjadwaldokter.adapterRecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
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
import mobile.project.cekjadwaldokter.paket.firebase.ModelRS;

public class RecyclerAdapterRS extends RecyclerView.Adapter<RecyclerAdapterRS.ViewHolder> {
    List<ModelRS> list;
    Context context;

    public RecyclerAdapterRS(List<ModelRS> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapterRS.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.custome_menu_utama,parent,false);
        RecyclerAdapterRS.ViewHolder myHoder = new RecyclerAdapterRS.ViewHolder(view);

        return myHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterRS.ViewHolder holder, final int position) {
        ModelRS mylist = list.get(position);

        holder.Nama.setText(mylist.getNama());
        holder.Alamat.setText(mylist.getAlamat());
        Picasso.with(context).load(mylist.getImage()).into(holder.Image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.getPosition(position);
            }
        });
        if (position == 0){
            holder.view.setCardBackgroundColor(Color.parseColor("#ffffb3"));
            holder.view2.setCardBackgroundColor(Color.parseColor("#ffffb3"));
        }else if (position == 1){
            holder.view.setCardBackgroundColor(Color.parseColor("#99ffbb"));
            holder.view2.setCardBackgroundColor(Color.parseColor("#99ffbb"));
        }else if (position == 2){
            holder.view2.setCardBackgroundColor(Color.parseColor("#99ffff"));
            holder.view.setCardBackgroundColor(Color.parseColor("#99ffff"));
        }else if (position == 3){
            holder.view2.setCardBackgroundColor(Color.parseColor("#adebad"));
            holder.view.setCardBackgroundColor(Color.parseColor("#adebad"));
        }else if (position == 4){
            holder.view2.setCardBackgroundColor(Color.parseColor("#99ccff"));
            holder.view.setCardBackgroundColor(Color.parseColor("#99ccff"));
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView Nama;
        TextView Alamat;
        ImageView Image;
        LinearLayout linearLayout;
        CardView view;
        CardView view2;

        public ViewHolder(View itemView) {
            super(itemView);
            Nama = (TextView) itemView.findViewById(R.id.idNamaRS);
            Alamat = (TextView) itemView.findViewById(R.id.idAlamatRS);
            Image = (ImageView) itemView.findViewById(R.id.idGambar);
            view = (CardView) itemView.findViewById(R.id.view2);
            view2 = (CardView) itemView.findViewById(R.id.view1);
        }
    }

    @Override
    public int getItemCount() {
        int arr = 0;

        try{
            if(list.size()==0){
                arr = 0;
            }
            else{

                arr=list.size();
            }
        }catch (Exception e){
        }
        return arr;
    }

    RecyclerAdapterRS.OnItemClick onItemClick;

    public void setOnItemClick(RecyclerAdapterRS.OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void getPosition(int pos); //pass any things
    }
}
