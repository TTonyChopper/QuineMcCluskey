package utils;
import java.util.ArrayList;
import java.util.List;

import model.Binaries;
import model.Binary;
import model.MinTermString;
import exception.CannotParseException;
import exception.InvalidMinTermException;

/**
 * Does the converting betwen the different formats
 * 
 * @author anthony
 *
 */
public class Converter {
	
	/**
	 * 
	 * @param b minterm in a String
	 * @return the length of the minterm
	 * @throws CannotParseException thrown when the String format is invalid
	 * only letters are accepted [a-z] or - to negate a bit
	 */
	public static int countLetters(String b) throws CannotParseException {
		int length = 0;
		int lengthCandidate = 0;
		
		for (int i=0;i<b.length();i++) {
			if (((b.charAt(i)=='-')&&(i<b.length()-1))&&(b.substring(i+1,i+2).matches("[a-z]"))) {
				i++;
				lengthCandidate = b.charAt(i) - 'a' + 1;
			}
			else if (b.substring(i,i+1).matches("[a-z]")) lengthCandidate = b.charAt(i) - 'a' + 1;
			else throw new CannotParseException("Cannot parse!");
			if (lengthCandidate > length) length = lengthCandidate;
		}
		
		if (length == 0) throw new CannotParseException("Cannot parse!");
		
		return length;
	}
	
	/**
	 * 
	 * @param b minterms in a String array
	 * @return the max length of the minterms
	 * @throws CannotParseException
	 */
	public static int countLetters(String[] b) throws CannotParseException {
		int length = 0;
		
		for (int i=0;i<b.length;i++) {
			int l = countLetters(b[i]);
			if (l > length) length = l; 
		}
		
		return length;
	}
	
	/**
	 * 
	 * @param s minterms in String form
	 * @param dimension max length of the minterms
	 * @return the minterms in Binaries form
	 * @throws InvalidMinTermException 
	 */
	public static Binaries stringToBinaries(String[] s,int dimension) throws InvalidMinTermException {
		List<Binary> binaries = new ArrayList<Binary>();
		
		for (String binaryWord : s) {
			MinTermString mts = new MinTermString(binaryWord.toCharArray());
			binaries.add(mts.toBinary(dimension));
		}
		
		return new Binaries(binaries);
	}
	
	/**
	 * 
	 * @param b minterms in Binaries form
	 * @return the minterms in String form
	 */
	public static String binariesToString(Binaries b) {
		StringBuffer sb = new StringBuffer();
		
		for (Binary bin : b.getBins()) {
			if (bin.isOne()) {
				return "1";
			}
		}
		
		for (int i=0;i<b.size();i++) {
			if (i>0) sb.append("+");
			sb.append(b.getBins().get(i).toMinTermString().toString());
		}
		
		return sb.toString();
	}

	/**
	 * Specific Dimension 2
	 * 
	 * @param s minterms in String form
	 * @return the minterms in Binaries form
	 */
	@Deprecated
	public static Binaries stringToBinariesDim2(String s) {
		Binaries result = new Binaries();
		
		Binary bin = null;
		String[] binaryWords = s.split("\\+");
		for (String binaryWord : binaryWords) {
			switch (binaryWord) {
				case "ba" :
				case "ab" :
					bin = new Binary("II");
					break;
				case "b-a" :
				case "-ab" :
					bin = new Binary("OI");
					break;
				case "-ba" :
				case "a-b" :
					bin = new Binary("IO");
					break;
				case "-b-a" :
				case "-a-b" :
					bin = new Binary("OO");
					break;
				default : break;
			}
			if (bin != null) result.addBinary(bin);
		}
		
		return result;
	}
	
	/**
	 * Specific Dimension 2
	 * 
	 * @param b minterms in Binaries form
	 * @return the minterms in String form
	 */
	@Deprecated
	public static String binariesToStringDim2(Binaries b) {
		StringBuffer result = new StringBuffer();
		
		char[] letters = {'a','b'};
		
		for (int i=0;i<b.size();i++) {
			if (i>0) result.append('+');
			Binary bin = b.getBins().get(i);
			for (int j=0;j<2;j++) {
				if (bin.getBin().get(j) == Binary.binaryValue.O) result.append('-');
				if (bin.getBin().get(j) != Binary.binaryValue.X) result.append(letters[j]);
			}
		}
		
		return result.toString();
	}
}
