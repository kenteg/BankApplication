package com.luxoft.bankapp.gui;

import com.luxoft.bankapp.model.Client;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.luxoft.bankapp.*;
import com.luxoft.bankapp.ui.BankCommander;

import static com.sun.glass.ui.Cursor.setVisible;

/**
 * @author Khrishpens Viktor
 *         created Ноябрь 17 2016
 */
public class BankApplicationForm extends JFrame {
    private JTable table1;
    private JPanel panel1;
    private JButton button1;


    public BankApplicationForm() {
        super("Bank");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BankCommander.testInitBank();
        TableModel model = new MyTableModel(BankCommander.currentBank.getClients());
        JTable table1 = new JTable(model);

        setPreferredSize(new Dimension(260, 220));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

class MyTableModel implements TableModel {

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
                return "Имя";
            case 1:
                return "Размер";
            case 2:
                return "Описание";
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
                return client.getBalance();
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