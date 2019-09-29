/*
Input  : pat = "bca", str = "abc"
Output : str = "bca"

Input  : pat = "bxyzca", str = "abcabcabc"
Output : str = "bbbcccaaa"

Input  : pat = "wcyuogmlrdfphitxjakqvzbnes", str = "jcdokai"
Output : str = "codijak"
*/
class SortStringBasedOnAnother {
	public static void main(String[] args) {
		String pattern = "bca";
		String input = "abc";

		String newString = getSortedStringWithGivenPattern(pattern, input);
		System.out.println(newString);

	}

	private static String getSortedStringWithGivenPattern(String pattern, String input) {

		int[] alphas = new int[26]; // considering only alphas
		String result = "";

		//Counting the number of occurrences of each character
		for (int i = 0; i < input.length(); i++) {
			alphas[input.charAt(i) - 'a']++;
		}

		//Building a new String with the same number of characters
		for (int i = 0; i < pattern.length(); i++) {
			char p = pattern.charAt(i);
			int charCountInString = alphas[p - 'a'];
			if (charCountInString > 0) {
				for (int j = 0; j < charCountInString; j++)
					result += p;
			}
			alphas[p - 'a'] = 0;

		}
		return result;

	}
}