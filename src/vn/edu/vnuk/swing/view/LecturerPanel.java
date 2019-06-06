package vn.edu.vnuk.swing.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import vn.edu.vnuk.swing.define.Define;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class LecturerPanel extends JPanel {
	private JTextField tbxHometown;
	private JTextField tbxDepartment;
	private JTextField tbxAllowance;
	private JTextField tbxPeriodsInMonth;
	private JTextField tbxSalaryRatio;
	private JTextField tbxYearOfWork;
	private JComboBox<String> cbxQualification;

	/**
	 * Create the panel.
	 */
	public LecturerPanel() {
		setLayout(null);
		
		JLabel lblHometown = new JLabel("Hometown:");
		lblHometown.setBounds(0, 79, 103, 21);
		add(lblHometown);
		
		tbxHometown = new JTextField();
		tbxHometown.setColumns(10);
		tbxHometown.setBounds(168, 76, 132, 26);
		add(tbxHometown);
		
		JLabel lblDepartment = new JLabel("Department:");
		lblDepartment.setBounds(0, 117, 103, 21);
		add(lblDepartment);
		
		tbxDepartment = new JTextField();
		tbxDepartment.setColumns(10);
		tbxDepartment.setBounds(168, 113, 132, 26);
		add(tbxDepartment);
		
		JLabel lblQualification = new JLabel("Qualification:");
		lblQualification.setBounds(0, 3, 103, 21);
		add(lblQualification);
		
		cbxQualification = new JComboBox<String>();
		cbxQualification.setBounds(168, 0, 132, 26);
		add(cbxQualification);
		cbxQualification.addItem(Define.QUALIFICATION_OF_BACHELOR);
		cbxQualification.addItem(Define.QUALIFICATION_OF_MASTER);
		cbxQualification.addItem(Define.QUALIFICATION_OF_DOCTOR);
		cbxQualification.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				setAllowanceByQualification(cbxQualification.getSelectedIndex());
			}
		});
		
		JLabel lblAlowance = new JLabel("Allowance:");
		lblAlowance.setBounds(0, 41, 112, 21);
		add(lblAlowance);
		
		tbxAllowance = new JTextField();
		tbxAllowance.setEditable(false);
		tbxAllowance.setColumns(10);
		tbxAllowance.setBounds(168, 37, 132, 26);
		add(tbxAllowance);
		
		JLabel lblPeriodsInMonth = new JLabel("Periods in month:");
		lblPeriodsInMonth.setBounds(0, 154, 133, 21);
		add(lblPeriodsInMonth);
		
		tbxPeriodsInMonth = new JTextField();
		tbxPeriodsInMonth.setColumns(10);
		tbxPeriodsInMonth.setBounds(168, 152, 132, 26);
		add(tbxPeriodsInMonth);
		
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
	public String getPeriodsInMonth() {
		return this.tbxPeriodsInMonth.getText();
	}

	@SuppressWarnings("unused")
	public void setPeriodsInMonth(String periodsInMonth) {
		this.tbxPeriodsInMonth.setText(periodsInMonth);
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
	public int getQualification() {
		return this.cbxQualification.getSelectedIndex();
	}
	
	public String getQualificationString() {	
		return this.cbxQualification.getSelectedItem().toString();
	}

	@SuppressWarnings("unused")
	public void setQualification(int qualification) {
		setAllowanceByQualification(qualification);
		this.cbxQualification.setSelectedIndex(qualification);
	}
	
	public void setQualification(String qualification) {
		int qualificationInt = 0;
		switch(qualification) {
		case Define.QUALIFICATION_OF_MASTER: {
			qualificationInt = Define.TYPE_OF_MASTER;
			break;
		}
		
		case Define.QUALIFICATION_OF_BACHELOR: {
			qualificationInt = Define.TYPE_OF_BACHELOR;
			break;
		}
		
		case Define.QUALIFICATION_OF_DOCTOR: {
			qualificationInt = Define.TYPE_OF_DOCTOR;
			break;
		}
		}
		
		setAllowanceByQualification(qualificationInt);
		this.cbxQualification.setSelectedIndex(qualificationInt);
	}
	
	private void setAllowanceByQualification(int qualification) {
		switch (qualification) {
		case Define.TYPE_OF_BACHELOR:
			this.tbxAllowance.setText(String.valueOf(Define.ALLOWANCE_OF_BACHELOR));
			break;

		case Define.TYPE_OF_MASTER:
			this.tbxAllowance.setText(String.valueOf(Define.ALLOWANCE_OF_MASTER));
			break;
			
		case Define.TYPE_OF_DOCTOR:
			this.tbxAllowance.setText(String.valueOf(Define.ALLOWANCE_OF_DOCTOR));
			break;
		}
	}
}