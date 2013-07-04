
package EnigmaRedux;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;

/**
 *Creates the graphic user interface for ease of use.
 */

public class UserControls extends JFrame {
    private JButton encrypt = new JButton("Encrypt");
    private JComboBox shiftI = new JComboBox();
    private JComboBox shiftII = new JComboBox();
    private JComboBox shiftIII = new JComboBox();
    private JTextArea input = new JTextArea("Input Here", 10, 50);
    private JTextArea output = new JTextArea("Output Here", 10, 50);
    private JLabel shiftIL = new JLabel("Rotor I:");
    private JLabel shiftIIL = new JLabel("Rotor II:");
    private JLabel shiftIIIL = new JLabel("Rotor III:");
    private JTextField keyword = new JTextField("Enter Keyword" , 13);
    private JCheckBox decryptB = new JCheckBox("Decrypt");
    
    
    /**
     *No-arg constructor for the UserControls class. Creates the GUI.
     */
    
    public UserControls() {
        output.setEditable(false);
        
        JPanel p1 = new JPanel(new GridLayout(1, 0, 5, 2)); //Button and shifters.
        JPanel p2 = new JPanel(); //input
        JPanel p3 = new JPanel(); //output
        JPanel pD = new JPanel(new GridLayout(0, 2, 5, 2)); //Decrypt Options
        JPanel p5 = new JPanel(new BorderLayout());
        
        JScrollPane inputSF = new JScrollPane(input);
        JScrollPane outputSF = new JScrollPane(output);
        
        //Add Components to p2 and p3.
        
        p2.add(inputSF);
        p3.add(outputSF);
        
        //Add components to panel 1.
        p1.add(shiftIL);
        p1.add(shiftI);
        p1.add(shiftIIL);
        p1.add(shiftII);
        p1.add(shiftIIIL);
        p1.add(shiftIII);
        p1.add(encrypt);
        
        //Add Pannels to the Frame.
        
        JPanel p4 = new JPanel(new GridLayout(2, 0, 5, 2));
        
        p4.add(p2);
        p4.add(p3);
        
        pD.add(decryptB);
        pD.add(keyword);
        
        p5.add(p4, BorderLayout.CENTER);
        p5.add(pD, BorderLayout.SOUTH);
        
        add(p5, BorderLayout.CENTER);
        add(p1, BorderLayout.SOUTH);
        
        for(int index = 0; index < EnigmaRedux.ALPHABET_SIZE; index++) {
            shiftI.addItem(index + "");
            shiftII.addItem(index + "");
            shiftIII.addItem(index + "");
        }//for
        
        encrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                try {
                    EnigmaRedux enigmaMachine = new EnigmaRedux(shiftI.getSelectedIndex(), shiftII.getSelectedIndex(), shiftIII.getSelectedIndex(), keyword.getText(), decryptB.isSelected());
                    enigmaMachine.setClearText(input.getText());
                    enigmaMachine.encryptClearText();
                    output.setText(enigmaMachine.getCipherText());

                }//try
                catch(IOException ex) {
                    System.exit(0);
                }//catch
            }//actionPerformed(actionEvent)
        }); //Anonymous Inner class for encrypt button.
    }//UserControls()
    
    public static void showUserControls() {
        UserControls frame = new UserControls();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Enigma Machine");
        frame.setVisible(true);
    }//main
}//UserControls
