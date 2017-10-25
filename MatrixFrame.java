/**
 * 
 */
package matricestool;

import java.awt.*;
import java.awt.event.*;

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
			undoButton = new JButton("Undo");
	
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
		opPanel.setLayout(new GridLayout(1, 3, 5, 5));
		
		opPanel.add(addButton);
		opPanel.add(scaleButton);
		opPanel.add(switchButton);
		opPanel.add(undoButton);
		
		addButton.addActionListener(this);
		scaleButton.addActionListener(this);
		switchButton.addActionListener(this);
		
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
	}
	
	public void addToRow(int addeeRow, int Row2, int multiplier) {
		for(int c = 0; c < columns; c++) {
			int addee = Integer.parseInt(matrix[addeeRow][c].getText());
			int adder = Integer.parseInt(matrix[Row2][c].getText());
			String sum = Integer.toString(addee + (multiplier * adder));
			matrix[addeeRow][c].setText(sum);
		}
	}
	public void scaleRow(int row, int divisor) {
		for(int c = 0; c < columns; c++) {
			int dividend = Integer.parseInt(matrix[row][c].getText());
			String quotient = Integer.toString(dividend / divisor);
			if(((double)dividend / (double)divisor) != (double)(dividend / divisor))
				quotient = String.format("%s/%s", dividend, divisor);
			matrix[row][c].setText(quotient);
		}
	}
	public void switchRow(int row1, int row2) {
		for(int c = 0; c < columns; c++) {
			int newRow1Element = Integer.parseInt(matrix[row2][c].getText());
			int newRow2Element = Integer.parseInt(matrix[row1][c].getText());
			matrix[row1][c].setText(Integer.toString(newRow1Element));
			matrix[row2][c].setText(Integer.toString(newRow2Element));
		}
	}
	public void undo() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == addButton) {
			AdditionFrame addFrame = new AdditionFrame("Add", 400, 300, rows);
		}
		if(event.getSource() == scaleButton) {
			ScaleFrame addFrame = new ScaleFrame("Scale", 400, 300, rows);
		}
		if(event.getSource() == switchButton) {
			SwitchFrame switchFrame = new SwitchFrame("Switch", 400, 300, rows);
		}
		if(event.getSource() == undoButton) {
			
		}
	}
}
