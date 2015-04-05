package model;
import java.util.ArrayList;
import java.util.List;

/**
 * Function,represented by the decimal values of its minterms
 * 
 * @author anthony
 *
 */
public class Function {

	private List<Integer> minTerms;

	public Function() {
		super();
		minTerms = new ArrayList<Integer>();
	}
	
	public Function(List<Integer> minTerms) {
		super();
		this.minTerms = minTerms;
	}

	public List<Integer> getMinTerms() {
		return minTerms;
	}

	public void setMinTerms(List<Integer> minTerms) {
		this.minTerms = minTerms;
	}
	
	public void addMinTerm(Integer minTerm) {
		this.minTerms.add(minTerm);
	}
}
