public class SelectionSorter implements ISorter {
    private final IChecker checker;

    public SelectionSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        for (int i = 0; i < values.length-1; i++)
        {
            int min_el_idx = i;
            for (int j = i+1; j < values.length; j++)
                if (values[j] < values[min_el_idx])
                    min_el_idx = j;

            int temp = values[min_el_idx];
            values[min_el_idx] = values[i];
            values[i] = temp;

            checker.check(values);
        }
    }
}
