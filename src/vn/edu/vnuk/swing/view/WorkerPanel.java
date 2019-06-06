package vn.edu.vnuk.swing.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class WorkerPanel extends JPanel {
	private JTextField tbxWorkDay;
	private JTextField tbxEarningPerDay;
	
	/**
	 * Create the panel.
	 */
	public WorkerPanel() {
		setLayout(null);
		
		JLabel lblWorkDay = new JLabel("Work day :");
		lblWorkDay.setBounds(0, 2, 103, 21);
		add(lblWorkDay);
		
		tbxWorkDay = new JTextField();
		tbxWorkDay.setColumns(10);
		tbxWorkDay.setBounds(168, 0, 132, 26);
		add(tbxWorkDay);
		
		tbxEarningPerDay = new JTextField();
		tbxEarningPerDay.setColumns(10);
		tbxEarningPerDay.setBounds(168, 38, 132, 26);
		add(tbxEarningPerDay);
		
		JLabel lblEarningPerDay = new JLabel("Earning per day :");
		lblEarningPerDay.setBounds(0, 40, 150, 21);
		add(lblEarningPerDay);
		
	}
	
	@SuppressWarnings("unused")
	public String getWorkDay() {
		return this.tbxWorkDay.getText();
	}
	
	@SuppressWarnings("unused")
	public void setWorkDay(String workDay) {
		this.tbxWorkDay.setText(workDay);
	}
	
	@SuppressWarnings("unused")
	public String getEarningPerDay() {
		return this.tbxEarningPerDay.getText();
	}
	
	@SuppressWarnings("unused")
	public void setEarningPerDay(String earningPerDay) {
		this.tbxEarningPerDay.setText(earningPerDay);
	}
}