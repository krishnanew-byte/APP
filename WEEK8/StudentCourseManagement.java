import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class StudentCourseManagement extends JFrame {

    JList<String> courseList;
    JTable table;

    DefaultTableModel model;

    JButton addButton;
    JButton removeButton;

    JTextField studentName;

    public StudentCourseManagement() {

        setTitle("Student Course Management");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Student Name
        JPanel topPanel = new JPanel();

        topPanel.add(new JLabel("Student Name:"));

        studentName = new JTextField(20);
        topPanel.add(studentName);

        add(topPanel, BorderLayout.NORTH);

        // Course List
        String[] courses = {
            "Java Programming",
            "Data Structures",
            "Database Management",
            "Operating Systems",
            "Computer Networks"
        };

        courseList = new JList<>(courses);
        courseList.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane courseScroll =
            new JScrollPane(courseList);

        courseScroll.setBorder(
            BorderFactory.createTitledBorder("Available Courses")
        );

        // Table
        String[] columns = {
            "Student Name",
            "Selected Course",
            "Enrollment Status"
        };

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        JScrollPane tableScroll =
            new JScrollPane(table);

        tableScroll.setBorder(
            BorderFactory.createTitledBorder("Course Registrations")
        );

        // Center Panel
        JPanel centerPanel = new JPanel(
            new GridLayout(1, 2, 10, 10)
        );

        centerPanel.add(courseScroll);
        centerPanel.add(tableScroll);

        add(centerPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();

        addButton = new JButton("Add Course");
        removeButton = new JButton("Remove Course");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Add Course
        addButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String name = studentName.getText();
                String course = courseList.getSelectedValue();

                if (name.isEmpty()) {

                    JOptionPane.showMessageDialog(
                        StudentCourseManagement.this,
                        "Please enter student name."
                    );

                } else if (course == null) {

                    JOptionPane.showMessageDialog(
                        StudentCourseManagement.this,
                        "Please select a course."
                    );

                } else {

                    model.addRow(
                        new Object[]{
                            name,
                            course,
                            "Enrolled"
                        }
                    );

                    studentName.setText("");
                    courseList.clearSelection();
                }
            }
        });

        // Remove Course
        removeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int selectedRow = table.getSelectedRow();

                if (selectedRow != -1) {

                    model.removeRow(selectedRow);

                } else {

                    JOptionPane.showMessageDialog(
                        StudentCourseManagement.this,
                        "Please select a registration to remove."
                    );
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new StudentCourseManagement();
    }
}