package Hidato.Part4;


import Hidato.Part1.Inici;


import javax.swing.*;

public class Menu4 {


    private static String tcela;
    private static boolean crear;
    private static boolean tsol;
    public static JFrame frame;


    private static void configurar() {
        tcela = Inici.cg.GetTcela();
        tsol = Inici.cg.isTso();
        crear = Inici.cg.isCrear();
        frame = new JFrame();
        frame.setTitle("Juego");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 1100);
        frame.setResizable(true);
        frame.setVisible(true);
    }
    private static void tablero() {
        if(tcela.equals("H")) {
            HexagonPattern hexPattern = new HexagonPattern(crear, tsol);
            //frame.setLocation(new Point(700, 300));
            frame.add(hexPattern);

        }
        else if (tcela.equals("T")){
            triangle tri = new triangle(crear, tsol);
            //frame.setLocation(new Point(700, 300));
            frame.add(tri);
        }
        else if(tcela.equals("Q")){
            Cuadrado cua = new Cuadrado(crear, tsol);
            frame.add(cua);
        }
    }

    public static void main(String[] args){
        configurar();
        tablero();
    }




}