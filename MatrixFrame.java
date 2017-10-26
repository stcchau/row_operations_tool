/**
 * 
 */
package matricestool;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * @author San Chau
 *
 */
public class MatrixFrame extends JFrame implements ActionListener {
	
	private JLabel[][] matrix;
	private final int rows;
	private final int columns;
	private JPanel matrixPanel = new JPanel();
	private JPanel opPanel = new JPanel();
	private final JButton addButton = new JButton("Add Rows"), 
			scaleButton = new JButton("Scale Row"), 
			switchButton = new JButton("Switch Rows"), 
			redoButton = new JButton("Redo"), 
			undoButton = new JButton("Undo");
	private ArrayList<String[][]> history = new ArrayList<String[][]>();
	private int cur = -1;
	
	public MatrixFrame(String title, int length, int width, double[][] matrix, int rows, int columns) {
		
		super(title);
		this.rows = rows;
		this.columns = columns;
		this.matrix = new JLabel[rows][columns];
		
		setSize(length, width);
		setLayout(new BorderLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		matrixPanel.setLayout(new GridLayout(rows, columns));
		opPanel.setLayout(new GridLayout(2, 1));
		
		JPanel opSubPanel1 = new JPanel();
		opSubPanel1.add(addButton);
		opSubPanel1.add(scaleButton);
		opSubPanel1.add(switchButton);
		
		JPanel opSubPanel2 = new JPanel();
		opSubPanel2.add(redoButton);
		opSubPanel2.add(undoButton);
		
		opPanel.add(opSubPanel1);
		opPanel.add(opSubPanel2);
		
		addButton.addActionListener(this);
		scaleButton.addActionListener(this);
		switchButton.addActionListener(this);
		redoButton.addActionListener(this);
		undoButton.addActionListener(this);
		
		for(int row = 0; row < rows; row++) {
			for(int column = 0; column < columns; column++) {
				JPanel matrixSubPanel = new JPanel();
				matrixSubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
				this.matrix[row][column] = new JLabel(Integer.toString((int)matrix[row][column]));
				matrixSubPanel.add(this.matrix[row][column]);
				matrixPanel.add(matrixSubPanel);
			}
		}
		
		add(matrixPanel, BorderLayout.CENTER);
		add(opPanel, BorderLayout.SOUTH);
		addToHistory();
	}
	
	public void addToHistory() {
		String[][] matrixArr = new String[rows][columns];
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				matrixArr[r][c] = matrix[r][c].getText();
			}
		}
		history.add(matrixArr);
		cur++;
	}
	
	public void fixHistory() {
		for(int i = history.size() - 1; i > cur; i--) {
			history.remove(i);
		}
	}
	
	public void addToRow(int addeeRow, int Row2, int multiplier) {
		fixHistory();
		for(int c = 0; c < columns; c++) {
			int addee = Integer.parseInt(matrix[addeeRow][c].getText());
			int adder = Integer.parseInt(matrix[Row2][c].getText());
			String sum = Integer.toString(addee + (multiplier * adder));
			matrix[addeeRow][c].setText(sum);
		}
		addToHistory();
	}
	
	public void multiplyRow(int row, int multiplier) {
		fixHistory();
		for(int c = 0; c < columns; c++) {
			int multiplicand = Integer.parseInt(matrix[row][c].getText());
			int product = multiplicand * multiplier;
			matrix[row][c].setText(Integer.toString(product));
		}
		fixHistory();
		addToHistory();
	}
	
	public void divideRow(int row, int divisor) {
		fixHistory();
		for(int c = 0; c < columns; c++) {
			int dividend = Integer.parseInt(matrix[row][c].getText());
			String quotient = Integer.toString(dividend / divisor);
			if(((double)dividend / (double)divisor) != (double)(dividend / divisor))
				quotient = String.format("%s/%s", dividend, divisor);
			matrix[row][c].setText(quotient);
		}
		fixHistory();
		addToHistory();
	}
	
	public void switchRow(int row1, int row2) {
		fixHistory();
		for(int c = 0; c < columns; c++) {
			int newRow1Element = Integer.parseInt(matrix[row2][c].getText());
			int newRow2Element = Integer.parseInt(matrix[row1][c].getText());
			matrix[row1][c].setText(Integer.toString(newRow1Element));
			matrix[row2][c].setText(Integer.toString(newRow2Element));
		}
		fixHistory();
		addToHistory();
	}
	
	public void redo() {
		try {
			cur++;
			for(int r = 0; r < rows; r++) {
				for(int c = 0; c < columns; c++) {
					matrix[r][c].setText(history.get(cur)[r][c]);
				}
			}
		}
		catch(Exception e) {
			cur--;
		};
	}
	
	public void undo() {
		try {
			cur--;
			for(int r = 0; r < rows; r++) {
				for(int c = 0; c < columns; c++) {
					matrix[r][c].setText(history.get(cur)[r][c]);
				}
			}		
		}
		catch(Exception e) {
			cur++;
		};
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == addButton) {
			new AdditionFrame("Add", 400, 300, rows);
		}
		if(event.getSource() == scaleButton) {
			new ScaleFrame("Scale", 400, 300, rows);
		}
		if(event.getSource() == switchButton) {
			new SwitchFrame("Switch", 400, 300, rows);
		}
		if(event.getSource() == redoButton) {
			redo();
		}
		if(event.getSource() == undoButton) {
			undo();
		}
	}
}
