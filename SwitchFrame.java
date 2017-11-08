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
public class SwitchFrame extends JFrame implements ActionListener {
	
	private final ButtonGroup BUTTON_GROUP_ONE = new ButtonGroup(), 
			BUTTON_GROUP_TWO = new ButtonGroup();
	private final JRadioButton[] ROW_ONE_BUTTON_ARRAY, ROW_TWO_BUTTON_ARRAY;
	private final JButton ENTER_BUTTON = new JButton("Enter");
	private final int ROWS;
	private final MatrixFrame MATRIX_FRAME;
	
	public SwitchFrame (String title, int length, int width, int rows, MatrixFrame matrixFrame) {
		
		super(title);
		ROWS = rows;
		ROW_ONE_BUTTON_ARRAY = new JRadioButton[rows];
		ROW_TWO_BUTTON_ARRAY = new JRadioButton[rows];
		final JLabel row1Label = new JLabel("Select Row to Switch:"), 
				row2Label = new JLabel("Select Row to Switch With:");
		final JPanel row1Panel = new JPanel(), 
				row2Panel = new JPanel();
		this.MATRIX_FRAME = matrixFrame;
		
		setSize(length, width);
		setLayout(new BorderLayout());
		setVisible(true);
		
		row1Panel.setLayout(new GridLayout(rows + 1, 1));
		row2Panel.setLayout(new GridLayout(rows + 1, 1));
		
		JPanel row1SubPanel = new JPanel();
		row1SubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		row1SubPanel.add(row1Label);
		row1Panel.add(row1SubPanel);
		
		JPanel row2SubPanel = new JPanel();
		row2SubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		row2SubPanel.add(row2Label);
		row2Panel.add(row2SubPanel);
		
		ENTER_BUTTON.addActionListener(this);
		
		for(int r = 0; r < rows; r++) {
			row1SubPanel = new JPanel();
			row1SubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			ROW_ONE_BUTTON_ARRAY[r] = new JRadioButton(String.format("Row %s", r + 1));
			BUTTON_GROUP_ONE.add(ROW_ONE_BUTTON_ARRAY[r]);
			row1SubPanel.add(ROW_ONE_BUTTON_ARRAY[r]);
			row1Panel.add(row1SubPanel);
			
			row2SubPanel = new JPanel();
			row2SubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			ROW_TWO_BUTTON_ARRAY[r] = new JRadioButton(String.format("Row %s", r + 1));
			BUTTON_GROUP_TWO.add(ROW_TWO_BUTTON_ARRAY[r]);
			row2SubPanel.add(ROW_TWO_BUTTON_ARRAY[r]);
			row2Panel.add(row2SubPanel);
		}
		
		add(row1Panel, BorderLayout.WEST);
		add(row2Panel, BorderLayout.CENTER);
		add(ENTER_BUTTON, BorderLayout.SOUTH);
		
	}
	
	public int getRow1() throws Exception {
		for(int r = 0; r < ROWS; r++) {
			if(ROW_ONE_BUTTON_ARRAY[r].isSelected())
				return r;
		}
		throw new Exception();
	}
	
	public int getRow2() throws Exception {
		for(int r = 0; r < ROWS; r++) {
			if(ROW_TWO_BUTTON_ARRAY[r].isSelected())
				return r;
		}
		throw new Exception();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == ENTER_BUTTON) {
			try {
				MATRIX_FRAME.switchRow(getRow1(), getRow2());
				dispose();
			}
			catch(Exception e) {
				try {
					getRow1();
					getRow2();
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Both rows must be selected. Please try again.");
				}
			}
		}
	}
}
