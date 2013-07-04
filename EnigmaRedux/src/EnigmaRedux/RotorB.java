
package EnigmaRedux;

import java.io.IOException;

/**
 *Models the behavior of RotorB.
 */

public class RotorB extends Rotor {
    
    /**
     *The constructor for RotorB.
     * @param shiftIndex the initial shift in the index.
     * @throws IOException from Rotor. 
     */
    public RotorB(int shiftIndex) throws IOException {
        super("Replacers/Shift/IIA.txt", "Replacers/Fixed/IIB.txt", shiftIndex);  
    }//RotorB(int)
    
    /**
     *Shifts the shiftIndex class member when the interations class member equals 70.
     */
    
    protected void incrementShiftIndex() {
        if((interations % shiftKey.length) == 0) {
            shiftIndex++;
        }//if
    }//incrementShiftIndex()
}//RotorB
