package finalproject.moralesty1.com.final_project;




import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * Created by Tyler on 5/3/2015.
 */
public class Api {

    public static String makeCall(String url) {
        // string buffers the url
        StringBuffer buffer_string = new StringBuffer(url);
        String replyString = "";

        // instanciate an HttpClient
        HttpClient httpclient = new DefaultHttpClient();
        // instanciate an HttpGet
        HttpGet httpget = new HttpGet(buffer_string.toString());

        // get the responce of the httpclient execution of the url
        try {
            HttpResponse response = httpclient.execute(httpget);
            InputStream is = response.getEntity().getContent();


            // buffer input stream the result
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayBuffer baf = new ByteArrayBuffer(20);
            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }
            replyString = new String(baf.toByteArray());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        // the result as a string is ready for parsing

        return replyString.trim();
    }




    public static ArrayList parseGooglePlaces(final String response) {

        ArrayList temp = new ArrayList();
        try {

            // make an jsonObject in order to parse the response
            JSONObject jsonObject = new JSONObject(response);

            // make an jsonObject in order to parse the response
            if (jsonObject.has("results")) {

                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {
                    Theater theater = new Theater();
                    if (jsonArray.getJSONObject(i).has("name")) {
                        theater.setName(jsonArray.getJSONObject(i).optString("name"));
                        theater.setAddress(jsonArray.getJSONObject(i).optString("formatted_address"));
                        theater.setLat(jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").optString("lat"));
                        theater.setLng(jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").optString("lng"));
                    }
                    temp.add(theater);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
        return temp;

    }

    public static String parseGoogleMaps(final String response) {
        String distance;
        ArrayList temp = new ArrayList();
        try {

            // make an jsonObject in order to parse the response
            JSONObject jsonObject = new JSONObject(response);

            // make an jsonObject in order to parse the response
            if (jsonObject.has("rows")) {

                JSONArray jsonArray = jsonObject.getJSONArray("rows");
                for (int i = 0; i < jsonArray.length(); i++) {
                    Theater theater = new Theater();
                    if (jsonArray.getJSONObject(i).has("elements")) {
                        JSONArray jsonArray2 = jsonArray.getJSONObject(i).getJSONArray("elements");
                            theater.setDistance(jsonArray2.getJSONObject(i).
                                    getJSONObject("distance").optString("text"));
                            distance = theater.getDistance();
                            return distance;
                    }

                    temp.add(theater);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return distance="Could Not be Found";
        }

        return distance="error";

    }

}
