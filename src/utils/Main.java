package utils;
import java.util.List;

import model.Binaries;
import model.CannotSimplifyException;


public class Main {

	public static void main(String[] args) {

//TESTS sans string
//		Binaries bin = new Binaries();
//		bin.addBinary(new Binary("IO"));
//		bin.addBinary(new Binary("II"));
//		bin.addBinary(new Binary("OO"));
//		bin.addBinary(new Binary("OI"));
//		
//		System.out.println(bin);
//		Binaries newBin = McCluskeySimplify(bin);
//		System.out.println(newBin);
		
//TESTS avec string
		String test = "ab+-ab+-a-b";
		String result = McCluskeySimplifyStrings(test);
		System.out.println(result);
		
		
	}
	
	public static String McCluskeySimplifyStrings(String test) {
		int dimension = Converter.stringToBinariesDimension(test);
		Binaries bins = Converter.stringToBinaries(test,dimension);
		
		Binaries simplifiedMinTerms = McCluskeySimplify(bins,dimension);
		
		String result = Converter.binariesToString(simplifiedMinTerms,dimension);
		
		return result;
	}
	
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
