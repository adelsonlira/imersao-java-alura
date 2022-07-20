import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer conexao HTTP e buscar os top 250 filmes
        //String url = "https://imdb-api.com/en/API/Top250Movies/k_32lkarv1";

        String url = "https://alura-imdb-api.herokuapp.com/movies";

        URI endereco = new URI(url);

        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(endereco).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        String body = response.body();

        // extrair os dados dos filmes (titulo, poster, classificação e ano)
        JsonParser parser = new JsonParser();

        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados dos filmes

        var geradora = new GeradoraDeStickers();

        for (Map<String, String> filme : listaDeFilmes) {

            String urlImage = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImage).openStream();
            String nomeArquivo = titulo + ".png";

            geradora.criar(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println("-------------------------");

        }

    }

}
