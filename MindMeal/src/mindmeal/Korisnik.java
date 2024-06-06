package mindmeal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.*;

public class Korisnik extends JFrame {
    private static final long serialVersionUID = 1L;

    public Korisnik() {
        getContentPane().setBackground(new Color(0, 128, 255));
        getContentPane().setForeground(new Color(0, 0, 0));
        setTitle("Korisničko sučelje");
        setSize(413, 337);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        JButton btnPodaciokorisniku = new JButton("Podaci o korisniku");
        btnPodaciokorisniku.setForeground(new Color(0, 0, 0));
        btnPodaciokorisniku.setBackground(new Color(255, 255, 255));
        btnPodaciokorisniku.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnPodaciokorisniku.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Podaci podaci = new Podaci("vitov@gmail.com", "12345", 98765, "Vito", "Lončar", Date.valueOf("2001-08-13"), "M", "-", null);
                podaci.setVisible(true);
            }
        });
        btnPodaciokorisniku.setBounds(98, 129, 190, 33);
        getContentPane().add(btnPodaciokorisniku);

        displayImage(); 
        
        JButton btnOpciplanoviprehrane = new JButton("Opći planovi prehrane");
        btnOpciplanoviprehrane.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OOP prozorPretrage = new OOP();
                prozorPretrage.setVisible(true);
            }
        });
        btnOpciplanoviprehrane.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnOpciplanoviprehrane.setBackground(Color.WHITE);
        btnOpciplanoviprehrane.setBounds(98, 172, 190, 33);
        getContentPane().add(btnOpciplanoviprehrane);
        
        JButton btnKorisnickiplanprehrane = new JButton("Korisnički plan prehrane");
        btnKorisnickiplanprehrane.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnKorisnickiplanprehrane.setBackground(new Color(255, 255, 255));
        btnKorisnickiplanprehrane.setBounds(98, 215, 190, 33);
        getContentPane().add(btnKorisnickiplanprehrane);

        btnKorisnickiplanprehrane.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                KPP korisnickiPlanPrehrane = new KPP();
                korisnickiPlanPrehrane.setVisible(true);
            }
        });
    }

    private void displayImage() {
        ImageIcon icon = new ImageIcon("C:\\Users\\Karen\\eclipse-workspace\\MindMeal\\Slika1.png");
        Image image = icon.getImage().getScaledInstance(245, 60, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(image);

        getContentPane().setLayout(null);

        JLabel label = new JLabel(scaledIcon);
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setBounds(63, 26, 259, 71); 
        getContentPane().add(label);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Korisnik korisnik = new Korisnik();
                korisnik.setVisible(true);
            }
        });
    }
}
