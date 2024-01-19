package software.ulpgc.moneycalculator.mock;

import software.ulpgc.moneycalculator.CurrencyLoader;
import software.ulpgc.moneycalculator.model.Currency;

import java.util.List;

public class MockCurrencyLoader implements CurrencyLoader {
    @Override
    public List<Currency> load() {
        return List.of(
                new Currency("USD"),
                new Currency("EUR"),
                new Currency("GBP")
        );
    }
}
