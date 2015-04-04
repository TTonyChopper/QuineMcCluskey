package utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Binaries;
import model.Binary;
import model.CannotSimplifyException;


public class Computer {
	
	public static boolean isSimpl(Binary.binaryValue bin1,Binary.binaryValue bin2) {
		return ((bin1 == Binary.binaryValue.O ) && (bin2 == Binary.binaryValue.I ))||((bin1 == Binary.binaryValue.I ) && (bin2 == Binary.binaryValue.O));
	}
	
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
	
	public static Binaries simplifyMinTerms(List<Binaries> sortedBins,int dimension) throws CannotSimplifyException{
		Binaries simplifiedMinTerms = new Binaries();
		boolean isSimplified = false;
		
		for (int i = sortedBins.size()-1;sortedBins.get(i).getBins().isEmpty();i--) {
			sortedBins.remove(i);
		}
		
		for (int i=0;i<sortedBins.size()-1;i++) {
			for(Binary bin1 : sortedBins.get(i).getBins()) {
				Binaries nextBinaries = sortedBins.get(i+1);
				List<Binary> simplifiedBin = simplifyWithBinaries(bin1,nextBinaries,dimension);
				if (simplifiedBin != null) {
					simplifiedMinTerms.addBinaries(simplifiedBin);
					isSimplified = true;
				}else {
					simplifiedMinTerms.addBinary(bin1);
				}
			}
		}
		
		if (!isSimplified) throw new CannotSimplifyException();
		
		return simplifiedMinTerms;
	}	
}
