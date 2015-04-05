package model;
import java.util.ArrayList;
import java.util.List;

/**
 * Binary words in a List, minterms of the function
 * 
 * @author anthony
 *
 */
public class Binaries{
	
	private List<Binary> bins;

	public Binaries() {
		super();	
		bins = new ArrayList<Binary>(); 
	}
	
	public Binaries(List<Binary> bins) {
		this();
		for (Binary bin : bins) {
			addBinary(bin);
		}
	}

	public List<Binary> getBins() {
		return bins;
	}

	public void setBins(List<Binary> bins) {
		this.bins = bins;
	}
	
	/**
	 * 
	 * @return the size of the list.
	 */
	public int size() {
		return this.bins.size();
	}
	
	/**
	 * Adds a minterm to the function
	 * 
	 * @param bin a binary to add to the binary list.
	 * Only added if distinct.
	 */
	public void addBinary(Binary bin) {
		if (!this.bins.contains(bin)) {
			this.bins.add(bin);
		}
	}
	
	/**
	 * Adds minterms to the function
	 * 
	 * @param bins binary to add to the binary list.
	 * Each only added if distinct.
	 */
	public void addBinaries(List<Binary> bins) {
		for (Binary bin : bins) {
			addBinary(bin);
		}
	}

	@Override
	public String toString() {
		return "Binaries [bins=" + bins + "]";
	}
}
