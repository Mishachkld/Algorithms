package SecondSemester.Lab17;

public class Lab17 {

    public static int firstFit(int[] weight, int n, int c) {
        int res = 0;
        int[] bin_rem = new int[n];

        for (int i = 0; i < n; i++) {
            int j = 0;
            while (j < res) {
                if (bin_rem[j] >= weight[i]) {
                    bin_rem[j] = bin_rem[j] - weight[i];
                    break;
                }
                j++;
            }

            if (j == res) {
                bin_rem[res] = c - weight[i];
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] weight = {2, 3, 5, 4, 7};
        int c = 10;
        int n = weight.length;
        System.out.println(firstFit(weight, n, c));
    }
}
