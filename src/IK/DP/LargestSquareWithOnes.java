package IK.DP;

/**
 * Created  on 5/18/2016.
 */
public class LargestSquareWithOnes {
    static int maxSubmatrixRecur(int[][] arr, int max, int row, int column){
        if(arr.length <= column + max || arr.length <= row + max)
            return max;
        if(arr[row][column] == 1){
            int currentMax = getlargestMatrix(arr, row, column, 1);
            if(currentMax > max){
                max = currentMax;
            }
            return Math.max(maxSubmatrixRecur(arr, max, row + currentMax, column),
                    maxSubmatrixRecur(arr, max, row, column + currentMax));
        }
        return Math.max(maxSubmatrixRecur(arr, max, row + 1,column),
                maxSubmatrixRecur(arr, max, row, column + 1));
    }

    private static int getlargestMatrix(int[][] arr, int row, int column, int size) {
        if(arr.length <= row + size || arr.length <= column + size)
            return size;
        boolean isSquare = true;
        if(arr[row + size][column] != 1|| arr[row][column + size] != 1 || arr[row + size][column + size] != 1){
            isSquare = false;
        }
        if(!isSquare){
            return size;
        }
        return getlargestMatrix(arr, row, column, size + 1);
    }

    static int maxSubmatrix(int[][] mtx) {
        int currentMax = 0; //represents no 1's in the array
        return maxSubmatrixRecur(mtx, currentMax, 0, 0);
    }
    public static void main(String[] args){
        int[][] arr = {
                {1,1,0,0,0,1},
                {1,1,0,0,0,1},
                {0,0,1,1,1,1},
                {0,0,1,1,1,1},
                {0,1,1,1,1,1},
                {0,1,1,1,1,1}
        };

        System.out.println("longest square size " + maxSubmatrix(arr));
    }
}
