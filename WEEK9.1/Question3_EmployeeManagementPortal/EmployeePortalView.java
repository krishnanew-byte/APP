import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * View component of the Employee Management Portal MVC application.
 * Contains both Login Window and Main Application Window with Menu Bar.
 * Utilizes: JFrame, JTextField, JPasswordField, JButton, JMenuBar, JMenu, JMenuItem, JOptionPane.
 */
public class EmployeePortalView {
    // --- Login Window Components ---
    private JFrame loginFrame;
    private JTextField txtLoginUsername;
    private JPasswordField txtLoginPassword;
    private JButton btnLogin;

    // --- Main Window Components ---
    private JFrame mainFrame;
    private JMenuBar menuBar;
    private JMenu menuEmployee;
    private JMenu menuTools;
    private JMenu menuExit;

    private JMenuItem itemAddEmployee;
    private JMenuItem itemViewEmployee;
    private JMenuItem itemChangePassword;
    private JMenuItem itemLogout;
    private JMenuItem itemExitApp;

    // Content cards
    private JPanel cardsPanel;
    private CardLayout cardLayout;

    // Add Employee Form Components
    private JTextField txtAddEmpId;
    private JTextField txtAddEmpName;
    private JTextField txtAddDepartment;
    private JButton btnSaveEmployee;
    private JButton btnClearAddEmp;

    // View Employee Components
    private DefaultTableModel tableModel;
    private JTable employeeTable;

    // Change Password Form Components
    private JPasswordField txtOldPassword;
    private JPasswordField txtNewPassword;
    private JPasswordField txtConfirmPassword;
    private JButton btnUpdatePassword;
    private JButton btnClearChangePass;

    public EmployeePortalView() {
        initLoginFrame();
        initMainFrame();
    }

