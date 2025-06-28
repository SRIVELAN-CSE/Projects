import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.*;
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
public String getSubject() {
    return subject;
}
public String getDate() {
    return date;
}
public String getTime() {
    return time;
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
        setLocationRelativeTo(null); // Center the window
        
        // Main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(100, 181, 246), 
                                                    0, getHeight(), new Color(41, 121, 255));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(null); // Using absolute positioning for this simple form
        
        // Title label
        JLabel titleLabel = new JLabel("Exam Management System");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(80, 30, 300, 30);
        mainPanel.add(titleLabel);
        
        // Username field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(80, 80, 100, 25);
        mainPanel.add(userLabel);
        
        JTextField userField = new JTextField();
        userField.setBounds(180, 80, 150, 25);
        mainPanel.add(userField);
        
        // Password field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(80, 120, 100, 25);
        mainPanel.add(passLabel);
        
        JPasswordField passField = new JPasswordField();
        passField.setBounds(180, 120, 150, 25);
        mainPanel.add(passField);
        
        // Role selection
        JLabel roleLabel = new JLabel("Login As:");
        roleLabel.setForeground(Color.WHITE);
        roleLabel.setBounds(80, 160, 100, 25);
        mainPanel.add(roleLabel);
        
        String[] roles = {"Teacher", "Student"};
        JComboBox<String> roleCombo = new JComboBox<>(roles);
        roleCombo.setBounds(180, 160, 150, 25);
        mainPanel.add(roleCombo);
        
        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 210, 100, 30);
        loginButton.setBackground(new Color(46, 125, 50));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        mainPanel.add(loginButton);
          // Add action listener to login button
        loginButton.addActionListener(e -> {
            try {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                String selectedRole = (String) roleCombo.getSelectedItem();
                
                System.out.println("Login attempt: Username=" + username + ", Role=" + selectedRole);
                
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter username and password!");
                    return;
                }
                
                // Simple validation (in a real app, you would check against a database)
                // For testing purposes, accept any username with password "admin"
                if (password.equals("admin")) {
                    System.out.println("Login successful. Opening " + selectedRole + " dashboard.");
                    if ("Teacher".equals(selectedRole)) {
                        TeacherDashboard dashboard = new TeacherDashboard();
                        dashboard.setLocationRelativeTo(null);
                    } else {
                        StudentDashboard dashboard = new StudentDashboard();
                        dashboard.setLocationRelativeTo(null);
                    }
                    dispose(); // Close login window
                } else {
                    System.out.println("Login failed: Invalid credentials");
                    JOptionPane.showMessageDialog(this, "Invalid credentials!\nHint: Use 'admin' as password",
                            "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                System.err.println("Error during login: " + ex.getMessage());
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error occurred: " + ex.getMessage(), 
                        "System Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        add(mainPanel);
        setVisible(true);
    }
}

// Teacher Dashboard
static class TeacherDashboard extends JFrame {
    private JTable examTable;
    private DefaultTableModel tableModel;
    
    public TeacherDashboard() {
        setTitle("Teacher Dashboard");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Schedule Exams", 
        SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 102, 204));
        add(titleLabel, BorderLayout.NORTH);
        
        // Input form panel with gradient background
        JPanel formPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(240, 248, 255), 
                                                    0, getHeight(), new Color(230, 230, 250));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        formPanel.setLayout(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel subjectLabel = new JLabel("Subject:");
        JTextField subjectField = new JTextField();
        JLabel dateLabel = new JLabel("Date:");
        JTextField dateField = new JTextField();
        JLabel timeLabel = new JLabel("Time:");
        JTextField timeField = new JTextField();
        JButton scheduleButton = new JButton("Schedule");
        JButton logoutButton = new JButton("Logout");
        
        // Style buttons
        scheduleButton.setBackground(new Color(46, 139, 87));
        scheduleButton.setForeground(Color.WHITE);
        scheduleButton.setFocusPainted(false);
        
        logoutButton.setBackground(new Color(220, 20, 60));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        
        formPanel.add(subjectLabel);
        formPanel.add(subjectField);
        formPanel.add(dateLabel);
        formPanel.add(dateField);
        formPanel.add(timeLabel);
        formPanel.add(timeField);
        formPanel.add(scheduleButton);
        formPanel.add(logoutButton);
        add(formPanel, BorderLayout.NORTH);
        
        // Create table model and table
        String[] columnNames = {"Subject", "Date", "Time"};
        tableModel = new DefaultTableModel(columnNames, 0);
        examTable = new JTable(tableModel);
        
        // Set custom renderer for alternating row colors
        examTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);
                
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? new Color(240, 248, 255) : Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });
        
        // Set table header style
        JTableHeader header = examTable.getTableHeader();
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Add existing exams to the table
        updateExamTable();
        
        JScrollPane tableScrollPane = new JScrollPane(examTable);
        add(tableScrollPane, BorderLayout.CENTER);
        
        // Status panel at bottom
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.setBackground(new Color(220, 220, 220));
        JLabel statusLabel = new JLabel("Total Exams: " + examSchedule.size());
        statusPanel.add(statusLabel);
        add(statusPanel, BorderLayout.SOUTH);
        
        scheduleButton.addActionListener(e -> {
            String subject = subjectField.getText();
            String date = dateField.getText();
            String time = timeField.getText();
            if (subject.isEmpty() || date.isEmpty() || time.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields!");
            } else {
                Exam exam = new Exam(subject, date, time);
                examSchedule.add(exam);
                updateExamTable();
                statusLabel.setText("Total Exams: " + examSchedule.size());
                JOptionPane.showMessageDialog(this, "Exam Scheduled Successfully!");
                
                // Clear fields
                subjectField.setText("");
        dateField.setText("");
                timeField.setText("");
            }
        });
        
        logoutButton.addActionListener(e -> {
            try {
                LoginPage login = new LoginPage();
                login.setLocationRelativeTo(null);
                dispose();
            } catch (Exception ex) {
                System.err.println("Error during logout: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
        
        setVisible(true);
    }
    
    private void updateExamTable() {
        tableModel.setRowCount(0); // Clear existing rows
        for (Exam exam : examSchedule) {
            tableModel.addRow(new Object[]{exam.getSubject(), exam.getDate(), exam.getTime()});
        }
    }
}

// Student Dashboard
static class StudentDashboard extends JFrame {
    private JTable examTable;
    
    public StudentDashboard() {
        setTitle("Student Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Title with gradient panel
        JPanel titlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(70, 130, 180), 
                                                    getWidth(), 0, new Color(100, 149, 237));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setPreferredSize(new Dimension(500, 60));
        
        JLabel titleLabel = new JLabel("Exam Timetable", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.NORTH);
        
        // Create table model and table
        String[] columnNames = {"Subject", "Date", "Time"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        examTable = new JTable(tableModel);
        
        // Set custom renderer for alternating row colors
        examTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);
                
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? new Color(230, 230, 250) : Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });
        
        // Set table header style
        JTableHeader header = examTable.getTableHeader();
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Add existing exams to the table
        if (examSchedule.isEmpty()) {
            tableModel.addRow(new Object[]{"No exams scheduled yet", "", ""});
        } else {
            for (Exam exam : examSchedule) {
                tableModel.addRow(new Object[]{exam.getSubject(), exam.getDate(), exam.getTime()});
            }
        }
        
        JScrollPane tableScrollPane = new JScrollPane(examTable);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(tableScrollPane, BorderLayout.CENTER);
        
        // Logout button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 240, 240));
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(220, 20, 60));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        logoutButton.setPreferredSize(new Dimension(100, 30));
        buttonPanel.add(logoutButton);
        add(buttonPanel, BorderLayout.SOUTH);
          logoutButton.addActionListener(e -> {
            try {
                LoginPage login = new LoginPage();
                login.setLocationRelativeTo(null);
                dispose();
            } catch (Exception ex) {
                System.err.println("Error during logout: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
        
        setVisible(true);
    }
}
}
