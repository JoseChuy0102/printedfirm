package josevillarreal.printedfirm.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import josevillarreal.printedfirm.Modelos.Producto;
import josevillarreal.printedfirm.Modelos.ProductoAdapter;
import josevillarreal.printedfirm.R;
import josevillarreal.printedfirm.verProductos;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductosFragment extends Fragment implements Response.ErrorListener, Response.Listener<String> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerView;
    ArrayList<Producto> listaProductos;
    final String URL_GET = "http://printedfirm.uttsistemas.com/mostrarProductos";


    public ProductosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductosFragment newInstance(String param1, String param2) {
        ProductosFragment fragment = new ProductosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*Creamos una vista y esa vista llevará el fragmento*/

        View vista = inflater.inflate(R.layout.fragment_productos, container, false);

        /*A través del objeto view, accedemos a la propiedades. En este caso el recycler que tenemos ahí metido*/
        listaProductos = new ArrayList<>();
        recyclerView = (RecyclerView) vista.findViewById(R.id.productosRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        cargarProductos();

        return vista;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void cargarProductos()
    {
        /*En el caso del get estoy creando un stringrequest*/
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GET, this, this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(6000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT ));
        Volley.newRequestQueue(getContext()).add(stringRequest);
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
            ProductoAdapter adapter = new ProductoAdapter(getContext(), listaProductos);
            recyclerView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
