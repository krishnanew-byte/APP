import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller component of the Employee Management Portal MVC application.
 * Manages authentication, menu routing, adding employees, viewing directory,
 * and changing administrator passwords.
 */
public class EmployeePortalController {
    private EmployeePortalModel model;
    private EmployeePortalView view;

    public EmployeePortalController(EmployeePortalModel model, EmployeePortalView view) {
        this.model = model;
        this.view = view;

        initListeners();
    }

    private void initListeners() {
        // Login action
        view.addLoginListener(new LoginListener());

        // Menu navigation actions
        view.addMenuAddEmployeeListener(e -> view.showCard("ADD_EMPLOYEE"));
        view.addMenuViewEmployeeListener(e -> {
            view.updateEmployeeTable(model.getEmployees());
            view.showCard("VIEW_EMPLOYEE");
        });
        view.addMenuChangePasswordListener(e -> {
            view.clearChangePasswordFields();
            view.showCard("CHANGE_PASSWORD");
        });
        view.addMenuLogoutListener(new LogoutListener());
        view.addMenuExitAppListener(new ExitAppListener());

        // Form actions
        view.addSaveEmployeeListener(new SaveEmployeeListener());
        view.addClearAddEmployeeListener(e -> view.clearAddEmployeeFields());
        view.addUpdatePasswordListener(new UpdatePasswordListener());
        view.addClearChangePasswordListener(e -> view.clearChangePasswordFields());
    }

    /**
     * Handles authentication on login button click.
     */
    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getLoginUsername();
            String password = view.getLoginPassword();

            if (username.isEmpty() || password.isEmpty()) {
                view.showErrorMessage(view.getLoginFrame(), "Please enter both Username and Password.", "Login Error");
                return;
            }

            if (model.validateCredentials(username, password)) {
                view.showInfoMessage(view.getLoginFrame(), "Login Successful! Welcome, " + username + ".", "Authentication Success");
                view.showLoginWindow(false);
                view.showMainWindow(true);
            } else {
                view.showErrorMessage(view.getLoginFrame(), "Invalid Username or Password. Please try again.", "Authentication Failed");
            }
        }
    }

    /**
     * Handles saving new employee details.
     */
    class SaveEmployeeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = view.getAddEmpId();
            String name = view.getAddEmpName();
            String dept = view.getAddDepartment();

            String error = model.addEmployee(id, name, dept);
            if (error != null) {
                view.showErrorMessage(view.getMainFrame(), error, "Validation Error");
            } else {
                view.showInfoMessage(view.getMainFrame(), "Employee '" + name + "' added successfully!", "Success");
                view.clearAddEmployeeFields();
                view.updateEmployeeTable(model.getEmployees());
                view.showCard("VIEW_EMPLOYEE");
            }
        }
    }

    /**
     * Handles changing administrator password.
     */
    class UpdatePasswordListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String oldPass = view.getOldPassword();
            String newPass = view.getNewPassword();
            String confirmPass = view.getConfirmPassword();

            String error = model.changePassword(oldPass, newPass, confirmPass);
            if (error != null) {
                view.showErrorMessage(view.getMainFrame(), error, "Password Change Failed");
            } else {
                view.showInfoMessage(view.getMainFrame(), "Password changed successfully!", "Success");
                view.clearChangePasswordFields();
                view.showCard("WELCOME");
            }
        }
    }

    /**
     * Handles user logout.
     */
    class LogoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int option = view.showConfirmDialog(view.getMainFrame(), "Are you sure you want to log out?", "Confirm Logout");
            if (option == JOptionPane.YES_OPTION) {
                view.showMainWindow(false);
                view.showLoginWindow(true);
            }
        }
    }

    /**
     * Handles application exit.
     */
    class ExitAppListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int option = view.showConfirmDialog(view.getMainFrame(), "Are you sure you want to exit the application?", "Confirm Exit");
            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }
}
