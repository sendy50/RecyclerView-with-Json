package com.example.sendy.recyclerview;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
{

    private ArrayList<Itemlist> itemlist;
    private RecyclerView recyclerView;
    private Adapter adapter;
    Itemlist contact;
    public String url="https://api.coinmarketcap.com/v1/ticker/";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new myServerCall().execute();

        //itemlist= new ArrayList<Itemlist>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    class myServerCall extends AsyncTask<String, Void, String>
    {
        ProgressDialog bar;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            bar=ProgressDialog.show(MainActivity.this,"wait","Loading");
        }


        @Override
        protected String doInBackground(String... params)
        {
            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder().url(url).build();

            try
            {
                Response response=client.newCall(request).execute();
                String data=response.body().string();

                if (!response.isSuccessful())
                {
                    throw new IOException("Unexpected code " + response);
                }

                return data;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            bar.dismiss();
            itemlist=new ArrayList<>();

            try {
               // JSONObject object=new JSONObject(s);
                JSONArray arrays = new JSONArray(s);
               // JSONArray array=arrays.getJSONArray("");
                for(int i=0;i<arrays.length();i++)
                {
                    JSONObject object1=arrays.getJSONObject(i);
                    contact=new Itemlist();
                    contact.setName(object1.getString("name"));
                    contact.setPrice(object1.getString("symbol"));
                    itemlist.add(contact);
                }

                adapter=new Adapter(itemlist);
                recyclerView.setAdapter(adapter);

            } catch (JSONException e)
            {
                e.printStackTrace();
            }

            //adapter.notifyDataSetChanged();
        }
    }
}
