
import java.io.*;
import java.util.Scanner;

// class to read file
public class Filereader {
    File file;
    Scanner sc;
    
    //initializes class with file as the parameter
    public Filereader(String file){
        try{
            this.file = new File(file);
            this.sc = new Scanner(this.file);
            
        }
        catch(Exception e){
            
        }
                
    }
    // prints out lines of the file with the number as the parameter
    public void printline(int i){
        for (int j = 0; j< i; j++){
            System.out.println(sc.next());

        }
        
        
        
    }
    public int[][] returnArray(){
        printline(6);
        int [][] array = new int[513][512];
        int i = 0;
        while (i < 513){
            String temp = this.sc.next();
            for (int j = 0; j < temp.length(); j++){
                if(Character.toString(temp.charAt(j)).equals("@")){
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
