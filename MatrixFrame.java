/**
 * 
 */
package row_operations_tool;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * @author San Chau
 *
 */
public class MatrixFrame extends JFrame {
	
	private final int rows;
	private final int columns;
	private ArrayList<String[][]> history = new ArrayList<String[][]>();
	private int cur = -1;
	private MatrixPanel matrixPanel;
	private OperationPanel opPanel = new OperationPanel();
	
	public MatrixFrame(String title, int length, int width, int[][] matrix, int rows, int columns) {
		
		super(title);
		this.rows = rows;
		this.columns = columns;

		setSize(length, width);
		setLayout(new BorderLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(matrixPanel = new MatrixPanel(matrix), BorderLayout.CENTER);
		add(opPanel, BorderLayout.SOUTH);
		addToHistory();
	}
	
	private class MatrixPanel extends JPanel {
		
		private JLabel[][] matrix;
		
		public MatrixPanel(int[][] matrix) {
			
			this.matrix = new JLabel[rows][columns];
			
			setLayout(new GridLayout(rows, columns));
			
			for(int row = 0; row < rows; row++) {
				for(int column = 0; column < columns; column++) {
					JPanel matrixSubPanel = new JPanel();
					matrixSubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
					this.matrix[row][column] = new JLabel(Integer.toString(matrix[row][column]));
					matrixSubPanel.add(this.matrix[row][column]);
					add(matrixSubPanel);
				}
			}
		}
	}
	
	private class OperationPanel extends JPanel implements ActionListener {
		
		private final JButton addButton = new JButton("Add Rows"), 
				scaleButton = new JButton("Scale Row"), 
				switchButton = new JButton("Switch Rows"), 
				undoButton = new JButton("Undo"), 
				redoButton = new JButton("Redo");
		
		public OperationPanel() {
			
			setLayout(new GridLayout(2, 1));
			
			JPanel topPanel = new JPanel();
			topPanel.add(addButton);
			topPanel.add(scaleButton);
			topPanel.add(switchButton);
			
			JPanel bottomPanel = new JPanel();
			bottomPanel.add(undoButton);
			bottomPanel.add(redoButton);
			
			addButton.addActionListener(this);
			scaleButton.addActionListener(this);
			switchButton.addActionListener(this);
			undoButton.addActionListener(this);
			redoButton.addActionListener(this);
			
			add(topPanel);
			add(bottomPanel);
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
			if(event.getSource() == undoButton) {
				undo();
			}
			if(event.getSource() == redoButton) {
				redo();
			}
		}
	}
	
	public void addToHistory() {
		String[][] matrixArr = new String[rows][columns];
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				matrixArr[r][c] = matrixPanel.matrix[r][c].getText();
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
	
	public void addToRow(int addeeRow, int adderRow, int multiplier) {
		fixHistory();
		for(int c = 0; c < columns; c++) {
			String sum;
			try {
				int addee = Integer.parseInt(matrixPanel.matrix[addeeRow][c].getText());
				int adder = Integer.parseInt(matrixPanel.matrix[adderRow][c].getText());
				sum = Integer.toString(addee + (multiplier * adder));
			}
			catch(Exception e) {
				String addee = matrixPanel.matrix[addeeRow][c].getText();
				String adder = matrixPanel.matrix[adderRow][c].getText();
				int[] addeeFraction = getNumAndDen(addee);
				int[] adderFraction = getNumAndDen(adder);
				int lcm = getLCM(addeeFraction[1], adderFraction[1]);
				addeeFraction[0] *= (lcm / addeeFraction[1]);
				addeeFraction[1] = lcm;
				adderFraction[0] *= (lcm / adderFraction[1]);
				adderFraction[0] *= multiplier;
				adderFraction[1] = lcm;
				sum = String.format("%s/%s", addeeFraction[0] + adderFraction[0], lcm);
				sum = reduceFraction(getNumAndDen(sum));
			}
			matrixPanel.matrix[addeeRow][c].setText(sum);
		}
		addToHistory();
	}
	
	public void multiplyRow(int row, int multiplier) {
		fixHistory();
		for(int c = 0; c < columns; c++) {
			String product;
			try {
				int multiplicand = Integer.parseInt(matrixPanel.matrix[row][c].getText());
				product = Integer.toString(multiplicand * multiplier);
			}
			catch(Exception e) {
				String multiplicand = matrixPanel.matrix[row][c].getText();
				int[] arr = getNumAndDen(multiplicand);
				arr[0] *= multiplier;
				product = String.format("%s/%s", arr[0], arr[1]);
				product = reduceFraction(getNumAndDen(product));
			}
			matrixPanel.matrix[row][c].setText(product);
		}
		fixHistory();
		addToHistory();
	}
	
	public void divideRow(int row, int divisor) {
		fixHistory();
		for(int c = 0; c < columns; c++) {
			String quotient;
			try {
				int dividend = Integer.parseInt(matrixPanel.matrix[row][c].getText());
				if(((double)dividend / (double)divisor) != (double)(dividend / divisor)) {
					quotient = String.format("%s/%s", dividend, divisor);
					quotient = reduceFraction(getNumAndDen(quotient));
				}
				else
					quotient = Integer.toString(dividend / divisor);
			}
			catch(Exception e) {
				String dividend = matrixPanel.matrix[row][c].getText();
				int[] arr = getNumAndDen(dividend);
				arr[1] *= divisor;
				quotient = String.format("%s/%s", arr[0], arr[1]);
				quotient = reduceFraction(getNumAndDen(quotient));
			}
			matrixPanel.matrix[row][c].setText(quotient);
		}
		fixHistory();
		addToHistory();
	}
	
	public void switchRow(int row1, int row2) {
		fixHistory();
		for(int c = 0; c < columns; c++) {
			int newRow1Element = Integer.parseInt(matrixPanel.matrix[row2][c].getText());
			int newRow2Element = Integer.parseInt(matrixPanel.matrix[row1][c].getText());
			matrixPanel.matrix[row1][c].setText(Integer.toString(newRow1Element));
			matrixPanel.matrix[row2][c].setText(Integer.toString(newRow2Element));
		}
		fixHistory();
		addToHistory();
	}
	
	public void redo() {
		try {
			cur++;
			for(int r = 0; r < rows; r++) {
				for(int c = 0; c < columns; c++) {
					matrixPanel.matrix[r][c].setText(history.get(cur)[r][c]);
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
					matrixPanel.matrix[r][c].setText(history.get(cur)[r][c]);
				}
			}		
		}
		catch(Exception e) {
			cur++;
		};
	}
	
	public int[] getNumAndDen(String fraction) {
		int[] arr = new int[2];
		try {
			int count = 0;
			String num = "";
			String den = "";
			for(int i = 0; i < fraction.length(); i++) {
				if(Character.isDigit(fraction.charAt(i))) {
					num += fraction.charAt(i);
				}
				else {
					count = i;
					break;
				}
			}
			arr[0] = Integer.parseInt(num);
			for(int i = count + 1; i < fraction.length(); i++) {
				if(Character.isDigit(fraction.charAt(i))) {
					den += fraction.charAt(i);
				}
				else
					break;
			}
			arr[1] = Integer.parseInt(den);
		}
		catch(Exception e) {
			arr[0] = Integer.parseInt(fraction);
			arr[1] = 1;
		}
		return arr;
	}
	
	public String reduceFraction(int[] arr) {
		for(int i = arr[0]; i > 0; i--) {
			if(arr[0] % i == 0 && arr[1] % i == 0) {
				arr[0] /= i;
				arr[1] /= i;
				break;
			}
		}
		if(arr[1] == 1)
			return Integer.toString(arr[0]);
		return String.format("%s/%s", arr[0], arr[1]);
	}
	
	public int getLCM(int den1, int den2) {
		int min = Math.min(den1, den2);
		int max = Math.max(den1, den2);
		for(int i = 1; i <= max; i++) {
			if((i * min) % max == 0) {
				return i * min;
			}
		}
		return (min * max);
	}
}
