package mobile.project.cekjadwaldokter.adapterRecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import mobile.project.cekjadwaldokter.R;
import mobile.project.cekjadwaldokter.paket.firebase.ModelInfoSpesialis;

public class RecyclerAdapterDetailDokter extends RecyclerView.Adapter<RecyclerAdapterDetailDokter.ViewHolder> {
    List<ModelInfoSpesialis> list;
    Context context;
    String warna;

    public void setWarna(String warna) {
        this.warna = warna;
    }

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        ModelInfoSpesialis mylist = list.get(position);

        holder.Hari.setText(mylist.getHari());
        holder.Poli.setText(mylist.getPoli());
        holder.Jam.setText(mylist.getJam());
        holder.NamaDokter.setText(mylist.getNamaDokter());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                onItemClick.getPosition(position);

                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, holder.view);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_long_click);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                //handle menu1 click
                                Toast.makeText(context.getApplicationContext(), "Edit ", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.delete:
                                //handle menu2 click
                                Toast.makeText(context.getApplicationContext(), "Hapus ", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                Toast.makeText(context.getApplicationContext(), "Data Tidak Ditemukan ", Toast.LENGTH_SHORT).show();
                                return false;
                        }
                    }
                });
                //displaying the popup
                popup.show();
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
        }else {
            if (position == 0) {
                holder.view.setBackgroundColor(Color.parseColor("#ffffb3"));
            } else {
                holder.view.setBackgroundColor(Color.parseColor("#ffffb3"));
            }
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView NamaDokter;
        TextView Hari;
        TextView Jam;
        TextView Poli;
        LinearLayout view;

        public ViewHolder(View itemView) {
            super(itemView);
            NamaDokter = (TextView) itemView.findViewById(R.id.namaDokter);
            Hari = (TextView) itemView.findViewById(R.id.idHari);
            Jam = (TextView) itemView.findViewById(R.id.idJam);
            Poli = (TextView) itemView.findViewById(R.id.idPoli);
            view = (LinearLayout) itemView.findViewById(R.id.card);

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
