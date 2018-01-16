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
public class InfoFrame extends JFrame implements ActionListener {
	
	private final JLabel ROWS_LABEL = new JLabel("Rows:");
	private final JLabel COLUMN_LABEL = new JLabel("Columns:");
	private final JTextField ROWS = new JTextField(1);
	private final JTextField COLUMNS = new JTextField(1);
	private final JButton ENTER_BUTTON = new JButton("Enter");
	
	public InfoFrame (String title, int length, int width) {
		super(title);
		setSize(length, width);
		setLayout(new FlowLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(ROWS_LABEL);
		add(ROWS);
		add(COLUMN_LABEL);
		add(COLUMNS);
		add(ENTER_BUTTON);
		
		ENTER_BUTTON.addActionListener(this);
	}
	
	public int getRows() {
		return Integer.parseInt(ROWS.getText());
	}
	
	public int getColumns() {
		return Integer.parseInt(COLUMNS.getText());
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == ENTER_BUTTON) {
			try {
				if(getRows() <= 0 || getColumns() <= 0)
					JOptionPane.showMessageDialog(null, "All inputs must be positive nonzero numbers. Please try again.");
				else {
					new MatrixFillInFrame("Row Operations Tool", 500, 300, getRows(), getColumns());
					dispose();
				}
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, "All inputs must be positive nonzero integers. Please try again.");
			}
		}
	}
}
