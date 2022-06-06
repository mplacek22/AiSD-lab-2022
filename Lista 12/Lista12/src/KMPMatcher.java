import java.util.ArrayList;
import java.util.List;

public class KMPMatcher implements IStringMatcher {
    @Override
    public List<Integer> validShifts(String textToSearch, String patternToFind) {
        // TODO: Zaimplementuj algorytm dopasowywania napisów Knutha-Morrisa-Pratta
        List<Integer> occuranceList = new ArrayList<>();

        char[] text = textToSearch.toCharArray();
        char[] pattern = patternToFind.toCharArray();

        int n = textToSearch.length();
        int m = patternToFind.length();

        int[] lps = computePrefixFunction(pattern); //longest proper prefix which is also suffix

        int i = 0; // index for txt[]
        int j = 0; // index for pat[]

        while (i < n) {
            if (pattern[j] == text[i]) {
                j++;
                i++;
            }
            if (j == m) {
                occuranceList.add(i - j);
                j = lps[j - 1];
            }

            // mismatch after j matches
            else if (i < n && pattern[j] != text[i]) {
                //lps[0...lps[j-1]] already match
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }

        return occuranceList;
    }

    private int[] computePrefixFunction(char[] pattern){
        int m = pattern.length;
        int[] lps = new int[m];

        int prevLPSLength = 0;
        int i = 1;
        lps[0] = 0;

        while (i < m) {
            if (pattern[i] == pattern[prevLPSLength]) {
                prevLPSLength++;
                lps[i] = prevLPSLength;
                i++;
            }
            else
            {
                if (prevLPSLength != 0) {
                    prevLPSLength = lps[prevLPSLength - 1];
                }
                else
                {
                    lps[i] = prevLPSLength;
                    i++;
                }
            }
        }

        return lps;
    }
}
