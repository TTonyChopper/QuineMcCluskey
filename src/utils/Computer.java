package utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Binaries;
import model.Binary;
import exception.CannotParseException;
import exception.CannotSimplifyException;
import exception.InvalidMinTermException;

/**
 * Does the computing tasks
 * 
 * @author anthony
 *
 */
public class Computer {
	
	/**
	 * 
	 * @param bin1 binary one
	 * @param bin2 binary two
	 * @return true if one of them is 0 and the other 1
	 */
	public static boolean isSimpl(Binary.binaryValue bin1,Binary.binaryValue bin2) {
		return ((bin1 == Binary.binaryValue.O ) && (bin2 == Binary.binaryValue.I ))||((bin1 == Binary.binaryValue.I ) && (bin2 == Binary.binaryValue.O));
	}
	
	/**
	 * 
	 * @param bin1 binary one
	 * @param bin2 binary two
	 * @param dimension The dimension of the function
	 * @return the simplification between binary one and binary two
	 */
	public static Binary simplifyWithBinary(Binary bin1,Binary bin2,int dimension) {
		int simplCounter = 0;
		int diffCounter = 0;
		int index = 0;
		
		for(int i=0;i<dimension;i++){	
			if (bin1.getBin().get(i) != bin2.getBin().get(i)) {
				diffCounter++;
				if (isSimpl(bin1.getBin().get(i),bin2.getBin().get(i))) {
					simplCounter++;
					index = i;
				}
			}	
		}
		
		Binary newBin = null;
		
		if ((diffCounter == 1) && (simplCounter == 1)) {
			newBin = new Binary(bin1);
			newBin.neutralizeBit(index);
		}
		
		return newBin;
	}
	
	/**
	 * 
	 * @param bin1 The binary one
	 * @param bins The binaries of the next set
	 * @param dimension The dimension of the function
	 * @return the simplification between binary one and the bins from the next set
	 */
	public static List<Binary> simplifyWithBinaries(Binary bin1,Binaries bins,int dimension) {
		 List<Binary> binaryList = new ArrayList<Binary>();
		 
		 for (Binary bin2 : bins.getBins()) {
			 Binary newBin = simplifyWithBinary(bin1,bin2,dimension);
			 if (newBin != null) {
				 binaryList.add(newBin);
			 }
		 }
		 
		 if (binaryList.isEmpty()) binaryList = null;
		 
		 return binaryList;
	}
	
	/**
	 * 
	 * @param bins binaries from the function to sort
	 * @param The dimension dimension of the function
	 * @return binaries sorted in zero containing sets
	 */
	public static List<Binaries> sortMinTerms(Binaries bins,int dimension) {
		
		List<Binaries> result = new ArrayList<Binaries>(dimension+1);
		
		for (int i=0;i<dimension+1;i++) {
			result.add(new Binaries());
		}
		
		for (Binary bin : bins.getBins()) {
			result.get(dimension-bin.zeroContained()).addBinary(bin);
		}
		
		for (Binaries binsToSort : result) {
			Collections.sort(binsToSort.getBins());
		}

		return result;
	}
	
	/**
	 * 
	 * @param sortedBins Binaries sorted in zero containing sets
	 * @param dimension The dimension of the function
	 * @return the simplified Binaries
	 * @throws CannotSimplifyException thrown if the simplification cannot longer proceed
	 */
	public static Binaries simplifyMinTerms(List<Binaries> sortedBins,int dimension) throws CannotSimplifyException{
		Binaries simplifiedMinTerms = new Binaries();
		boolean isSimplified = false;
		
		for (int i = sortedBins.size()-1;sortedBins.get(i).getBins().isEmpty();i--) {
			sortedBins.remove(i);
		}
		
		for (int i=0;i<sortedBins.size();i++) {
			for(Binary bin1 : sortedBins.get(i).getBins()) {
				int next = (i == sortedBins.size()-1) ? i-1 : i+1;
				Binaries nextBinaries = sortedBins.get(next);
				List<Binary> simplifiedBin = simplifyWithBinaries(bin1,nextBinaries,dimension);
				if (simplifiedBin != null) {
					simplifiedMinTerms.addBinaries(simplifiedBin);
					isSimplified = true;
				}else {
					simplifiedMinTerms.addBinary(bin1);
				}
			}
		}
		
		if (!isSimplified) throw new CannotSimplifyException("Cannot simplify anymore!");
		
		return simplifiedMinTerms;
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