    // ==========================================
    // 1. LOGIN FRAME INITIALIZATION
    // ==========================================
    private void initLoginFrame() {
        loginFrame = new JFrame("Employee Portal - Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(420, 320);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setResizable(false);

        JPanel panel = new JPanel(new BorderLayout(10, 15));
        panel.setBorder(new EmptyBorder(20, 25, 20, 25));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(44, 62, 80));
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel lblHeader = new JLabel("Employee Portal Login", JLabel.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblHeader.setForeground(Color.WHITE);
        headerPanel.add(lblHeader);
        panel.add(headerPanel, BorderLayout.NORTH);

        // Fields Panel
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 15));
        formPanel.setBorder(new EmptyBorder(15, 5, 15, 5));

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 13));
        txtLoginUsername = new JTextField();
        txtLoginUsername.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 13));
        txtLoginPassword = new JPasswordField();
        txtLoginPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        formPanel.add(lblUsername);
        formPanel.add(txtLoginUsername);
        formPanel.add(lblPassword);
        formPanel.add(txtLoginPassword);
        panel.add(formPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLogin.setBackground(new Color(41, 128, 185));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginFrame.getRootPane().setDefaultButton(btnLogin);

        btnPanel.add(btnLogin);
        panel.add(btnPanel, BorderLayout.SOUTH);

        loginFrame.setContentPane(panel);
    }

    // ==========================================
    // 2. MAIN APPLICATION FRAME INITIALIZATION
    // ==========================================
    private void initMainFrame() {
        mainFrame = new JFrame("Employee Management Portal");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(680, 520);
        mainFrame.setLocationRelativeTo(null);

        // Build Menu Bar
        menuBar = new JMenuBar();

        // 1. Employee Menu
        menuEmployee = new JMenu("Employee");
        itemAddEmployee = new JMenuItem("Add Employee");
        itemViewEmployee = new JMenuItem("View Employee");
        menuEmployee.add(itemAddEmployee);
        menuEmployee.add(itemViewEmployee);

        // 2. Tools Menu
        menuTools = new JMenu("Tools");
        itemChangePassword = new JMenuItem("Change Password");
        menuTools.add(itemChangePassword);

        // 3. Exit Menu
        menuExit = new JMenu("Exit");
        itemLogout = new JMenuItem("Logout");
        itemExitApp = new JMenuItem("Exit Application");
        menuExit.add(itemLogout);
        menuExit.addSeparator();
        menuExit.add(itemExitApp);

        menuBar.add(menuEmployee);
        menuBar.add(menuTools);
        menuBar.add(menuExit);
        mainFrame.setJMenuBar(menuBar);

        // CardLayout container for swapping center panels
        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        cardsPanel.add(createDashboardCard(), "WELCOME");
        cardsPanel.add(createAddEmployeeCard(), "ADD_EMPLOYEE");
        cardsPanel.add(createViewEmployeeCard(), "VIEW_EMPLOYEE");
        cardsPanel.add(createChangePasswordCard(), "CHANGE_PASSWORD");

        mainFrame.setContentPane(cardsPanel);
    }

    // --- Card 1: Welcome Dashboard ---
    private JPanel createDashboardCard() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(40, 30, 40, 30));

        JPanel welcomeBox = new JPanel(new GridLayout(4, 1, 10, 10));
        welcomeBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199)),
                new EmptyBorder(25, 25, 25, 25)));
        welcomeBox.setBackground(Color.WHITE);

        JLabel title = new JLabel("Welcome to Employee Management Portal", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(44, 62, 80));

        JLabel info = new JLabel("Use the top navigation menu to access management features:", JLabel.CENTER);
        info.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel opt1 = new JLabel("• Employee -> Add Employee / View Employee", JLabel.CENTER);
        opt1.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JLabel opt2 = new JLabel("• Tools -> Change Password  |  Exit -> Logout", JLabel.CENTER);
        opt2.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        welcomeBox.add(title);
        welcomeBox.add(info);
        welcomeBox.add(opt1);
        welcomeBox.add(opt2);

        panel.add(welcomeBox, BorderLayout.CENTER);
        return panel;
    }

    // --- Card 2: Add Employee Panel ---
    private JPanel createAddEmployeeCard() {
        JPanel container = new JPanel(new GridBagLayout());
        container.setBorder(new EmptyBorder(20, 30, 20, 30));

        JPanel form = new JPanel(new GridLayout(4, 2, 12, 15));
        form.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Add New Employee",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 14)));
        form.setPreferredSize(new Dimension(500, 250));

        JLabel lblId = new JLabel("Employee ID:");
        lblId.setFont(new Font("Segoe UI", Font.BOLD, 13));
        txtAddEmpId = new JTextField();

        JLabel lblName = new JLabel("Employee Name:");
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 13));
        txtAddEmpName = new JTextField();

        JLabel lblDept = new JLabel("Department:");
        lblDept.setFont(new Font("Segoe UI", Font.BOLD, 13));
        txtAddDepartment = new JTextField();

        btnSaveEmployee = new JButton("Save Employee");
        btnSaveEmployee.setBackground(new Color(39, 174, 96));
        btnSaveEmployee.setForeground(Color.WHITE);
        btnSaveEmployee.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSaveEmployee.setFocusPainted(false);

        btnClearAddEmp = new JButton("Clear");
        btnClearAddEmp.setBackground(new Color(149, 165, 166));
        btnClearAddEmp.setForeground(Color.WHITE);
        btnClearAddEmp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnClearAddEmp.setFocusPainted(false);

        form.add(lblId);
        form.add(txtAddEmpId);
        form.add(lblName);
        form.add(txtAddEmpName);
        form.add(lblDept);
        form.add(txtAddDepartment);
        form.add(btnSaveEmployee);
        form.add(btnClearAddEmp);

        container.add(form);
        return container;
    }

    // --- Card 3: View Employee Panel ---
    private JPanel createViewEmployeeCard() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Registered Employee Directory", JLabel.LEFT);
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(title, BorderLayout.NORTH);

        String[] columns = {"Employee ID", "Employee Name", "Department"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        employeeTable = new JTable(tableModel);
        employeeTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        employeeTable.setRowHeight(24);
        employeeTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        employeeTable.getTableHeader().setBackground(new Color(236, 240, 241));

        JScrollPane scrollPane = new JScrollPane(employeeTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    // --- Card 4: Change Password Panel ---
    private JPanel createChangePasswordCard() {
        JPanel container = new JPanel(new GridBagLayout());
        container.setBorder(new EmptyBorder(20, 30, 20, 30));

        JPanel form = new JPanel(new GridLayout(4, 2, 12, 15));
        form.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Change Administrator Password",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 14)));
        form.setPreferredSize(new Dimension(500, 250));

        JLabel lblOld = new JLabel("Old Password:");
        lblOld.setFont(new Font("Segoe UI", Font.BOLD, 13));
        txtOldPassword = new JPasswordField();

        JLabel lblNew = new JLabel("New Password:");
        lblNew.setFont(new Font("Segoe UI", Font.BOLD, 13));
        txtNewPassword = new JPasswordField();

        JLabel lblConfirm = new JLabel("Confirm Password:");
        lblConfirm.setFont(new Font("Segoe UI", Font.BOLD, 13));
        txtConfirmPassword = new JPasswordField();

        btnUpdatePassword = new JButton("Update Password");
        btnUpdatePassword.setBackground(new Color(41, 128, 185));
        btnUpdatePassword.setForeground(Color.WHITE);
        btnUpdatePassword.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnUpdatePassword.setFocusPainted(false);

        btnClearChangePass = new JButton("Clear");
        btnClearChangePass.setBackground(new Color(149, 165, 166));
        btnClearChangePass.setForeground(Color.WHITE);
        btnClearChangePass.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnClearChangePass.setFocusPainted(false);

        form.add(lblOld);
        form.add(txtOldPassword);
        form.add(lblNew);
        form.add(txtNewPassword);
        form.add(lblConfirm);
        form.add(txtConfirmPassword);
        form.add(btnUpdatePassword);
        form.add(btnClearChangePass);

        container.add(form);
        return container;
    }

    // ==========================================
    // VIEW CONTROL & NAVIGATION
    // ==========================================
    public void showLoginWindow(boolean visible) {
        loginFrame.setVisible(visible);
        if (visible) {
            txtLoginUsername.setText("");
            txtLoginPassword.setText("");
            txtLoginUsername.requestFocus();
        }
    }

    public void showMainWindow(boolean visible) {
        mainFrame.setVisible(visible);
        if (visible) {
            showCard("WELCOME");
        }
    }

    public void showCard(String cardName) {
        cardLayout.show(cardsPanel, cardName);
    }

    // Input Getters for Login
    public String getLoginUsername() {
        return txtLoginUsername.getText().trim();
    }

    public String getLoginPassword() {
        return new String(txtLoginPassword.getPassword());
    }

    // Input Getters for Add Employee
    public String getAddEmpId() {
        return txtAddEmpId.getText().trim();
    }

    public String getAddEmpName() {
        return txtAddEmpName.getText().trim();
    }

    public String getAddDepartment() {
        return txtAddDepartment.getText().trim();
    }

    public void clearAddEmployeeFields() {
        txtAddEmpId.setText("");
        txtAddEmpName.setText("");
        txtAddDepartment.setText("");
        txtAddEmpId.requestFocus();
    }

    // Input Getters for Change Password
    public String getOldPassword() {
        return new String(txtOldPassword.getPassword());
    }

    public String getNewPassword() {
        return new String(txtNewPassword.getPassword());
    }

    public String getConfirmPassword() {
        return new String(txtConfirmPassword.getPassword());
    }

    public void clearChangePasswordFields() {
        txtOldPassword.setText("");
        txtNewPassword.setText("");
        txtConfirmPassword.setText("");
        txtOldPassword.requestFocus();
    }

    // Table population
    public void updateEmployeeTable(List<EmployeePortalModel.Employee> employees) {
        tableModel.setRowCount(0);
        for (EmployeePortalModel.Employee emp : employees) {
            tableModel.addRow(new Object[]{emp.getEmployeeId(), emp.getEmployeeName(), emp.getDepartment()});
        }
    }

    // JOptionPane Helpers
    public void showInfoMessage(Component parent, String message, String title) {
        JOptionPane.showMessageDialog(parent != null ? parent : mainFrame, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void showErrorMessage(Component parent, String message, String title) {
        JOptionPane.showMessageDialog(parent != null ? parent : mainFrame, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public int showConfirmDialog(Component parent, String message, String title) {
        return JOptionPane.showConfirmDialog(parent != null ? parent : mainFrame, message, title, JOptionPane.YES_NO_OPTION);
    }

    public JFrame getLoginFrame() {
        return loginFrame;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    // Listener Registration
    public void addLoginListener(ActionListener listener) {
        btnLogin.addActionListener(listener);
    }

    public void addMenuAddEmployeeListener(ActionListener listener) {
        itemAddEmployee.addActionListener(listener);
    }

    public void addMenuViewEmployeeListener(ActionListener listener) {
        itemViewEmployee.addActionListener(listener);
    }

    public void addMenuChangePasswordListener(ActionListener listener) {
        itemChangePassword.addActionListener(listener);
    }

    public void addMenuLogoutListener(ActionListener listener) {
        itemLogout.addActionListener(listener);
    }

    public void addMenuExitAppListener(ActionListener listener) {
        itemExitApp.addActionListener(listener);
    }

    public void addSaveEmployeeListener(ActionListener listener) {
        btnSaveEmployee.addActionListener(listener);
    }

    public void addClearAddEmployeeListener(ActionListener listener) {
        btnClearAddEmp.addActionListener(listener);
    }

    public void addUpdatePasswordListener(ActionListener listener) {
        btnUpdatePassword.addActionListener(listener);
    }

    public void addClearChangePasswordListener(ActionListener listener) {
        btnClearChangePass.addActionListener(listener);
    }
}
