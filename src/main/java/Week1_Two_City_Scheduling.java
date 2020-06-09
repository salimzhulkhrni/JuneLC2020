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

There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].

Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.

 

Example 1:

Input: [[10,20],[30,200],[400,50],[30,20]]
Output: 110
Explanation: 
The first person goes to city A for a cost of 10.
The second person goes to city A for a cost of 30.
The third person goes to city B for a cost of 50.
The fourth person goes to city B for a cost of 20.

The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
 

Note:

1 <= costs.length <= 100
It is guaranteed that costs.length is even.
1 <= costs[i][0], costs[i][1] <= 1000

*/

import java.util.*;

public class Week1_Two_City_Scheduling {
    
    /*
    
    Approach: Greedy
    1. Get the extra money to pay/ refund when you calculate the profit
    2. Sort by maximum refunds in (ascending order)
    3. n/2 people with maximum refunds(in ascending order) will go to city B, rest will be on city A
    
    Example 1: [[10,20],[30,200],[400,50],[30,20]]        [400, 50] -> on moving from city A(cost 400) to city B(cost 50), refund(savings) on -350, so better move to city B
    Refunds: [ +10], [+170], [-350], [-10]
    when going to city B
    
    +ve = extra money to pay when going to city B, if that is the case, why would he go to city B, instead stay at city A
    -ve = refund(savings) recieved. If going to city B gives him maximum refund(savings), then why not go to city B instead of City A?
    
    refunds      =  [-350, -10, +10, +170]
    cities to go =  [ B,    B,   A,   A  ]
    person       =  P3,    P4,  P1    P2
    
    Example 2:        [ [20, 60], [10. 50], [40, 200], [30, 300]]
    Refund(savings):  [   +40,      +40,     +160,      +270]
    sort:             [   +40,      +40,     +160,      +270]
    simple logic? why would P3, P4 spent +160 +270 to go city B .. instead P1, P2 can spent extra 40 and got city B. P3, P4 can stay in city A instead of specnding extra +160, +270 to go to city B
    */
    
    
    public static int twoCitySchedCost(int[][] costs){
        
        if(costs == null || costs.length == 0)
            return 0;
        
        int totalCosts = 0;
        
        Arrays.sort(costs, new Comparator<int[]>(){
            
            public int compare(int[] a, int[] b){
                
                int cost1 = a[1] - a[0];
                int cost2 = b[1] - b[0];
                
                // cost = -ve = more refund , cost  +ve = loss(extra money needed to be spent)
                
                if(cost1 < cost2 )
                    return -1;
                
                else if(cost1 > cost2) // sort by asceding order of refunds(savings)
                    return 1;
                
                else
                    return 0;
            }
        });
        
        for(int i=0; i<costs.length; i++){
            
            int[] cost = costs[i];
            totalCosts+= i < costs.length / 2 ? cost[1] : cost[0];   // after sorted, move n/2 with maximum refund(savings) to city B, then move the rest n/2 to city A
        }
        
        return totalCosts;
    }
    
    public static void main(String[] args){
        
        int[][] costs1 = new int[][]{ {10, 20}, {30, 200}, {400, 50}, {30, 20}};
        System.out.println("res: " + (110 == (twoCitySchedCost(costs1))));
        
        int[][] costs2 = new int[][]{ {20, 60}, {10, 50}, {40, 200}, {30, 300}};
        System.out.println("res: " + (180 == (twoCitySchedCost(costs2))));
        
    }
}
