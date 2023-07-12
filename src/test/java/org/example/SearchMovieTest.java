package org.example;

import org.example.models.SearchResponse;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.IOException;

public class SearchMovieTest {
   ImdbApiClient imdbApiClient = new ImdbApiClient();
   Assertion assertion = new Assertion();

   @Test
   public void checkSearchingMovieInception() throws IOException {
      SearchResponse searchResponse = imdbApiClient.searchService
              .searchMovies("inception 2010")
              .execute().body();
      String actualTitle = searchResponse.results.get(0).title;
      assertion.assertEquals(actualTitle, "Inception",
              "Title is not equal to 'Inception'. Title is " + actualTitle);
      assertion.assertTrue(searchResponse.results.get(0).description.contains("2010"), "Description does not contain '2010' value");
   }

   @Test
   public void checkSearchingMovieWithIncorrectExpression() throws IOException {
      SearchResponse searchResponse = imdbApiClient.searchService
              .searchMovies("")
              .execute().body();
      assertion.assertTrue(searchResponse.results.isEmpty(), "Result is not empty");
      assertion.assertTrue(searchResponse.errorMessage.contains("Invalid request."),
              "searchResponse does not contain 'Invalid request.'");
   }

   @Test
   public void checkSearchingMovieBatmen() throws IOException {
      SearchResponse searchResponse = imdbApiClient.searchService
              .searchMovies("batman 1994")
              .execute().body();
      String actualTitle =  searchResponse.results.get(0).title.toLowerCase();
      assertion.assertTrue(actualTitle.contains("batman"), "Title does not contain 'batman' value");
      assertion.assertFalse(actualTitle.contains("1994"), "Title contains '1994' value");
      assertion.assertTrue(searchResponse.results.get(0).description.contains("1994"),
              "Description doest not contain '1994' value");
   }


}
