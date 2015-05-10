package finalproject.moralesty1.com.final_project;

/**
 * Created by Tyler on 5/3/2015.
 */
public class Theater {
    private String name;
    private String distance;
    private String address;
    private String lat;
    private String lng;

    public Theater(){
        this.name = "";
        this.distance = "";
        this.address = "";
        this.lat = "";
        this.lng = "";
    }

    public void setLat (String latitude){
        this.lat = latitude;
    }

    public void setLng (String longitude){
        this.lng = longitude;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDistance(String distance){
        this.distance = distance;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getDistance() {
        return distance;
    }

    public String getAddress() {
        return address;
    }

    public String getLat(){
        return lat;
    }

    public String getLng(){
        return lng;
    }


}
