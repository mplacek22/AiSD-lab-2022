public class RadixSorter implements ISorter {
    private final IChecker checker;

    public RadixSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        int max = values[0];

        for (int i = 1; i < values.length; i++) {
            if(values[i] > max)
                max = values[i];
        }

        for (int exp = 1; max / exp > 0; exp *= 10){
            counting_sort(values, values.length, exp);
            checker.check(values);
        }
    }

    private static void counting_sort (int[] values, int n, int exp){
        int result[] = new int[n];
        int i;
        int count[] = new int[10];


        for (i = 0; i < n; i++)
            count[(values[i] / exp) % 10]++;

        count[0]--;

        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (i = n - 1; i >= 0; i--) {
            result[count[(values[i] / exp) % 10]] = values[i];
            count[(values[i] / exp) % 10]--;
        }

        for (i = 0; i < n; i++)
            values[i] = result[i];
    }
}
