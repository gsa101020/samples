
package EnigmaRedux;

//Useful script for altering the replacer files.

import java.io.*;
import java.util.Scanner;
public class repalcerFixer {
        
        public static void main(String[] args) throws IOException {
        File replacerFile = new File("");
        Scanner fileReader = new Scanner(replacerFile);
        
        int[] array = new int[70];
        int index = 0;
        
        while(fileReader.hasNext()) {
            array[index] = (fileReader.nextInt() +  1);
            index++;
        }//while
        
        fileReader.close();
        PrintWriter fileWriter = new PrintWriter(replacerFile);
        
        for(int i = 0; i < array.length; i++) {
            fileWriter.println(array[i]);
        }//for
        
        fileWriter.close();
    }//main(String[])
}//replacerFixer
