package finalproject.moralesty1.com.final_project;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;

import java.util.List;


public class DisplayResults extends ActionBarActivity {

    // This is the dataset for the RecyclerView
    private List<Theater> theater;



    private TextView tvDownload;
    private RecyclerView recyclerPeople;
    private String longitude;
    private String latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);

        Intent intent = getIntent();

        longitude = intent.getStringExtra("Lng");
        latitude = intent.getStringExtra("Lat");


        tvDownload = (TextView) findViewById(R.id.tv_download);
        recyclerPeople = (RecyclerView) findViewById(R.id.recycler_download);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerPeople.setLayoutManager(layoutManager);

        tvDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This is an AsyncTask call
                new TheaterTask().execute();
            }
        });
    }


    // This is the AsyncTask which will make our network call off the main thread
    // otherwise, our app would be locked up until it was finished
    private class TheaterTask extends AsyncTask<Void, Void, Void> {
        String placesUrl;
        @Override
        protected Void doInBackground(Void... params) {
            // This accesses our Api singleton and requests a service, then a specific call.
            placesUrl = Api.makeCall("https://maps.googleapis.com/maps/api/place/textsearch/json?query=movie+theater&types=movie_theater&location=" + latitude + "," + longitude + "&radius=500&key=AIzaSyBkEg-_RAuj_0W9P8YxSKM4Bu4OuM5mBNw");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            recyclerPeople.setAdapter(new TheaterAdapter(theater,placesUrl,latitude,longitude));
            super.onPostExecute(aVoid);
        }
    }
}
