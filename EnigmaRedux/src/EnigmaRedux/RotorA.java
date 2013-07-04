
package EnigmaRedux;

import java.io.IOException;

/**
 *Models the behavior of RotorA.
 */

public class RotorA extends Rotor {
    
    /**
    *Constructor for RotorA.
    * @param shiftIndex the initial shift in the rotor.
    * @throws IOException from Rotor.
    */
    
    public RotorA(int shiftIndex) throws IOException {
        super("Replacers/Shift/IA.txt", "Replacers/Fixed/IB.txt", shiftIndex);  
    }//RotorA(int)
    
    /**
     *Shifts the shiftIndex when called.
     */
    
    protected void incrementShiftIndex() {
        shiftIndex++;
    }//incrementShiftIndex()
}//RotorA
