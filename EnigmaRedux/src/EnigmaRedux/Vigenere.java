
package EnigmaRedux;

/**
 *Performs a Vigenere Cipher on contributed characters of a cleartext.
 */

public class Vigenere {

    private int[] keyword;
    private int index;
    
    /**
     *Constructor for the Vigenere class.
     * @param keyword a keyword which will be employed in the application of the cipher.
     */
    
    public Vigenere(String keyword) {
        this.keyword = Custodian.latinToNumberConverter(keyword.toCharArray());
        index = 0;
    }//Vigenere(String)
    
    /**
     *Applies the Vigenere cipher term by term.
     * @param input an int value representing a member character of the cleartext.
     * @return an int value representing an encoded term.
     */
    
    public int encrypt(int input) {
        input = (input + keyword[index % keyword.length]) % EnigmaRedux.ALPHABET_SIZE;
        index++;
        return input;
    }//encrypt(int)
    
    /**
     *Reverses the Vigenere cipher term by term.
     * @param input a int value representing a member of the encoded ciphertext.
     * @return an int value representing a member of the decoded cleartext.
     */
    
    public int decrypt(int input) {
        input = (input - keyword[index % keyword.length]);
        
        if(input < 0) {
            input = EnigmaRedux.ALPHABET_SIZE + input;
        }//if
        
        index++;
        return input;
    }//decrypt(int input)
}//Vigenere
