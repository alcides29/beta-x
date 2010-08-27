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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Graphics;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import npuzzle.NPuzzle;


public class NpuzzleGUI extends javax.swing.JFrame {
    private JPanel panel;
    private JLabel label;
    private ImagePanel hole;
    private Image source;
    private Image blank;
    //private Image image;
    private int dimension;
    private boolean leftOut;
    private boolean cuadricula;
    boolean imagenes;
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
        imagenes = false;
        pos = new int[3][3];
        for (int i = 0; i<dimension*dimension; i++){
            pos[i/dimension][i%dimension]=i;
        }

        try {
            ImageIcon sh = new ImageIcon(NpuzzleGUI.class.getResource("Square.jpg"));
            ImageIcon bl = new ImageIcon(NpuzzleGUI.class.getResource("Blank.jpg"));
            blank = bl.getImage();
            source = sh.getImage();
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

        for (int i = 0; i<dimension*dimension; i++){
            if (i==(dimension*dimension-1)){
                hole = new ImagePanel(i, dimension, cuadro);
                panelCentral.add(hole);
                this.panelCentral.validate();
            } else {
                panel = new ImagePanel(i, dimension, cuadro);
                panelCentral.add(panel);
            }
        }     
        /*for ( int i = 0; i < dimension; i++) {
            for ( int j = 0; j < dimension; j++) {
                if ( j == dimension-1 && i == dimension-1) {
                    label = new JLabel("");
                    //panelCentral.add(label);
                    hole = new ImagePanel(j, i, dimension);
                    panelCentral.add(hole);
                    this.panelCentral.validate();
                } else {
                    panel = new ImagePanel(j, i, dimension);
                    //button.addActionListener(this);
                    //panelCentral.add(button);
                    panelCentral.add(panel);
                    //image = createImage(new FilteredImageSource(source.getSource(), new CropImageFilter(j*width/3, i*height/3, (width/3), height/3)));
                    //button.setIcon(new ImageIcon(image));
                    
                    
                }
            }
        }*/
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
        BtnVistaPrevia = new javax.swing.JButton();
        configuracion = new javax.swing.JPanel();
        jTextDimension = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jCheckCuadricula = new javax.swing.JCheckBox();
        jImagenes = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(590, 650));
        setResizable(false);

        barPestanas.setMinimumSize(new java.awt.Dimension(580, 44));
        barPestanas.setPreferredSize(new java.awt.Dimension(577, 677));

