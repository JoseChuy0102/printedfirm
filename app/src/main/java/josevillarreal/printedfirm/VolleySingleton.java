package josevillarreal.printedfirm;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Umi on 31/03/2018.
 */

public class VolleySingleton
{
    private static VolleySingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private VolleySingleton(Context contexto)
    {
        mCtx = contexto;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized VolleySingleton getInstance(Context contexto)
    {
        if(mInstance == null)
        {
            mInstance = new VolleySingleton(contexto);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue()
    {
        if(mRequestQueue == null)
        {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <JsonObjectRequest> void addToRequestQueue(Request<JsonObjectRequest> req)
    {
        getRequestQueue().add(req);
    }



}
