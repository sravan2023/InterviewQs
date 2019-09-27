import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ScatteredSubStringPalandrome {

	public static void main(String[] args) {
		Instant start = Instant.now();

		List<String> strToEvaluate = new ArrayList<>();
		strToEvaluate.add("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaxaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaxaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaxaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaxaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaxaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaxaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaxaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println(scatterPalindrome(strToEvaluate));

		Instant finish = Instant.now();
		long timeElapsed = Duration.between(start, finish).toMillis();
		System.out.println(timeElapsed);
	}

	public static List<Integer> scatterPalindrome(List<String> strToEvaluate) {
		List<Integer> resultList = new ArrayList<>();
		for(String str : strToEvaluate){
			resultList.add(getScatterPalindromeFor(str));
		}
		return resultList;
	}

	/**
	 * For a given string returns the number of substrings that can form a palindrome
	 * @param str String of only alphabets
	 * @return int count of substrings that are valid scattered palindrome
	 */
	private static int getScatterPalindromeFor(String str) {
		char s[] = str.toCharArray();
		int count = s.length;

		for (int i = 0; i < s.length; i++) {
			int[] alphas = new int[26];
			alphas[s[i] - 'a']++;
			for (int j = i + 1; j < s.length; j++) {
				alphas[s[j] - 'a']++;
				if (isPalindrome(alphas)) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Checks if the given alphabetic array can form a palindrome or not
	 * @param alphas int array of size 26 that contains the occurrences of characters fo the test string
	 * @return true if palindrome can be formed with given character count
	 */
	private static boolean isPalindrome(int[] alphas){

		int singles = 0;
		for(int i=0;i<alphas.length;i++)
			if (alphas[i] % 2 == 0)
				continue;
			else if(singles == 0)
				singles++;
			else
				return false;

		return true;
	}

}
