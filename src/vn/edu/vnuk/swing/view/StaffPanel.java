package vn.edu.vnuk.swing.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import vn.edu.vnuk.swing.define.Define;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class StaffPanel extends JPanel {
	private JTextField tbxHometown;
	private JTextField tbxDepartment;
	private JTextField tbxAllowance;
	private JTextField tbxWorkDay;
	private JTextField tbxSalaryRatio;
	private JTextField tbxYearOfWork;
	private JComboBox<String> cbxPosition;

	/**
	 * Create the panel.
	 */
	public StaffPanel() {
		setLayout(null);
		
		JLabel lblHometown = new JLabel("Hometown:");
		lblHometown.setBounds(0, 79, 103, 21);
		add(lblHometown);
		
		tbxHometown = new JTextField();
		tbxHometown.setColumns(10);
		tbxHometown.setBounds(169, 76, 132, 26);
		add(tbxHometown);
		
		JLabel lblDepartment = new JLabel("Department:");
		lblDepartment.setBounds(0, 117, 103, 21);
		add(lblDepartment);
		
		tbxDepartment = new JTextField();
		tbxDepartment.setColumns(10);
		tbxDepartment.setBounds(168, 114, 133, 26);
		add(tbxDepartment);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(0, 3, 103, 21);
		add(lblPosition);
		
		cbxPosition = new JComboBox<String>();
		cbxPosition.setBounds(169, 1, 132, 26);
		add(cbxPosition);
		cbxPosition.addItem(Define.POSITION_OF_CHIEF);
		cbxPosition.addItem(Define.POSITION_OF_DEPUTY);
		cbxPosition.addItem(Define.POSITION_OF_EMPLOYEE);
		cbxPosition.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				setAllowanceByPostion(cbxPosition.getSelectedIndex());
			}
		});
		
		JLabel lblAlowance = new JLabel("Allowance:");
		lblAlowance.setBounds(0, 41, 112, 21);
		add(lblAlowance);
		
		tbxAllowance = new JTextField();
		tbxAllowance.setEditable(false);
		tbxAllowance.setColumns(10);
		tbxAllowance.setBounds(169, 39, 132, 26);
		add(tbxAllowance);
		
		JLabel lblWorkDay = new JLabel("Work day:");
		lblWorkDay.setBounds(0, 154, 133, 21);
		add(lblWorkDay);
		
		tbxWorkDay = new JTextField();
		tbxWorkDay.setColumns(10);
		tbxWorkDay.setBounds(168, 152, 132, 26);
		add(tbxWorkDay);
		
		JLabel lblSalaryRatio = new JLabel("Salary ratio:");
		lblSalaryRatio.setBounds(0, 192, 112, 21);
		add(lblSalaryRatio);
		
		tbxSalaryRatio = new JTextField();
		tbxSalaryRatio.setColumns(10);
		tbxSalaryRatio.setBounds(168, 190, 132, 26);
		add(tbxSalaryRatio);
		
		JLabel lblYearOfWork = new JLabel("Year of work:");
		lblYearOfWork.setBounds(0, 230, 112, 21);
		add(lblYearOfWork);
		
		tbxYearOfWork = new JTextField();
		tbxYearOfWork.setColumns(10);
		tbxYearOfWork.setBounds(168, 228, 132, 26);
		add(tbxYearOfWork);
	}
	

	@SuppressWarnings("unused")
	public String getHometown() {
		return this.tbxHometown.getText();
	}

	@SuppressWarnings("unused")
	public void setHometown(String hometown) {
		this.tbxHometown.setText(hometown);
	}

	@SuppressWarnings("unused")
	public String getDepartment() {
		return this.tbxDepartment.getText();
	}

	@SuppressWarnings("unused")
	public void setDepartment(String department) {
		this.tbxDepartment.setText(department);
	}

	@SuppressWarnings("unused")
	public String getAllowance() {
		return this.tbxAllowance.getText();
	}

	@SuppressWarnings("unused")
	public void setAllowance(String allowance) {
		this.tbxAllowance.setText(allowance);
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
	public String getSalaryRatio() {
		return this.tbxSalaryRatio.getText();
	}

	@SuppressWarnings("unused")
	public void setSalaryRatio(String salaryRatio) {
		this.tbxSalaryRatio.setText(salaryRatio);
	}

	@SuppressWarnings("unused")
	public String getYearOfWork() {
		return this.tbxYearOfWork.getText();
	}

	@SuppressWarnings("unused")
	public void setYearOfWork(String yearOfWork) {
		this.tbxYearOfWork.setText(yearOfWork);
	}

	@SuppressWarnings("unused")
	public int getPosition() {
		return this.cbxPosition.getSelectedIndex();
	}
	
	public String getPositionString() {
		return this.cbxPosition.getSelectedItem().toString();
	}

	@SuppressWarnings("unused")
	public void setPosition(int position) {
		setAllowanceByPostion(position);
		this.cbxPosition.setSelectedIndex(position);
	}
	
	public void setPosition(String position) {
		int positionInt = 0;
		
		switch(position) {
		case Define.POSITION_OF_EMPLOYEE: {
			positionInt = Define.TYPE_OF_CHIEF;
			break;
		}
		
		case Define.POSITION_OF_DEPUTY: {
			positionInt = Define.TYPE_OF_DEPUTY;
			break;
		}
		
		case Define.POSITION_OF_CHIEF: {
			positionInt = Define.TYPE_OF_CHIEF;
			break;
		}
		}
		
		setAllowanceByPostion(positionInt);
		this.cbxPosition.setSelectedIndex(positionInt);
	}
	
	private void setAllowanceByPostion(int position) {
		switch (position) {
		case Define.TYPE_OF_CHIEF:
			this.tbxAllowance.setText(String.valueOf(Define.ALLOWANCE_OF_CHIEF));
			break;
		case Define.TYPE_OF_DEPUTY:
			this.tbxAllowance.setText(String.valueOf(Define.ALLOWANCE_OF_DEPUTY));
			break;
		case Define.TYPE_OF_EMPLOYEE:
			this.tbxAllowance.setText(String.valueOf(Define.ALLOWANCE_OF_EMPLOYEE));
			break;
		}
	}
}