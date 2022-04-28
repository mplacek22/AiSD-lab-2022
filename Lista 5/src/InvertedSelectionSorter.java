public class InvertedSelectionSorter implements ISorter {
    private final IChecker checker;

    public InvertedSelectionSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        for (int i = values.length-1; i >=0; i--)
        {
            int max_el_idx = i;
            for (int j = i-1; j >= 0; j--)
                if (values[j] > values[max_el_idx])
                    max_el_idx = j;

            int temp = values[max_el_idx];
            values[max_el_idx] = values[i];
            values[i] = temp;

            checker.check(values);
        }
    }
}
