package Hidato.Part4;

import Hidato.Part1.Inici;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menufinal {


    private JPanel Menuf;
    private JLabel foto;
    private JButton jugar;
    private JButton Resultado;
    private static JFrame frame;

    public Menufinal() {
        Resultado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Inici.cg.settsol(true);
                Inici.cg.SetCrear(false);
                String[] s = new String[0];
                Menu4.main(s);
                Menufinal.frame.dispose();
            }
        });
        jugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Inici.cg.settorn();
                Inici.cg.settsol(false);
                Inici.cg.SetCrear(false);
                String[] s = new String[0];
                Menu4.main(s);
                Menufinal.frame.dispose();
            }
        });
    }

    public static void  main(String[] args){
        frame = new JFrame("Menu4");
        frame.setContentPane(new Menufinal().Menuf);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
