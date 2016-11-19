package com.luxoft.bankapp.gui;

import com.luxoft.bankapp.model.Client;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.luxoft.bankapp.ui.BankCommander;

/**
 * @author Khrishpens Viktor
 *         created Ноябрь 17 2016
 */
public class BankApplicationForm extends JFrame {
    private JTable table1;
    private JPanel panel1;
    private JButton button1;
    private JTextArea textArea1;


    public BankApplicationForm() {
        super("Bank");
        BankCommander.testInitBank();
        TableModel model = new MyTableModel(BankCommander.currentBank.getClients());
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        table1.setModel(model);
        setPreferredSize(new Dimension(260, 220));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = table1.getSelectedRow();
                BankCommander.currentClient = BankCommander.currentBank.getClients().get(id);
                BankCommander.currentClient.deposit(Float.parseFloat(textArea1.getText()));
                table1.updateUI();
            }
        });
    }

    private class MyTableModel implements TableModel {

        private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

        private List<Client> clients;

        public MyTableModel(List<Client> clients) {
            this.clients = clients;
        }

        public void addTableModelListener(TableModelListener listener) {
            listeners.add(listener);
        }

        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        public int getColumnCount() {
            return 3;
        }

        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return "Name";
                case 1:
                    return "gender";
                case 2:
                    return "Balance";
            }
            return "";
        }

        public int getRowCount() {
            return clients.size();
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Client client = clients.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return client.getName();
                case 1:
                    return client.getGender();
                case 2:
                    return String.valueOf(client.getBalance());
            }
            return "";
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        public void removeTableModelListener(TableModelListener listener) {
            listeners.remove(listener);
        }

        public void setValueAt(Object value, int rowIndex, int columnIndex) {

        }

    }

}
