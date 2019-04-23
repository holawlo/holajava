import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonExample {
    @Test
    void serializeToJson() {
        List<OtherObject> objects = new ArrayList<>();
        objects.add(new OtherObject(3, "o"));
        objects.add(new OtherObject(0, "k"));
        SomeObject someObject = new SomeObject("Adam", 22, BigDecimal.valueOf(20), objects);
        String json = new Gson().toJson(someObject);
        System.out.println(json);
        SomeObject someObject1 = new Gson().fromJson(json, SomeObject.class);
        System.out.println(someObject1);
        System.out.println(someObject.equals(someObject1));
    }

    @Data
    @AllArgsConstructor
    private class SomeObject {
        private String name;
        private Integer age;
        private BigDecimal salary;
        private List<OtherObject> objectList;
    }

    @Data
    @AllArgsConstructor
    private class OtherObject {
        private Integer id;
        private String text;
    }

    @Test
    void nbpApi() {
        try {
            URL url = new URL("http://api.nbp.pl/api/exchangerates/tables/A/last?format=json");
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String result = "";
            String inputLine;
            while ((inputLine = bufferedReader.readLine())!= null){
                result = result + inputLine;
            }
            bufferedReader.close();

            System.out.println(result);
            RatesWrapper[] ratesWrapper = new Gson().fromJson(result, RatesWrapper[].class);
            System.out.println(Arrays.toString(ratesWrapper));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Data
    private class RatesWrapper {
         private String table;
         private String no;
         private String effectiveDate;
         private List<Rate> rates;

    }
    @Data
    private class Rate {
        private String currency;
        private String code;
        private String mid;
    }
}
