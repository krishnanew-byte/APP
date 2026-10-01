import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Main application launcher for Vehicle Service Cost Estimator (MVC).
 */
public class VehicleServiceApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            VehicleServiceCostModel model = new VehicleServiceCostModel();
            VehicleServiceCostView view = new VehicleServiceCostView();
            new VehicleServiceCostController(model, view);

            view.setVisible(true);
        });
    }
}
