package com.jindagi.byteridge.data;

/**
 * Created by Jindagi on 11/18/2017.
 */

import retrofit2.Call;
import retrofit2.http.GET;


public interface FetchData {
    @GET("v2/59a66706100000fb0408fbb3")
    Call<Object> getStaggeredLayoutData();

}

