package com.hfad.ezycommerceapp.Retrofit;

import com.hfad.ezycommerceapp.ProductModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {

    @GET("book?nim=2201791692&nama=OengDavidSantoso")
    Call<ProductModel> getList();

    @GET("book/{bookId}/\n" +
            "?nim=2201791692&nama=OengDavidSantoso")
    Call<ProductModel> getDetail();


}
