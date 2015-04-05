package utils;
import java.util.List;

import model.Binaries;
import exception.CannotParseException;
import exception.CannotSimplifyException;
import exception.InvalidMinTermException;

/**
 * Main class
 * 
 * @author anthony
 *
 */
public class Main {

	/*
	 * Entry point 
	 */
	public static void main(String[] args) {

//TESTS sans string
//		Binaries bin = new Binaries();
//		bin.addBinary(new Binary("IOXII"));
//		bin.addBinary(new Binary("IXIOX"));
//		bin.addBinary(new Binary("OOXXX"));
//		bin.addBinary(new Binary("OIOXX"));
//		
//		System.out.println(bin);
//		Binaries newBin = McCluskeySimplify(bin);
//		System.out.println(newBin);
		
//TESTS avec string
		String test = "b-ac+-cb-a+-a-bc+-b-c-a+-g";
		String result = null;
		try {
			result = McCluskeySimplifyStrings(test);
		} catch (CannotParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidMinTermException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
	}
	
	/**
	 * 
	 * @param test
	 * @return
	 * @throws CannotParseException 
	 * @throws InvalidMinTermException 
	 */
	public static String McCluskeySimplifyStrings(String test) throws CannotParseException, InvalidMinTermException {
		String[] binaryWords = test.split("\\+");
		
		int dimension = Converter.countLetters(binaryWords);
		Binaries bins = Converter.stringToBinaries(binaryWords,dimension);
		
		Binaries simplifiedMinTerms = McCluskeySimplify(bins,dimension);
		
		String result = Converter.binariesToString(simplifiedMinTerms);
		
		return result;
	}
	
	/**
	 * 
	 * @param bins
	 * @param dimension
	 * @return
	 */
	public static Binaries McCluskeySimplify(Binaries bins,int dimension) {
		List<Binaries> sortedBins = Computer.sortMinTerms(bins,dimension);
		Binaries simplifiedMinTerms = bins;
		boolean stop = false;
		
		do {
			try {
				simplifiedMinTerms = Computer.simplifyMinTerms(sortedBins,dimension);
				sortedBins = Computer.sortMinTerms(simplifiedMinTerms,dimension);
			} catch (CannotSimplifyException e) {
				stop = true;
			}		
		} while(!stop);
		
		return simplifiedMinTerms;
	}
	
}
