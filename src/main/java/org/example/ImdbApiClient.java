package org.example;

import okhttp3.OkHttpClient;
import org.example.services.SearchService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImdbApiClient {

    public SearchService searchService;

    public ImdbApiClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://imdb-api.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        searchService = retrofit.create(SearchService.class);
    }


}
