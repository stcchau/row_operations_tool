/**
 * Please do not steal.
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
	
	private final JButton ENTER_BUTTON = new JButton("Enter Numbers");
	private final int ROWS;
	private final int COLUMNS;
	private final JTextField[][] J_TEXT_FIELD_MATRIX;
	private final JPanel TEXT_FIELD_PANEL;
	
	public MatrixFillInFrame(String title, int length, int width, int rows, int columns) {
		
		super(title);
		this.ROWS = rows;
		this.COLUMNS = columns;
		J_TEXT_FIELD_MATRIX = new JTextField[rows][columns];
		TEXT_FIELD_PANEL = new TextFieldPanel();
		
		setSize(length, width);
		setLayout(new BorderLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ENTER_BUTTON.addActionListener(this);

		add(TEXT_FIELD_PANEL, BorderLayout.CENTER);
		add(ENTER_BUTTON, BorderLayout.SOUTH);
	}
	
	private class TextFieldPanel extends JPanel {
		public TextFieldPanel() {
			setLayout(new GridLayout(ROWS, COLUMNS));
			for(int row = 0; row < ROWS; row++) {
				for(int column = 0; column < COLUMNS; column++) {
					JPanel textFieldSubPanel = new JPanel();
					textFieldSubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
					textFieldSubPanel.add(J_TEXT_FIELD_MATRIX[row][column] = new JTextField(2));
					add(textFieldSubPanel);
				}
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == ENTER_BUTTON) {
			try {
				Number[][] matrix = new Number[ROWS][COLUMNS];
				for(int row = 0; row < ROWS; row++) {
					for(int column = 0; column < COLUMNS; column++) {
						matrix[row][column] = Number.parseNumber(J_TEXT_FIELD_MATRIX[row][column].getText());
					}
				}
				new MatrixFrame("Row Operations Tool", 500, 300, matrix, ROWS, COLUMNS);
				dispose();
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, "All inputs must be integers or fractions. Please try again.");
			}
		}
	}
}
