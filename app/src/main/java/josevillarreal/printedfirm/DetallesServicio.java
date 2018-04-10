package josevillarreal.printedfirm;

import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class DetallesServicio extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    TextView txnom, txdesc, txid, loading;
    ImageView img;

    ProgressBar pb;
    int npbstatus = 0;
    Handler handler = new Handler();
    final String url = "http://printedfirm.uttsistemas.com/detallesServicio";
    RequestQueue requestqueue;
    JSONObject jsonObject;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_servicio);

        pb = (ProgressBar) findViewById(R.id.pb);
        loading = (TextView) findViewById(R.id.txtwait);
        txnom = (TextView) findViewById(R.id.detallesnom);
        txdesc = (TextView) findViewById(R.id.detallesdesc);
        img = (ImageView) findViewById(R.id.detallesimg);

        requestqueue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        crearJSON();
        getDetalles();

        new Thread(new Runnable() {
            @Override
            public void run()
            {
                while (npbstatus < 100)
                {
                    npbstatus++;
                    android.os.SystemClock.sleep(120);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            pb.setProgress(npbstatus);
                            loading.setVisibility(View.VISIBLE);
                            loading.setText("Cargando... Por favor, espere...");
                        }
                    });
                }
            }
        }).start();

    }

    private void crearJSON()
    {
        jsonObject = new JSONObject();
        try {
            jsonObject.put("id", getIntent().getIntExtra("detallesid", 0));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getDetalles()
    {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, this, this);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(6000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT ));
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error)
    {
        pb.setVisibility(View.GONE);
        loading.setText("Ha ocurrido un error. Verifique su conexión a internet y vuelva a intentar.");
        Log.e("rror", "" + error);
    }

    @Override
    public void onResponse(final JSONObject response)
    {
        handler.post(new Runnable() {
            @Override
            public void run()
            {
                loading.setText("");
                pb.setVisibility(View.GONE);
            }
        });

        try
        {
            txnom.setText(response.getString("nombre_servicio"));
            txdesc.setText(response.getString("descripcion_servicio"));
            Glide.with(this).load(response.getString("imagen_servicio")).into(img);

        }catch (JSONException err)
        {
            err.printStackTrace();
        }

        Log.e("Kek", "" + response);
    }
}
