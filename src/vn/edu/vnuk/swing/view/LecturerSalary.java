package vn.edu.vnuk.swing.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import vn.edu.vnuk.swing.define.Define;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class LecturerSalary extends JPanel {
	private JTextField tbxAllowance;
	private JComboBox<String> cbxQualification;

	/**
	 * Create the panel.
	 */
	public LecturerSalary() {
		setLayout(null);
		
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