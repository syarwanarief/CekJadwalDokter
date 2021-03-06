package mobile.project.cekjadwaldokter.adapterRecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

import mobile.project.cekjadwaldokter.MenuOpsiAdmin.EditActivity;
import mobile.project.cekjadwaldokter.R;
import mobile.project.cekjadwaldokter.paket.firebase.ModelInfoSpesialis;

public class RecyclerAdapterDetailDokter extends RecyclerView.Adapter<RecyclerAdapterDetailDokter.ViewHolder> {
    List<ModelInfoSpesialis> list;
    Context context;
    String warna, spesialis, key;

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setSpesialis(String spesialis) {
        this.spesialis = spesialis;
    }

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

        final String keyid = mylist.getKey();
        holder.Hari.setText(mylist.getHari());
        holder.Poli.setText(mylist.getPoli());
        holder.Jam.setText(mylist.getJam());
        Picasso.with(context).load(mylist.getImage()).into(holder.imageView);
        holder.NamaDokter.setText(mylist.getNamaDokter());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              //creating a popup menu
                PopupMenu popup = new PopupMenu(context, holder.view);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_long_click);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item
                            .getMenuInfo();

                        switch (item.getItemId()) {
                            case R.id.edit:
                                String uid = "key";
                                final String keyFirebase = "keyFB";
                                Intent intent = (new Intent(context,EditActivity.class));
                                Bundle b = new Bundle();
                                b.putString(keyFirebase, spesialis);
                                b.putString(uid, keyid);
                                intent.putExtras(b);
                                context.startActivity(intent);
                                return true;
                            case R.id.delete:

                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder
                                        .setTitle("Yakin Ingin Hapus?")
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {

                                                FirebaseDatabase.getInstance().getReference(spesialis)
                                                        .child(keyid).removeValue();
                                            }
                                        })
                                        .setNegativeButton("Tidak", null)//Do nothing on no
                                        .show();
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
                holder.view.setCardBackgroundColor(Color.parseColor("#99ffbb"));
            } else {
                holder.view.setCardBackgroundColor(Color.parseColor("#99ffbb"));
            }
        } else if (warna.equals("bumiwaras")) {
            if (position == 0) {
                holder.view.setCardBackgroundColor(Color.parseColor("#99ffff"));
            } else {
                holder.view.setCardBackgroundColor(Color.parseColor("#99ffff"));
            }
        }else if (warna.equals("dkt")) {
            if (position == 0) {
                holder.view.setCardBackgroundColor(Color.parseColor("#adebad"));
            } else {
                holder.view.setCardBackgroundColor(Color.parseColor("#adebad"));
            }
        }else if (warna.equals("imanuel")) {
            if (position == 0) {
                holder.view.setCardBackgroundColor(Color.parseColor("#99ccff"));
            } else {
                holder.view.setCardBackgroundColor(Color.parseColor("#99ccff"));
            }
        }else {
            if (position == 0) {
                holder.view.setCardBackgroundColor(Color.parseColor("#ffffb3"));
            } else {
                holder.view.setCardBackgroundColor(Color.parseColor("#ffffb3"));
            }
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView NamaDokter;
        TextView Hari;
        TextView Jam;
        TextView Poli;
        ImageView imageView;
        CardView view;

        public ViewHolder(View itemView) {
            super(itemView);
            NamaDokter = (TextView) itemView.findViewById(R.id.namaDokter);
            Hari = (TextView) itemView.findViewById(R.id.idHari);
            Jam = (TextView) itemView.findViewById(R.id.idJam);
            Poli = (TextView) itemView.findViewById(R.id.idPoli);
            imageView = (ImageView) itemView.findViewById(R.id.fotoDokter);
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
