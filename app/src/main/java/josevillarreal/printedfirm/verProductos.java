package josevillarreal.printedfirm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import josevillarreal.printedfirm.Modelos.Producto;
import josevillarreal.printedfirm.Modelos.ProductoAdapter;

public class verProductos extends AppCompatActivity implements Response.ErrorListener, Response.Listener<String> {

    RecyclerView recyclerView;
    final String URL_GET = "http://printedfirm.uttsistemas.com/mostrarProductos";
    List<Producto> listaProductos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_productos);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaProductos = new ArrayList<>();

        cargarProductos();

    }

    private void cargarProductos()
    {
        /*En el caso del get estoy creando un stringrequest*/
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GET, this, this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(6000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT ));
        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error)
    {


    }

    @Override
    public void onResponse(String response)
    {
        /*En el response manejo la lógica. Me traigo el arreglo de la ruta que hay en el controlador y saco todos los elementos JSON
        * y después consigo los valores de sus propiedades y las asigno a este objeto aquí e igualmente lo meto a la lista pra mostrarlos*/

        try {
            JSONArray array = new JSONArray(response);

            for (int i =0; i < array.length(); i++)
            {
                JSONObject producto = array.getJSONObject(i);

                listaProductos.add(new Producto(producto.getInt("idproductos"),
                        producto.getString("nombre_producto"),
                        producto.getString("descripcion_producto"),
                        producto.getDouble("precio_unitario"),
                        producto.getString("imagen_producto")
                        ));
            }

            ProductoAdapter adapter = new ProductoAdapter(verProductos.this, listaProductos);
            recyclerView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
