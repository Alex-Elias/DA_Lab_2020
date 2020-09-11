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
    public final int x;
    public final int y;
    
    /**
     * initializes the Tuple class
     * @param x the first element is stored in x
     * @param y the second element is stored in y
     */
    public Tuple(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString(){
        return "X = " + this.x + ", Y = " + this.y;
    }
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
