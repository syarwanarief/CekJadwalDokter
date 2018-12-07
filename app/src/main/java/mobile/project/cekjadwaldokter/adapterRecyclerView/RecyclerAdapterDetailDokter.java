package mobile.project.cekjadwaldokter.adapterRecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mobile.project.cekjadwaldokter.R;
import mobile.project.cekjadwaldokter.paket.firebase.ModelInfoSpesialis;

public class RecyclerAdapterDetailDokter extends RecyclerView.Adapter<RecyclerAdapterDetailDokter.ViewHolder> {
    List<ModelInfoSpesialis> list;
    Context context;

    public RecyclerAdapterDetailDokter(List<ModelInfoSpesialis> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_info_dokter,parent,false);
        ViewHolder myHoder = new ViewHolder(view);

        return myHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ModelInfoSpesialis mylist = list.get(position);

        holder.Hari.setText(mylist.getHari());
        holder.Poli.setText(mylist.getPoli());
        holder.Jam.setText(mylist.getJam());
        holder.NamaDokter.setText(mylist.getNamaDokter());

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView NamaDokter;
        TextView Hari;
        TextView Jam;
        TextView Poli;
        CardView view;

        public ViewHolder(View itemView) {
            super(itemView);
            NamaDokter = (TextView) itemView.findViewById(R.id.namaDokter);
            Hari = (TextView) itemView.findViewById(R.id.idHari);
            Jam = (TextView) itemView.findViewById(R.id.idJam);
            Poli = (TextView) itemView.findViewById(R.id.idPoli);
            view = (CardView) itemView.findViewById(R.id.card);

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

    RecyclerAdapterSpesialis.OnItemClick onItemClick;

    public void setOnItemClick(RecyclerAdapterSpesialis.OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void getPosition(int pos); //pass any things
    }
}
