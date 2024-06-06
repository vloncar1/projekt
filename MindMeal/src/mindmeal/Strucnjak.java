package mindmeal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.*;

public class Strucnjak extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;

    public Strucnjak() {
        getContentPane().setBackground(new Color(0, 128, 255));
        getContentPane().setForeground(new Color(0, 0, 0));
        setTitle("Sučelje za stručnjaka");
        setSize(413, 337);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JButton btnPodaciostrucnjaku = new JButton("Podaci o stručnjaku");
        btnPodaciostrucnjaku.setForeground(new Color(0, 0, 0));
        btnPodaciostrucnjaku.setBackground(new Color(255, 255, 255));
        btnPodaciostrucnjaku.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnPodaciostrucnjaku.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Podaci2 podaci = new Podaci2("loncarl@gmail.com", "agz6775", 123456, "Ivan", "Perić", "Nutricionist");
                podaci.setVisible(true);
            }
        });
        btnPodaciostrucnjaku.setBounds(98, 129, 190, 33);
        getContentPane().add(btnPodaciostrucnjaku);

        displayImage();

        JButton btnOpciplanoviprehrane = new JButton("Opći planovi prehrane");
        btnOpciplanoviprehrane.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OPP2 opp2 = new OPP2();
                opp2.setVisible(true);
            }
        });
        btnOpciplanoviprehrane.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnOpciplanoviprehrane.setBackground(Color.WHITE);
        btnOpciplanoviprehrane.setBounds(98, 172, 190, 33);
        getContentPane().add(btnOpciplanoviprehrane);
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
                new Strucnjak().setVisible(true);
            }
        });
    }
}

