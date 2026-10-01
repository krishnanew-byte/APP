import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * View component of the Vehicle Service Cost Estimator MVC application.
 * Provides the graphical interface using JFrame, JTextField, JRadioButton,
 * JCheckBox, JButton, and JLabel.
 */
public class VehicleServiceCostView extends JFrame {
    // Input Components
    private JTextField txtRegNumber;
    private JRadioButton rbTwoWheeler;
    private JRadioButton rbCar;
    private ButtonGroup bgVehicleType;

    // Checkboxes for Services
    private JCheckBox chkGeneralService;
    private JCheckBox chkOilChange;
    private JCheckBox chkBrakeService;
    private JCheckBox chkBatteryCheck;

    // Buttons
    private JButton btnCalculateCost;
    private JButton btnReset;

    // Output Labels
    private JLabel lblTotalCostDisplay;
    private JTextArea txtSummaryArea;

    public VehicleServiceCostView() {
        setTitle("Vehicle Service Cost Estimator (MVC)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 620);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 12));
        mainPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(230, 126, 34)); // Warm automotive orange
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel lblHeader = new JLabel("Automobile Service Cost Estimator", JLabel.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 19));
        lblHeader.setForeground(Color.WHITE);
        headerPanel.add(lblHeader);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Center Content
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // --- Vehicle Details Panel ---
        JPanel vehiclePanel = new JPanel(new GridLayout(2, 2, 10, 10));
        vehiclePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Vehicle Details",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 13)));

        JLabel lblRegNo = new JLabel("Registration Number:");
        lblRegNo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtRegNumber = new JTextField();
        txtRegNumber.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JLabel lblType = new JLabel("Vehicle Type:");
        lblType.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        rbTwoWheeler = new JRadioButton("Two Wheeler");
        rbCar = new JRadioButton("Car", true);
        rbTwoWheeler.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        rbCar.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        bgVehicleType = new ButtonGroup();
        bgVehicleType.add(rbTwoWheeler);
        bgVehicleType.add(rbCar);

        radioPanel.add(rbCar);
        radioPanel.add(rbTwoWheeler);

        vehiclePanel.add(lblRegNo);
        vehiclePanel.add(txtRegNumber);
        vehiclePanel.add(lblType);
        vehiclePanel.add(radioPanel);

        centerPanel.add(vehiclePanel);
        centerPanel.add(Box.createVerticalStrut(10));

        // --- Services Checkbox Panel ---
        JPanel servicePanel = new JPanel(new GridLayout(4, 1, 6, 6));
        servicePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Select Required Services",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 13)));

        chkGeneralService = new JCheckBox("General Service - Rs. 1,000");
        chkOilChange = new JCheckBox("Oil Change - Rs. 800");
        chkBrakeService = new JCheckBox("Brake Service - Rs. 1,200");
        chkBatteryCheck = new JCheckBox("Battery Check - Rs. 500");

        Font checkFont = new Font("Segoe UI", Font.PLAIN, 13);
        chkGeneralService.setFont(checkFont);
        chkOilChange.setFont(checkFont);
        chkBrakeService.setFont(checkFont);
        chkBatteryCheck.setFont(checkFont);

        servicePanel.add(chkGeneralService);
        servicePanel.add(chkOilChange);
        servicePanel.add(chkBrakeService);
        servicePanel.add(chkBatteryCheck);

        centerPanel.add(servicePanel);
        centerPanel.add(Box.createVerticalStrut(10));

        // --- Buttons Panel ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        btnCalculateCost = new JButton("Calculate Cost");
        btnCalculateCost.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnCalculateCost.setBackground(new Color(46, 204, 113));
        btnCalculateCost.setForeground(Color.WHITE);
        btnCalculateCost.setFocusPainted(false);
        btnCalculateCost.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnReset.setBackground(new Color(149, 165, 166));
        btnReset.setForeground(Color.WHITE);
        btnReset.setFocusPainted(false);
        btnReset.setCursor(new Cursor(Cursor.HAND_CURSOR));

        buttonPanel.add(btnCalculateCost);
        buttonPanel.add(btnReset);
        centerPanel.add(buttonPanel);
        centerPanel.add(Box.createVerticalStrut(10));

        // --- Result / Estimation Panel ---
        JPanel resultPanel = new JPanel(new BorderLayout(5, 5));
        resultPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Service Cost Estimate",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 13)));

        JPanel totalCostPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JLabel lblTotalTitle = new JLabel("Total Estimated Cost:");
        lblTotalTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTotalCostDisplay = new JLabel("Rs. 0.00");
        lblTotalCostDisplay.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTotalCostDisplay.setForeground(new Color(192, 57, 43));
        totalCostPanel.add(lblTotalTitle);
        totalCostPanel.add(lblTotalCostDisplay);

        txtSummaryArea = new JTextArea(4, 30);
        txtSummaryArea.setEditable(false);
        txtSummaryArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        txtSummaryArea.setBackground(new Color(248, 249, 250));
        txtSummaryArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JScrollPane scrollPane = new JScrollPane(txtSummaryArea);

        resultPanel.add(totalCostPanel, BorderLayout.NORTH);
        resultPanel.add(scrollPane, BorderLayout.CENTER);

        centerPanel.add(resultPanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    // Input Getters
    public String getRegistrationNumber() {
        return txtRegNumber.getText().trim();
    }

    public String getSelectedVehicleType() {
        return rbTwoWheeler.isSelected() ? "Two Wheeler" : "Car";
    }

    public boolean isGeneralServiceSelected() {
        return chkGeneralService.isSelected();
    }

    public boolean isOilChangeSelected() {
        return chkOilChange.isSelected();
    }

    public boolean isBrakeServiceSelected() {
        return chkBrakeService.isSelected();
    }

    public boolean isBatteryCheckSelected() {
        return chkBatteryCheck.isSelected();
    }

    // Result Update Methods
    public void displayResult(String regNo, String type, List<String> services, double totalCost) {
        lblTotalCostDisplay.setText(String.format("Rs. %,.2f", totalCost));

        StringBuilder sb = new StringBuilder();
        sb.append("Vehicle Reg No : ").append(regNo).append("\n");
        sb.append("Vehicle Type   : ").append(type).append("\n");
        sb.append("Services Chosen:\n");
        if (services.isEmpty()) {
            sb.append("  - None selected\n");
        } else {
            for (String s : services) {
                sb.append("  * ").append(s).append("\n");
            }
        }
        sb.append("---------------------------------------\n");
        sb.append(String.format("TOTAL SERVICE CHARGE: Rs. %,.2f", totalCost));
        txtSummaryArea.setText(sb.toString());
    }

    public void resetFields() {
        txtRegNumber.setText("");
        rbCar.setSelected(true);
        chkGeneralService.setSelected(false);
        chkOilChange.setSelected(false);
        chkBrakeService.setSelected(false);
        chkBatteryCheck.setSelected(false);
        lblTotalCostDisplay.setText("Rs. 0.00");
        txtSummaryArea.setText("");
        txtRegNumber.requestFocus();
    }

    public void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Input Warning", JOptionPane.WARNING_MESSAGE);
    }

    // Event Listener bindings
    public void addCalculateCostListener(ActionListener listener) {
        btnCalculateCost.addActionListener(listener);
    }

    public void addResetListener(ActionListener listener) {
        btnReset.addActionListener(listener);
    }
}
