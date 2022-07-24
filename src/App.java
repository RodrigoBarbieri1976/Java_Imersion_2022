import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    
    public static void main(String[] args) throws Exception {

        // do a conection and find HTTP top 250 movies
        String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        URI adress = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(adress).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // catch only interesting data (title, poster, rating)
        var parser = new JsonParser();
        List<Map<String, String>> topMovies = parser.parse(body);

        //Definy color background arguments
        // Declare ANSI_RESET to reset color
        final String ANSI_RESET = "\u001B[0m";
        // Declare background color 
        final String ANSI_RED_BACKGROUND = "\u001B[41m";
        
        // Shows and handler the data
        for (Map<String, String> movies : topMovies) {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Title: " + movies.get("title"));
            System.out.println("Image: " + movies.get("image"));
            System.out.println(ANSI_RED_BACKGROUND + "Rating: " + movies.get("imDbRating") + ANSI_RESET);        
            int rating = (int) Math.round(Double.parseDouble(movies.get("imDbRating")));
            for (int i = 1; i <= rating; i++) {
            	System.out.print("\u2b50");
            }        
            System.out.println();
            System.out.println("----------------------------------------------------------------------");
        }
    
    }

}

