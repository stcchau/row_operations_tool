/**
 * 
 */
package row_operations_tool;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * @author San Chau
 *
 */
public class MatrixFillInFrame extends JFrame implements ActionListener {
	
	private final JButton enterButton = new JButton("Enter Numbers");
	private final JLabel errorMessage = new JLabel();
	private final int rows;
	private final int columns;
	private JTextField[][] JTFMatrix;
	private JPanel errorMessagePanel = new JPanel();
	private JPanel textFieldPanel;
	
	public MatrixFrame display3;
	
	public MatrixFillInFrame(String title, int length, int width, int rows, int columns) {
		
		super(title);
		this.rows = rows;
		this.columns = columns;
		JTFMatrix = new JTextField[rows][columns];
		textFieldPanel = new TextFieldPanel();
		
		setSize(length, width);
		setLayout(new BorderLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		errorMessagePanel.setLayout(new FlowLayout());
		errorMessagePanel.add(errorMessage);
		
		enterButton.addActionListener(this);
		
		add(errorMessagePanel, BorderLayout.NORTH);
		add(textFieldPanel, BorderLayout.CENTER);
		add(enterButton, BorderLayout.SOUTH);
	}
	
	private class TextFieldPanel extends JPanel {
		public TextFieldPanel() {
			setLayout(new GridLayout(rows, columns));
			for(int row = 0; row < rows; row++) {
				for(int column = 0; column < columns; column++) {
					JPanel textFieldSubPanel = new JPanel();
					textFieldSubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
					textFieldSubPanel.add(JTFMatrix[row][column] = new JTextField(2));
					add(textFieldSubPanel);
				}
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == enterButton) {
			try {
			int[][] matrix = new int[rows][columns];
			for(int row = 0; row < rows; row++) {
				for(int column = 0; column < columns; column++) {
					matrix[row][column] = Integer.parseInt(JTFMatrix[row][column].getText());
				}
			}
			display3 = new MatrixFrame("Matrices Tool", 500, 300, matrix, rows, columns);
			dispose();
			}
			catch(Exception e) {
				errorMessage.setText("All inputs must be integers. Please try again.");
			}
		}
	}
}
