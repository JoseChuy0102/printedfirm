package josevillarreal.printedfirm.Modelos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import josevillarreal.printedfirm.DetallesServicio;
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
        return new ServicioAdapter.ServicioViewHolder(view, mCtx, listaServicios);
    }

    @Override
    public void onBindViewHolder(ServicioAdapter.ServicioViewHolder holder, final int position) {
        Servicio servicio = listaServicios.get(position);

        Glide.with(mCtx).load(servicio.getImagenServicio()).into(holder.imageView);

        holder.textViewNombre.setText(servicio.getNombreServicio());
        holder.textViewDescripcion.setText(servicio.getDescripcionServicio());
        holder.btnadd.setText(String.valueOf(servicio.getIdServicio()));

        holder.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mCtx, "kekito " + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaServicios.size();
    }

    class ServicioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView textViewNombre, textViewDescripcion;
        ImageView imageView;
        Button btnadd;
        List<Servicio> listaServicios = new ArrayList<>();
        Context mCtx;


        public ServicioViewHolder(View itemView, Context mCtx, List<Servicio> listaServicios) {
            super(itemView);

            this.listaServicios = listaServicios;

            this.mCtx=mCtx;

            itemView.setOnClickListener(this);

            textViewNombre = (TextView) itemView.findViewById(R.id.textViewNombre);
            textViewDescripcion = (TextView) itemView.findViewById(R.id.textViewDescripcion);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);

            btnadd = itemView.findViewById(R.id.btnadd);
        }

        @Override
        public void onClick(View view)
        {
            int position = getAdapterPosition();
            Servicio servicio = this.listaServicios.get(position);
            Intent intent = new Intent(this.mCtx, DetallesServicio.class);
            intent.putExtra("detallesid", servicio.getIdServicio());


            this.mCtx.startActivity(intent);

        }
    }



}
