package Hidato.Part4;

import Hidato.Part1.Inici;
import Hidato.Part2.Menu2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cuadrado extends JPanel{
    private final int ROWS;
    private final int COLUMNS;
    private cuadradoBoton[][] cuabuton;
    private String [][] taulerG;
    private boolean crear;

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
        ROWS = Inici.cg.getFila();
        COLUMNS = Inici.cg.getColumna();
        if (crear) {
            taulerG = new String[ROWS][COLUMNS];
            llenarmatriz();
        }
        setLayout(null);
        initGUI();
    }

    private void llenarmatriz(){
        for(int i = 0; i < ROWS;++i){
            for(int j = 0; j < COLUMNS;++j){
                taulerG[i][j] = "?";
            }
        }
    }
    private void configurarelpanel() {
        offsetX = 0;
        offsetY = 0;
        setBackground(Color.white);
        cuabuton = new cuadradoBoton[ROWS][COLUMNS];

        //texto
        num = new JTextField();
        num.setSize(25,25);
        num.setBounds( 900, 350,100,55);
        num.setFont(new Font("Calibri",1,50));

        //botones
        configurarboronoes();
    }
    private void configurarboronoes(){
        if(crear) {
            Jcrear = new JButton();
            Jcrear.setText("Generar");
            Jcrear.setBounds(900, 50, 100, 30);
            add(Jcrear);
        }

        if(!crear) {
            Jguardar = new JButton();
            Jguardar.setText("Guardar");
            Jguardar.setBounds(900, 100, 100, 30);

            Jayuda = new JButton();
            Jayuda.setText("Ajuda");
            Jayuda.setBounds(900, 150, 100, 30);
            add(Jayuda);
            add(Jguardar);
        }

        Jmenu = new JButton();
        Jmenu.setText("Menú");
        Jmenu.setBounds(900, 200, 100, 30);

        Jsalir = new JButton();
        Jsalir.setBounds(900,250,100,30);
        Jsalir.setText("Sortir");
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
                String[] s = new String[0];
                Menu2.main(s);
                Menu4.frame.dispose();
                System.out.println("Menu");
            }
        });

        Jsalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
                System.out.println("Salir");
            }
        });

        if(!crear) {
            Jayuda.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Inici.cg.Transpartida(-1,-1,"s","AJUDA");
                    System.out.println("Ayuda");
                    Menu4.frame.dispose();
                    String[] s = new String[0];
                    Menu4.main(s);
                    Inici.cg.setTayuda(false);
                }
            });

            Jguardar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Inici.cg.Transpartida(-1,-1,"s","GUARDAR");
                    System.out.println("Guardar");
                    String[] s = new String[0];
                    try {
                        Ayuda.main(s);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Menu4.frame.dispose();
                }
            });
        }
        if(crear){
            Jcrear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println("Crear");
                    Inici.cg.SetTauler(taulerG);
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
                String cel = Inici.cg.Stringcela(row,col);
                System.out.println(cel);
                int finalRow = row;
                int finalCol = col;
                if (!cel.equals("#")) {
                    cuabuton[row][col] = new cuadradoBoton();
                    cuabuton[row][col].setText(cel);
                    if(!Inici.cg.isTso()) {
                        if (cel.equals("?") || cel.charAt(cel.length()-1) == 'I') {
                            cuabuton[row][col].setText(cel);
                            cuabuton[row][col].addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    s = num.getText();
                                    if (!s.isEmpty()) {
                                        Inici.cg.Transpartida(finalRow, finalCol, s, "NUMERO");
                                        if(!Inici.cg.comprobajugada()) cuabuton[finalRow][finalCol].setText(s);
                                        if(Inici.cg.isTso()) {
                                            String[] s = new String[0];
                                            Final.main(s);
                                            Menu4.frame.dispose();
                                        }
                                    }
                                }
                            });
                        } else if (cel.equals("*")) cuabuton[row][col].setText("NO");
                        else cuabuton[row][col].setText(cel);
                    }
                    cuabuton[row][col].setBounds(offsetX, offsetY, 70, 70);
                    add(cuabuton[row][col]);
                }
                offsetX += 70;
            }
            offsetY += 70;
        }
    }

    private void generar() {
        for (int row = 0; row < ROWS; row++) {
            offsetX = 0;
            for (int col = 0; col < COLUMNS; col++) {
                cuabuton[row][col] = new cuadradoBoton();
                int finalRow = row;
                int finalCol = col;
                cuabuton[row][col].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        s = num.getText();
                        if (!s.isEmpty()) {
                            cuabuton[finalRow][finalCol].setText(s);
                            taulerG[finalRow][finalCol] = s;
                            //System.out.println("Button clicked: [" + finalRow + "][" + finalCol + "]");
                        }
                    }
                });
                cuabuton[row][col].setBounds(offsetX, offsetY, 70  , 70);
                add(cuabuton[row][col]);
                offsetX += 70;
            }
            offsetY += 70;
        }
    }
    private void initGUI(){
        configurarelpanel();
        accionbotones();
        if(!crear)  jugar();
        else generar();

    }

    class cuadradoBoton extends JButton {
        private static final int LENGTH = 70;
        private static final int WIDTH = 70;


        public cuadradoBoton() {
            setContentAreaFilled(false);
            setFocusPainted(true);
            setBorderPainted(false);
            setPreferredSize(new Dimension(WIDTH, LENGTH));
            setFont(new Font("Calibri",1,20));
            setBackground(Color.GREEN);
        }

            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.red);
                g.drawRect(0,0,69,69);

        }
    }
}

