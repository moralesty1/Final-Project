package finalproject.moralesty1.com.final_project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tyler on 5/3/2015.
 */
public class TheaterAdapter extends RecyclerView.Adapter<TheaterAdapter.ViewHolder> {
    private static final int ITEM_RES_ID = R.layout.item_theater;

    private List<Theater> theaters;

    private ViewHolder.ItemClickListener listener;
    private Context context;

    public TheaterAdapter(final List<Theater> theaters) {
        this.theaters = theaters;
        listener = new ViewHolder.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Theater theater = theaters.get(position);
                // Whatever you gotta do!
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
        holder.theaterName.setText(theater.getName());
        holder.theaterDistance.setText(theater.getDistance());

    }

    @Override
    public int getItemCount() {
        return theaters.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemClickListener listener;
        TextView theaterName;
        TextView theaterDistance;



        public ViewHolder(View itemView, ItemClickListener listener) {
            super(itemView);
            this.listener = listener;
            theaterName = (TextView) itemView.findViewById(R.id.theater_name);
            theaterDistance = (TextView) itemView.findViewById(R.id.theater_distance);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(view, getPosition());
        }

        public static interface ItemClickListener {
            public void onItemClick(View view, int position);
        }
    }
}
