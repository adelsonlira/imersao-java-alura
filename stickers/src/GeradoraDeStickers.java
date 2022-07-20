import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeStickers {

    public void criar(InputStream inputStream, String nomeArquivo) throws Exception {

        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // criar imagem e com novo tamanho
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        int newheight = height + 200;

        BufferedImage novaImagem = new BufferedImage(width, newheight, BufferedImage.TRANSLUCENT);

        // copiar imagem original para nova imagem
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        Font fonte = new Font("Impact", Font.ROMAN_BASELINE, 75);
        graphics.setFont(fonte);
        graphics.setColor(Color.yellow);        

        // escrever texto na imagem
        String texto = "IMERSÃO";
        int textoWidth = graphics.getFontMetrics().stringWidth(texto);

        int center = (width / 2) - (textoWidth / 2);

        graphics.drawString(texto, center, newheight - 100);

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("imagem/saida/"+nomeArquivo));

    }

}
