package filereader;

/**
 * reads a file where '.' are open spaces and '@' are obstacles
 * returns an 2d array where zeros are open spaces and ones are obstacles
 */
import java.io.*;
import java.util.Scanner;


public class Filereader {
    /**
     * file data type which stores the maze file 
     */
    File file;
    /**
     * the scanner which reads the file
     */
    Scanner sc;
    
    /**
     * reads a file to a Scanner variable
     * @param file the location of the file to be read, must be a String
     */
    public Filereader(String file){
        try{
            this.file = new File(file);
            this.sc = new Scanner(this.file);
            
        }
        catch(Exception e){
            
        }
                
    }
    /**
     * prints a certain number of lines of the file
     * @param i the number of lines to be printed, must be an integer
     */
    public void printline(int i){
        for (int j = 0; j< i; j++){
            sc.next();

        }
        
        
        
    }
    /**
     * returns a 512X512 array of the file where ones represent obstacles and zeros as open spaces
     * @return the 2d array of the file with open spaces and obstacles converted to 0's and 1's respectively
     */
    public int[][] returnArray(){
        printline(6);
        int [][] array = new int[513][512];
        int i = 0;
        while (i < 513){
            String temp = this.sc.next();
            for (int j = 0; j < temp.length(); j++){
                if(Character.toString(temp.charAt(j)).equals("@") ||Character.toString(temp.charAt(j)).equals("T")){
                    array[i][j] = 1;
                }else{
                    array[i][j] = 0;
                }
            }
            i++;
        }
        return array;
        
    }
    /**
     * returns a 2d array of the file for any size
     * @return a 2d array of the file for any size
     */
    public int[][] returnMaze(){
        printline(6);
        String temp = this.sc.next();
        int[][] array = new int[temp.length()][temp.length()];
        int i = 0;
        for (int j = 0; j < temp.length(); j++){
            if(Character.toString(temp.charAt(j)).equals("@") ||Character.toString(temp.charAt(j)).equals("T")){
                array[i][j] = 1;
            }else{
                array[i][j] = 0;
            }
        }
        i++;
        while(sc.hasNext()){
            temp = this.sc.next();
            for (int j = 0; j < temp.length(); j++){
                if(Character.toString(temp.charAt(j)).equals("@") ||Character.toString(temp.charAt(j)).equals("T")){
                    array[i][j] = 1;
                }else{
                    array[i][j] = 0;
                }
            }
            i++;
        }
        return array;
        
    }
    
    
}
