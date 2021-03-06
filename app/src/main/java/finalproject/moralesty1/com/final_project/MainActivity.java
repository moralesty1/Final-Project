package finalproject.moralesty1.com.final_project;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    private double longitude;
    private double latitude;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button current = (Button) findViewById(R.id.currentButton);
        Button favorites = (Button) findViewById(R.id.favButton);
        current.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent intent = new Intent(MainActivity.this, DisplayResults.class);

                CurrentLatLong locate = new CurrentLatLong(MainActivity.this);
                locate.findLocation();
                longitude = locate.getLongitude();
                latitude = locate.getLatitude();

                String lng = Double.toString(longitude);
                String lat = Double.toString(latitude);
                intent.putExtra("Lng", lng);
                intent.putExtra("Lat", lat);
                intent.putExtra("Fav", "NO");
                startActivity(intent);
            }
        });

        try{
            if(!SaveFavorites.getFavorites(context).isEmpty()){
                favorites.setEnabled(true);
                favorites.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){

                        Intent intent = new Intent(MainActivity.this, DisplayResults.class);

                        CurrentLatLong locate = new CurrentLatLong(MainActivity.this);
                        locate.findLocation();
                        longitude = locate.getLongitude();
                        latitude = locate.getLatitude();

                        String lng = Double.toString(longitude);
                        String lat = Double.toString(latitude);
                        intent.putExtra("Lng", lng);
                        intent.putExtra("Lat", lat);
                        intent.putExtra("Fav", "YES");
                        startActivity(intent);
                    }
                });
            }
        }
        catch (Exception e){

        }



    }
}
