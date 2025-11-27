import javax.swing.*;
import java.awt.*;

public class WindowPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextArea showArea;
    private JButton showButton;
    private JLabel capsLockLabel;
    private JPanel mainPanel;

    private WindowColorChange colorChange;
    private WindowExtra extraFeatures;
    private WindowWritingData writingData;

    public JPanel createPanel() {
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Inicjalizacja komponentów i klas pomocnyczych
        initializeComponents();
        initializeHelperClasses();

        mainPanel.add(createFormPanel(), BorderLayout.NORTH);
        mainPanel.add(createShowPanel(), BorderLayout.CENTER);
        mainPanel.add(createControlPanel(), BorderLayout.SOUTH);

        // Konfiguracja dodatkowych funkcji
        extraFeatures.setupEscapeKey();
        writingData.setupEnterKeySupport();
        extraFeatures.setupCapsLockDetection(usernameField, passwordField);

        return mainPanel;
    }

    private void initializeComponents() {
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        showArea = new JTextArea(10, 25);
        showButton = new JButton("Wpisz dane");
        capsLockLabel = new JLabel("", JLabel.CENTER);
    }

    private void initializeHelperClasses() {
        colorChange = new WindowColorChange(showArea, usernameField, passwordField, showButton);
        extraFeatures = new WindowExtra(mainPanel, capsLockLabel);
        writingData = new WindowWritingData(usernameField, passwordField, showArea);
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        formPanel.add(new JLabel("Nazwa użytkownika:"));
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Hasło:"));
        formPanel.add(passwordField);

        capsLockLabel.setForeground(Color.RED);
        capsLockLabel.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(new JLabel(""));
        formPanel.add(capsLockLabel);

        return formPanel;
    }

    private JScrollPane createShowPanel() {
        showArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(showArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Wpisane dane:"));
        return scrollPane;
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));

        JPanel mainButtonPanel = new JPanel();
        showButton.addActionListener(e -> writingData.handleButtonClick());
        mainButtonPanel.add(showButton);

        JButton calculatorButton = new JButton("Otwórz Kalkulator");
        calculatorButton.addActionListener(e -> openCalculator());
        mainButtonPanel.add(calculatorButton);

        JPanel colorPanel = new JPanel();
        colorPanel.setBorder(BorderFactory.createTitledBorder("Zmiana kolorów:"));

        JButton bgColorBtn = new JButton("Kolor tła");
        bgColorBtn.addActionListener(e -> colorChange.changeBackgroundColor());

        JButton textColorBtn = new JButton("Kolor tekstu");
        textColorBtn.addActionListener(e -> colorChange.changeTextColor());

        JButton buttonColorBtn = new JButton("Kolor przycisku");
        buttonColorBtn.addActionListener(e -> colorChange.changeButtonColor());

        colorPanel.add(bgColorBtn);
        colorPanel.add(textColorBtn);
        colorPanel.add(buttonColorBtn);

        panel.add(mainButtonPanel);
        panel.add(colorPanel);

        return panel;
    }

    private void openCalculator() {
        WindowCalculator calculator = new WindowCalculator();
        calculator.createAndShowCalculator();
    }
}