package app.api.tutorial.Interface;

import app.api.tutorial.Response.Data;
import app.api.tutorial.Response.Setting;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api.php")
    Call<Data> getUser(@Query("name") String name);

    @GET("api.php?data")
    Call<Setting> getData();
}
