import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Controller component of the Vehicle Service Cost Estimator MVC application.
 * Coordinates user actions between the View and Model.
 */
public class VehicleServiceCostController {
    private VehicleServiceCostModel model;
    private VehicleServiceCostView view;

    public VehicleServiceCostController(VehicleServiceCostModel model, VehicleServiceCostView view) {
        this.model = model;
        this.view = view;

        // Register action listeners
        this.view.addCalculateCostListener(new CalculateCostListener());
        this.view.addResetListener(new ResetListener());
    }

    /**
     * Handles "Calculate Cost" button action.
     */
    class CalculateCostListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String regNo = view.getRegistrationNumber();
            if (regNo.isEmpty()) {
                view.showErrorMessage("Please enter the Vehicle Registration Number.");
                return;
            }

            boolean general = view.isGeneralServiceSelected();
            boolean oil = view.isOilChangeSelected();
            boolean brake = view.isBrakeServiceSelected();
            boolean battery = view.isBatteryCheckSelected();

            if (!general && !oil && !brake && !battery) {
                view.showErrorMessage("Please select at least one service.");
                return;
            }

            // Update model state
            model.setRegistrationNumber(regNo);
            model.setVehicleType(view.getSelectedVehicleType());
            model.setGeneralServiceSelected(general);
            model.setOilChangeSelected(oil);
            model.setBrakeServiceSelected(brake);
            model.setBatteryCheckSelected(battery);

            // Compute total cost in model
            model.calculateTotalCost();

            // Retrieve calculated details and update view
            List<String> services = model.getSelectedServicesList();
            double total = model.getTotalCost();
            view.displayResult(model.getRegistrationNumber(), model.getVehicleType(), services, total);
        }
    }

    /**
     * Handles "Reset" button action.
     */
    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.resetFields();
        }
    }
}
