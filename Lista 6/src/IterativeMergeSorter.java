public class IterativeMergeSorter implements ISorter {
    private final IChecker checker;

    public IterativeMergeSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        int curr_sub_size, left_start_idx;

        for (curr_sub_size = 1; curr_sub_size <= values.length-1; curr_sub_size = 2*curr_sub_size) {
            for (left_start_idx = 0; left_start_idx < values.length-1; left_start_idx += 2*curr_sub_size) {
                int mid_idx = Math.min(left_start_idx + curr_sub_size - 1, values.length-1);

                int right_end_idx = Math.min(left_start_idx + 2*curr_sub_size - 1, values.length-1);

                merge(values, left_start_idx, mid_idx, right_end_idx);
            }
            checker.check(values);
        }
    }

    private static void merge(int arr[], int left_idx, int mid_idx, int right_idx)
    {
        int l_size = mid_idx - left_idx + 1;
        int r_size = right_idx - mid_idx;

        int L[] = new int[l_size];
        int R[] = new int[r_size];

        for (int i = 0; i < l_size; i++)
            L[i] = arr[left_idx + i];
        for (int j = 0; j < r_size; j++)
            R[j] = arr[mid_idx + 1+ j];

        int i = 0; int j = 0;
        int k = left_idx;

        while (i < l_size && j < r_size)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < l_size)
        {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < r_size)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
