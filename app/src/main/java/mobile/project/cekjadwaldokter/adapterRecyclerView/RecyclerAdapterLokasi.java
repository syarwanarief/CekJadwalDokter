package mobile.project.cekjadwaldokter.adapterRecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import mobile.project.cekjadwaldokter.R;
import mobile.project.cekjadwaldokter.paket.firebase.ModelRS;

public abstract class RecyclerAdapterLokasi extends RecyclerView.Adapter<RecyclerAdapterLokasi.ViewHolder> {
    List<ModelRS> list;
    Context context;

    public RecyclerAdapterLokasi(List<ModelRS> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapterLokasi.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_lokasi,parent,false);
        RecyclerAdapterLokasi.ViewHolder myHoder = new RecyclerAdapterLokasi.ViewHolder(view);

        return myHoder;
    }

    public void onBindViewHolder(@NonNull RecyclerAdapterLokasi.ViewHolder holder, final int position) {
        ModelRS mylist = list.get(position);

        holder.Nama.setText(mylist.getNama());
        Picasso.with(context).load(mylist.getImage()).into(holder.Image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.getPosition(position);
            }
        });

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView Nama;
        ImageView Image;
        public ViewHolder(View itemView) {
            super(itemView);
            Nama = (TextView) itemView.findViewById(R.id.idNamaRS);
            Image = (ImageView) itemView.findViewById(R.id.idGambar);
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
