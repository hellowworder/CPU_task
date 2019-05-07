package ProjectPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

public class TextToGraphics {

    private static File outputFile;
    private static BufferedImage img;
    private Thread MXBean;

    public TextToGraphics(){
        MXBean = new ThreadMXBeanTest().new UserThread();
    }

    public BufferedImage getImage(){

        String text = MXBean.toString();
        img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        Font font = new Font("Arial", Font.PLAIN, 48);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(text);
        int height = fm.getHeight();
        g2d.dispose();

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        if(Integer.parseInt(text.replace("%","")) < 50){
            g2d.setColor(Color.BLACK);
        } else {
            g2d.setColor(Color.RED);
        }

        g2d.drawString(text, 0, fm.getAscent());
        g2d.dispose();

        //Сохранение файла. Фрагмент для отладки
        /*outputFile = new File("image.jpg");
        try {

            BufferedImage newBufferedImage = new BufferedImage(img.getWidth(),
                    img.getHeight(), BufferedImage.TYPE_INT_RGB);
            newBufferedImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);

            // write to jpeg file
            ImageIO.write(newBufferedImage, "jpg", outputFile);

        } catch (IOException ex) {
            ex.printStackTrace();
        }*/

        //конвертация в jpeg
        BufferedImage newBufferedImage = new BufferedImage(img.getWidth(),
                img.getHeight(), BufferedImage.TYPE_INT_RGB);
        newBufferedImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);


        return newBufferedImage;
    }

}