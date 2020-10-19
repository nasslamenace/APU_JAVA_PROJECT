package APUJavaProject;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class MyTable extends JTable {
	
	public MyTable() {
  	  this.setOpaque(true);
  	  this.setBackground(new Color(61, 97, 166));
  	  this.setFillsViewportHeight(true);
  	  this.setForeground(Color.white);
  	  this.setSelectionBackground(Color.black);
  	  this.setFont(new Font("ChalkBoard", Font.BOLD, 13));
  	  this.getTableHeader().setOpaque(true);
  	  this.getTableHeader().setBackground(Color.white);
  	  this.getTableHeader().setForeground(new Color(61, 97, 166));
  	  this.getTableHeader().setFont(new Font("ChalkBoard", Font.ITALIC, 15));
		
	}

	public MyTable(AbstractTableModel model) {
		super(model);
	 	this.setOpaque(true);
	    this.setBackground(Color.white);
	    this.setGridColor(new Color(61, 97, 166));
	    this.setFillsViewportHeight(true);
	    this.setForeground(new Color(61, 97, 166));
	    this.setSelectionBackground(Color.black);
	 	this.setFont(new Font("ChalkBoard", Font.BOLD, 13));
	  	this.getTableHeader().setOpaque(true);
	  	this.getTableHeader().setBackground(new Color(61, 97, 166));
	  	this.getTableHeader().setForeground(Color.white);
	  	this.getTableHeader().setFont(new Font("ChalkBoard", Font.ITALIC, 15));
	}
	
	static void applyDesign( JTable table) {
		table.setOpaque(true);
		table.setBackground(Color.white);
		table.setGridColor(new Color(61, 97, 166));
		table.setFillsViewportHeight(true);
		table.setForeground(new Color(61, 97, 166));
		table.setSelectionBackground(Color.black);
		table.setFont(new Font("ChalkBoard", Font.BOLD, 13));
		table.getTableHeader().setOpaque(true);
		table.getTableHeader().setBackground(new Color(61, 97, 166));
		table.getTableHeader().setForeground(Color.white);
	  	table.getTableHeader().setFont(new Font("ChalkBoard", Font.ITALIC, 15));
		
		
	}

}
