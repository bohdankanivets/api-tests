package org.example.services;

import okhttp3.ResponseBody;
import org.example.models.AdvancedSearchResponse;
import org.example.models.SearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface SearchService {

    @GET("search/k_kw0qy43q/{expression}")
    Call<SearchResponse> searchMovies(@Path("expression") String expression);

    @GET("advancedsearch/k_kw0qy43q/?")
    Call<AdvancedSearchResponse> advancedSearch(@QueryMap Map<String, String> options);

}
