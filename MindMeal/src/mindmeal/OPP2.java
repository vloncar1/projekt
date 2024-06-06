
package mindmeal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class OPP2 extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String url = "jdbc:mysql://ucka.veleri.hr:3306/kbazon";
    private static final String user = "kbazon";
    private static final String password = "11";

    private Connection conn;
    private JTextField nazivPlanaField;
    private JTextArea opisPlanaArea;
    private JTextField listaSastojakaField;
    private JTextField idStrucnjakaField;

    public OPP2() {
        getContentPane().setBackground(new Color(0, 128, 255));
        connectToDatabase();
        setTitle("Unos plana prehrane");
        setSize(531, 533);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JLabel nazivPlanaLabel = new JLabel("Naziv plana prehrane:");
        nazivPlanaLabel.setForeground(new Color(0, 0, 0));
        nazivPlanaLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        nazivPlanaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nazivPlanaLabel.setBounds(0, 38, 193, 43);
        getContentPane().add(nazivPlanaLabel);
        nazivPlanaField = new JTextField();
        nazivPlanaField.setHorizontalAlignment(SwingConstants.LEFT);
        nazivPlanaField.setFont(new Font("SansSerif", Font.PLAIN, 10));
        nazivPlanaField.setBounds(193, 47, 298, 30);
        getContentPane().add(nazivPlanaField);

        JLabel opisPlanaLabel = new JLabel("Opis plana prehrane:");
        opisPlanaLabel.setForeground(new Color(0, 0, 0));
        opisPlanaLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        opisPlanaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        opisPlanaLabel.setBounds(0, 100, 193, 38);
        getContentPane().add(opisPlanaLabel);

        JLabel listaSastojakaLabel = new JLabel("Lista sastojaka:");
        listaSastojakaLabel.setForeground(new Color(0, 0, 0));
        listaSastojakaLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        listaSastojakaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        listaSastojakaLabel.setBounds(0, 210, 193, 38);
        getContentPane().add(listaSastojakaLabel);
        listaSastojakaField = new JTextField();
        listaSastojakaField.setHorizontalAlignment(SwingConstants.LEFT);
        listaSastojakaField.setFont(new Font("SansSerif", Font.PLAIN, 10));
        listaSastojakaField.setBounds(193, 209, 298, 140);
        getContentPane().add(listaSastojakaField);

        JLabel idStrucnjakaLabel = new JLabel("ID Strucnjaka:");
        idStrucnjakaLabel.setForeground(new Color(0, 0, 0));
        idStrucnjakaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        idStrucnjakaLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        idStrucnjakaLabel.setBounds(10, 361, 154, 38);
        getContentPane().add(idStrucnjakaLabel);
        idStrucnjakaField = new JTextField();
        idStrucnjakaField.setHorizontalAlignment(SwingConstants.LEFT);
        idStrucnjakaField.setFont(new Font("SansSerif", Font.PLAIN, 10));
        idStrucnjakaField.setBounds(193, 370, 193, 25);
        getContentPane().add(idStrucnjakaField);

        JButton insertButton = new JButton("Unesi plan prehrane");
        insertButton.setForeground(new Color(0, 128, 255));
        insertButton.setBackground(new Color(128, 255, 0));
        insertButton.setFont(new Font("SansSerif", Font.BOLD, 15));
        insertButton.setBounds(10, 437, 216, 38);
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nazivPlana = nazivPlanaField.getText();
                String opisPlana = opisPlanaArea.getText();
                String listaSastojaka = listaSastojakaField.getText();
                int idStrucnjaka = Integer.parseInt(idStrucnjakaField.getText());
                insertPlanPrehrane(nazivPlana, opisPlana, listaSastojaka, idStrucnjaka);
            }
        });
        getContentPane().add(insertButton);
        
        opisPlanaArea = new JTextArea(); 
        opisPlanaArea.setFont(new Font("SansSerif", Font.PLAIN, 10));
        opisPlanaArea.setLineWrap(true); 
        opisPlanaArea.setBounds(193, 100, 298, 100); 
        getContentPane().add(opisPlanaArea);

        setVisible(true);
    }

    private void connectToDatabase() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Uspješno spojeni na bazu podataka.");
        } catch (SQLException e) {
            System.out.println("Nije moguće spojiti se na bazu podataka.");
            e.printStackTrace();
        }
    }

    public void insertPlanPrehrane(String nazivPlana, String opisPlana, String listaSastojaka, int idStrucnjaka) {
        if (!isValidStrucnjak(idStrucnjaka)) {
            System.out.println("Stručnjak s ID-om " + idStrucnjaka + " ne postoji.");
            return;
        }
        
        String query = "INSERT INTO Opći_plan_prehrane (Naziv_plana_prehrane, Opis_plana_prehrane, Lista_sastojaka, ID_Stručnjaka) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nazivPlana);
            statement.setString(2, opisPlana);
            statement.setString(3, listaSastojaka);
            statement.setInt(4, idStrucnjaka);
            statement.executeUpdate();
            System.out.println("Podaci o općem planu prehrane su uspješno spremljeni u bazu podataka.");
        } catch (SQLException ex) {
            System.out.println("Greška prilikom spremanja podataka o općem planu prehrane u bazu podataka.");
            ex.printStackTrace();
        }
    }

    
    private boolean isValidStrucnjak(int idStrucnjaka) {
        
        return (idStrucnjaka == 123456 || idStrucnjaka == 123457);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new OPP2();
            }
        });
    }
}
