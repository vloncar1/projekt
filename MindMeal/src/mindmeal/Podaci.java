package mindmeal;

import java.io.Serializable;
import java.sql.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class Podaci extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;

    private JLabel lblEmail;
    private JLabel lblPassword;
    private JLabel lblUserID;
    private JLabel lblFirstName;
    private JLabel lblLastName;
    private JLabel lblDateOfBirth;
    private JLabel lblGender;
    private JLabel lblHealthConditions;
    private JLabel lblDiagnosis;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JPanel panel;
    private JPanel panel_1;
    private JPanel panel_2;

    public Podaci(String email, String password, int userID, String firstName, String lastName, Date dateOfBirth, String gender, String healthConditions, String diagnosis) {
        super("User Information");
        setTitle("Podaci o korisniku");
        getContentPane().setBackground(new Color(255, 255, 255));

        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        lblEmail = new JLabel("Email: " + email);
        lblEmail.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblEmail.setBackground(new Color(255, 255, 255));
        lblEmail.setBounds(28, 132, 264, 27);
        lblPassword = new JLabel("Lozinka: " + password);
        lblPassword.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblPassword.setBounds(28, 153, 264, 27);
        lblUserID = new JLabel("ID Korisnika: " + userID);
        lblUserID.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblUserID.setBounds(28, 176, 264, 27);
        lblFirstName = new JLabel("Ime: " + firstName);
        lblFirstName.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblFirstName.setBounds(28, 220, 264, 27);
        lblLastName = new JLabel("Prezime: " + lastName);
        lblLastName.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblLastName.setBounds(28, 241, 264, 27);
        lblDateOfBirth = new JLabel("Datum rođenja: " + dateOfBirth);
        lblDateOfBirth.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblDateOfBirth.setBounds(28, 257, 264, 27);
        lblGender = new JLabel("Spol: " + gender);
        lblGender.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblGender.setBounds(28, 278, 264, 27);
        lblHealthConditions = new JLabel("Zdravstveni uvijeti: " + healthConditions);
        lblHealthConditions.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblHealthConditions.setBounds(28, 342, 449, 27);
        lblDiagnosis = new JLabel("Dijagnoza: " + diagnosis);
        lblDiagnosis.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblDiagnosis.setBounds(28, 379, 449, 27);
        getContentPane().setLayout(null);

        getContentPane().add(lblEmail);
        getContentPane().add(lblPassword);
        getContentPane().add(lblUserID);
        getContentPane().add(lblFirstName);
        getContentPane().add(lblLastName);
        getContentPane().add(lblDateOfBirth);
        getContentPane().add(lblGender);
        getContentPane().add(lblHealthConditions);
        getContentPane().add(lblDiagnosis);
        
        lblNewLabel = new JLabel("Podaci o korisniku");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblNewLabel.setBackground(new Color(240, 240, 240));
        lblNewLabel.setBounds(68, 48, 258, 35);
        getContentPane().add(lblNewLabel);
        
        lblNewLabel_1 = new JLabel("⸙");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 38));
        lblNewLabel_1.setForeground(new Color(128, 255, 0));
        lblNewLabel_1.setBounds(31, 36, 27, 47);
        getContentPane().add(lblNewLabel_1);
        
        panel = new JPanel();
        panel.setBackground(new Color(0, 128, 255));
        panel.setBounds(0, 36, 486, 51);
        getContentPane().add(panel);
        
        panel_1 = new JPanel();
        panel_1.setBackground(new Color(128, 255, 0));
        panel_1.setBounds(0, 112, 486, 10);
        getContentPane().add(panel_1);
        
        panel_2 = new JPanel();
        panel_2.setBackground(new Color(128, 255, 0));
        panel_2.setBounds(0, 431, 486, 10);
        getContentPane().add(panel_2);
    }

    public static void main(String[] args) {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://ucka.veleri.hr:3306/kbazon";
            String user = "kbazon";
            String password = "11";
            conn = DriverManager.getConnection(url, user, password);

            String query = "SELECT * FROM Korisnik WHERE ID_Korisnika = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, 98765); 

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String email = resultSet.getString("Mail_Korisnika");
                String pass = resultSet.getString("Lozinka_Korisnika");
                int userID = resultSet.getInt("ID_Korisnika");
                String firstName = resultSet.getString("Ime_Korisnika");
                String lastName = resultSet.getString("Prezime_Korisnika");
                Date dob = resultSet.getDate("Datum_rođenja");
                String gender = resultSet.getString("Spol");
                String healthConditions = resultSet.getString("Zdravstveni_uvjeti_Korisnika");
                String diagnosis = resultSet.getString("Dijagnoza_Korisnka");

                Podaci userInfoWindow = new Podaci(email, pass, userID, firstName, lastName, dob, gender, healthConditions, diagnosis);
                userInfoWindow.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Korisnik nije pronađen.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
