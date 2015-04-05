package view;

import java.awt.Insets;

import javax.swing.JFrame;

public class Frame extends JFrame {
	 /**
	 * 
	 */
	private static final long serialVersionUID = -8546443063801430643L;
	private int margin;
	
	 public Frame()
	 {	 
		 super("Quine McCluskey Algorithm");
	 }
	 
	 public Insets getInsets()
	 {
		 Insets in = super.getInsets();
		 return new Insets(in.top+margin,in.left+margin,in.bottom+margin,in.right+margin);
	 }
	 
	 public void setMargin(int m)
	 {
		 margin=m;
	 }
}
