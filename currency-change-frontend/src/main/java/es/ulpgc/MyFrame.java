package es.ulpgc;

import es.ulpgc.model.Currencies;
import es.ulpgc.model.Exchange;
import es.ulpgc.services.CurrencyChangeClient;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Arrays;

public class MyFrame extends JFrame {

    private final JComboBox<String> currenciesSource;

    private final JComboBox<String> currenciesDestination;

    private final CurrencyChangeClient client;

    private final JTextField jTextField;

    public MyFrame() {
        super("Currencies exchange");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        this.client = new CurrencyChangeClient();
        JPanel jPanelMain = new JPanel();
        jPanelMain.setLayout(new BoxLayout(jPanelMain, BoxLayout.PAGE_AXIS));
        JPanel jPanelInput = new JPanel();
        jPanelMain.add(jPanelInput);
        currenciesSource = new JComboBox<>();
        currenciesDestination = new JComboBox<>();
        jTextField = new JTextField();
        Label inputLabel = new Label("Input value: ");
        Label sourceLabel = new Label("Select source currency: ");
        Label destinationLabel = new Label("Select destination currency: ");
        Label output = new Label();
        JButton jButton = new JButton("OK");
        jButton.setAlignmentX(CENTER_ALIGNMENT);
        jButton.addActionListener(r -> {
            String text = jTextField.getText();
            Double value = Double.parseDouble(text);
            String source = (String) currenciesSource.getSelectedItem();
            String destination = (String) currenciesDestination.getSelectedItem();
            Exchange exchange = client.exchange(source, destination, value);
            BigDecimal afterChange = exchange.getAfterChange();
            output.setText(afterChange.toString());
        });
        jPanelMain.add(jButton);
        JScrollPane scroller = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jPanelMain.add(scroller);
        GroupLayout layout = new GroupLayout(jPanelInput);
        jPanelInput.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup().
                addComponent(inputLabel).addComponent(sourceLabel).addComponent(destinationLabel));
        hGroup.addGroup(layout.createParallelGroup().
                addComponent(jTextField).addComponent(currenciesSource).addComponent(currenciesDestination));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup().
                addComponent(inputLabel).addComponent(jTextField));
        vGroup.addGroup(layout.createParallelGroup().
                addComponent(sourceLabel).addComponent(currenciesSource));
        vGroup.addGroup(layout.createParallelGroup().
                addComponent(destinationLabel).addComponent(currenciesDestination));
        layout.setVerticalGroup(vGroup);
        Currencies currencies = client.getCurrencies();
        Arrays.stream(currencies.getCurrencies()).forEach(c -> currenciesSource.addItem(c.getCurrency()));
        Arrays.stream(currencies.getCurrencies()).forEach(c -> currenciesDestination.addItem(c.getCurrency()));
        add(jPanelMain);
        pack();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new MyFrame().setVisible(true));
    }

}
