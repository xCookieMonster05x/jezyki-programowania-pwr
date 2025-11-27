import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class WindowCalculator {
    private JFrame calculatorFrame;
    private JTextField display;
    private double firstNumber = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    public void createAndShowCalculator() {
        calculatorFrame = new JFrame("Kalkulator");
        calculatorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        calculatorFrame.setSize(300, 400);
        calculatorFrame.setLocationRelativeTo(null);

        calculatorFrame.add(createCalculatorPanel());

        setupEscapeKey();

        calculatorFrame.setVisible(true);
    }

    private void setupEscapeKey() {
        // Bind klawisza Escape dla całego okna
        InputMap inputMap = calculatorFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = calculatorFrame.getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "closeCalculator");
        actionMap.put("closeCalculator", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculatorFrame.dispose();
            }
        });
    }

    private JPanel createCalculatorPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 20));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        panel.add(display, BorderLayout.NORTH);

        panel.add(createButtonPanel(), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.addActionListener(this::handleButtonClick);
            panel.add(button);
        }

        return panel;
    }

    private void handleButtonClick(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "0": case "1": case "2": case "3": case "4":
            case "5": case "6": case "7": case "8": case "9":
                inputNumber(command);
                break;
            case "+": case "-": case "*": case "/":
                setOperator(command);
                break;
            case "=":
                calculate();
                break;
            case "C":
                clear();
                break;
        }
    }

    private void inputNumber(String number) {
        if (startNewNumber) {
            display.setText(number);
            startNewNumber = false;
        } else {
            display.setText(display.getText() + number);
        }
    }

    private void setOperator(String newOperator) {
        if (!operator.isEmpty()) {
            calculate();
        }
        firstNumber = Double.parseDouble(display.getText());
        operator = newOperator;
        startNewNumber = true;
    }

    private void calculate() {
        if (operator.isEmpty()) return;

        double secondNumber = Double.parseDouble(display.getText());
        double result = 0;

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber != 0) {
                    result = firstNumber / secondNumber;
                } else {
                    display.setText("Błąd");
                    startNewNumber = true;
                    operator = "";
                    return;
                }
                break;
        }

        display.setText(String.format("%s", result));
        operator = "";
        startNewNumber = true;
    }

    private void clear() {
        display.setText("0");
        firstNumber = 0;
        operator = "";
        startNewNumber = true;
    }
}