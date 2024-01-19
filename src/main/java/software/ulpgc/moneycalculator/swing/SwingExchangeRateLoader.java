package software.ulpgc.moneycalculator.swing;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import software.ulpgc.moneycalculator.ExchangeRateLoader;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;

public class SwingExchangeRateLoader implements ExchangeRateLoader {
    private static final String API_KEY = "c6bb5a99a896e39c49cee77f";

    @Override
    public ExchangeRate load(Currency from, Currency to) {
        try {
            return getExchangeRate(from,to,loadJson(from));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ExchangeRate getExchangeRate(Currency from, Currency to, String json) {
        JsonObject symbols = JsonParser.parseString(json).getAsJsonObject().get("conversion_rates").getAsJsonObject();
        for (String code : symbols.keySet()) {
            if(code.equals(to.code()))
                return new ExchangeRate(from, to, LocalDate.now(), symbols.get(code).getAsDouble());
        }
        return null;
    }

    private String loadJson(Currency from) throws IOException {
        URL url = new URL("https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + from.code());
        try(InputStream is = url.openStream()){
            return new String(is.readAllBytes());
        }
    }
}
