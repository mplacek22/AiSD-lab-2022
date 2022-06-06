import java.util.ArrayList;
import java.util.List;

public class AutomatonMatcher implements IStringMatcher {
    int alphabetSize = 256;

    @Override
    public List<Integer> validShifts(String textToSearch, String patternToFind) {
        // TODO: Zaimplementuj algorytm dopasowywania napisów oparty na automacie skończonym
        List<Integer> occuranceList = new ArrayList<>();

        char[] text = textToSearch.toCharArray();
        char[] pattern = patternToFind.toCharArray();

        int n = text.length;
        int m = pattern.length;

        int[][] TF = new int[m+1][alphabetSize];

        computeTF(pattern, TF);

        int q = 0;
        for (int i = 0; i < n; i++)
        {
            q = TF[q][text[i]];
            if (q == m)
                occuranceList.add(i-m+1);
        }

        return occuranceList;
    }

    //transition function
    private void computeTF(char[] pattern, int TF[][])
    {
        int m = pattern.length;
        for (int q = 0; q <= m; ++q)
            for (int a = 0; a < alphabetSize; ++a)
                TF[q][a] = getNextState(pattern, q, a);
    }

    private int getNextState(char[] pattern, int q, int a) {
        int m = pattern.length;

        if(q < m && a == pattern[q])
            return q + 1;

        for (int nextState = q; nextState > 0; nextState--)
        {
            if (pattern[nextState-1] == a)
            {
                int i;
                for (i = 0; i < nextState-1; i++)
                    if (pattern[i] != pattern[q-nextState+1+i])
                        break;
                if (i == nextState-1)
                    return nextState;
            }
        }

        return 0;
    }

}
