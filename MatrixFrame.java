/**
 * Please do not steal.
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
public class MatrixFrame extends JFrame implements ActionListener {
	
	private final int ROWS;
	private final int COLUMNS;
	private JLabel[][] JLMatrix;
	private final ArrayList<String[][]> HISTORY = new ArrayList<String[][]>();
	private int cur = -1;
	private final JButton 
			ADD_BUTTON = new JButton("Add Rows"), 
			SCALE_BUTTON = new JButton("Scale Row"), 
			SWITCH_BUTTON = new JButton("Switch Rows"), 
			UNDO_BUTTON = new JButton("Undo"), 
			REDO_BUTTON = new JButton("Redo"),  
			NEW_MATRIX_BUTTON = new JButton("New Matrix");
	
	
	public MatrixFrame(String title, int length, int width, Number[][] matrix, int rows, int columns) {
		
		super(title);
		this.ROWS = rows;
		this.COLUMNS = columns;
		MatrixPanel matrixPanel = new MatrixPanel(matrix);
		OperationPanel opPanel =  new OperationPanel();

		setSize(length, width);
		setLayout(new BorderLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ADD_BUTTON.addActionListener(this);
		SCALE_BUTTON.addActionListener(this);
		SWITCH_BUTTON.addActionListener(this);
		UNDO_BUTTON.addActionListener(this);
		REDO_BUTTON.addActionListener(this);
		NEW_MATRIX_BUTTON.addActionListener(this);
		
		add(matrixPanel, BorderLayout.CENTER);
		add(opPanel, BorderLayout.SOUTH);
		addToHistory();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == ADD_BUTTON) 
			new AdditionFrame("Add", 400, 300, ROWS, this);
		if(event.getSource() == SCALE_BUTTON)
			new ScaleFrame("Scale", 400, 300, ROWS, this);
		if(event.getSource() == SWITCH_BUTTON) 
			new SwitchFrame("Switch", 400, 300, ROWS, this);
		if(event.getSource() == UNDO_BUTTON) 
			undo();
		if(event.getSource() == REDO_BUTTON) 
			redo();
		if(event.getSource() == NEW_MATRIX_BUTTON) {
			new InfoFrame("Row Operations Tool", 300, 100);
			dispose();
		}
	}
	
	private void addToHistory() {
		String[][] matrixArr = new String[ROWS][COLUMNS];
		for(int r = 0; r < ROWS; r++) {
			for(int c = 0; c < COLUMNS; c++) {
				matrixArr[r][c] = JLMatrix[r][c].getText();
			}
		}
		HISTORY.add(matrixArr);
		cur++;
	}
	
	private void fixHistory() {
		for(int i = HISTORY.size() - 1; i > cur; i--) {
			HISTORY.remove(i);
		}
	}
	
	public void addToRow(int addeeRow, int adderRow, Number multiplier) throws Exception {
		fixHistory();
		for(int c = 0; c < COLUMNS; c++) {
			Number addee = Number.parseNumber(JLMatrix[addeeRow][c].getText());
			Number adder = Number.parseNumber(JLMatrix[adderRow][c].getText());
			JLMatrix[addeeRow][c].setText(addee.add(adder.multiply(multiplier)).toString());
		}
		addToHistory();
	}
	
	public void multiplyRow(int row, Number multiplier) throws Exception {
		fixHistory();
		for(int c = 0; c < COLUMNS; c++) {
			Number multiplicand = Number.parseNumber(JLMatrix[row][c].getText());
			JLMatrix[row][c].setText(multiplicand.multiply(multiplier).toString());
		}
		addToHistory();
	}
	
	public void divideRow(int row, Number divisor) throws Exception {
		fixHistory();
		for(int c = 0; c < COLUMNS; c++) {
			Number dividend = Number.parseNumber(JLMatrix[row][c].getText());

			JLMatrix[row][c].setText(dividend.divide(divisor).toString());
		}
		addToHistory();
	}
	
	public void switchRow(int row1, int row2) throws Exception {
		fixHistory();
		for(int c = 0; c < COLUMNS; c++) {
			Number newRow1Element = Number.parseNumber(JLMatrix[row2][c].getText());
			Number newRow2Element = Number.parseNumber(JLMatrix[row1][c].getText());
			JLMatrix[row1][c].setText(newRow1Element.toString());
			JLMatrix[row2][c].setText(newRow2Element.toString());
		}
		addToHistory();
	}
	
	private void redo() {
		try {
			cur++;
			for(int r = 0; r < ROWS; r++) {
				for(int c = 0; c < COLUMNS; c++) {
					JLMatrix[r][c].setText(HISTORY.get(cur)[r][c]);
				}
			}
		}
		catch(Exception e) {
			cur--;
		};
	}
	
	private void undo() {
		try {
			cur--;
			for(int r = 0; r < ROWS; r++) {
				for(int c = 0; c < COLUMNS; c++) {
					JLMatrix[r][c].setText(HISTORY.get(cur)[r][c]);
				}
			}		
		}
		catch(Exception e) {
			cur++;
		};
	}
	
	private class MatrixPanel extends JPanel {
		public MatrixPanel(Number[][] matrix) {
			JLMatrix = new JLabel[ROWS][COLUMNS];
			setLayout(new GridLayout(ROWS, COLUMNS));
			for(int r = 0; r < ROWS; r++) {
				for(int c = 0; c < COLUMNS; c++) {
					JPanel matrixSubPanel = new JPanel();
					matrixSubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
					JLMatrix[r][c] = new JLabel(matrix[r][c].toString());
					matrixSubPanel.add(JLMatrix[r][c]);
					add(matrixSubPanel);
				}
			}
		}
	}
	
	private class OperationPanel extends JPanel {
		public OperationPanel() {
			setLayout(new GridLayout(2, 1));
			JPanel topPanel = new JPanel();
			topPanel.add(ADD_BUTTON);
			topPanel.add(SCALE_BUTTON);
			topPanel.add(SWITCH_BUTTON);
			JPanel bottomPanel = new JPanel();
			bottomPanel.add(UNDO_BUTTON);
			bottomPanel.add(REDO_BUTTON);
			bottomPanel.add(NEW_MATRIX_BUTTON);
			add(topPanel);
			add(bottomPanel);
		}
	}
}
