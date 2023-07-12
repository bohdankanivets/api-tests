package org.example;

import org.example.models.AdvancedResult;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AdvancedSearchTest {

    ImdbApiClient imdbApiClient = new ImdbApiClient();
    Assertion assertion = new Assertion();

    @Test
    public void advancedSearchTitleType() throws IOException {

        Map<String, String> queryParametersMovieAndSeries = new HashMap<>();
        queryParametersMovieAndSeries.put("title", "batman");
        queryParametersMovieAndSeries.put("title_type", "tv_movie,tv_series");
        int quantityMovieAndSeries = Objects.requireNonNull(imdbApiClient.searchService
                        .advancedSearch(queryParametersMovieAndSeries)
                        .execute()
                        .body())
                .results
                .size();
        Map<String, String> queryParametersMovie = new HashMap<>();
        queryParametersMovie.put("title", "batman");
        queryParametersMovie.put("title_type", "tv_movie");
        int quantityMovie = Objects.requireNonNull(imdbApiClient.searchService
                        .advancedSearch(queryParametersMovie)
                        .execute()
                        .body())
                .results
                .size();
        assertion.assertTrue(quantityMovieAndSeries > quantityMovie,
                "Number of first request results less, that from second");
    }

    @Test
    public void advancedSearchGenres() throws IOException {

        SoftAssert softAssert = new SoftAssert();

        Map<String, String> queryParameterGenres = new HashMap<>();
        queryParameterGenres.put("genres", "comedy,thriller");
        List<AdvancedResult> comedyAndThrillerResult = Objects.requireNonNull(imdbApiClient.searchService
                .advancedSearch(queryParameterGenres)
                .execute()
                .body())
                .results;

        for (AdvancedResult film: comedyAndThrillerResult) {
            softAssert.assertTrue(film.genres.toLowerCase().contains("comedy") | film.genres.toLowerCase().contains("thriller"),
                    film.title + "' has differ genres '" + film.genres + "', where '" + film.title + "' and '" + film.genres + "' are fields values from the result");
        }

        softAssert.assertAll();
    }
}
