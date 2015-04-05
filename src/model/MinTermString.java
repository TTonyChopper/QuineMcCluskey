package model;

import exception.InvalidMinTermException;

/**
 * Min Term utility class
 * 
 * @author anthony
 *
 */
public class MinTermString {
	
	private char[] chars;

	public MinTermString() {
		super();
	}
	
	public MinTermString(char[] c) {
		super();
		this.chars = c;
	}
	
	public char[] getChars() {
		return chars;
	}

	public void setChars(char[] chars) {
		this.chars = chars;
	}
	
	/**
	 * 
	 * @param c The char to look for in the minterm
	 * @return the CharPresence of this char in this minterm
	 */
	public CharPresence check(char c) {
		for (int i=0;i<chars.length;i++) {
			if (chars[i] == c) return new CharPresence(i);
		}
		return new CharPresence();
	}
	
	/**
	 * 
	 * @param c The char to count in the minterm
	 * @return the number of occurences of this char
	 */
	public int count(char c){
		int count = 0;
		for (char l : chars) {
			if (l == c) count++;
		}
		return count;
	}
	
	/**
	 * 
	 * @return true if the minterm is valid.There cannot be the same letter more than once.
	 */
	public boolean isValid() {
		for (char l : chars) {
			if ((l!='-')&&(count(l)>1)) return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param dimension of the function
	 * @return the Binary representation of the minterm
	 * @throws InvalidMinTermException if the minTerm is invalid.
	 */
	public Binary toBinary(int dimension) throws InvalidMinTermException {
		if (!isValid()) throw new InvalidMinTermException("Invalid Min Term!");
		
		char[] binary = new char[dimension];
		char letter = 'a';
		for(int i=0;i<dimension;i++,letter++){
			CharPresence cp = check(letter);
			if (cp.isPresent()) {
				int pos = cp.getPosition();
				if ((pos>0)&&(chars[pos-1]=='-')) binary[i] = 'O';
				else binary[i] = 'I';
			} else {
				binary[i] = 'X';
			}
		}
		return new Binary(new String(binary));
	}
	
	@Override
	public String toString() {
		return new String(chars);
	}
	
	class CharPresence {

		private boolean present;
		
		private int position;
		
		public CharPresence() {
			super();
			this.present = false;
		}

		public CharPresence(int position) {
			super();
			this.present = true;
			this.position = position;
		}

		public boolean isPresent() {
			return present;
		}

		public void setPresent(boolean presence) {
			this.present = presence;
		}

		public int getPosition() {
			return position;
		}

		public void setPosition(int position) {
			this.position = position;
		}	
	}
}
