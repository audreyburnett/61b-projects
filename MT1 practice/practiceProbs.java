public class practiceProbs {
    public static int mysteryRecursive(int[] inputArray, int k){
        return mysteryRecursiveHelper(inputArray, k, k + 1);
    }
    public static int mysteryRecursiveHelper(int[] inputArray, int i, int j){
        if (inputArray.length == 1){
            return i;
        }
        int index = i;
        if (inputArray[index]< mysteryRecursiveHelper(inputArray[index+1:], i , j)){
            return index;
        }
        return mysteryRecursiveHelper(inputArray[index+1:], i, j);
    }
}
