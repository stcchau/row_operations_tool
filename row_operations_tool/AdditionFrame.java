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
public class AdditionFrame extends JFrame implements ActionListener {
	
	private final ButtonGroup BUTTON_GROUP_ONE = new ButtonGroup(), 
			BUTTON_GROUP_TWO = new ButtonGroup();
	private final JRadioButton[] ADDEE_BUTTON_ARRAY, ADDER_BUTTON_ARRAY;
	private final JTextField MULTIPLIER_TEXT_FIELD = new JTextField(2);
	private final JButton ENTER_BUTTON = new JButton("Enter");
	private final int ROWS;
	private final MatrixFrame MATRIX_FRAME;
	
	public AdditionFrame (String title, int length, int width, int rows, MatrixFrame matrixFrame) {
		
		super(title);
		ROWS = rows;
		ADDEE_BUTTON_ARRAY = new JRadioButton[ROWS];
		ADDER_BUTTON_ARRAY = new JRadioButton[ROWS];
		MATRIX_FRAME = matrixFrame;
		
		setSize(length, width);
		setLayout(new BorderLayout());
		setVisible(true);
		
		ENTER_BUTTON.addActionListener(this);
		
		add(new AddeeRowPanel(), BorderLayout.WEST);
		add(new AdderRowPanel(), BorderLayout.CENTER);
		add(new ButtonPanel(), BorderLayout.SOUTH);
		
	}
	
	private int getAddeeRow() throws Exception {
		for(int r = 0; r < ROWS; r++) {
			if(ADDEE_BUTTON_ARRAY[r].isSelected())
				return r;
		}
		throw new Exception();
	}
	
	private int getAdderRow() throws Exception {
		for(int r = 0; r < ROWS; r++) {
			if(ADDER_BUTTON_ARRAY[r].isSelected())
				return r;
		}
		throw new Exception();
	}
	
	private Fraction getMultiplier() {
		if(MULTIPLIER_TEXT_FIELD.getText().equals(""))
			return new Fraction(1, 1);
		return Fraction.parseFraction(MULTIPLIER_TEXT_FIELD.getText());
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == ENTER_BUTTON) {
			try {
				if(getMultiplier().getDenominator() == 0)
					JOptionPane.showMessageDialog(null, "Cannot divide by zero. Please try again.");
				else {
					MATRIX_FRAME.addToRow(getAddeeRow(), getAdderRow(), getMultiplier());
					dispose();
				}
			}
			catch(Exception e) {
				try {
					getAddeeRow();
					getAdderRow();
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Both rows must be selected. Please try again.");
				}
				try {
					getMultiplier();
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "All inputs must be integers or fractions. Please try again.");
				}
			}
		}
	}
	private class AddeeRowPanel extends JPanel {
		public AddeeRowPanel() {
			setLayout(new GridLayout(ROWS + 1, 1));
			JPanel subPanel = new JPanel();
			subPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			subPanel.add(new JLabel("Select Row to Replace:"));
			add(subPanel);
			for(int r = 0; r < ROWS; r++) {
				subPanel = new JPanel();
				subPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
				ADDEE_BUTTON_ARRAY[r] = new JRadioButton(String.format("Row %s", r + 1));
				BUTTON_GROUP_ONE.add(ADDEE_BUTTON_ARRAY[r]);
				subPanel.add(ADDEE_BUTTON_ARRAY[r]);
				add(subPanel);
			}
		}
	}
	
	private class AdderRowPanel extends JPanel {
		private JLabel adderRowLabel = new JLabel("Select Row to Add With:");
		public AdderRowPanel() {
			setLayout(new GridLayout(ROWS + 1, 1));
			JPanel subPanel = new JPanel();
			subPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			subPanel.add(adderRowLabel);
			add(subPanel);
			for(int r = 0; r < ROWS; r++) {
				subPanel = new JPanel();
				subPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
				ADDER_BUTTON_ARRAY[r] = new JRadioButton(String.format("Row %s", r + 1));
				BUTTON_GROUP_TWO.add(ADDER_BUTTON_ARRAY[r]);
				subPanel.add(ADDER_BUTTON_ARRAY[r]);
				add(subPanel);
			}
		}
	}
	private class ButtonPanel extends JPanel {
		public ButtonPanel() {
			setLayout(new FlowLayout());
			add(new JLabel("Multiplier:"));
			add(MULTIPLIER_TEXT_FIELD);
			add(ENTER_BUTTON);
		}
	}
}
