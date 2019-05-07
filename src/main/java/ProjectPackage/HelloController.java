package ProjectPackage;

import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class HelloController extends HttpServlet {


    public HelloController() {
        super();
    }

    @RequestMapping("/")
    public String index() throws Exception {
        String strOut = "<img src=http://localhost:8080/CPU/stream/mjpg > <br><br>";

        return strOut;
    }


   @RequestMapping(value = "/stream/mjpg", method = RequestMethod.GET)
    @ResponseBody
    public void setPageImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
       doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Streamer strm = null; //Генератор данных для потока MJPEG
        try {

            strm = new Streamer(new TextToGraphics());//TextToGraphics генерирует изображение

            OutputStream outputStream = response.getOutputStream();
            response.setContentType("multipart/x-mixed-replace; boundary=--BoundaryString");

            while(true) {
                try {
                    //Разделитель кадров
                    outputStream.write((
                            "--BoundaryString\r\n" +
                                    "Content-type: image/jpeg\r\n" +
                                    "Content-Length: " +
                                    strm.GetImgLength() +
                                    "\r\n\r\n").getBytes());

                    outputStream.write(strm.GetScreen()); //Данные кадра
                    outputStream.write("\r\n\r\n".getBytes());
                    outputStream.flush();


                    TimeUnit.MILLISECONDS.sleep(1000);
                    strm.RefreshImgData();//Обновить картинку
                } catch (Exception e) {
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}