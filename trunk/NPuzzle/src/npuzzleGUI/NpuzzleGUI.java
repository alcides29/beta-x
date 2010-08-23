/*
 * NpuzzleGUI.java
 *
 * Created on 20 de agosto de 2010, 10:33 PM
 */

package npuzzleGUI;

/**
 *
 * @author  Administrador
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Graphics;



import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class NpuzzleGUI extends javax.swing.JFrame {
    private JPanel panel;
    private JLabel label;
    private Image source;
    private Image image;
    private int dimension;
    private boolean leftOut;
    private boolean cuadricula;
    //private JPanel panelCentral;
    int[][] pos;
    int width, height;
    int cuadro;
    /** Creates new form NpuzzleGUI */
    public NpuzzleGUI() { 
        initComponents();        
        //panelCentral.setLayout(new GridLayout(3, 3));
        //panelCentral = new JPanel();
        dimension = getDimension();

        try {
            ImageIcon sid = new ImageIcon(NpuzzleGUI.class.getResource("Square.jpg"));
            source = sid.getImage();
            width = 575;//sid.getIconWidth();
            height = 575;//sid.getIconHeight();
            cuadro = width/dimension;
            leftOut = (width%dimension)>3;
            cuadricula = getCuadricula();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }



        //add(Box.createRigidArea(new Dimension(0, 5)), BorderLayout.NORTH);    
        //(panelCentral, BorderLayout.CENTER);

        for ( int i = 0; i < dimension; i++) {
            for ( int j = 0; j < dimension; j++) {
                if ( j == dimension-1 && i == dimension-1) {
                    label = new JLabel("");
                    //panelCentral.add(label);
                    this.panelCentral.validate();
                } else {
                    //button = new JButton();
                    panel = new ImagePanel(j, i, dimension);
                    //button.addActionListener(this);
                    //panelCentral.add(button);
                    panelCentral.add(panel);
                    //image = createImage(new FilteredImageSource(source.getSource(), new CropImageFilter(j*width/3, i*height/3, (width/3), height/3)));
                    //button.setIcon(new ImageIcon(image));
                    
                    
                }
            }
        }
        for (java.awt.Component pan : this.panelCentral.getComponents()) {
            pan.validate();
            pan.repaint();
        }

        new Thread(new Runnable() {
                public void run() {
                    try{
                        Thread.currentThread().sleep(200);
                    } catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    panelCentral.repaint();
                }
        }).start();
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        barPestanas = new javax.swing.JTabbedPane();
        tablero = new javax.swing.JPanel();
        panelCentral = new javax.swing.JPanel();
        barBtnes = new javax.swing.JPanel();
        BtnNuevo = new javax.swing.JButton();
        BtnResolver = new javax.swing.JButton();
        BtnAnterior = new javax.swing.JButton();
        BtnSgte = new javax.swing.JButton();
        configuracion = new javax.swing.JPanel();
        jTextDimension = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jCheckCuadricula = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(590, 650));
        setResizable(false);

        barPestanas.setMinimumSize(new java.awt.Dimension(580, 44));
        barPestanas.setPreferredSize(new java.awt.Dimension(577, 677));

        tablero.setDoubleBuffered(false);

        panelCentral.setMinimumSize(new java.awt.Dimension(560, 100));
        panelCentral.setPreferredSize(new java.awt.Dimension(575, 575));
        panelCentral.setRequestFocusEnabled(false);

        javax.swing.GroupLayout panelCentralLayout = new javax.swing.GroupLayout(panelCentral);
        panelCentral.setLayout(panelCentralLayout);
        panelCentralLayout.setHorizontalGroup(
            panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 572, Short.MAX_VALUE)
        );
        panelCentralLayout.setVerticalGroup(
            panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 636, Short.MAX_VALUE)
        );

        barBtnes.setPreferredSize(new java.awt.Dimension(575, 26));

        BtnNuevo.setActionCommand("jButton1");
        BtnNuevo.setLabel("Nuevo");
        BtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNuevoActionPerformed(evt);
            }
        });

        BtnResolver.setLabel("Resolver");
        BtnResolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResolverActionPerformed(evt);
            }
        });

        BtnAnterior.setLabel("Anterior");

        BtnSgte.setLabel("Siguiente");

        javax.swing.GroupLayout barBtnesLayout = new javax.swing.GroupLayout(barBtnes);
        barBtnes.setLayout(barBtnesLayout);
        barBtnesLayout.setHorizontalGroup(
            barBtnesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barBtnesLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(BtnNuevo)
                .addGap(31, 31, 31)
                .addComponent(BtnResolver)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                .addComponent(BtnAnterior)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnSgte)
                .addGap(55, 55, 55))
        );
        barBtnesLayout.setVerticalGroup(
            barBtnesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barBtnesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(barBtnesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnNuevo)
                    .addComponent(BtnResolver)
                    .addComponent(BtnSgte)
                    .addComponent(BtnAnterior))
                .addContainerGap())
        );

        javax.swing.GroupLayout tableroLayout = new javax.swing.GroupLayout(tablero);
        tablero.setLayout(tableroLayout);
        tableroLayout.setHorizontalGroup(
            tableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tableroLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(barBtnes, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE))
            .addComponent(panelCentral, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
        );
        tableroLayout.setVerticalGroup(
            tableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tableroLayout.createSequentialGroup()
                .addComponent(panelCentral, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barBtnes, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        barPestanas.addTab("Tablero", tablero);

        jTextDimension.setText("3");

        jLabel1.setText("Dimensión");

        jCheckCuadricula.setText("Cuadrícula");

        javax.swing.GroupLayout configuracionLayout = new javax.swing.GroupLayout(configuracion);
        configuracion.setLayout(configuracionLayout);
        configuracionLayout.setHorizontalGroup(
            configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configuracionLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addGroup(configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckCuadricula)
                    .addComponent(jTextDimension, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(403, Short.MAX_VALUE))
        );
        configuracionLayout.setVerticalGroup(
            configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configuracionLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextDimension, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jCheckCuadricula)
                .addContainerGap(583, Short.MAX_VALUE))
        );

        barPestanas.addTab("Configuración", configuracion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(barPestanas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(barPestanas, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        barPestanas.getAccessibleContext().setAccessibleName("Tablero");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoActionPerformed
        this.nuevo();
        for (java.awt.Component pan : this.panelCentral.getComponents()) {
            pan.validate();
            pan.repaint();
        }

        new Thread(new Runnable() {//GEN-LAST:event_BtnNuevoActionPerformed
                public void run() {
                    try{
                        Thread.currentThread().sleep(200);
                    } catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    panelCentral.repaint();
                }
        }).start();
    }                                        

    private void BtnResolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResolverActionPerformed
        this.tablero.repaint();
        
        //System.out.println(this.panelCentral.getComponents().length);
    }//GEN-LAST:event_BtnResolverActionPerformed
    public void nuevo(){
        //System.out.println(getDimension());
        dimension = getDimension();
        if (dimension == 0){
            return;
        }
        cuadro = width/dimension;
        leftOut = (width%dimension)>3;
        cuadricula = getCuadricula();
        panelCentral.removeAll();
        for ( int i = 0; i < dimension; i++) {
            for ( int j = 0; j < dimension; j++) {
                if ( j == dimension-1 && i == dimension-1) {
                    label = new JLabel("");
                    this.panelCentral.validate();
                } else {
                    panel = new ImagePanel(j, i, dimension);
                    //panel.setDoubleBuffered(true);
                    panel.setVisible(true);
                    panelCentral.add(panel);
                    //panel.validate();
                    //panel.repaint();
                    
                }
            }
        }
        //this.panelCentral.validate();
        //this.panelCentral.repaint();
        //this.validate();
    }
    public class ImagePanel extends JPanel{

        private Image image;
        private int dimension;
        private boolean square;

        public ImagePanel(int x, int y, int n) {
            dimension = n;
            try {                
                if(cuadricula){
                    image = createImage(new FilteredImageSource(source.getSource(),
                            new CropImageFilter(x*cuadro, y*cuadro, cuadro-1, cuadro-1)));
                } else{
                    image = createImage(new FilteredImageSource(source.getSource(),
                            new CropImageFilter(x*cuadro, y*cuadro, cuadro, cuadro)));
                }

                this.setSize(new Dimension(cuadro, cuadro));
                this.setMinimumSize(new Dimension(cuadro, cuadro));
                this.setPreferredSize(new Dimension(cuadro, cuadro));
                if(leftOut){
                    int correc;
                    correc = (width%dimension)/2;
                    this.setLocation((x*cuadro)+correc, (y*cuadro));
                } else{
                    this.setLocation((x*(cuadro)), (y*(cuadro)));
                }
                /*if (x>0){
                    if(y>0){
                        this.setLocation((x*(cuadro)), (y*(cuadro)));
                    } else {
                        this.setLocation((x*cuadro), (y*cuadro));
                    }
                } else {
                    if(y>0){
                        this.setLocation((x*cuadro), (y*cuadro));
                    }else {
                        this.setLocation((x*cuadro), (y*cuadro));
                    }
                }*/
                
            } catch (Exception ex) {
                ex.getMessage();
            }    
        }
        public void changeImage(int x, int y){
            try{
                image = createImage(new FilteredImageSource(source.getSource(),
                            new CropImageFilter(x*(width/dimension), y*(height/dimension), (width/dimension), (height/dimension))));
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters

        }

    }
    //se debe controlar que sea un entero
    //hace falta algún tipo de manejo de excepciones
    public int getDimension(){
        int dim = 0;
        try{
            dim = Integer.parseInt(jTextDimension.getText());
            
        } catch(Exception ex){
            JOptionPane.showMessageDialog(this, "La dimensión debe ser un número entero");
            try{
                this.barPestanas.setSelectedComponent(configuracion);
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        return dim;
    }
    public boolean getCuadricula(){
        return this.jCheckCuadricula.isSelected();
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               NpuzzleGUI juego = new NpuzzleGUI();
               juego.setVisible(true); 
               juego.panelCentral.repaint();
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAnterior;
    private javax.swing.JButton BtnNuevo;
    private javax.swing.JButton BtnResolver;
    private javax.swing.JButton BtnSgte;
    private javax.swing.JPanel barBtnes;
    private javax.swing.JTabbedPane barPestanas;
    private javax.swing.JPanel configuracion;
    private javax.swing.JCheckBox jCheckCuadricula;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextDimension;
    public javax.swing.JPanel panelCentral;
    private javax.swing.JPanel tablero;
    // End of variables declaration//GEN-END:variables
    
}
