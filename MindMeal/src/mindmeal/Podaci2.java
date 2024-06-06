package mindmeal;

import java.io.Serializable;
import java.sql.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class Podaci2 extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;

    private JLabel lblMail;
    private JLabel lblLozinka;
    private JLabel lblIDStrucnjaka;
    private JLabel lblIme;
    private JLabel lblPrezime;
    private JLabel lblSpecijalizacija;
    private JPanel panel;
    private JPanel panel_1;

    public Podaci2(String mail, String lozinka, int idStrucnjaka, String ime, String prezime, String specijalizacija) {
        super("Podaci o stručnjaku");
        setTitle("Podaci o stručnjaku");
        getContentPane().setBackground(new Color(255, 255, 255));

        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        lblMail = new JLabel("Mail: " + mail);
        lblMail.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblMail.setBackground(new Color(255, 255, 255));
        lblMail.setBounds(28, 132, 264, 27);
        lblLozinka = new JLabel("Lozinka: " + lozinka);
        lblLozinka.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblLozinka.setBounds(28, 153, 264, 27);
        lblIDStrucnjaka = new JLabel("ID Stručnjaka: " + idStrucnjaka);
        lblIDStrucnjaka.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblIDStrucnjaka.setBounds(28, 176, 264, 27);
        lblIme = new JLabel("Ime: " + ime);
        lblIme.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblIme.setBounds(28, 220, 264, 27);
        lblPrezime = new JLabel("Prezime: " + prezime);
        lblPrezime.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblPrezime.setBounds(28, 241, 264, 27);
        lblSpecijalizacija = new JLabel("Specijalizacija: " + specijalizacija);
        lblSpecijalizacija.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblSpecijalizacija.setBounds(28, 289, 264, 27);
        getContentPane().setLayout(null);

        getContentPane().add(lblMail);
        getContentPane().add(lblLozinka);
        getContentPane().add(lblIDStrucnjaka);
        getContentPane().add(lblIme);
        getContentPane().add(lblPrezime);
        getContentPane().add(lblSpecijalizacija);
        
        JLabel lblNewLabel = new JLabel("Podaci o stručnjaku");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblNewLabel.setBackground(new Color(240, 240, 240));
        lblNewLabel.setBounds(68, 48, 258, 35);
        getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("⸙");
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
    }

    public static void main(String[] args) {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://ucka.veleri.hr:3306/kbazon";
            String user = "kbazon";
            String password = "11";
            conn = DriverManager.getConnection(url, user, password);

            String query = "SELECT * FROM Stručnjak WHERE ID_Stručnjaka = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, 123456); 

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String mail = resultSet.getString("Mail_Stručnjaka");
                String lozinka = resultSet.getString("Lozinka_Stručnjaka");
                int idStrucnjaka = resultSet.getInt("ID_Stručnjaka");
                String ime = resultSet.getString("Ime_Stručnjaka");
                String prezime = resultSet.getString("Prezime_Stručnjaka");
                String specijalizacija = resultSet.getString("Specijalizacija_Stručnjaka");

                Podaci2 strucnjakInfoWindow = new Podaci2(mail, lozinka, idStrucnjaka, ime, prezime, specijalizacija);
                strucnjakInfoWindow.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Stručnjak nije pronađen.");
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
