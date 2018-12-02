package mobile.project.cekjadwaldokter.adapterRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import mobile.project.cekjadwaldokter.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    View view;

    public ViewHolder(View itemView){
        super(itemView);

        view = itemView;
    }

    //set to rv
    public void setDetails(Context ctx, String Spesialiss, String Image){
        //view
        TextView title = view.findViewById(R.id.Vspesialis);
        ImageView imageView = view.findViewById(R.id.imV);

        //setdata
        title.setText(Spesialiss);
    }
}
