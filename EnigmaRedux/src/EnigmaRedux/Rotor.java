
package EnigmaRedux;

import java.io.*;
import java.util.Scanner;

/**
 *An abstracted class which models the functionality of the specific rotor objects.
 */

public abstract class Rotor {
    int[] shiftKey;
    int[] fixedKey;
    int[] shiftKeyMirror;
    int[] fixedKeyMirror;
    int shiftIndex;
    int interations;
    
    /**
     *Constructor for the rotor class.
     * @param shiftKeyFile a string representing the name of the seed alphabet cipher.
     * @param fixedKeyFile a string representing the name of the seed alphabet cipher.
     * @param shfitIndex an integer which will set the initial shift of the rotor.
     * @throws IOException.
     */
    
    public Rotor(String shiftKeyFile, String fixedKeyFile, int shiftIndex) throws IOException {
        File shiftKeyFileF = new File(shiftKeyFile);
        File fixedKeyFileF = new File(fixedKeyFile);
        
        Scanner fileReaderS = new Scanner(shiftKeyFileF);
        Scanner fileReaderF = new Scanner(fixedKeyFileF);
        
        int index = 0;
        shiftKey = new int[EnigmaRedux.ALPHABET_SIZE];
        fixedKey = new int[EnigmaRedux.ALPHABET_SIZE];
        shiftKeyMirror = new int[EnigmaRedux.ALPHABET_SIZE];
        fixedKeyMirror = new int[EnigmaRedux.ALPHABET_SIZE];
        
        while(fileReaderS.hasNext() && fileReaderF.hasNext()) {
            shiftKey[index] = fileReaderS.nextInt();
            fixedKey[index] = fileReaderF.nextInt();
            shiftKeyMirror[shiftKey[index]] = index;
            fixedKeyMirror[fixedKey[index]] = index;
            index++;
        }//while
        
        this.shiftIndex = shiftIndex;
    }//Rotor(String, String, int)
    
    /**
     *A mutator method which allows one to modify the shiftIndex of the rotor.
     * @param shiftIndex an int value which replaces the current class member shiftIndex.
     */
    
    public void setShiftIndex(int shiftIndex) {
        this.shiftIndex = shiftIndex;
        interations = 0;
    }//setShiftIndex(int)
    
    /**
     *An accessor method which allows one to obtain the current value of shiftIndex.
     * @return an int value representing the current state of the shiftIndex class member.
     */
    
    public int getShiftIndex() {
        return shiftIndex;
    }//getShiftIndex()
    
    /**
     *Encodes a single character in a sequence.
     * @param input a single member of a sequence to be encoded.
     * @return an encoded term of the sequence.
     */
    
    public int encode(int input) {
    	input = shiftKey[(input + shiftIndex) % shiftKey.length];
	input = fixedKey[input];
   
        return input;
    }//encode(int)
    
    /**
     *Reverse encodes a single character in sequence.
     * @param input an integer value to be encoded.
     * @return an encoded integer value.
     */
    
    public int encodeMirror(int input) {
        
        input = fixedKeyMirror[input];
        input = ((shiftKeyMirror[input] - shiftIndex) % shiftKey.length);
        
        if(input < 0) {
            input = shiftKey.length - Math.abs(input);
        }//if
        
        interations++;
	incrementShiftIndex(); 
        return input;
    }//encodeMirror(int)
    
    /**
     *Abstract method to increment the shiftIndex class member.
     */
    
    protected abstract void incrementShiftIndex();
    
}//Rotor
