
package EnigmaRedux;

import java.io.IOException;

/**
 *Models the behavior of RotorC.
 */
public class RotorC extends Rotor {
    
    /**
     *The constructor for RotorC.
     * @param shiftIndex the initial shift in the index.
     * @throws IOException from Rotor. 
     */
    
    public RotorC(int shiftIndex) throws IOException {
        super("Replacers/Shift/IIIA.txt", "Replacers/Fixed/IIIB.txt", shiftIndex);  
    }//RotorB(int)
    
    /**
     *Shifts the shiftIndex class member when the interations class member equals 4900.
     */
    
    protected void incrementShiftIndex() {
        if((interations % (shiftKey.length * shiftKey.length)) == 0) {
            shiftIndex++;
        }//if
    }//incrementShiftIndex()
}//RotorC
