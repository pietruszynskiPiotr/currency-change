package es.ulpgc;

import javax.swing.*;

public class MyFrame extends JFrame {

    private final JPanel jPanel;

    public MyFrame() {
        super("Currencies exchange");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        jPanel = new JPanel();
        JComboBox<String> currenciesSource = new JComboBox<>();
        currenciesSource.addItem("EUR");
        currenciesSource.addItem("USD");


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
