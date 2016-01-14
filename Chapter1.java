/**
 * This class implements code for questions in Chapter1, CC150
 */

import java.util.*;

public class Chapter1 {

    /**
     * 1.1 Implement an algorithm to determine if a string has all
     * unique characters. What if you can not use additional data 
     * structures?
     *
     * This method implements using extra space.
     *
     * Returns true if all characters in the string is unique. Returns
     * false otherwise.
     * @param str the string to be checked
     * @return true if all characters unique
     */
    public static boolean isUniqueChar1(String str) {
	boolean[] checker = new boolean[256];

	for (int i = 0; i < str.length(); i++) {
	    int cur = str.charAt(i);
	    if (checker[cur]) { return false; }
	    else              { checker[cur] = true; }
	}

	return true;
    }

    /**
     * Same question as above, in the condition that all characters in
     * the range of [a-z], implemented without extra space.
     */
    public static boolean isUniqueChar2(String str) {
	int checker = 0;
	for (int i = 0; i < str.length(); i++) {
	    int val = str.charAt(i) - 'a';
	    if ((checker & (1 << val)) > 0) { return false; }
	    else                            { checker |= (1 << val);}
	}

	return true;
    }


    /**
     * 1.2 Write code to reverse a C-Style String. (C-String means that)
     * "abcd" is represented as five characters, including the null character.)
     *
     * This method reverse a C-String.
     * @param the C-String to be reversed
     */
    public static void reverseCString(char[] chars) {
	int end = chars.length - 1;
	int start = 0;
	while (start < end) {
	    char tmp = chars[start];
	    chars[start++] = chars[end];
	    chars[end--] = tmp;
	}
    } 

    /**
     * 1.3 Design an algorithm and write code to remove the duplicate characters 
     * in a string without using any additinal buffer. NOTE: one or two 
     * additional variables are fine. An extra copy of the array is not.
     *     
     * FOLLOW UP: Write the test cases for the method.
     * 1. null
     * 2. no duplicates: abcdef
     * 3. all duplicates: aaaaa
     * 4. all continuous duplicates: aaabbbccc
     * 5. non-continuous duplicates: abcabcabc
     * 
     * Remove the duplicate characters in a string.
     * @param str the arr of string to be manipulated
     */
    public static void removeDuplicates(char[] str) {
	if (str == null) {return;}

	int len = str.length;
	if (len < 2) {return;}

	int tail = 1;
	
	for(int i = 1; i < len; i++) {
	    int j;
	    for (j = 0; j < tail; j++) {
		if(str[j] == str[i]) { break; }
	    }
	    if (j == tail) {
		str[tail++] = str[i];
	    }
	}
	if (tail < len) { str[tail] = 0; }
    }

    /**
     * Same problem as above, but implemented with additional space.
     */
    public static void removeDuplicates2(char[] str) {
	if (str == null) {return;}
	int len = str.length;
	if (len < 2) {return;}

	boolean[] checkers = new boolean[256];
	checkers[str[0]] = true;
	int tail = 1;

	for (int i = 1; i < len; i++) {
	    if (!checkers[str[i]]) {
		str[tail++] = str[i];
		checkers[str[i]] = true;
	    }
	}

	if (tail < len) {str[tail] = 0;}
    }

    
    /**
     * 1.4 Write a method to decide if two strings are anagrams or not.
     *
     * This method implemented using sort.
     *
     * Returns true if two strings are anagrams. Return false otherwise.
     * @param str1 first string to be compared
     * @parma str2 second string to be compared
     * @return true if two strings are anagrams
     */
    public static boolean isAnagram(String str1, String str2) {
	if (str1 == null || str2 == null)   { return false; }
	if (str1.length() != str2.length()) { return false; }
	
	char[] char1 = str1.toCharArray();
	char[] char2 = str2.toCharArray();
	Arrays.sort(char1);
	Arrays.sort(char2);

	for (int i = 0; i < char1.length; i++) {
	    if (char1[i] != char2[i]) {return false;}
	}

	return true;
    }

