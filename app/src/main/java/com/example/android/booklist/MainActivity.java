package com.example.android.booklist;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    private BookAdapter mAdapter;
    public static final String LOG_TAG = MainActivity.class.getName();
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String recievedInput = intent.getStringExtra("Data");



        BookAsyncTask task = new BookAsyncTask();
        task.execute(recievedInput);


        listView = findViewById(R.id.list);
        mEmptyStateTextView = findViewById(R.id.empty_view);
        listView.setEmptyView(mEmptyStateTextView);


        mAdapter = new BookAdapter(this,new ArrayList<Books>());
       listView.setAdapter(mAdapter);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               Books bookIndex = mAdapter.getItem(position);

               Uri bookUri = Uri.parse(bookIndex.getUrl());

               Intent websiteIntent = new Intent(Intent.ACTION_VIEW,bookUri);

               startActivity(websiteIntent);

           }
       });



    }

    private class BookAsyncTask extends AsyncTask<String,Void, List<Books>>{

        @Override
        protected List<Books> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
           List<Books> result = QueryUtills.fetchEarthquakeData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<Books> data) {

            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            mAdapter.clear();

            if(data != null && !data.isEmpty()){
                mAdapter.addAll(data);
            }
            mEmptyStateTextView.setText(R.string.no_books);

        }
    }


}