        tablero.setDoubleBuffered(false);
        tablero.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tableroComponentShown(evt);
            }
        });

        panelCentral.setMinimumSize(new java.awt.Dimension(560, 100));
        panelCentral.setPreferredSize(new java.awt.Dimension(575, 575));
        panelCentral.setRequestFocusEnabled(false);
        panelCentral.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelCentralMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelCentralLayout = new javax.swing.GroupLayout(panelCentral);
        panelCentral.setLayout(panelCentralLayout);
        panelCentralLayout.setHorizontalGroup(
            panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 572, Short.MAX_VALUE)
        );
        panelCentralLayout.setVerticalGroup(
            panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 573, Short.MAX_VALUE)
        );

        barBtnes.setPreferredSize(new java.awt.Dimension(575, 26));

        BtnNuevo.setFont(new java.awt.Font("Arial", 0, 11));
        BtnNuevo.setActionCommand("jButton1");
        BtnNuevo.setLabel("Nuevo");
        BtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNuevoActionPerformed(evt);
            }
        });

        BtnResolver.setFont(new java.awt.Font("Arial", 0, 11));
        BtnResolver.setLabel("Resolver");
        BtnResolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResolverActionPerformed(evt);
            }
        });

        BtnAnterior.setFont(new java.awt.Font("Arial", 0, 11));
        BtnAnterior.setLabel("Anterior");

        BtnSgte.setFont(new java.awt.Font("Arial", 0, 11));
        BtnSgte.setLabel("Siguiente");

        BtnVistaPrevia.setFont(new java.awt.Font("Arial", 0, 11));
        BtnVistaPrevia.setText("Vista Previa");
        BtnVistaPrevia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVistaPreviaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout barBtnesLayout = new javax.swing.GroupLayout(barBtnes);
        barBtnes.setLayout(barBtnesLayout);
        barBtnesLayout.setHorizontalGroup(
            barBtnesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barBtnesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnVistaPrevia)
                .addGap(16, 16, 16)
                .addComponent(BtnNuevo)
                .addGap(23, 23, 23)
                .addComponent(BtnResolver)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
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
                    .addComponent(BtnSgte)
                    .addComponent(BtnAnterior)
                    .addComponent(BtnResolver)
                    .addComponent(BtnNuevo)
                    .addComponent(BtnVistaPrevia))
                .addContainerGap())
        );

        javax.swing.GroupLayout tableroLayout = new javax.swing.GroupLayout(tablero);
        tablero.setLayout(tableroLayout);
        tableroLayout.setHorizontalGroup(
            tableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCentral, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
            .addComponent(barBtnes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
        );
        tableroLayout.setVerticalGroup(
            tableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tableroLayout.createSequentialGroup()
                .addComponent(panelCentral, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barBtnes, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        barPestanas.addTab("Tablero", tablero);

        jTextDimension.setText("3");

        jLabel1.setText("Dimensión");

        jCheckCuadricula.setSelected(true);
        jCheckCuadricula.setText("Cuadrícula");

        jImagenes.setText("Imágenes");
        jImagenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jImagenesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout configuracionLayout = new javax.swing.GroupLayout(configuracion);
        configuracion.setLayout(configuracionLayout);
        configuracionLayout.setHorizontalGroup(
            configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configuracionLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addGroup(configuracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jImagenes)
                    .addComponent(jCheckCuadricula)
                    .addComponent(jTextDimension, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(401, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addComponent(jImagenes)
                .addContainerGap(468, Short.MAX_VALUE))
        );

        barPestanas.addTab("Configuración", configuracion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(barPestanas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(barPestanas, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        barPestanas.getAccessibleContext().setAccessibleName("Tablero");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnResolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResolverActionPerformed
        this.tablero.repaint();
        
        //System.out.println(this.panelCentral.getComponents().length);
    }//GEN-LAST:event_BtnResolverActionPerformed

    private void BtnVistaPreviaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVistaPreviaActionPerformed
        this.nuevaVista();
        for (java.awt.Component pan : this.panelCentral.getComponents()) {
            pan.validate();
            pan.repaint();
        }

        new Thread(new Runnable() {
                public void run() {
                    try{
                        Thread.currentThread().sleep(dimension*10+200);
                    } catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    panelCentral.repaint();
                }
        }).start();
    }//GEN-LAST:event_BtnVistaPreviaActionPerformed

    private void panelCentralMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCentralMouseClicked
        //System.out.println("hasta aqui");
        ImagePanel button = (ImagePanel) panelCentral.getComponentAt(evt.getPoint());
        Dimension size = button.getSize();
        
        int labelX = hole.getX()/size.width;
        int labelY = hole.getY()/size.width;
        int buttonX = button.getX()/size.width;
        int buttonY = button.getY()/size.width;
        int buttonPosX = buttonX / size.width;
        int buttonPosY = buttonY / size.height;
        //int buttonIndex = pos[buttonPosY][buttonPosX];
        //System.out.println("posY"+buttonY+" posX"+buttonX);
        //System.out.println("2posY"+labelY+" 2posX"+labelX);
        if(buttonX==labelX+1&&buttonY==labelY){
            this.moveBlank(3);//derecha
            //System.out.println("posY"+buttonPosY+" posX"+buttonPosX);
            //System.out.println("jaja");
        } else if(buttonX==labelX-1 && buttonY==labelY){
            this.moveBlank(1);//izquierda
           //System.out.println("jaja");
        } else if(buttonX==labelX&&buttonY==labelY+1){
            this.moveBlank(2);//abajo
            //System.out.println("posY"+buttonPosY+" posX"+buttonPosX);
        } else if(buttonX==labelX&&buttonY==labelY-1){
            this.moveBlank(4);//arriba
            //System.out.println("posY"+buttonPosY+" posX"+buttonPosX);
        }

        new Thread(new Runnable() {
                public void run() {
                    try{
                        Thread.currentThread().sleep(dimension*10+20);
                    } catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    panelCentral.repaint();
                }
        }).start();
    }//GEN-LAST:event_panelCentralMouseClicked

    private void BtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoActionPerformed
        this.nuevaVista();
        NPuzzle puzzle = new NPuzzle( this.dimension );
        puzzle.generarTablero();
        puzzle.desordenarTablero(this);
        new Thread(new Runnable() {
                public void run() {
                    try{
                        Thread.currentThread().sleep(dimension*10+200);
                    } catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    panelCentral.repaint();
                }
        }).start();
    }//GEN-LAST:event_BtnNuevoActionPerformed

    private void jImagenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jImagenesActionPerformed
        imagenes = jImagenes.isSelected();
}//GEN-LAST:event_jImagenesActionPerformed

    private void tableroComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tableroComponentShown
        new Thread(new Runnable() {
                public void run() {
                    try{
                        Thread.currentThread().sleep(dimension*10+20);
                    } catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    panelCentral.repaint();
                }
        }).start();
    }//GEN-LAST:event_tableroComponentShown
    public void nuevaVista(){
        //System.out.println(getDimension());
        dimension = getDimension();
        if (dimension == 0){
            return;
        }
        pos = new int[dimension][dimension];
        for (int i = 0; i<dimension*dimension; i++){
            pos[i/dimension][i%dimension]=i;
        }
        cuadro = width/dimension;
        leftOut = (width%dimension)>3;
        cuadricula = getCuadricula();
        panelCentral.removeAll();
        
        for (int i = 0; i<dimension*dimension; i++){
            if (i==(dimension*dimension-1)){
                hole = new ImagePanel(i, dimension, cuadro);
                panelCentral.add(hole);
                this.panelCentral.validate();
            } else {
                panel = new ImagePanel(i, dimension, cuadro);
                panelCentral.add(panel);
            }
        }        
    }
    public class ImagePanel extends JPanel{
        private Image image;
        private int dim;
        private Integer valor;
        private int celda;

        public ImagePanel(int nro, int n, int pCuadro) {
            int x;
            int y;
            dim = n;
            valor = nro;
            celda = pCuadro;
            x = nro%dim;
            y = nro/dim;
            try {
                if(x==dim-1&&y==dim-1){
                    if (cuadricula){
                        image = createImage(new FilteredImageSource(blank.getSource(),
                            new CropImageFilter(x*celda, y*celda, celda-1, celda-1)));
                    } else{
                        image = createImage(new FilteredImageSource(blank.getSource(),
                            new CropImageFilter(x*celda, y*celda, celda, celda)));
                    }
                    //System.out.println("hasta");
                } else if(cuadricula){
                    image = createImage(new FilteredImageSource(source.getSource(),
                            new CropImageFilter(x*celda, y*celda, celda-1, celda-1)));
                } else{
                    image = createImage(new FilteredImageSource(source.getSource(),
                            new CropImageFilter(x*celda, y*celda, celda, celda)));
                }

                this.setSize(new Dimension(celda, celda));
                this.setMinimumSize(new Dimension(celda, celda));
                this.setPreferredSize(new Dimension(celda, celda));
                if(leftOut){
                    int correc;
                    correc = (width%dim)/2;
                    this.setLocation((x*celda)+correc, (y*celda));
                } else{
                    this.setLocation((x*(celda)), (y*(celda)));
                }
                
            } catch (Exception ex) {
                ex.getMessage();
            }    
        }
        //hata aora no encontre donde usar esto, seguro le quito
        public void changeImage(int x, int y){
            try{
                image = createImage(new FilteredImageSource(source.getSource(),
                            new CropImageFilter(x*(width/dim), y*(height/dim), (width/dim), (height/dim))));
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        public Image getImage(){
            return this.image;
        }
        public void setImage(Image pImage){
            this.image = pImage;
        }
        public int getValor(){
            return this.valor;
        }
        public void setValor(int val){
            this.valor = val;
        }
        

        @Override
        public void paintComponent(Graphics g) {
            if (imagenes){
                g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters
                return;
            }
            super.paintComponent(g);
            for (int r=0; r<dim; r++) {
                for (int c=0; c<dim; c++) {
                    int x = c * celda;
                    int y = r * celda;
                    Integer j = (valor+1);
                    String text = j.toString();
                    if (valor != (dim*dim)-1 ) {
                        g.setColor(Color.gray);
                        g.fillRect(x+2, y+2, celda-4, celda-4);
                        g.setColor(Color.black);
                        g.setFont(new Font("SansSerif", Font.BOLD, celda/2));
                        if (text.length()<3){
                            g.drawString(text, x+(celda/4), y+(3*celda)/4);
                        } else {
                            g.setFont(new Font("SansSerif", Font.BOLD, celda/3));
                            g.drawString(text, x+(celda/6), y+(3*celda)/4);
                        }
                    }
                }
            }
        //end paintComponent
        }

    }
    
    public void moveBlank(int direccion){
        ImagePanel aux1;
        ImagePanel aux2;
        Image imgAux1;
        Image imgAux2;
        if(direccion==1){//izquierda
            //System.out.println(hole.getX());
            if((hole.getX()/hole.getSize().width)!=0){
                aux1 = (ImagePanel) panelCentral.getComponentAt(hole.getX()-hole.getSize().width, hole.getY());
                aux2 = hole;
                hole = aux1;
                imgAux1 = aux1.getImage();
                imgAux2 = aux2.getImage();
                aux2.setImage(imgAux1);
                aux1.setImage(imgAux2);
                aux2.setValor(aux1.getValor());
                aux1.setValor(dimension*dimension-1);
            }
        } else if(direccion==2){//abajo
            if((hole.getY()/hole.getSize().width)!=dimension-1){
                aux1 = (ImagePanel) panelCentral.getComponentAt(hole.getX(), hole.getY()+hole.getSize().width);
                aux2 = hole;
                hole = aux1;
                imgAux1 = aux1.getImage();
                imgAux2 = aux2.getImage();
                aux2.setImage(imgAux1);
                aux1.setImage(imgAux2);
                aux2.setValor(aux1.getValor());
                aux1.setValor(dimension*dimension-1);
            }
        } else if(direccion==3){//derecha;
            if((hole.getX()/hole.getSize().width)!=dimension-1){
                aux1 = (ImagePanel) panelCentral.getComponentAt(hole.getX()+hole.getSize().width, hole.getY());
                aux2 = hole;
                //System.out.println("jeje");
                hole = aux1;
                imgAux1 = aux1.getImage();
                imgAux2 = aux2.getImage();
                aux2.setImage(imgAux1);
                aux1.setImage(imgAux2);
                aux2.setValor(aux1.getValor());
                aux1.setValor(dimension*dimension-1);
            }
        } else if(direccion==4){//arriba
            if((hole.getY()/hole.getSize().width)!=0){
                aux1 = (ImagePanel) panelCentral.getComponentAt(hole.getX(), hole.getY()-hole.getSize().width);
                aux2 = hole;
                hole = aux1;
                imgAux1 = aux1.getImage();
                imgAux2 = aux2.getImage();
                aux2.setImage(imgAux1);
                aux1.setImage(imgAux2);
                aux2.setValor(aux1.getValor());
                aux1.setValor(dimension*dimension-1);
            }
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
    private javax.swing.JButton BtnVistaPrevia;
    private javax.swing.JPanel barBtnes;
    private javax.swing.JTabbedPane barPestanas;
    private javax.swing.JPanel configuracion;
    private javax.swing.JCheckBox jCheckCuadricula;
    private javax.swing.JToggleButton jImagenes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextDimension;
    public javax.swing.JPanel panelCentral;
    private javax.swing.JPanel tablero;
    // End of variables declaration//GEN-END:variables
    
}
