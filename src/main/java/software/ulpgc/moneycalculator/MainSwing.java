package software.ulpgc.moneycalculator;

import software.ulpgc.moneycalculator.control.ExchangeCommand;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.swing.MainFrame;
import software.ulpgc.moneycalculator.api.APICurrencyLoader;
import software.ulpgc.moneycalculator.api.APIExchangeRateLoader;

import java.util.List;

public class MainSwing {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        CurrencyLoader currencyLoader = new APICurrencyLoader();
        List<Currency> currencies = currencyLoader.load();
        ExchangeRateLoader exchangeRateLoader = new APIExchangeRateLoader();
        MoneyDialog moneyDialog = frame.getMoneyDialog().define(currencies);
        CurrencyDialog currencyDialog = frame.getCurrencyDialog().define(currencies);
        MoneyDisplay moneyDisplay = frame.getMoneyDisplay();
        frame.add("exchange", new ExchangeCommand(moneyDialog, currencyDialog, exchangeRateLoader, moneyDisplay));
        frame.setVisible(true);
    }
}
