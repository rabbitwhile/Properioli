package Hidato.Part4;

import Hidato.Part1.Inici;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cuadrado extends JPanel{
    private final int ROWS;
    private final int COLUMNS;
    private cuadrado[][] cuabuton;
    private String [][] tauler;
    private boolean crear;
    private boolean tsol;

    private JTextField num;
    private int offsetX;
    private int offsetY;
    private String s;
    private JButton Jcrear;
    private JButton Jmenu;
    private JButton Jguardar;
    private JButton Jayuda;
    private JButton Jsalir;

    public Cuadrado(boolean cr, boolean ts) {
        crear = cr;
        tsol = ts;
        if (!crear) {
            //if(!tsol) tauler = Inici.cg.GetTauler();
            //else {
                tauler = new String[Inici.cg.getFila()][Inici.cg.getColumna()];
                for(int i = 0; i < Inici.cg.getFila(); ++i){
                    for(int j = 0; j < Inici.cg.getColumna(); ++j){
                        tauler[i][j] = Inici.cg.Stringcela(i,j);
                    }
                }
                System.out.println("estoy aqui");
                //tauler = Inici.cg.getTaulerresol();
            //}
            ROWS = tauler.length;
            COLUMNS = tauler[0].length;
            System.out.println(ROWS);
            System.out.println(COLUMNS);
        }
        else{
            ROWS = Inici.cg.getFila();
            COLUMNS = Inici.cg.getColumna();
            tauler = new String[ROWS][COLUMNS];
            llenarmatriz();
        }
        setLayout(null);
        initGUI();
    }

    private void llenarmatriz(){
        for(int i = 0; i < ROWS;++i){
            for(int j = 0; j < COLUMNS;++j){
                tauler[i][j] = "?";
            }
        }
    }
    private void configurarelpanel() {
        offsetX = 0;
        offsetY = 0;
        setBackground(Color.white);
        cuabuton = new cuadrado[ROWS][COLUMNS];

        //texto
        num = new JTextField();
        num.setSize(25,25);
        num.setBounds(800 , 800,100,30);

        //botones
        configurarboronoes();
    }
    private void configurarboronoes(){
        if(crear) {
            Jcrear = new JButton();
            Jcrear.setText("Crear");
            Jcrear.setBounds(530, 950, 100, 30);
            add(Jcrear);
        }

        if(!crear) {
            Jguardar = new JButton();
            Jguardar.setText("Guardar");
            Jguardar.setBounds(50, 950, 100, 30);

            Jayuda = new JButton();
            Jayuda.setText("ayuda");
            Jayuda.setBounds(170, 950, 100, 30);
            add(Jayuda);
            add(Jguardar);
        }

        Jmenu = new JButton();
        Jmenu.setText("Menu");
        Jmenu.setBounds(290, 950, 100, 30);

        Jsalir = new JButton();
        Jsalir.setBounds(410,950,100,30);
        Jsalir.setText("Salir");
        //añadir
        add(num);
        add(Jmenu);
        add(Jsalir);
    }

    private void accionbotones(){
        Jmenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Menu");
            }
        });

        Jsalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Salir");
            }
        });

        if(!crear) {
            Jayuda.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println("Ayuda");
                }
            });

            Jguardar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println("Guardar");
                }
            });
        }
        if(crear){
            Jcrear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println("Crear");
                    Inici.cg.SetTauler(tauler);
                    Inici.cg.CtrlGenerarHidato();
                    String[] s = new String[0];
                    Menufinal.main(s);
                    Menu4.frame.dispose();

                }
            });
        }

    }

    private void jugar() {
        for (int row = 0; row < ROWS; row++) {
            offsetX = 0;
            for (int col = 0; col < COLUMNS; col++) {
                int finalRow = row;
                int finalCol = col;
                if (!(tauler[row][col].equals("#"))) {
                    cuabuton[row][col] = new cuadrado();
                    if (tauler[row][col].equals("?")) {
                        cuabuton[row][col].setText(tauler[row][col]);
                        cuabuton[row][col].addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                s = num.getText();
                                if(!s.isEmpty()) {
                                    cuabuton[finalRow][finalCol].setText(s);
                                    //pasarnumero(fila,columna,s,"NUMERO")
                                    //tauler[finalRow][finalCol] = s;
                                }
                            }
                        });
                    }
                    else if (tauler[row][col].equals("*")) cuabuton[row][col].setText("NO");
                    else cuabuton[row][col].setText(tauler[row][col]);
                    cuabuton[row][col].setBounds(offsetX, offsetY, 80, 80);
                    add(cuabuton[row][col]);
                }
                offsetX += 80;
            }
            offsetY += 80;
        }
    }    

    private void crear() {
        for (int row = 0; row < ROWS; row++) {
            offsetX = 0;
            for (int col = 0; col < COLUMNS; col++) {
                cuabuton[row][col] = new cuadrado();
                int finalRow = row;
                int finalCol = col;
                cuabuton[row][col].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        s = num.getText();
                        if (!s.isEmpty()) {
                            cuabuton[finalRow][finalCol].setText(s);
                            tauler[finalRow][finalCol] = s;
                            System.out.println("Button clicked: [" + finalRow + "][" + finalCol + "]");
                        }
                    }
                });
                cuabuton[row][col].setBounds(offsetX, offsetY, 80, 80);
                add(cuabuton[row][col]);
                offsetX += 80;
            }
            offsetY += 80;
        }
    }
    private void initGUI(){
        configurarelpanel();
        accionbotones();
        if(!crear)  jugar();
        else if(crear)crear();

    }

    class cuadrado extends JButton {
        private static final int LENGTH = 80;
        private static final int WIDTH = 80;


        public cuadrado() {
            setContentAreaFilled(false);
            setFocusPainted(true);
            setBorderPainted(false);
            setPreferredSize(new Dimension(WIDTH, LENGTH));
            setBackground(Color.GREEN);
        }

            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.red);
                g.drawRect(0,0,79,79);

        }
    }
}

