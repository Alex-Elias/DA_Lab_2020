
package performanceTesting;

import datastructures.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 *
 * @author alex
 */
public class PerformanceTestingDatastructures {
    
    private final int[] nums = {100, 1000, 10000, 100000, 1000000};
    private final double[] priorityQueueInsert = new double[nums.length];
    private final double[] priorityQueueInsertOwn = new double[nums.length];
    private final double[] priorityQueueRemove = new double[nums.length];
    private final double[] priorityQueueRemoveOwn = new double[nums.length];
    
    private final double[] nodelistInsert = new double[nums.length];
    private final double[] arraylistInsert = new double[nums.length];
    
    public PerformanceTestingDatastructures(){
        
    }
    
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
    
    private Node[] createRandomNodeArray(int size){
        Node[] array = new Node[size];
        Random r = new Random();
        for (int i = 0; i < size; i++){
            array[i] = new Node(new Tuple(i,i),r.nextInt(size));
        }
        return array;
    }
}

class CustomNodeComparator implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        return o1.getPriority() > o2.getPriority() ? 1 : -1;
    }
}
