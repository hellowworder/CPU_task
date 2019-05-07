package ProjectPackage;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//Класс
//обновляет изображение 
//возвращает изображение массивом байтов
//возвращает размер возвращаемого массива байтов
public class Streamer {

    //byte[] data;
    byte[] imgData;
    TextToGraphics ttg;

    public Streamer(TextToGraphics TextConverter) throws Exception {
        ttg = TextConverter;
        RefreshImgData();
    }

    public void RefreshImgData()throws Exception {
        Thread th3= new ThreadMXBeanTest().new UserThread(); //Для вычисления нагрузки
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write( ttg.getImage(), "jpg", baos );
        baos.flush();

        imgData = baos.toByteArray();
        baos.close();
    }

    public byte[] GetScreen() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        outputStream.write(imgData);
        outputStream.write("\r\n\r\n".getBytes());
        outputStream.flush();
        return outputStream.toByteArray();
    }

    public int GetImgLength(){
        return imgData.length;
    }

}
