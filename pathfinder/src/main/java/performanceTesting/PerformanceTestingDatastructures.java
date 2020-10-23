
package performanceTesting;

import datastructures.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * performance testing for the priority queue and list data structures
 * the class tests the inserting and removing time of different amount of elements
 * @author alex
 */
public class PerformanceTestingDatastructures {
    /**
     * an array of the different lengths for testing the data structures
     */
    private final int[] nums = {100, 1000, 10000, 100000, 1000000};
    /**
     * java priority queue median insert time array
     */
    private final double[] priorityQueueInsert = new double[nums.length];
    /**
     * my own priority queue median insert time array
     */
    private final double[] priorityQueueInsertOwn = new double[nums.length];
    /**
     * java priority queue median removal time array
     */
    private final double[] priorityQueueRemove = new double[nums.length];
    /**
     * my own priority queue median removal time array
     */
    private final double[] priorityQueueRemoveOwn = new double[nums.length];
    /**
     * my own list median insert time array
     */
    private final double[] nodelistInsert = new double[nums.length];
    /**
     * java array list median insert time array
     */
    private final double[] arraylistInsert = new double[nums.length];
    /**
     * initializes the class
     */
    public PerformanceTestingDatastructures(){
        
    }
    /**
     * runs the performance testing
     * each data structure is inserted a certain amount of elements then removed fully 100 times
     * each median run time of the data structures is stored in the appropriate array
     */
    public void runPerformanceTesting(){
        int n = 100;
        for (int run = 0; run < nums.length; run++){
            int num = nums[run];
            Node[] array = createRandomNodeArray(num);
            long[] times = new long[n];
            long[] times2 = new long[n];
            long t;
            
            
            for (int i = 0; i < n; i++){
                NodePriorityQueue pq = new NodePriorityQueue();
                t = System.nanoTime();
                for (int j = 0; j < num; j++){
                    pq.insert(array[j], array[j].getPriority());
                }
                t = System.nanoTime() - t;
                times[i] = t;
                t = System.nanoTime();
                while(!pq.isEmpty()){
                    pq.deleteMin();
                }
                t = System.nanoTime() - t;
                times2[i] = t;
                
            }
            Arrays.sort(times);
            Arrays.sort(times2);
            this.priorityQueueInsertOwn[run] = times[times.length / 2] / 1000;
            this.priorityQueueRemoveOwn[run] = times2[times2.length / 2] / 1000;
            
            
            for (int i = 0; i < n; i++){
                PriorityQueue<Node> jpq = new PriorityQueue<>(new CustomNodeComparator());
                t = System.nanoTime();
                for (int j = 0; j < num; j++){
                    jpq.add(array[j]);
                }
                t = System.nanoTime() - t;
                times[i] = t;
                t = System.nanoTime();
                while(!jpq.isEmpty()){
                    jpq.poll();
                }
                t = System.nanoTime() - t;
                times2[i] = t;
                
            }
            Arrays.sort(times);
            Arrays.sort(times2);
            this.priorityQueueInsert[run] = times[times.length / 2] / 1000;
            this.priorityQueueRemove[run] = times2[times2.length / 2] / 1000;
            
            
            for (int i = 0; i < n; i++){
                NodeList nodelist = new NodeList();
                t = System.nanoTime();
                for (int j = 0; j < num; j++){
                    nodelist.add(array[j]);
                }
                t = System.nanoTime() - t;
                times[i] = t;
                
                
            }
            Arrays.sort(times);
            this.nodelistInsert[run] = times[times.length / 2] / 1000;
            
            
            for (int i = 0; i < n; i++){
                ArrayList<Node> list = new ArrayList<>();
                t = System.nanoTime();
                for (int j = 0; j < num; j++){
                    list.add(array[j]);
                }
                t = System.nanoTime() - t;
                times[i] = t;
                
                
            }
            Arrays.sort(times);
            
            this.arraylistInsert[run] = times[times.length / 2] / 1000;
            
            
        }
        
        
        
        
        
        
    }
    /**
     * toString override which returns statistics as a string
     * @return the string of the statistics
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        sb.append("NodePriorityQueue times\n");
        appendResults(sb,this.priorityQueueInsertOwn, this.priorityQueueRemoveOwn);
        
        sb.append("\nPriorityQueue times\n");
        appendResults(sb, this.priorityQueueInsert, this.priorityQueueRemove);
        
        sb.append("\nNodeList times\n");
        appendResults(sb, this.nodelistInsert, null);
        
        sb.append("\nArraylist times\n");
        appendResults(sb, this.arraylistInsert, null);
        
        return sb.toString();
        
    }
    /**
     * 
     * appends the results to the string builder and returns the newly appended string builder
     * @param sb the string builder which is appended to
     * @param array the median insert run time array
     * @param remove the median removal run time array
     */
    private void appendResults(StringBuilder sb, double[] array, double[] remove){
        for (int i = 0; i < nums.length; i++){
            String num = Integer.toString(nums[i]);
            for (int j = 0; j < 7 - num.length(); j++){
                sb.append(" ");
            }
            sb.append(num);
            sb.append(": Insert: ");
            sb.append(array[i]);
            sb.append("us");
            if (remove != null){
                sb.append(", Remove: ");
                
                sb.append(remove[i]);
                sb.append("us");
            }
            
            sb.append("\n");
        }
    }
    /**
     * creates an array with random elements of certain length
     * @param size the desired length of the array
     * @return an array of random nodes
     */
    private Node[] createRandomNodeArray(int size){
        Node[] array = new Node[size];
        Random r = new Random();
        for (int i = 0; i < size; i++){
            array[i] = new Node(new Tuple(i,i),r.nextInt(size));
        }
        return array;
    }
}
/**
 * custom comparator for the node class for use in the java priority queue 
 * @author alex
 */
class CustomNodeComparator implements Comparator<Node> {
    /**
     * compare override for node class
     * @param o1 first node to compare
     * @param o2 second node to compare
     * @return return an int value determining which node is larger
     */
    @Override
    public int compare(Node o1, Node o2) {
        return o1.getPriority() > o2.getPriority() ? 1 : -1;
    }
}
