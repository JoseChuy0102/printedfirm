package josevillarreal.printedfirm.Modelos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import josevillarreal.printedfirm.R;

/**
 * Created by Umi on 05/04/2018.
 */

public class ServicioAdapter extends RecyclerView.Adapter<ServicioAdapter.ServicioViewHolder>
{
    private Context mCtx;
    private List<Servicio> listaServicios;

    public ServicioAdapter(Context mCtx, List<Servicio> listaServicios)
    {
        this.mCtx = mCtx;
        this.listaServicios = listaServicios;
    }


    @Override
    public ServicioAdapter.ServicioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.lista_servicios, null);
        return new ServicioAdapter.ServicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ServicioAdapter.ServicioViewHolder holder, int position) {
        Servicio servicio = listaServicios.get(position);

        Glide.with(mCtx).load(servicio.getImagenServicio()).into(holder.imageView);

        holder.textViewNombre.setText(servicio.getNombreServicio());
        holder.textViewDescripcion.setText(servicio.getDescripcionServicio());
        holder.btnadd.setText(String.valueOf(servicio.getIdServicio()));

    }

    @Override
    public int getItemCount() {
        return listaServicios.size();
    }

    class ServicioViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewNombre, textViewDescripcion;
        ImageView imageView;
        Button btnadd;

        public ServicioViewHolder(View itemView) {
            super(itemView);

            textViewNombre = (TextView) itemView.findViewById(R.id.textViewNombre);
            textViewDescripcion = (TextView) itemView.findViewById(R.id.textViewDescripcion);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);

            btnadd = itemView.findViewById(R.id.btnadd);
        }
    }



}
