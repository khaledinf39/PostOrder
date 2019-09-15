package com.kh_sof_dev.kzajeeh;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class Requestes_adapter extends RecyclerView.Adapter<Requestes_adapter.ViewHolder> {

    public interface onEditeListenner{
       void Onselected(Request product);
    }
    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private List<Request> mItems = new ArrayList<>();

    private Context mContext;
private onEditeListenner mlistenner;
private  String user_id;
    public Requestes_adapter(Context context, List<Request> names, onEditeListenner listenner) {
        mItems = names;
        mContext = context;
        mlistenner=listenner;


    }
    private View mView;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //parent = theme type
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_order_item, parent, false);
        mView=view;

        return new ViewHolder(view); // Inflater means reading a layout XML
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
holder.name.setText(mItems.get(position).getName());
        holder.id.setText(mItems.get(position).getId()+"");

holder.price.setText(mItems.get(position).getPrice().toString()+" ");
        holder.status.setText(mItems.get(position).getStatus());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistenner.Onselected(mItems.get(position));
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_popup(position);
            }
        });

    }



    private void delete_popup( final int position) {
        new AlertDialog.Builder(mContext)
                .setTitle("Delete")
                .setMessage("are you sure")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       http_request http_request=new http_request();
                       http_request.Post_delete(mContext, mItems.get(position), new http_request.OnCoupon_lisennter() {
                           @Override
                           public void onSuccess(int status) {
                               if (status==200){
                                   mItems.remove(mItems.get(position));
                                   notifyDataSetChanged();
                               }
                           }

                           @Override
                           public void onSuccess(List<Request> data) {

                           }

                           @Override
                           public void onStart() {

                           }

                           @Override
                           public void onFailure(String msg) {

                           }
                       });
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView id,name,price,status;
ImageView delete,edit;
        public ViewHolder(View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.order_nb);

            price=itemView.findViewById(R.id.order_price);
            status=itemView.findViewById(R.id.status);

            name=itemView.findViewById(R.id.prod_name);

            delete=itemView.findViewById(R.id.delete);
            edit=itemView.findViewById(R.id.validate);


        }
    }
}