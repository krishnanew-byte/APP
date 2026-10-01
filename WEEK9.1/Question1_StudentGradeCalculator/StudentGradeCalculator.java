import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Main application launcher for Student Grade Calculator (MVC).
 */
public class StudentGradeCalculator {
    public static void main(String[] args) {
        // Run UI creation on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            try {
                // Apply modern system look and feel
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            // Initialize MVC components
            StudentModel model = new StudentModel();
            StudentView view = new StudentView();
            new StudentController(model, view);

            // Make the GUI visible
            view.setVisible(true);
        });
    }
}
