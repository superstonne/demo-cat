import java.util.ArrayList;

/**
 * @description:
 * @author: 肖奈（贺金龙）
 * @create: 2019-12-23 19:33
 */
public class Solution {

    public static void main(String[] args) {
        removeElement(new int[]{2}, 3);
    }

    public static int removeElement(int[] A, int elem) {
        // write your code here
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            if (elem == A[i]) {
                result++;
            } else {
                resultList.add(A[i]);
            }
        }

        System.out.println(resultList);

        for (int i =  0; i < resultList.size(); i++) {
            A[i] = resultList.get(i);
        }

        for (int t : A) {
            System.out.println(t);
        }

        return result;

    }
}
