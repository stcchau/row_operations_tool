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
		this.MATRIX_FRAME = matrixFrame;
		
		setSize(length, width);
		setLayout(new BorderLayout());
		setVisible(true);

		ENTER_BUTTON.addActionListener(this);
		
		add(new RowPanelOne(), BorderLayout.WEST);
		add(new RowPanelTwo(), BorderLayout.CENTER);
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
	
	private class RowPanelOne extends JPanel {
		private final JLabel rowLabel = new JLabel("Select Row to Switch:");
		public RowPanelOne() {
			setLayout(new GridLayout(ROWS + 1, 1));
			JPanel subPanel = new JPanel();
			subPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			subPanel.add(rowLabel);
			add(subPanel);
			for(int r = 0; r < ROWS; r++) {
				subPanel = new JPanel();
				subPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
				ROW_ONE_BUTTON_ARRAY[r] = new JRadioButton(String.format("Row %s", r + 1));
				BUTTON_GROUP_ONE.add(ROW_ONE_BUTTON_ARRAY[r]);
				subPanel.add(ROW_ONE_BUTTON_ARRAY[r]);
				add(subPanel);
			}
		}
	}
	private class RowPanelTwo extends JPanel {
		private final JLabel rowLabel = new JLabel("Select Row to Switch With:");
		public RowPanelTwo() {
			setLayout(new GridLayout(ROWS + 1, 1));
			JPanel subPanel = new JPanel();
			subPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			subPanel.add(rowLabel);
			add(subPanel);
			for(int r = 0; r < ROWS; r++) {
				subPanel = new JPanel();
				subPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
				ROW_TWO_BUTTON_ARRAY[r] = new JRadioButton(String.format("Row %s", r + 1));
				BUTTON_GROUP_TWO.add(ROW_TWO_BUTTON_ARRAY[r]);
				subPanel.add(ROW_TWO_BUTTON_ARRAY[r]);
				add(subPanel);
			}
		}
	}
}
