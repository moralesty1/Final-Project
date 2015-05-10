package finalproject.moralesty1.com.final_project;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tyler on 5/10/2015.
 */
public class SaveFavorites {

    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Product_Favorite";

    public static void save(Context context, ArrayList<Theater> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.apply();
    }

    public static void addFavorite(Context context, Theater theater) {
        ArrayList<Theater> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<Theater>();
        favorites.add(theater);
        save(context, favorites);
    }

    public static ArrayList<Theater> getFavorites(Context context) {
        SharedPreferences settings;
        List<Theater> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            Theater[] favoriteItems = gson.fromJson(jsonFavorites,
                    Theater[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<Theater>(favorites);
        } else
            return null;

        return (ArrayList<Theater>) favorites;
    }

}
