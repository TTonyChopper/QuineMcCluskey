package model;
import java.util.ArrayList;
import java.util.List;

import exception.InvalidMinTermException;

/**
 * Binary word,minterm,represented by enum binaryValue
 * 
 * @author anthony
 *
 */
public class Binary implements Comparable<Binary>{
	
	public static enum binaryValue{O('O'),I('I'),X('X');
	
		private char c;
	
		private binaryValue(char c) {
			this.c = c;
		}
		
		public char toChar() {
			return c;
		}
	}
	
	private List<binaryValue> bin;

	public Binary() {
		super();	
		bin = new ArrayList<binaryValue>();
	}
	
	public Binary(List<binaryValue> bin) {
		super();
		this.bin = bin;
	}
	
	public Binary(Binary binToClone) {
		super();	
		bin = new ArrayList<binaryValue>();
		
		for (binaryValue binValue : binToClone.getBin()) {
			bin.add(binValue);
		}
	}
	
	public Binary(String s) {
		super();
		bin = new ArrayList<binaryValue>();
		for (int i=0;i<s.length();i++) {
			switch (s.charAt(i)) {
				default :
				case 'O' :
					bin.add(binaryValue.O);
					break;
				case 'I' :
					bin.add(binaryValue.I);
					break;
				case 'X' :
					bin.add(binaryValue.X);
					break;
			}
		}
	}
	
	public Binary(binaryValue ...args) {
		super();
		bin = new ArrayList<binaryValue>();
		for (binaryValue arg : args) {
			bin.add(arg);
		}
	}

	public List<binaryValue> getBin() {
		return bin;
	}

	public void setBin(List<binaryValue> bin) {
		this.bin = bin;
	}
	
	public void addBin(binaryValue binValue){
		this.bin.add(binValue);
	}
	
	/**
	 * Sets the given binaryValue to X
	 * 
	 * @param index index of the word to neutralize
	 */
	public void neutralizeBit(int index){
		this.bin.set(index,binaryValue.X);
	}
	
	/**
	 * 
	 * @return the number of zero contained in the minterm
	 */
	public int zeroContained() {
		int result = 0;
		for (binaryValue binValue : bin) {
			if (binValue == binaryValue.O) result++;
		}
		return result;
	}
	
	/**
	 * 
	 * @return the number of zero contained in the minterm
	 */
	public int oneContained() {
		int result = 0;
		for (binaryValue binValue : bin) {
			if (binValue == binaryValue.I) result++;
		}
		return result;
	}
	
	/**
	 * 
	 * @param bin bin to convert to decimal value
	 * @return the decimal value of the bin
	 */
	public static int convertFromBin(Binary bin){
		int result = 0;
		int size = bin.getBin().size();
		
		for (int i=0;i<size;i++) {
			int binValue = (bin.getBin().get(i)==Binary.binaryValue.O) ? 0:1;
			result += binValue * Math.pow(2,(size-i-1));
		}
		return result;
	}
	
	//TODO
	/*
	public static int shift(int value,int right) {
		for (int i=0;i<right;i++) {
			value = value >> 1;
		}
		return value;
	}*/
	
	//TODO
	/*
	public static Binary convertToBin(Integer val){
		return new Binary();
	}*/
	
	/**
	 * 
	 * @param dimension
	 * @return
	 * @throws InvalidMinTermException
	 */
	public MinTermString toMinTermString() {		
		int dimension = bin.size();
		int zero = zeroContained();
		int one = oneContained();
		
		char[] minterm = new char[2*zero+one];
		char letter = 'a';
		
		for(int i=0,j=0;i<dimension;i++,letter++){
			switch (bin.get(i)) {
				case O :
					minterm[j++] = '-';
				case I :
					minterm[j++] = letter;
					break;
				case X :
				default :
					break;
			}
		}
		return new MinTermString(minterm);
	}

	@Override
	public String toString() {
		char[] chars = new char[bin.size()];
		for (int i=0;i<chars.length;i++) {
			chars[i] = bin.get(i).toChar();
		}
		return new String(chars);
	}

	@Override
	public int compareTo(Binary arg0) {
		return convertFromBin(this)-convertFromBin(arg0);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bin == null) ? 0 : bin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Binary other = (Binary) obj;
		if (bin == null) {
			if (other.bin != null)
				return false;
		} else if (!bin.equals(other.bin))
			return false;
		return true;
	}
}