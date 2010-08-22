/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package npuzzle;

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
        JFrame window = new NpuzzleGUI();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();  // finalize layout
        window.show();  // make window visible
        window.setResizable(false);
    }//end main

}
