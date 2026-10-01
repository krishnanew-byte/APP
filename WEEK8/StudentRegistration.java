import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentRegistration extends JFrame {

    JTextField nameField;
    JTextField regField;

    JRadioButton male;
    JRadioButton female;

    JComboBox<String> department;

    JButton submit;

    public StudentRegistration() {

        setTitle("Student Registration System");
        setSize(450, 350);
        setLayout(new GridLayout(5, 2, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Student Name
        add(new JLabel("Student Name:"));

        nameField = new JTextField();
        add(nameField);

        // Register Number
        add(new JLabel("Register Number:"));

        regField = new JTextField();
        add(regField);

        // Gender
        add(new JLabel("Gender:"));

        JPanel genderPanel = new JPanel();

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");

        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);

        genderPanel.add(male);
        genderPanel.add(female);

        add(genderPanel);

        // Department
        add(new JLabel("Department:"));

        String[] departments = {
            "CSE",
            "ECE",
            "EEE",
            "Mechanical",
            "Civil"
        };

        department = new JComboBox<>(departments);
        add(department);

        // Submit Button
        submit = new JButton("Submit");
        add(new JLabel(""));
        add(submit);

        submit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                String reg = regField.getText();

                String gender;

                if (male.isSelected()) {
                    gender = "Male";
                } else if (female.isSelected()) {
                    gender = "Female";
                } else {
                    gender = "Not Selected";
                }

                String dept = (String) department.getSelectedItem();

                String message =
                    "Student Name: " + name +
                    "\nRegister Number: " + reg +
                    "\nGender: " + gender +
                    "\nDepartment: " + dept;

                JOptionPane.showMessageDialog(
                    StudentRegistration.this,
                    message,
                    "Student Details",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new StudentRegistration();
    }
}