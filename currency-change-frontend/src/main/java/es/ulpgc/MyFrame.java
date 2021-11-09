package es.ulpgc;

import es.ulpgc.model.Currencies;
import es.ulpgc.model.Exchange;
import es.ulpgc.services.CurrencyChangeClient;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Arrays;

public class MyFrame extends JFrame {

    private final JPanel jPanelInput;

    private final JPanel jPanelMain;

    private final JComboBox<String> currenciesSource;

    private final JComboBox<String> currenciesDestination;

    private final Label sourceLabel;

    private final Label destinationLabel;

    private final CurrencyChangeClient client;

    private final Currencies currencies;

    private final JTextField jTextField;

    private final Label inputLabel;

    public MyFrame() {
        super("Currencies exchange");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        this.client = new CurrencyChangeClient();
        jPanelMain = new JPanel();
        jPanelMain.setLayout(new BoxLayout(jPanelMain, BoxLayout.PAGE_AXIS));
        jPanelInput = new JPanel();
        jPanelMain.add(jPanelInput);
        currenciesSource = new JComboBox<>();
        currenciesDestination = new JComboBox<>();
        jTextField = new JTextField();
        inputLabel = new Label("Input value: ");
        sourceLabel = new Label("Select source currency: ");
        destinationLabel = new Label("Select destination currency: ");
        Label output = new Label();
        JButton jButton = new JButton("OK");
        jButton.setAlignmentX(CENTER_ALIGNMENT);
        jButton.addActionListener(r -> {
            String text = jTextField.getText();
            Double value = Double.valueOf(text);
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
        this.currencies = client.getCurrencies();
        Arrays.stream(this.currencies.getCurrencies()).forEach(c -> currenciesSource.addItem(c.getCurrency()));
        Arrays.stream(this.currencies.getCurrencies()).forEach(c -> currenciesDestination.addItem(c.getCurrency()));
        add(jPanelMain);
        pack();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new MyFrame().setVisible(true));
    }

}
