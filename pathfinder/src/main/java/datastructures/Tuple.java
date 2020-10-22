package datastructures;

/**
 * A basic two integer Tuple class
 * the parameters of this class must be int
 * the first element is stored in x
 * the second element is stored in y
 * this class has the toString Override which prints in the form: X = \d*, Y = \d*
 * this class has the equals Override
 * @author alex
 */






public class Tuple {
    /**
     * the first element
     */
    private final int x;
    /**
     * the second element
     */
    private final int y;
    
    /**
     * initializes the Tuple class
     * @param x the first element is stored in x
     * @param y the second element is stored in y
     */
    public Tuple(int x, int y){
        this.x = x;
        this.y = y;
    }
    /**
     * returns the value of x
     * @return the value of x
     */
    public int getX(){
        return this.x;
    }
    /**
     * returns the value of y
     * @return the value of y
     */
    public int getY(){
        return this.y;
    }
    /**
     * the override toString method
     * @return the x and y value as a string
     */
    @Override
    public String toString(){
        return "X = " + this.x + ", Y = " + this.y;
    }
    /**
     * override equals method
     * @param o the element to be compared to the class
     * @return the boolean value whether the two elements are equal
     */
    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        if(!(o instanceof Tuple)){
            return false;
        }
        Tuple t = (Tuple) o;
        
        return t.x == this.x && t.y == this.y;
    }
}
