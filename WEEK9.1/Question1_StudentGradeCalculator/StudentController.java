import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller component of the Student Grade Calculator MVC application.
 * Manages user interactions, validates inputs, coordinates between Model and View.
 */
public class StudentController {
    private StudentModel model;
    private StudentView view;

    public StudentController(StudentModel model, StudentView view) {
        this.model = model;
        this.view = view;

        // Attach listeners to View components
        this.view.addCalculateListener(new CalculateListener());
        this.view.addClearListener(new ClearListener());
    }

    /**
     * Listener handling the "Calculate Result" button click.
     */
    class CalculateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getStudentName();
            String s1Str = view.getSubject1Text();
            String s2Str = view.getSubject2Text();
            String s3Str = view.getSubject3Text();

            // Validate student name
            if (name.isEmpty()) {
                view.showErrorMessage("Please enter the student's name.");
                return;
            }

            // Validate and parse marks
            double sub1, sub2, sub3;
            try {
                sub1 = Double.parseDouble(s1Str);
                sub2 = Double.parseDouble(s2Str);
                sub3 = Double.parseDouble(s3Str);
            } catch (NumberFormatException ex) {
                view.showErrorMessage("Marks must be valid numbers.");
                return;
            }

            // Check marks range (0 to 100)
            if (sub1 < 0 || sub1 > 100 || sub2 < 0 || sub2 > 100 || sub3 < 0 || sub3 > 100) {
                view.showErrorMessage("Marks for each subject must be between 0 and 100.");
                return;
            }

            // Update model
            model.setStudentName(name);
            model.setSubject1(sub1);
            model.setSubject2(sub2);
            model.setSubject3(sub3);

            // Perform calculations in model
            model.calculateResult();

            // Update view with computed results
            view.setTotalMarks(model.getTotalMarks());
            view.setAverageMarks(model.getAverageMarks());
            view.setGrade(model.getGrade());
        }
    }

    /**
     * Listener handling the "Clear" button click.
     */
    class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.clearFields();
        }
    }
}
