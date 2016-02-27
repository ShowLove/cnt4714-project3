import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainUi {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bikedb?useSSL=false";
    static final String USERNAME= "root";
    static final String PASSWORD= "password";

    static final String DEFAULT_QUERY = "SELECT * FROM bikes";

    private static ResultSetTableModel tableModel;

    public JTable resultTable;
    public JPanel resultsPanel;


    public MainUi() {

        try {
            tableModel = new ResultSetTableModel( JDBC_DRIVER, DATABASE_URL,USERNAME, PASSWORD, DEFAULT_QUERY );
        } catch (SQLException er) {
            er.printStackTrace();
        } catch (ClassNotFoundException er) {
            er.printStackTrace();
        }

        JFrame frame = new JFrame("SQL Client GUI");
        GridBagConstraints c = new GridBagConstraints();
        JPanel mainPanel = new JPanel(new GridBagLayout());

        JPanel dbInfoPanel = new JPanel(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(dbInfoPanel,c);

        JPanel queryPanel = new JPanel(new GridBagLayout());
        c.gridx = 1;
        c.gridy = 0;
        mainPanel.add(queryPanel,c);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        mainPanel.add(buttonPanel,c);

        resultsPanel = new JPanel(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 2;
        resultTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        resultsPanel.add(scrollPane);
        mainPanel.add(resultsPanel,c);

        // reset gridwith to 1 for other components
        c.gridwidth = 1;


        JLabel dbInfoLabel = new JLabel("Enter Database Information");
        c.gridx = 0;
        c.gridy = 0;
        dbInfoPanel.add(dbInfoLabel,c);

        JLabel driverLabel = new JLabel("JDBC Driver");
        c.gridx = 0;
        c.gridy = 1;
        dbInfoPanel.add(driverLabel,c);

        JComboBox driverComboBox = new JComboBox();
        c.gridx = 1;
        c.gridy = 1;
        driverComboBox.setPreferredSize(new Dimension(200,30));
        driverComboBox.addItem(JDBC_DRIVER);
        dbInfoPanel.add(driverComboBox,c);

        JLabel dbUrlLabel = new JLabel("Database URL");
        c.gridx = 0;
        c.gridy = 2;
        dbInfoPanel.add(dbUrlLabel,c);

        JComboBox urlComboBox = new JComboBox();
        c.gridx = 1;
        c.gridy = 2;
        urlComboBox.setPreferredSize(new Dimension(200,30));
        urlComboBox.addItem(DATABASE_URL);
        dbInfoPanel.add(urlComboBox,c);


        JLabel userLabel = new JLabel("Username");
        c.gridx = 0;
        c.gridy = 3;
        dbInfoPanel.add(userLabel,c);

        JTextField userTextField = new JTextField();
        c.gridx = 1;
        c.gridy = 3;
        userTextField.setPreferredSize(new Dimension(150,30));
        userTextField.setText(USERNAME);
        dbInfoPanel.add(userTextField,c);

        JLabel passLabel = new JLabel("Password");
        c.gridx = 0;
        c.gridy = 4;
        dbInfoPanel.add(passLabel,c);

        JPasswordField passField = new JPasswordField();
        c.gridx = 1;
        c.gridy = 4;
        passField.setPreferredSize(new Dimension(150,30));
        passField.setText(PASSWORD);
        dbInfoPanel.add(passField,c);

        JLabel sqlLabel = new JLabel("Enter a SQL Command");
        c.gridx = 0;
        c.gridy = 0;
        queryPanel.add(sqlLabel,c);

        JTextArea queryTextArea = new JTextArea();
        c.gridx = 0;
        c.gridy = 1;
        queryTextArea.setPreferredSize(new Dimension(300,100));
        queryTextArea.setText(DEFAULT_QUERY);
        queryPanel.add(queryTextArea,c);


        JLabel statusLabel = new JLabel("No Connection Now");
        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(statusLabel,c);

        JButton connectButton = new JButton("Connect to Database");
        c.gridx = 1;
        c.gridy = 0;
        buttonPanel.add(connectButton,c);
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton clearButton = new JButton("Clear Command");
        c.gridx = 2;
        c.gridy = 0;
        buttonPanel.add(clearButton,c);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.removeAll();
                resultTable.getTableHeader().setVisible(false);
            }
        });

        JButton executeButton = new JButton("Execute SQL Command");
        c.gridx = 3;
        c.gridy = 0;
        buttonPanel.add(executeButton,c);
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    resultTable.getTableHeader().setVisible(true);
                    tableModel.setQuery( queryTextArea.getText() );
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JLabel test = new JLabel("SQL Execution Result:");
        c.gridx = 0;
        c.gridy = 1;
        buttonPanel.add(test,c);


        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
