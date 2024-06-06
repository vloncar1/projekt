package mindmeal;


import javax.swing.*;
import java.awt.event.*;
import java.io.Serializable;
import java.sql.*;
import java.awt.Font;
import java.awt.Color;

public class OOP extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;

    private JTextField txtPlanName;
    private JTextField txtIngredients;
    private JTextArea txtAreaResults;

    private Connection conn;

    public OOP() {
        super("Pretraživanje planova prehrane");
        setTitle("Pretraga planova prehrane");
        getContentPane().setBackground(new Color(0, 128, 255));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(483, 506);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 128, 255));
        panel.setBounds(0, 0, 459, 456);

        txtPlanName = new JTextField(20);
        txtPlanName.setBackground(new Color(255, 255, 255));
        txtPlanName.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtPlanName.setBounds(30, 113, 236, 33);
        txtIngredients = new JTextField(20);
        txtIngredients.setBackground(new Color(255, 255, 255));
        txtIngredients.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtIngredients.setBounds(30, 187, 236, 33);

        JButton btnSearch = new JButton("Search");
        btnSearch.setForeground(new Color(0, 128, 255));
        btnSearch.setBackground(new Color(128, 255, 0));
        btnSearch.setBounds(30, 260, 81, 33);
        btnSearch.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchMealPlans(txtPlanName.getText(), txtIngredients.getText());
            }
        });
        getContentPane().setLayout(null);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 308, 406, 138);
        panel.setLayout(null);

        JLabel label = new JLabel("Naziv:");
        label.setBounds(30, 73, 56, 44);
        label.setForeground(new Color(255, 255, 255));
        label.setFont(new Font("SansSerif", Font.BOLD, 15));
        panel.add(label);
        panel.add(txtPlanName);
        JLabel label_1 = new JLabel("Sastojci:");
        label_1.setBounds(30, 156, 65, 33);
        label_1.setForeground(new Color(255, 255, 255));
        label_1.setFont(new Font("SansSerif", Font.BOLD, 15));
        panel.add(label_1);
        panel.add(txtIngredients);
        panel.add(btnSearch);
        panel.add(scrollPane);
        
        txtAreaResults = new JTextArea(10, 30);
        txtAreaResults.setFont(new Font("Arial", Font.PLAIN, 12));
        scrollPane.setViewportView(txtAreaResults);

        getContentPane().add(panel);
        
        JLabel lblPretraziplanoveprehrane = new JLabel("Pretraži planove prehrane!");
        lblPretraziplanoveprehrane.setForeground(new Color(255, 255, 255));
        lblPretraziplanoveprehrane.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblPretraziplanoveprehrane.setBounds(72, 33, 282, 44);
        panel.add(lblPretraziplanoveprehrane);
        
        JLabel lblNewLabel = new JLabel("⸙");
        lblNewLabel.setForeground(new Color(128, 255, 0));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 33));
        lblNewLabel.setBounds(37, 28, 25, 44);
        panel.add(lblNewLabel);
        
        

        connectToDatabase();
    }

    private void connectToDatabase() {
        String url = "jdbc:mysql://ucka.veleri.hr:3306/kbazon";
        String user = "kbazon";
        String password = "11";

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Successfully connected to the database.");
        } catch (SQLException ex) {
            System.out.println("Error connecting to the database: " + ex.getMessage());
        }
    }

    private void searchMealPlans(String planName, String ingredients) {
        if (conn == null) {
            JOptionPane.showMessageDialog(OOP.this, "Error: Database connection not established.");
            return;
        }

        try {
            String sql = "SELECT * FROM Opći_plan_prehrane WHERE Naziv_plana_prehrane LIKE ? OR Lista_sastojaka LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + planName + "%");
            stmt.setString(2, "%" + ingredients + "%");

            ResultSet rs = stmt.executeQuery();

            StringBuilder resultText = new StringBuilder();
            while (rs.next()) {
                String name = rs.getString("Naziv_plana_prehrane");
                String ing = rs.getString("Lista_sastojaka");
                resultText.append("Naziv plana: ").append(name).append(", Sastojci: ").append(ing).append("\n");
            }

            if (resultText.length() == 0) {
                txtAreaResults.setText("Nema rezultata.");
            } else {
                txtAreaResults.setText(resultText.toString());
            }

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(OOP.this, "Error executing query: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                OOP frame = new OOP(); 
                frame.setVisible(true); 
            }
        });
    }
}
