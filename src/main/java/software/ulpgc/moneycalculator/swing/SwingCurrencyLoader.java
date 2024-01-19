package software.ulpgc.moneycalculator.swing;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import software.ulpgc.moneycalculator.CurrencyLoader;
import software.ulpgc.moneycalculator.model.Currency;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SwingCurrencyLoader implements CurrencyLoader {
    private static final String API_KEY = "c6bb5a99a896e39c49cee77f";
    @Override
    public List<Currency> load() {
        try {
            return currenciesToList(loadJson());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private List<Currency> currenciesToList(String json) {
        List<Currency> list = new ArrayList<>();
        JsonObject symbols = JsonParser.parseString(json).getAsJsonObject().get("conversion_rates").getAsJsonObject();
        for (String code : symbols.keySet()) {
            list.add(new Currency(code));
        }
        return list;
    }

    private String loadJson() throws IOException {
        URL url = new URL("https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/ANG");
        try (InputStream is = url.openStream()){
            return new String(is.readAllBytes());
        }

    }
}
