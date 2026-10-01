import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * View component of the Student Grade Calculator MVC application.
 * Manages the Swing graphical user interface.
 */
public class StudentView extends JFrame {
    // Input components
    private JTextField txtStudentName;
    private JTextField txtSubject1;
    private JTextField txtSubject2;
    private JTextField txtSubject3;

    // Action button
    private JButton btnCalculate;
    private JButton btnClear;

    // Result components
    private JTextField txtTotalMarks;
    private JTextField txtAverageMarks;
    private JTextField txtGrade;

    public StudentView() {
        setTitle("Student Grade Calculator (MVC)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 520);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 15));
        mainPanel.setBorder(new EmptyBorder(15, 20, 20, 20));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(41, 128, 185));
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel lblHeader = new JLabel("Student Grade Calculator", JLabel.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblHeader.setForeground(Color.WHITE);
        headerPanel.add(lblHeader);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Center Panel containing Input and Result sections
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // --- Input Panel ---
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Student & Marks Details",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 13)));

        JLabel lblName = new JLabel("Student Name:");
        lblName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtStudentName = new JTextField();

        JLabel lblSub1 = new JLabel("Subject 1 Marks:");
        lblSub1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtSubject1 = new JTextField();

        JLabel lblSub2 = new JLabel("Subject 2 Marks:");
        lblSub2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtSubject2 = new JTextField();

        JLabel lblSub3 = new JLabel("Subject 3 Marks:");
        lblSub3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtSubject3 = new JTextField();

        inputPanel.add(lblName);
        inputPanel.add(txtStudentName);
        inputPanel.add(lblSub1);
        inputPanel.add(txtSubject1);
        inputPanel.add(lblSub2);
        inputPanel.add(txtSubject2);
        inputPanel.add(lblSub3);
        inputPanel.add(txtSubject3);

        centerPanel.add(inputPanel);
        centerPanel.add(Box.createVerticalStrut(10));

        // --- Button Panel ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        btnCalculate = new JButton("Calculate Result");
        btnCalculate.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnCalculate.setBackground(new Color(39, 174, 96));
        btnCalculate.setForeground(Color.WHITE);
        btnCalculate.setFocusPainted(false);
        btnCalculate.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnClear = new JButton("Clear");
        btnClear.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnClear.setBackground(new Color(149, 165, 166));
        btnClear.setForeground(Color.WHITE);
        btnClear.setFocusPainted(false);
        btnClear.setCursor(new Cursor(Cursor.HAND_CURSOR));

        buttonPanel.add(btnCalculate);
        buttonPanel.add(btnClear);
        centerPanel.add(buttonPanel);
        centerPanel.add(Box.createVerticalStrut(10));

        // --- Result Panel ---
        JPanel resultPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        resultPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Calculation Results",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 13)));

        JLabel lblTotal = new JLabel("Total Marks:");
        lblTotal.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtTotalMarks = new JTextField();
        txtTotalMarks.setEditable(false);
        txtTotalMarks.setBackground(new Color(245, 245, 245));

        JLabel lblAvg = new JLabel("Average Marks:");
        lblAvg.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtAverageMarks = new JTextField();
        txtAverageMarks.setEditable(false);
        txtAverageMarks.setBackground(new Color(245, 245, 245));

        JLabel lblGrade = new JLabel("Grade Assigned:");
        lblGrade.setFont(new Font("Segoe UI", Font.BOLD, 13));
        txtGrade = new JTextField();
        txtGrade.setEditable(false);
        txtGrade.setFont(new Font("Segoe UI", Font.BOLD, 14));
        txtGrade.setForeground(new Color(192, 57, 43));
        txtGrade.setBackground(new Color(245, 245, 245));

        resultPanel.add(lblTotal);
        resultPanel.add(txtTotalMarks);
        resultPanel.add(lblAvg);
        resultPanel.add(txtAverageMarks);
        resultPanel.add(lblGrade);
        resultPanel.add(txtGrade);

        centerPanel.add(resultPanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Grade Criteria Information at bottom
        JLabel lblCriteria = new JLabel("Criteria: A (>=90) | B (75-89) | C (60-74) | D (50-59) | F (<50)", JLabel.CENTER);
        lblCriteria.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblCriteria.setForeground(Color.GRAY);
        mainPanel.add(lblCriteria, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    // Input Getters
    public String getStudentName() {
        return txtStudentName.getText().trim();
    }

    public String getSubject1Text() {
        return txtSubject1.getText().trim();
    }

    public String getSubject2Text() {
        return txtSubject2.getText().trim();
    }

    public String getSubject3Text() {
        return txtSubject3.getText().trim();
    }

    // Result Setters
    public void setTotalMarks(double total) {
        txtTotalMarks.setText(String.format("%.2f", total));
    }

    public void setAverageMarks(double avg) {
        txtAverageMarks.setText(String.format("%.2f", avg));
    }

    public void setGrade(String grade) {
        txtGrade.setText(grade);
    }

    // Helper methods
    public void clearFields() {
        txtStudentName.setText("");
        txtSubject1.setText("");
        txtSubject2.setText("");
        txtSubject3.setText("");
        txtTotalMarks.setText("");
        txtAverageMarks.setText("");
        txtGrade.setText("");
        txtStudentName.requestFocus();
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    // Listener registration
    public void addCalculateListener(ActionListener listener) {
        btnCalculate.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        btnClear.addActionListener(listener);
    }
}
