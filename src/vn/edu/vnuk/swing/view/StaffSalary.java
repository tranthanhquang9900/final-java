package vn.edu.vnuk.swing.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import vn.edu.vnuk.swing.define.Define;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class StaffSalary extends JPanel {
	private JTextField tbxAllowance;
	private JComboBox<String> cbxPosition;

	/**
	 * Create the panel.
	 */
	public StaffSalary() {
		setLayout(null);
		
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