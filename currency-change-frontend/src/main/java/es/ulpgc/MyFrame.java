package es.ulpgc;

import es.ulpgc.model.Currencies;
import es.ulpgc.services.CurrencyChangeClient;

import javax.swing.*;
import java.util.Arrays;

public class MyFrame extends JFrame {

    private final JPanel jPanel;

    private final CurrencyChangeClient client;

    private final Currencies currencies;

    public MyFrame() {
        super("Currencies exchange");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        jPanel = new JPanel();
        JComboBox<String> currenciesSource = new JComboBox<>();
        this.client = new CurrencyChangeClient();
        this.currencies = client.getCurrencies();
        Arrays.stream(this.currencies.getCurrencies()).forEach(c -> currenciesSource.addItem(c.getCurrency()));

        JComboBox<String> currenciesDestination = new JComboBox<>();
        jPanel.add(currenciesSource);
        jPanel.add(currenciesDestination);
        add(jPanel);
        // jpanel lista z wyborem waluty
        // jpanel lista z wyborem docelowej waluty
        // miejsce do wpisania kwoty
        // ok
        pack();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new MyFrame().setVisible(true));
    }

}
