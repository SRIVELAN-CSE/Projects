import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
// Exam 
class Exam {
private String subject;
private String date;
private String time;
public Exam(String subject, String date, String time) {
this.subject = subject;
this.date = date;
this.time = time;
}
public String getDetails() {
return "Subject: " + subject + ", Date: " + date + ", Time: " + time;
}
}
// Main Class
public class ExamManagementSystem {
private static ArrayList<Exam> examSchedule = new ArrayList<>();
public static void main(String[] args) {
SwingUtilities.invokeLater(() -> new LoginPage());
}
// Login Page
static class LoginPage extends JFrame {
public LoginPage() {
setTitle("Exam Management System - Login");
setSize(400, 300);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLayout(new GridLayout(4, 2));
JLabel userLabel = new JLabel("Username:");
JTextField userField = new JTextField();
JLabel passLabel = new JLabel("Password:");
JPasswordField passField = new JPasswordField();
JLabel roleLabel = new JLabel("Role:");
JComboBox<String> roleCombo = new JComboBox<>(new 
String[]{"Teacher", "Student"});
JButton loginButton = new JButton("Login");
add(userLabel);
add(userField);
add(passLabel);
add(passField);
add(roleLabel);
add(roleCombo);
add(new JLabel()); // Spacer

add(loginButton);
loginButton.addActionListener(e -> {
String username = userField.getText();
String password = new String(passField.getPassword());
String role = roleCombo.getSelectedItem().toString();
if (username.isEmpty() || password.isEmpty()) {
JOptionPane.showMessageDialog(this, "Please fill in all fields!");
} else if (role.equals("Teacher")) {
new TeacherDashboard();
dispose();
} else {
new StudentDashboard();
dispose();
}
});
setVisible(true);
}
}
// Teacher Dashboard
static class TeacherDashboard extends JFrame {
public TeacherDashboard() {
setTitle("Teacher Dashboard");
setSize(500, 400);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

setLayout(new BorderLayout());
JLabel titleLabel = new JLabel("Schedule Exams", 
SwingConstants.CENTER);
titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
add(titleLabel, BorderLayout.NORTH);
JPanel formPanel = new JPanel(new GridLayout(4, 2));
JLabel subjectLabel = new JLabel("Subject:");
JTextField subjectField = new JTextField();
JLabel dateLabel = new JLabel("Date:");
JTextField dateField = new JTextField();
JLabel timeLabel = new JLabel("Time:");
JTextField timeField = new JTextField();
JButton scheduleButton = new JButton("Schedule");
JButton logoutButton = new JButton("Logout");
formPanel.add(subjectLabel);
formPanel.add(subjectField);
formPanel.add(dateLabel);
formPanel.add(dateField);
formPanel.add(timeLabel);
formPanel.add(timeField);
formPanel.add(scheduleButton);
formPanel.add(logoutButton);
add(formPanel, BorderLayout.CENTER);
JTextArea scheduleArea = new JTextArea();

scheduleArea.setEditable(false);
add(new JScrollPane(scheduleArea), BorderLayout.SOUTH);
scheduleButton.addActionListener(e -> {
String subject = subjectField.getText();
String date = dateField.getText();
String time = timeField.getText();
if (subject.isEmpty() || date.isEmpty() || time.isEmpty()) {
JOptionPane.showMessageDialog(this, "Please fill in all fields!");
} else {
Exam exam = new Exam(subject, date, time);
examSchedule.add(exam);
scheduleArea.append(exam.getDetails() + "\n");
JOptionPane.showMessageDialog(this, "Exam Scheduled Successfully!");
}
});
logoutButton.addActionListener(e -> {
new LoginPage();
dispose();
});
setVisible(true);
}
}
// Student Dashboard
static class StudentDashboard extends JFrame {
public StudentDashboard() {
setTitle("Student Dashboard");
setSize(400, 300);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLayout(new BorderLayout());
JLabel titleLabel = new JLabel("Exam Timetable", 
SwingConstants.CENTER);
titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
add(titleLabel, BorderLayout.NORTH);
JTextArea timetableArea = new JTextArea();
timetableArea.setEditable(false);
if (examSchedule.isEmpty()) {
timetableArea.setText("No exams scheduled yet.");
} else {
for (Exam exam : examSchedule) {
timetableArea.append(exam.getDetails() + "\n");
}
}
JButton logoutButton = new JButton("Logout");
add(logoutButton, BorderLayout.SOUTH);
add(new JScrollPane(timetableArea), BorderLayout.CENTER);
logoutButton.addActionListener(e -> {
    new LoginPage();
    dispose();
});
setVisible(true);
}
}
}