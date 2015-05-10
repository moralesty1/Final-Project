package finalproject.moralesty1.com.final_project;

import android.content.Context;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tyler on 5/3/2015.
 */


public class TheaterAdapter extends RecyclerView.Adapter<TheaterAdapter.ViewHolder> {
    private static final int ITEM_RES_ID = R.layout.item_theater;

    private static ArrayList<Theater> theaters;
    private double lat;
    private double lng;
    private ViewHolder.ItemClickListener listener;
    private static Context context;

    public TheaterAdapter(final List<Theater> theaters,String pUrl,String sLat, String sLng,String status) {
        lat = Double.parseDouble(sLat);
        lng = Double.parseDouble(sLng);
        if(status.equals("YES"))
        {
            this.theaters = SaveFavorites.getFavorites(context);
        }
        else
        {
            this.theaters = Api.parseGooglePlaces(pUrl);
        }

        listener = new ViewHolder.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Theater theater = theaters.get(position);
                SaveFavorites.addFavorite(context ,theater);
            }
        };
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(ITEM_RES_ID, parent, false);
        if (context == null) {
            context = parent.getContext();
        }
        ViewHolder viewHolder = new ViewHolder(view, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Theater theater = theaters.get(position);
        double sDist;
        double dLat = Double.parseDouble(theater.getLat());
        double dLng = Double.parseDouble(theater.getLng());

        sDist = distance(lat, lng, dLat, dLng, "M");
        String dist = new DecimalFormat("0.##").format(sDist);
        theater.setDistance(dist);

        holder.theaterName.setText("Name: "+theater.getName());
        holder.theaterAddress.setText("Address: "+theater.getAddress());
        holder.theaterDistance.setText("Distance: " + theater.getDistance() + " Miles");


    }

    @Override
    public int getItemCount() {
        return theaters.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemClickListener listener;
        TextView theaterName;
        TextView theaterAddress;
        TextView theaterDistance;



        public ViewHolder(View itemView, ItemClickListener listener) {
            super(itemView);
            this.listener = listener;
            theaterName = (TextView) itemView.findViewById(R.id.theater_name);
            theaterAddress = (TextView) itemView.findViewById(R.id.theater_address);
            theaterDistance = (TextView) itemView.findViewById(R.id.theater_distance);



            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(view, getPosition());
            TextView theaterFav = (TextView) itemView.findViewById(R.id.favorite);
            theaterFav.setText("Saved as Favorite");

        }

        public interface ItemClickListener {
            void onItemClick(View view, int position);
        }
    }

    public double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit.equals("K")) {
            dist = dist * 1.609344;
        } else if (unit.equals("N")) {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
