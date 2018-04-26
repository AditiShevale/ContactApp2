package com.example.aditi.contactapp2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.aditi.contactapp2.Pojo.Contact;
import com.example.aditi.contactapp2.Pojo.Recycler;

import org.json.JSONException;

import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   private Recycler mRecyclerContact;
   private RecyclerView mRecyclerView;
   //private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        //mProgressBar = findViewById(R.id.progressBar);

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
           // mProgressBar.setVisibility(View.VISIBLE);
        }

        protected List<Contact> doInBackground(URL... urls) {


            try {
                List<Contact>  result = Network.fetchContactData(urls[0]);
                return  result;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(List<Contact> contactList) {
//            Toast.makeText(MainActivity.this, String.valueOf(contactList),
//                    Toast.LENGTH_SHORT).show();
            super.onPostExecute(contactList);
            //mProgressBar.setVisibility(View.INVISIBLE);
            mRecyclerContact = new Recycler(contactList);
            //Log.i("lo", String.valueOf(contactList));
            mRecyclerView.setAdapter(mRecyclerContact);
            mRecyclerContact.notifyDataSetChanged();


        }
    }
    }

