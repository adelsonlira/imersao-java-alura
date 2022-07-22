import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // url e chama extrator para os top 250 filmes
        // String url = "https://imdb-api.com/en/API/Top250Movies/k_32lkarv1";
        // String url = "https://alura-imdb-api.herokuapp.com/movies";
        // var extrator = new ExtratorDeConteudoDoIMDB();

        // url e chama extrator para imagens da Nasa
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        var extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados dos conteudo

        extrator.extraiConteudos(json);
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeStickers();

        // for (Map<String, String> conteudo : listaDeConteudos) {

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            String titulo = conteudo.getTitulo();

            InputStream inputStream = new URL(conteudo.getUrlImage()).openStream();
            String nomeArquivo = titulo + ".png";

            geradora.criar(inputStream, nomeArquivo);

            System.out.println(i + 1 + ") " + titulo);
            System.out.println("-------------------------");

        }

    }

}
