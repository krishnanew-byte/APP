import java.util.ArrayList;
import java.util.List;

/**
 * Model component of the Vehicle Service Cost Estimator MVC application.
 * Manages service pricing and total cost calculation based on selected services.
 */
public class VehicleServiceCostModel {
    // Constant service rates
    public static final double PRICE_GENERAL_SERVICE = 1000.0;
    public static final double PRICE_OIL_CHANGE = 800.0;
    public static final double PRICE_BRAKE_SERVICE = 1200.0;
    public static final double PRICE_BATTERY_CHECK = 500.0;

    private String registrationNumber;
    private String vehicleType; // "Two Wheeler" or "Car"
    private boolean generalServiceSelected;
    private boolean oilChangeSelected;
    private boolean brakeServiceSelected;
    private boolean batteryCheckSelected;

    private double totalCost;

    public VehicleServiceCostModel() {
        this.registrationNumber = "";
        this.vehicleType = "Car";
        this.totalCost = 0.0;
    }

    /**
     * Calculates the total cost based on selected services.
     */
    public void calculateTotalCost() {
        totalCost = 0.0;
        if (generalServiceSelected) {
            totalCost += PRICE_GENERAL_SERVICE;
        }
        if (oilChangeSelected) {
            totalCost += PRICE_OIL_CHANGE;
        }
        if (brakeServiceSelected) {
            totalCost += PRICE_BRAKE_SERVICE;
        }
        if (batteryCheckSelected) {
            totalCost += PRICE_BATTERY_CHECK;
        }
    }

    /**
     * Returns a list of names of the selected services.
     */
    public List<String> getSelectedServicesList() {
        List<String> list = new ArrayList<>();
        if (generalServiceSelected) {
            list.add("General Service (Rs. 1,000)");
        }
        if (oilChangeSelected) {
            list.add("Oil Change (Rs. 800)");
        }
        if (brakeServiceSelected) {
            list.add("Brake Service (Rs. 1,200)");
        }
        if (batteryCheckSelected) {
            list.add("Battery Check (Rs. 500)");
        }
        return list;
    }

    // Getters and Setters
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isGeneralServiceSelected() {
        return generalServiceSelected;
    }

    public void setGeneralServiceSelected(boolean generalServiceSelected) {
        this.generalServiceSelected = generalServiceSelected;
    }

    public boolean isOilChangeSelected() {
        return oilChangeSelected;
    }

    public void setOilChangeSelected(boolean oilChangeSelected) {
        this.oilChangeSelected = oilChangeSelected;
    }

    public boolean isBrakeServiceSelected() {
        return brakeServiceSelected;
    }

    public void setBrakeServiceSelected(boolean brakeServiceSelected) {
        this.brakeServiceSelected = brakeServiceSelected;
    }

    public boolean isBatteryCheckSelected() {
        return batteryCheckSelected;
    }

    public void setBatteryCheckSelected(boolean batteryCheckSelected) {
        this.batteryCheckSelected = batteryCheckSelected;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
