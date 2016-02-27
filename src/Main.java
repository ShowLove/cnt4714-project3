import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Created by jamesbedont on 2/26/16.
 */
public class Main {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bikedb?useSSL=false";
    static final String USERNAME= "root";
    static final String PASSWORD= "password";

    static final String DEFAULT_QUERY = "SELECT * FROM bikes";

    private static ResultSetTableModel tableModel;

    public static void main(String[] args) {

        try {
            tableModel = new ResultSetTableModel( JDBC_DRIVER, DATABASE_URL,USERNAME, PASSWORD, DEFAULT_QUERY );
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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

        JPanel resultsPanel = new JPanel(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 2;
        JTable resultTable = new JTable(tableModel);
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
        driverComboBox.setPreferredSize(new Dimension(150,30));
        dbInfoPanel.add(driverComboBox,c);

        JLabel dbUrlLabel = new JLabel("Database URL");
        c.gridx = 0;
        c.gridy = 2;
        dbInfoPanel.add(dbUrlLabel,c);

        JComboBox urlComboBox = new JComboBox();
        c.gridx = 1;
        c.gridy = 2;
        urlComboBox.setPreferredSize(new Dimension(150,30));
        dbInfoPanel.add(urlComboBox,c);


        JLabel userLabel = new JLabel("Username");
        c.gridx = 0;
        c.gridy = 3;
        dbInfoPanel.add(userLabel,c);

        JTextField userTextField = new JTextField();
        c.gridx = 1;
        c.gridy = 3;
        userTextField.setPreferredSize(new Dimension(150,30));
        dbInfoPanel.add(userTextField,c);

        JLabel passLabel = new JLabel("Password");
        c.gridx = 0;
        c.gridy = 4;
        dbInfoPanel.add(passLabel,c);

        JPasswordField passField = new JPasswordField();
        c.gridx = 1;
        c.gridy = 4;
        passField.setPreferredSize(new Dimension(150,30));
        dbInfoPanel.add(passField,c);

        JLabel sqlLabel = new JLabel("Enter a SQL Command");
        c.gridx = 0;
        c.gridy = 0;
        queryPanel.add(sqlLabel,c);

        JTextArea queryTextArea = new JTextArea();
        c.gridx = 0;
        c.gridy = 1;
        queryTextArea.setPreferredSize(new Dimension(300,100));
        queryPanel.add(queryTextArea,c);


        JLabel statusLabel = new JLabel("No Connection Now");
        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(statusLabel,c);

        JButton connectButton = new JButton("Connect to Database");
        c.gridx = 1;
        c.gridy = 0;
        buttonPanel.add(connectButton,c);

        JButton clearButton = new JButton("Clear Command");
        c.gridx = 2;
        c.gridy = 0;
        buttonPanel.add(clearButton,c);

        JButton executeButton = new JButton("Execute SQL Command");
        c.gridx = 3;
        c.gridy = 0;
        buttonPanel.add(executeButton,c);

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
