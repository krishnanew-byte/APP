import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Main application launcher for Employee Management Portal (MVC).
 */
public class EmployeeManagementApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            EmployeePortalModel model = new EmployeePortalModel();
            EmployeePortalView view = new EmployeePortalView();
            new EmployeePortalController(model, view);

            // Display the Login window initially as required
            view.showLoginWindow(true);
        });
    }
}