    /**
     * Same problem as above but implemented by counting times of 
     * each character that occurs in two strings.
     */
    public static boolean isAnagram2(String str1, String str2) {
	if (str1 == null || str2 == null)   { return false; }
	if (str1.length() != str2.length()) { return false; }
	
	int[] count = new int[256];
	int numUnique = 0;
	int numComplete = 0;
	
	for (int i = 0; i < str1.length(); i++) {
	    if (count[str1.charAt(i)] == 0) {numUnique++;}
	    count[str1.charAt(i)]++;
	}

	for (int i = 0; i < str2.length(); i++) {
	    int cur = str2.charAt(i);
	    if (count[cur] == 0) {return false;}
	    count[cur]--;
	    
	    if (count[cur] == 0) {
		numComplete++;
		if (numComplete == numUnique) {return i == str2.length() - 1;}
	    }
	}

	return false;
    }

    /**
     * 1.6 Given an image represented by an NxN matrix, where each pixel in the image
     * is 4 bytes, write a method to rotate the image by 90 degrees. Can you do it
     * in place?
     *
     */
    public static void rotateImage90(int[][] image) {
	assert image != null && (image.length == 0 || image.length == image[0].length);

	int n = image.length;
	
	for (int layer = 0; layer < n / 2; layer++) {
	    int first = layer;
	    int last = n - 1 - layer;
	    for (int i = first; i < last; i++) {
		int offset = i - first;
		int top = image[first][i]; // save top
		image[first][i] = image[last - offset][first]; // left -> top
		image[last - offset][first] = image[last][last - offset]; // bottom -> left
		image[last][last - offset] = image[i][last]; // right -> bottom
		image[i][last] = top;
	    }
	}
    }


    /**
     * 1.7 Write an algorithm such that if an element in an MxN matrix is 0, its entire row 
     * and column is set to 0
     *
     * Notice that here we implement it in-place. Actually we can do it by using two
     * additional arrays, which will make our code much cleaner.
     *
     * This method set entire row and column to zero's if there is 0 in the row / column.
     * @param matrix the matrix to be manipulated
     */
    public static void setZeros(int[][] matrix) {
	if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {return;}

	int m = matrix.length;
	int n = matrix[0].length;
	boolean top = false;
	boolean left = false;
	
	for (int i = 0; i < n; i++) {
	    if (matrix[0][i] == 0) {
		top = true;
		break;
	    }
	}
	
	for (int i = 0; i < m; i++) {
	    if (matrix[i][0] == 0) {
		left = true;
		break;
	    }
	}

	for (int i = 1; i < m; i++) {
	    for (int j = 1; j < n; j++) {
		if (matrix[i][j] == 0) {
		    matrix[0][j] = 0;
		    matrix[i][0] = 0;
		}
	    }
	}

	for (int i = 1; i < n; i++) {
	    if (matrix[0][i] == 0) {
		for (int j = 1; j < m; j++){
		    matrix[j][i] = 0;
		}
	    }
	}

	for (int i = 1; i < m; i++){
	    if (matrix[i][0] == 0) {
		for (int j = 1; j < n; j++) {
		    matrix[i][j] = 0;
		}
	    }
	}

	if (top) {
	    for (int i = 0; i < n; i++) {
		matrix[0][i] = 0;
	    }
	}

	if (left) {
	    for (int i = 0; i < m; i++) {
		matrix[i][0] = 0;
	    }
	}
    }

    /**
     * 1.8 Assume you have a method isSubstring which checks if one word is 
     * a substring of another. Given two strings, s1 and s2, write code to 
     * check if s2 is a rotation of s1 using only one cal to isSubstirng.
     * (i.e., "waterbottle" is a rotation of "erbottlewat")
     * 
     * Idea here is that check:
     * 1. whether two strings has the same length
     * 2. whether if s1 is a substring of (s2 + s2)
     *
     * Returns true if two strings are rotations of one another
     * @param s1 first string to be compared
     * @param s2 second string to be compared
     * @return true if two strings are rotations of one another
     */
    public static boolean isRotation(String s1, String s2) {
	if (s1 == null || s2 == null)                       {return false;}
	if (s1.length() != s2.length() || s1.length() == 0) {return false;}

	return isSubstring(s2 + s2, s1);
    }

    /**
     * Returns true if second string is substring of the first one
     * @param s1 the first string
     * @param s2 the second string
     * @return true if second string is substring of the first one
     */
    public static boolean isSubstring(String s1, String s2) {
	if (s1 == null || s2 == null) {return false;}
	return s1.indexOf(s2) >= 0;
    }

    


    public static void main(String[] args) {
	String s1 = "waterbottle";
	String s2 = "erbottlewat";
	
	System.out.println(isRotation(s1, s2));
    }

}