package com.mahmud.ramadan2017;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by User on 5/28/2017.
 */

public interface TimingsAPI {
    @GET("http://api.aladhan.com/timingsByCity?city=Dhaka&country=BD&method=2")
    Call<TimingsMain>getTimingsData();
}
