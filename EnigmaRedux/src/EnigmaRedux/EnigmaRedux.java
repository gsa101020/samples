
package EnigmaRedux;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *Performs the functionality of the rotor machine.
 * @author Skratch
 */

public class EnigmaRedux {
    public static final int NUMBER_OF_ROTORS = 3;
    public static final int ALPHABET_SIZE = 70;
    private Rotor[] rotors;
    private Reflector reflector;
    private String clearText;
    private String cipherText;
    private boolean decrypt; // T if decrypt F if encrypt
    private Vigenere coder;
    private HashMap latinToNumeric;
    private char[] numericToLatin;
    /**
     *No arg constructor for the EnigmaRedux class.
     * @throws IOException from EnigmaRedux(int, int, int).
     */
    
    public EnigmaRedux() throws IOException {
        this(0, 0, 0, "Keyword", false);
    }//EnigmaRedux()
    
    /**
     *Constructor for the Enigmaredux class, initializes all class members.
     * @param shiftI determines the initial shift of the first rotor.
     * @param shiftII determines the initial shift of the second rotor.
     * @param shiftIII determines the initial shift of the third rotor.
     * @param keyword a keyword with which a Vinegre cipher will be applied.
     * @throws IOException from Rotor and Reflector.
     */
    
    public EnigmaRedux(int shiftI, int shiftII, int shiftIII, String keyword, boolean decrypt) throws IOException {
        rotors = new Rotor[NUMBER_OF_ROTORS];
        rotors[0] = new RotorA(shiftI);
        rotors[1] = new RotorB(shiftII);
        rotors[2] = new RotorC(shiftIII);
        coder = new Vigenere(keyword);
        this.decrypt = decrypt;
        reflector = new Reflector();
        Scanner fileReader = new Scanner(new File("Alphabet/alpha.txt"));
        int index = 0;
        
        numericToLatin = new char[ALPHABET_SIZE];
        latinToNumeric = new HashMap();
        
        while(fileReader.hasNext()) {
            char temp = fileReader.nextLine().charAt(0);
            numericToLatin[index] = temp;
            latinToNumeric.put((Character)temp, (Integer)index);
            index++;
        }//while
  
        fileReader.close();
        
        if(keyword.length() == 0) {
            cipherText = "Keyword has not been set.";
        }//if
        else {
            for(int i = 0; i < keyword.length(); i++) {
                if(!latinToNumeric.containsKey((keyword.charAt(i)))) {
                    cipherText = "Keyword contains illegal characters.";
                }//if
            }//for
        }//else
    }//enigmaRedux(int, int, int);
    
    /**
     *Allows one to set the shift index of the first rotor. 
     * @param shiftIndex sets the new shift index of the system.
     */
    
    public void setRotorIShift(int shiftIndex) {
        rotors[0].setShiftIndex(shiftIndex);
    }//setRotorIShift(int)

    /**
     *Allows one to set the shift index of the second rotor. 
     * @param shiftIndex sets the new shift index of the system.
     */
    
    public void setRotorIIShift(int shiftIndex) {
        rotors[1].setShiftIndex(shiftIndex);
    }//setRotorIIShift(int)

    /**
     *Allows one to set the shift index of the third rotor. 
     * @param shiftIndex sets the new shift index of the system.
     */

    public void setRotorIIIShift(int shiftIndex) {
        rotors[2].setShiftIndex(shiftIndex);
    }//setRotorIIIShift(int)
    
    /**
     *Allows one to set the current value of the clearText class member.
     * @param clearText a string to replace the current clearText.
     */
    
    public void setClearText(String clearText) {
        this.clearText = clearText;
    }//setClearText(String)
    
    /**
     *Accessor for the clearText class member.
     * @return returns a string value representing the state of clearText.
     */
    
    public String getClearText() {
        return clearText;
    }//getClearText()

    /**
     *Accessor for the cipherText class member.
     * @return returns a string value representing the state of cipherText.
     */
    
    public String getCipherText() {
        return cipherText;
    }//getCipherText()
    
    public void encryptClearText() {
        
        //convert from latin to number.
        if(cipherText == null) {
            char[] clearTextArray = clearText.toCharArray();
            int[] convertedNumeric = new int[clearTextArray.length];

            for(int index = 0; index < clearTextArray.length; index++) {
                if(latinToNumeric.containsKey(clearTextArray[index])) {
                    convertedNumeric[index] = (Integer)latinToNumeric.get(clearTextArray[index]);
                }
                else {
                    cipherText = "Input contains illegal characters.";
                    break;
                }//else
            }//for
            
            if(cipherText == null) {
                if(!decrypt) {
                    for(int index = 0; index < convertedNumeric.length; index++) {
                        convertedNumeric[index] = coder.encrypt(convertedNumeric[index]);
                    }//for

                    convertedNumeric = applyRotors(convertedNumeric);
                }//if
                else {
                    convertedNumeric = applyRotors(convertedNumeric);

                    for(int index = 0; index < convertedNumeric.length; index++) {
                        convertedNumeric[index] = coder.decrypt(convertedNumeric[index]);
                    }//for    
                }//else

                //Convert from number to latin.

                char[] cipherTextArray = new char[convertedNumeric.length];

                for(int index = 0; index < convertedNumeric.length; index++) {
                    cipherTextArray[index] = numericToLatin[convertedNumeric[index]];
                }//for

                cipherText = new String(cipherTextArray);
            }//if
        }//if
    }//encryptClearText()
    
    /**
     *Performs the encryption of the clearText. 
     * @param convertedNumeric an array of integers representing the pre-encrypted cleartext.
     * @return an array of ints representing the ciphertext.
     */
    
    public int[] applyRotors(int[] convertedNumeric) {
            
        //Perform the Encryption.

        for(int index = 0; index < convertedNumeric.length; index++) {

            for(int i = 0; i < rotors.length; i++) {
                convertedNumeric[index] = rotors[i].encode(convertedNumeric[index]);
            }//for

            convertedNumeric[index] = reflector.encode(convertedNumeric[index]);

            for(int j = rotors.length -1; j >= 0; j--) {
                convertedNumeric[index] =  rotors[j].encodeMirror(convertedNumeric[index]);
            }//for
        }//for

        return convertedNumeric;
    }//applyRotors()
}//EnigmaRedux
