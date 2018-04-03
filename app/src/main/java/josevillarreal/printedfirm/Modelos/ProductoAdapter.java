package josevillarreal.printedfirm.Modelos;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import josevillarreal.printedfirm.R;

/**
 * Created by Umi on 31/03/2018.
 */

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>
{
    private Context mCtx;
    private List<Producto> listaProductos;

    public ProductoAdapter(Context mCtx, List<Producto> listaProductos)
    {
        this.mCtx = mCtx;
        this.listaProductos = listaProductos;
    }


    @Override
    public ProductoAdapter.ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.lista_productos, null);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductoAdapter.ProductoViewHolder holder, int position) {
        Producto producto = listaProductos.get(position);

        Glide.with(mCtx).load(producto.getImagenProducto()).into(holder.imageView);

        holder.textViewNombre.setText(producto.getNombreProducto());
        holder.textViewDescripcion.setText(producto.getDescripcionProducto());
        holder.textViewPrecio.setText(String.valueOf(producto.getPrecioProducto()));

    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    class ProductoViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewNombre, textViewDescripcion, textViewPrecio;
        ImageView imageView;

        public ProductoViewHolder(View itemView) {
            super(itemView);

            textViewNombre = (TextView) itemView.findViewById(R.id.textViewNombre);
            textViewDescripcion = (TextView) itemView.findViewById(R.id.textViewDescripcion);
            textViewPrecio = (TextView) itemView.findViewById(R.id.textViewPrecio);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
