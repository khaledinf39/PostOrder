package com.kh_sof_dev.kzajeeh;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class http_request {
    public interface OnCoupon_lisennter{
        void onSuccess(int status);
void onSuccess(List<com.kh_sof_dev.kzajeeh.Request> data);
        void onStart();
        void onFailure(String msg);
    }
    public void Post_add(final Context mcontext, final com.kh_sof_dev.kzajeeh.Request request, final OnCoupon_lisennter listener){
        listener.onStart();
        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context
        String url = "http://zabeb.com/regestration.php";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        String jsonData = response;
                        JSONObject Jobject = null;
                        try {
                            Jobject = new JSONObject(jsonData);
                            listener.onSuccess(Jobject.getInt("status"));
                        } catch (JSONException e1) {
                            listener.onFailure(e1.getMessage());
                            e1.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", String.valueOf(error.getMessage()));
                        listener.onFailure("Error.Response");
                    }
                }
        ) {


            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  parameters = new HashMap<String, String>();
//
                parameters.put("Name",request.getName());
                parameters.put("Price",request.getPrice().toString());
                parameters.put("Status",request.getStatus());

                return parameters;
            }
        };
        queue.getCache().initialize();
        queue.add(postRequest);
        queue.getCache().clear();

        // prepare the Request

    }
    public void Post_update(final Context mcontext, final com.kh_sof_dev.kzajeeh.Request request, final OnCoupon_lisennter listener){
        listener.onStart();
        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context
        String url = "http://zabeb.com/update.php";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        String jsonData = response;
                        JSONObject Jobject = null;
                        try {
                            Jobject = new JSONObject(jsonData);
                            listener.onSuccess(Jobject.getInt("status"));
                        } catch (JSONException e1) {
                            listener.onFailure(e1.getMessage());
                            e1.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", String.valueOf(error.getMessage()));
                        listener.onFailure("Error.Response");
                    }
                }
        ) {


            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  parameters = new HashMap<String, String>();
//
                parameters.put("Name",request.getName());
                parameters.put("Price",request.getPrice().toString());
                parameters.put("Status",request.getStatus());
                parameters.put("id",request.getId()+"");

                return parameters;
            }
        };
        queue.getCache().initialize();
        queue.add(postRequest);
        queue.getCache().clear();

        // prepare the Request

    }
    public void Post_delete(final Context mcontext, final com.kh_sof_dev.kzajeeh.Request request, final OnCoupon_lisennter listener){
        listener.onStart();
        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context
        String url = "http://zabeb.com/delete.php";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        String jsonData = response;
                        JSONObject Jobject = null;
                        try {
                            Jobject = new JSONObject(jsonData);
                            listener.onSuccess(Jobject.getInt("status"));
                        } catch (JSONException e1) {
                            listener.onFailure(e1.getMessage());
                            e1.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", String.valueOf(error.getMessage()));
                        listener.onFailure("Error.Response");
                    }
                }
        ) {


            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  parameters = new HashMap<String, String>();
//
                parameters.put("id",request.getId()+"");

                return parameters;
            }
        };
        queue.getCache().initialize();
        queue.add(postRequest);
        queue.getCache().clear();

        // prepare the Request

    }
    public void Post_fetch(final Context mcontext, final com.kh_sof_dev.kzajeeh.Request request, final OnCoupon_lisennter listener){
        listener.onStart();
        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context
        String url = "http://zabeb.com/fetch.php";

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        String jsonData = response;
                        JSONObject Jobject = null;
                        try {
                            Jobject = new JSONObject(jsonData);
                            listener.onSuccess(Jobject.getInt("status"));
                            listener.onSuccess(objectjsonArray(Jobject));
                        } catch (JSONException e1) {
                            listener.onFailure(e1.getMessage());
                            e1.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", String.valueOf(error.getMessage()));
                        listener.onFailure("Error.Response");
                    }
                }
        ) {



        };
        queue.getCache().initialize();
        queue.add(postRequest);
        queue.getCache().clear();

        // prepare the Request

    }


    private List<com.kh_sof_dev.kzajeeh.Request> objectjsonArray( JSONObject jsonObject){
    List<com.kh_sof_dev.kzajeeh.Request> item=new ArrayList<>();
    if(jsonObject == null){
        return item;
    }
    JSONArray JsonArray = jsonObject.optJSONArray("data");
    if(JsonArray != null){
        for (int i = 0; i < JsonArray.length(); i++) {
            JSONObject catigoriessObject = JsonArray.optJSONObject(i);
            try {
                item.add(new com.kh_sof_dev.kzajeeh.Request(catigoriessObject));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
}
    return item;
    }
}
