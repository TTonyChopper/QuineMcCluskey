package model;
import java.util.ArrayList;
import java.util.List;

public class Binaries{
	
	private List<Binary> bins;

	public Binaries() {
		super();	
		bins = new ArrayList<Binary>(); 
	}
	
	public Binaries(List<Binary> bins) {
		super();
		this.bins = bins;
	}

	public List<Binary> getBins() {
		return bins;
	}

	public void setBins(List<Binary> bins) {
		this.bins = bins;
	}
	
	public int size() {
		return this.bins.size();
	}
	
	public void addBinary(Binary bin) {
		if (!this.bins.contains(bin)) {
			this.bins.add(bin);
		}
	}
	
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
