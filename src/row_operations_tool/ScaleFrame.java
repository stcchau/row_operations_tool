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
public class ScaleFrame extends JFrame implements ActionListener {
	
	private final ButtonGroup BUTTON_GROUP = new ButtonGroup();
	private final JRadioButton[] ROW_BUTTON_ARRAY;
	private final JTextField SCALAR_TEXT_FIELD = new JTextField(2);
	private final JButton MULTIPLY_BUTTON = new JButton("Multiply"),
			DIVIDE_BUTTON = new JButton("Divide");
	private final int ROWS;
	private final MatrixFrame MATRIX_FRAME;
	
	public ScaleFrame(String title, int length, int width, int rows, MatrixFrame matrixFrame) {
		
		super(title);
		ROWS = rows;
		ROW_BUTTON_ARRAY = new JRadioButton[ROWS];
		MATRIX_FRAME = matrixFrame;
		
		setSize(length, width);
		setLayout(new BorderLayout());
		setVisible(true);
		
		MULTIPLY_BUTTON.addActionListener(this);
		DIVIDE_BUTTON.addActionListener(this);
		
		add(new RowPanel(), BorderLayout.CENTER);
		add(new ButtonPanel(), BorderLayout.SOUTH);
	}
	
	private int getRow() throws Exception {
		for(int r = 0; r < ROWS; r++) {
			if(ROW_BUTTON_ARRAY[r].isSelected()) {
				return r;
			}
		}
		throw new Exception();
	}
	
	public Fraction getScalar() {
		if(SCALAR_TEXT_FIELD.getText().equals(""))
			return new Fraction(1, 1);
		return Fraction.parseFraction(SCALAR_TEXT_FIELD.getText());
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == MULTIPLY_BUTTON) {
			try {
				if(getScalar().getDenominator() == 0)
					JOptionPane.showMessageDialog(null, "Cannot divide by zero. Please try again.");
				else {
					MATRIX_FRAME.multiplyRow(getRow(), getScalar());
					dispose();
				}
			}
			catch(Exception e) {
				try {
					getRow();
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "A row must be selected. Please try again.");
				}
				try {
					getScalar();
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "All inputs must be integers or fractions. Please try again.");
				}
			}
		}
		
		if(event.getSource() == DIVIDE_BUTTON) {
			try {
				if(getScalar().getNumerator() == 0 || getScalar().getDenominator() == 0)
					JOptionPane.showMessageDialog(null, "Cannot divide by zero. Please try again.");
				else {
					MATRIX_FRAME.divideRow(getRow(), getScalar());
					dispose();
				}
			}
			catch(Exception e) {
				try {
					getRow();
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "A row must be selected. Please try again.");
				}
				try {
					getScalar();
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "All inputs must be integers or fractions. Please try again.");
				}
			}
		}
	}
	
	private class RowPanel extends JPanel {
		public RowPanel() {
			setLayout(new GridLayout(ROWS + 1, 1));
			JPanel subPanel = new JPanel();
			subPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			subPanel.add(new JLabel("Select Row to Scale:"));
			add(subPanel);
			for(int r = 0; r < ROWS; r++) {
				subPanel = new JPanel();
				subPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
				ROW_BUTTON_ARRAY[r] = new JRadioButton(String.format("Row %s", r + 1));
				BUTTON_GROUP.add(ROW_BUTTON_ARRAY[r]);
				subPanel.add(ROW_BUTTON_ARRAY[r]);
				add(subPanel);
			}
		}
	}
	private class ButtonPanel extends JPanel {
		public ButtonPanel() {
			add(new JLabel("Scalar:"));
			add(SCALAR_TEXT_FIELD);
			add(MULTIPLY_BUTTON);
			add(DIVIDE_BUTTON);
		}
	}
}
