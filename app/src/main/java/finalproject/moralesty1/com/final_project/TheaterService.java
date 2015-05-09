package finalproject.moralesty1.com.final_project;

import java.util.List;
import retrofit.http.GET;

/**
 * Created by Tyler on 5/5/2015.
 */
public interface TheaterService {

    @GET("/name/")
    List<Theater> getName();

}
