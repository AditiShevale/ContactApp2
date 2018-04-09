package com.example.aditi.contactapp2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;

import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   private Recycler mRecyclerContact;
   private RecyclerView mRecyclerView;
   private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        mProgressBar = findViewById(R.id.progressBar);

        RecyclerView.LayoutManager mLayoutManager = new
                LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        URL final_Url = Network.buildUrl();
        new MovieDBQueryTask().execute(final_Url);

    }

    private class MovieDBQueryTask extends AsyncTask<URL,Void,List<Contact>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        protected List<Contact> doInBackground(URL... urls) {

            List<Contact> result = null;
            try {
                result = Network.fetchContactData(urls[0]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;

        }

        @Override
        protected void onPostExecute(List<Contact> contactList) {
            Toast.makeText(MainActivity.this, String.valueOf(contactList), Toast.LENGTH_SHORT).show();
            mProgressBar.setVisibility(View.INVISIBLE);
            mRecyclerContact = new Recycler(MainActivity.this,
                    contactList);
            mRecyclerView.setAdapter(mRecyclerContact);

        }
    }
    }

