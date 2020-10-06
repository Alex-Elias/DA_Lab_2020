package performanceTesting;

/**
 *
 * @author alex
 */
public class Mergesort {
    
    
    public Mergesort(){
        
    }
    public long[] runMergesort(long[] A){
        return mergesort(A);
    }
    
    private long[] mergesort(long[] A){
        int length = 1;
        while(length < A.length){
            int start = 0;
            while(start + length < A.length){
                int left = start;
                int mid = start + length -1;
                int right = mid + length;
                right = this.min(right, A.length-1);
                merge(A,left,mid,mid+1,right);
                start = start +2*length;
                
                
            }
            length =2*length;
        }
        return A;
    }
    private int min(int a, int b){
        if(a < b){
            return a;
        }
        if (b < a){
            return b;
        }
        return a;
    }
    
    
    private void merge(long[] A, int a1, int b1, int a2, int b2){
        long[] C = new long[A.length];
        int a = a1;
        int b = b2;
        for(int i = a; i <=b; i++){
            if(a2>b2 ||(a1<=b1 && A[a1] <= A[a2])){
                C[i]=A[a1];
                a1++;
            }else{
                C[i] = A[a2];
                a2++;
            }
        }
        for(int i = a; i<=b; i++){
            A[i] = C[i];
        }
        
        
        
    }
}
