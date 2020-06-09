/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author salim
 */

/*

Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

 
Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]


*/

import java.util.*;

public class Week1_Queue_Reconstruction_By_Height {
    
    /*
    
    Approach: Greedy
    
    1. Sort the people by decreasing order of their heights
       - If the heights are equals, sort by ascending order of k-values
    2. Insert each people at the kth index.
    
    Why this works?
     k-value is only determined the persons who are in front of them who would be greated than or equal the current height, since we already sorted by decreasing order of heights
    1. If we sort people by decreasing order, when we bring in a smaller height to insert as in Step 2(of above), we are guranteed to insert at the right index identified by k-value,
       since all the people in front of the person brought in from behind, are having heights greater than or equal to curent person's height.
    
    Ex: per example:
       1)After sorting by decreasing order:-
    
        [7, 0], [7, 1], [6, 1], [5, 0], [5, 2], [4, 4]
      
       2) insert [7,0]  -> [7, 0]
          insert [7, 1] -> [7, 0], [7, 1]  (see we are inserting by k-value, since sorting by decreasing order of heights helps guarantee the relative index of the current person's height)
          insert [6,1]  -> [7, 0], [6, 1], [7, 1] -> insert [6, 1] at k=1 th position, since before that only one person is there with [7, 0] & its current position of [6, 1] holds true
          insert [5, 0] -> [5, 0], [7, 0], [6, 1], [7, 1]
          insert [5, 2] -> [5, 0], [7, 0], [5, 2], [6, 1], [7, 1]
          insert [4, 4] -> [5, 0], [7, 0], [5, 2], [6, 1], [4, 4], [7, 1]
    
    Also, what if it is sorted as [5, 1], [5, 3]? and [5, 3] brought in before of [5, 1]?
       
       <--[5, 3] (let say there are 3 elements), [5, 1] 
                                                    ||
                                                    (this k=1 will be false, since [5,3] has 3 elements before it so including [5, 3] & 3 elements before, it must be showing [5, 4] instead of [5, 1])
       so, [5, 1], [5, 3] is the correct ascending sort order of k when heights are same
    */
    
    
    public static int[][] reconstructQueue(int[][] people){
        
        if(people == null || people.length == 0)
            return new int[0][0];
        
        List<int[]> res = new ArrayList<>(people.length);
        
        Arrays.sort(people, new Comparator<int[]>() {
            
            public int compare(int[] a, int[] b){
                
                if(a[0] == b[0])
                    return a[1] - b[1];
                else
                    return b[0] - a[0];
            }
        
        });
        
        for(int[] arr: people)
            res.add(arr[1], arr);
        
        return res.toArray(new int[people.length][people[0].length]);
        
    }
    
    
    public static void main(String[] args){
        
        int[][] arr = new int[][] {
                                        {7, 0},
                                        {4, 4},
                                        {7, 1}, 
                                        {5, 0}, 
                                        {6, 1}, 
                                        {5, 2}
                                  };
        
       int[][] res = reconstructQueue(arr);
       int[][] expected = new int[][]{
                                        {5, 0}, 
                                        {7, 0}, 
                                        {5, 2}, 
                                        {6, 1}, 
                                        {4, 4}, 
                                        {7, 1}
                                     };
       boolean result = true;
       
       for(int i=0; i<res.length; i++){
           
           result = result && Arrays.equals(res[i], expected[i]);
       }
       
        System.out.println("result: " + result);
    }
}
