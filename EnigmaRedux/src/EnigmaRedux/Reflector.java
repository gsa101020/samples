
package EnigmaRedux;

import java.util.Scanner;
import java.io.*;

/**
 *Performs the functionality of a direct alphabet cipher.
 */

public class Reflector {
    
    int[] reflectorKey;
    
    /**
     *No arg constructor for the Reflector Class.
     * @throws IOException.
     */
    
    public Reflector() throws IOException {
        
        File replacerKeyFile = new File("Replacers/Reflector/Reflector.txt");
        Scanner replacerReader = new Scanner(replacerKeyFile);
        
        reflectorKey = new int[70];
        int index = 0;
        
        while(replacerReader.hasNext()) {
            reflectorKey[index] = replacerReader.nextInt();
            index++;
        }//while
    }//Reflector()
    
    /**
     *Encodes one term in a sequence.
     * @param input an integer value to be encoded.
     * @return an encoded integer value. 
     */
    
    public int encode(int input) {
        input = reflectorKey[input];
        return input;
    }//encode(int)
}//Reflector
