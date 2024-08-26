public class Array {
    public static int[] insert(int[] arr, int item, int position) {
        int[] new_arr = new int[arr.length + 1];
        int i = 0;
        int j = 0;
        while (i < new_arr.length) {
            if (i == position) {
                i++;
                continue;
            }
            else {
                new_arr[i] = arr[j];
                i++;
                j++;
            }
        }
        new_arr[position] = item;
        return new_arr;
    }

    public static void main(String args[]) {
        int[] x = {5, 9, 14, 15};
        int[] y = insert(x, 6, 2);
        for (int item : y) {
            System.out.println(item);

        }
    }
}
