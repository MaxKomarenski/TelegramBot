import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Weather extends Action{

    @Override
    public String returnerOfData(){
        try {
            Document doc = Jsoup.connect("https://www.accuweather.com/ru/ua/lviv/324561/weather-forecast/324561").get();
            Elements temperature = doc.getElementsByAttributeValue("class", "temp");

            return temperature.get(0).text();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }
}