package com.kh_sof_dev.kzajeeh;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private Requestes_adapter adapter;
private List<Request> requestList;
private http_request http_request;
private RecyclerView RV;
private EditText name,price,status;
Button save,cancel;
ImageView refresh;
    boolean existedata=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!existedata){
                    findViewById(R.id.progress).setVisibility(View.GONE);
                    findViewById(R.id.noItem).setVisibility(View.VISIBLE);

                }

            }
        }, 5000);
        refresh=findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData();
            }
        });
        http_request=new http_request();
        save=findViewById(R.id.save);
        cancel=findViewById(R.id.cancel);
        name=findViewById(R.id.prod_name);
        price=findViewById(R.id.price);
        status=findViewById(R.id.status);
      RV=findViewById(R.id.RV);
      requestList=new ArrayList<>();

      fetchData();
      save.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if (verify()) {
                  Request request=new Request(name.getText().toString(),status.getText().toString()
                          ,Double.parseDouble(price.getText().toString()));

                  http_request.Post_add(MainActivity.this, request
                          , new http_request.OnCoupon_lisennter() {
                              @Override
                              public void onSuccess(int status) {
                                  if (status==200){
                                      cleare();
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
          }
      });

      cancel.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              cleare();
          }
      });
    }

    private void fetchData() {
        http_request.Post_fetch(this, new Request(), new http_request.OnCoupon_lisennter() {
            @Override
            public void onSuccess(int status) {
                if (status!=200){
                    findViewById(R.id.progress).setVisibility(View.GONE);
                    findViewById(R.id.noItem).setVisibility(View.VISIBLE);
                    existedata=false;
                }else {
                    findViewById(R.id.progress).setVisibility(View.GONE);
                    findViewById(R.id.noItem).setVisibility(View.GONE);
                    existedata=true;

                }
            }

            @Override
            public void onSuccess(List<Request> data) {

                adapter=new Requestes_adapter(MainActivity.this, data,
                        new Requestes_adapter.onEditeListenner() {
                            @Override
                            public void Onselected(final Request product) {
                                name.setText(product.getName());
                                price.setText(product.getPrice().toString());
                                status.setText(product.getStatus());
                                save.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (verify()) {
                                            Request request=new Request(name.getText().toString(),status.getText().toString()
                                                    ,Double.parseDouble(price.getText().toString()));
                                            request.setId(product.getId());
                                            http_request.Post_update(MainActivity.this, request
                                                    , new http_request.OnCoupon_lisennter() {
                                                        @Override
                                                        public void onSuccess(int status) {
                                                            if (status==200){
                                                                cleare();
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
                                    }
                                });
                            }
                        });

                RV.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL
                        ,true));
                RV.setAdapter(adapter);
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFailure(String msg) {

            }
        });

    }

    private void cleare() {
        name.setText("");
        status.setText("");

        price.setText("");
    }

    private boolean verify() {

if (name.getText().toString().isEmpty()){
    name.setError("");
    return false;
}

        if (status.getText().toString().isEmpty()){
            status.setError("");
            return false;
        }


        if (price.getText().toString().isEmpty()){
            price.setError("");
            return false;
        }

        return true;
    }
}
