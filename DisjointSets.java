import java.io.*;
import java.util.*;
 
 
/****************************
*
* Author : Asu Simla Ayduran
* ID : 260822715
* No Collaborators
*
*
*****************************/
 
 
public class DisjointSets {
	private int[] par;
    private int[] rank;
    
    /* contructor: creates a partition of n elements. */
    /* Each element is in a separate disjoint set */
    DisjointSets(int n) {
        if (n>0) {
            par = new int[n];
            rank = new int[n];
            for (int i=0; i<this.par.length; i++) {
                par[i] = i;
            }
        }
    }
    
    public String toString(){
        int pari,countsets=0;
        String output = "";
        String[] setstrings = new String[this.par.length];
        /* build string for each set */
        for (int i=0; i<this.par.length; i++) {
            pari = find(i);
            if (setstrings[pari]==null) {
                setstrings[pari] = String.valueOf(i);
                countsets+=1;
            } else {
                setstrings[pari] += "," + i;
            }
        }
        /* print strings */
        output = countsets + " set(s):\n";
        for (int i=0; i<this.par.length; i++) {
            if (setstrings[i] != null) {
                output += i + " : " + setstrings[i] + "\n";
            }
        }
        return output;
    }
    
    /* find resentative of element i */
    public int find(int i) {
    	//I solved this question according to our lecture slides
    	// if i is the parent 
        if (par[i] == i) 
        {
            return i;
        }
        else
        { 
            //As we saw in class for path comparison
        	// We move i’s node  under the root 
           par[i] = find(par[i]);
           return par[i];
      
         }
        
    }
        /* Fill this method (The statement return 0 is here only to compile) */
       /* return 0; */
        
   
 
    /* merge sets containing elements i and j */
    public int union(int i, int j) {
    	// Arbitrarily merge the set on i into the set of j. 
    	// Going to root nodes
    	 int jroot = this.find(j);
         int iroot = this.find(i);
       
        // Get  rank of treess
          rank[j] = rank[jroot];
 
          rank[i] = rank[iroot];
          
        // If they are same don't do anything    
        if (jroot == iroot)
            return 0;
       
        // As we saw in class
        // if ( find(i) != find(j) ){
        //	par[find(i)] = find(j); 
        // So we say similarly
        // If one of the nodes rank is less than the other's rank
        if (rank[j] < rank[i]) 
        {
            this.par[jroot] = iroot;
        } 
        else if (rank[i] < rank[j]) 
        {
            this.par[iroot] = jroot;
        } 
       
    
        // In case the ranks are same
        else
        {
            // Then move i under j not the other way around
            this.par[iroot] = jroot;
            rank[jroot]++;
        }
        // initilize (I have returned this value since the function won't resolve without returning a value)
        return 1;
    } 
       
        
    
    
    public static void main(String[] args) {
        
        DisjointSets myset = new DisjointSets(6);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 1");
        myset.union(2,1);
        System.out.println(myset);
        System.out.println("-> Union 4 and 5");
        myset.union(4,5);
        System.out.println(myset);
        System.out.println("-> Union 3 and 1");
        myset.union(3,1);
        System.out.println(myset);
        System.out.println("-> Union 2 and 4");
        myset.union(2,4);
        System.out.println(myset);
        
    }
 
}