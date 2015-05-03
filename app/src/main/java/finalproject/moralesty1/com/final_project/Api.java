package finalproject.moralesty1.com.final_project;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Tyler on 5/3/2015.
 */
public class Api {

    // Notice these are both Static

    // This is the endpoint URL we will be accessing -- it will return to us JSON data
    private static final String ENDPOINT = "http://private-8ceb6-learnretofitpeople.apiary-mock.com/people/";
    // PeopleService is an interface which describes all of the network calls we can make
    private static PeopleService peopleService;

    // A private parameterless constructor prevents instantiation
    private Api() {
    }

    // This function ensures that only one instance of the Service is ever instantiated
    public static PeopleService get() {
        if (peopleService == null) {
            // Gson is a JSON serialization wrapper. It defines how we wish to interpret incoming JSON
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();

            // The RestAdapter is the link between Retrofit and our interface. It describes how to return the service
            RestAdapter restAdapter = new RestAdapter.Builder()
                    // Set the URL where it's going to be called
                    .setEndpoint(ENDPOINT)
                            // We set a converter so the JSON author can have a different naming convention than us
                    .setConverter(new GsonConverter(gson))
                            // Setting a higher log level is great for debugging, but not necessary
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                            // Build the converter
                    .build();

            // Create it, assign it to our static People Service
            peopleService = restAdapter.create(PeopleService.class);
        }

        return peopleService;
    }
}
