import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Model component of the Employee Management Portal MVC application.
 * Manages authentication credentials, password updates, and employee records.
 */
public class EmployeePortalModel {

    /**
     * Inner class representing an Employee record.
     */
    public static class Employee {
        private String employeeId;
        private String employeeName;
        private String department;

        public Employee(String employeeId, String employeeName, String department) {
            this.employeeId = employeeId;
            this.employeeName = employeeName;
            this.department = department;
        }

        public String getEmployeeId() {
            return employeeId;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public String getDepartment() {
            return department;
        }
    }

    private String adminUsername = "admin";
    private String adminPassword = "admin123";

    private final List<Employee> employeeList = new ArrayList<>();

    public EmployeePortalModel() {
        // Initialize with sample records
        employeeList.add(new Employee("EMP001", "Alice Johnson", "Information Technology"));
        employeeList.add(new Employee("EMP002", "Bob Smith", "Human Resources"));
        employeeList.add(new Employee("EMP003", "Carol White", "Finance & Accounts"));
    }

    /**
     * Validates login credentials.
     */
    public boolean validateCredentials(String username, String password) {
        return adminUsername.equals(username) && adminPassword.equals(password);
    }

    /**
     * Changes the admin password after verifying the old password
     * and confirming that new password and confirm password match.
     * @return null if successful, or error message string if failed.
     */
    public String changePassword(String oldPassword, String newPassword, String confirmPassword) {
        if (!adminPassword.equals(oldPassword)) {
            return "Old password does not match current password.";
        }
        if (newPassword.isEmpty()) {
            return "New password cannot be empty.";
        }
        if (!newPassword.equals(confirmPassword)) {
            return "New password and Confirm Password do not match.";
        }
        if (newPassword.equals(oldPassword)) {
            return "New password cannot be the same as the old password.";
        }

        this.adminPassword = newPassword;
        return null; // Success
    }

    /**
     * Adds a new employee record.
     * @return null if successful, or error message string if failed.
     */
    public String addEmployee(String id, String name, String dept) {
        if (id == null || id.trim().isEmpty()) {
            return "Employee ID cannot be empty.";
        }
        if (name == null || name.trim().isEmpty()) {
            return "Employee Name cannot be empty.";
        }
        if (dept == null || dept.trim().isEmpty()) {
            return "Department cannot be empty.";
        }

        // Check for duplicate ID
        for (Employee emp : employeeList) {
            if (emp.getEmployeeId().equalsIgnoreCase(id.trim())) {
                return "Employee ID '" + id + "' already exists.";
            }
        }

        employeeList.add(new Employee(id.trim(), name.trim(), dept.trim()));
        return null; // Success
    }

    /**
     * Returns an unmodifiable list of employees.
     */
    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employeeList);
    }
}
