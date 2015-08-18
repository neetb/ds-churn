package leetcode;

import java.util.*;

/**
 * Created by abhinav on 26/7/15.
 */
public class CombinationSum {

    public static void main(String[] args)  {
        CombinationSum sum = new CombinationSum();
        System.out.println(sum.combinationSum(new int[]{1,1}, 2));
       // System.out.println(sum.combinationSum2(new int[]{1,1}, 2));
    }

    //Recursive
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        //base case checking
        if((candidates == null) || (candidates.length == 0) || (target == 0))   {
            return resultList;
        }

        List<Integer> current = new ArrayList<Integer>();
        Arrays.sort(candidates);

        combinationSumUtil(candidates, target, 0, current, resultList);
        return resultList;
    }

    private void combinationSumUtil(int[] candidates, int target, int k, List<Integer> current, List<List<Integer>> result) {
       if(target == 0)  {
           List<Integer> temp = new ArrayList<Integer>(current);
           result.add(temp);
           return;
       }

       for(int i = k; i < candidates.length; i++)   {
           if(target < candidates[i])   {
               return;
           }else    {
               current.add(candidates[i]);
               combinationSumUtil(candidates, target - candidates[i], i, current, result);
               current.remove(current.size() - 1);
           }
       }

    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if((candidates == null) || (candidates.length == 0) || (target == 0))   {
            return result;
        }

        combinationSum2(candidates, new ArrayList<Integer>(), 0, target, result);
        return result;
    }

    private void combinationSum2(int[] candidates, List<Integer> current, int lo, int target, List<List<Integer>> result) {
        if(target == 0) {
            result.add(current);
            return;
        }

        for(int i = lo; i < candidates.length; i++) {
            if((i > 0) && (i == lo) && (candidates[i] == candidates[i-1]))   {
                continue;
            }

            if(candidates[i] > target)   {
                return;
            }else   {
                current.add(candidates[i]);
                combinationSum2(candidates, current, i + 1, target - candidates[i], result);
                current = new ArrayList<Integer>();
            }
        }

    }


    //Iterative
    public List<List<Integer>> combinationSumV1(int[] candidates, int target) {
        Set<List<Integer>> result = new HashSet<List<Integer>>();
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        //base case checking
        if((candidates == null) || (candidates.length == 0) || (target == 0))   {
           return resultList;
        }

        Arrays.sort(candidates);
        Set<List<Integer>>[] combinations = new Set[target + 1];

        for(int i = 1; i <= target; i++)    {
            combinations[i] = new HashSet();
            for(int j = 0; j < candidates.length; j++)  {
                if(candidates[j] == i)   {
                   List<Integer> list = new ArrayList();
                   list.add(i);
                   combinations[i].add(list);
                }else if(candidates[j] < i)  {
                   int rem = i - candidates[j];
                   int current = candidates[j];
                   if((combinations[current] != null) && (combinations[current].size() > 0) && (combinations[rem] != null) && (combinations[rem].size() > 0)) {
                       combinations[i].addAll(union(combinations[current], combinations[rem]));
                   }
                }else   {
                    break;
                }
            }
        }

        for(List<Integer> list : combinations[target])    {
            resultList.add(list);
        }

        return resultList;
    }

    private Set<List<Integer>> union(Set<List<Integer>> a, Set<List<Integer>> b)   {
         if((a == null) || (a.isEmpty()) || (b == null) || (b.isEmpty()) )   {
             return new HashSet<List<Integer>>();
         }

        Set<List<Integer>> result = new HashSet<List<Integer>>();

        for(List<Integer> listA : a) {
            for(List<Integer> listB : b)    {
                List<Integer> res = new ArrayList<Integer>();
                res.addAll(listB);
                res.addAll(listA);
                Collections.sort(res);
                result.add(res);
            }
        }
        return result;
    }
}
