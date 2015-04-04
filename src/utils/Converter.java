package utils;
import model.Binaries;
import model.Binary;

public class Converter {
	
	//TODO
		/*
	public static Function stringToFunction(String s) {
		return new Function();
	}*/
	
	//TODO
		/*
	public static String functionToString(Function f) {
		return new String();
	}*/

	//TODO
		/*
	public static Function binariesToFunction(Binaries b) {
		Function result = new Function();
		for(Binary bin : b.getBins()) {
			result.addMinTerm(Binary.convertFromBin(bin));
		}
		return result;
	}*/

	//TODO
	/*
	public static Binaries functionToBinaries(Function f) {
		Binaries result = new Binaries();

		for(Integer val : f.getMinTerms()) {
			result.addBinary(Binary.convertToBin(val));
		}
		return result;
	}*/
	
	public static int stringToBinariesDimension(String s) {
		return 2;
	}
	
	public static Binaries stringToBinaries(String s,int dimension) {
		return stringToBinariesDim2(s);
	}
	
	public static String binariesToString(Binaries b,int dimension) {
		return binariesToStringDim2(b);
	}
	
	//Specific Dimension 2
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
	
	//Specific Dimension 2
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
