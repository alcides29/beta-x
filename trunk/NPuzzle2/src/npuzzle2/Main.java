/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package npuzzle2;

/**
 *
 * @author gusamasan
 */
import javax.swing.JFrame;
import npuzzleGUI.NpuzzleGUI;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NpuzzleGUI window = new NpuzzleGUI();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.pack();  // finalize layout
        window.setVisible(true);  // make window visible
        window.setResizable(false);
        
    }//end main

}
