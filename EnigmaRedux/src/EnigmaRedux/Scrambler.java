//
//package EnigmaRedux;
//
///**
// *Method applies a fed fixed cipher to provide a preliminary encryption over the data.
// */
//
//public class Scrambler {
//
//    private int[] scramblerKey;
//    
//    /**
//     *Primary constructor for the Scrambler class.
//     * @param scramblerKey an array of int primatives which provides the fixed cipher.
//     */
//    
//    public Scrambler(int[] scramblerKey) {
//        this.scramblerKey = scramblerKey;
//    }//Scrambler(int[])
//    
//    /**
//     *Encodes one term in a sequence.
//     * @param input an int value to be coded.
//     * @return a coded integer value.
//     */
//    
//    public int encode(int input) {
//        input = scramblerKey[input];
//        return input;
//    }//encode(int)
//}//Scrambler
