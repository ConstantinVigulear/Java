/*
Создать приложение, работающее с БД посредством ГПИ(GUI). Пользователь должен иметь возможность в окне приложения
•        вводить данные в БД (из полей GUI) и из текстового файла;
•        выводить данные из БД в окне;
•        выводить данные из БД по определённым критериям (минимум 2);
•        выводить отсортированные данные.
В БД должно быть минимум 3 таблицы. При проектировании графического пользовательского интерфейса использовать модель MVC (Model/View/Controller, модель – вид – контроллер)

1. БД содержит данные об услугах tattoo салона, включая татуировки по категориям, размерам, ценам, мастерам, способным их выполнять etc.

 */

package VigulearJDBC;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DisplayTattooSaloon extends JFrame implements ActionListener {
    static ArrayList<TattooModel> tattooSaloon = new ArrayList<>(); // to contain objects obtained from data base
    static JTable table; // will be placed within java frame
    static JFrame frame1;
    static JFrame frame2;
    static JLabel label0, label1, label3;
    static JComboBox<String> comboBox1;
    static JComboBox<String> comboBox2;
    static JButton button0, button1, button2, button3;
    static Vector<String> options = new Vector<>();
    static Vector<String> subOptions = new Vector<>();
    static Connection connection = null;
    static Statement statement;
    static ResultSet resultSet;
    static String from;
    static String[] columnNames = {"ArtistName", "ArtistSurname", "Category", "SizeName", "SizeNumber", "Price"};
    static PTextField fieldName, fieldCategory, fieldSizeName;
    static String query;
    static final JFileChooser fileChooser = new JFileChooser();

    // constructor for class Main wih java frame
    public DisplayTattooSaloon() {
        label0 = new JLabel("Welcome to Tattoo Saloon!");
        label0.setForeground(Color.red);
        label0.setFont(new Font("Serif", Font.BOLD, 20));
        label0.setBounds(100, 50, 350, 40);

        label1 = new JLabel("Select your option:");
        label1.setBounds(30, 110, 110, 20);

        button0 = new JButton("Submit Show");
        button0.setBounds(150, 150, 150, 20);
        button0.addActionListener(this);

        button1 = new JButton("Submit Add");
        button1.setBounds(150, 230, 150, 20);
        button1.addActionListener(this);
        button1.setVisible(false);

        button2 = new JButton("Submit");
        button2.setBounds(55, 130, 75, 20);
        button2.addActionListener(this);

        button3 = new JButton("Choose a file");
        button3.setBounds(310, 230, 150, 20);
        button3.addActionListener(this);
        button3.setVisible(false);

        options.add("Entire data base");
        options.add("Tattoos under 500$");
        options.add("Tattoos in cartoon setting with min price");
        options.add("Tattoos offered by Joey Pang");
        options.add("Add data...");

        subOptions.add("Add data from GUI");
        subOptions.add("Add data from file");

        comboBox1 = new JComboBox<>(options);
        comboBox2 = new JComboBox<>(subOptions);
        comboBox1.setBounds(150, 110, 240, 20);
        comboBox2.setBounds(150, 190, 240, 20);
        comboBox2.setVisible(false);

        this.add(label0);
        this.add(label1);
        this.add(button0);
        this.add(comboBox1);
        this.add(comboBox2);
        this.add(button1);
        this.add(button3);

        this.setTitle("Fetching Tattoo Info from Data Base");
        this.setIconImage(new ImageIcon("src/VigulearJDBC/tattoo.png").getImage());
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(500, 310);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == button0)
            getTattooSaloonTableBasedOnQuery();
        else if (event.getSource() == button1)
            addDataToDatabase();
        else if (event.getSource() == button2)
            insertFromGUI();
        else if (event.getSource() == button3) {
            fileChooser.showOpenDialog(this);
            insertFromFile();
        }
    }

    //making the connection
    static Connection makeJDBCConnection() {
        try {
            // load and register JDBC driver for MySQL
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tattoosaloon", "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(DisplayTattooSaloon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    // fill the ArrayList of TattooModels with data taken from the Database
    public static ArrayList<TattooModel> getTattooSaloon(String query) {
        tattooSaloon.clear();
        connection = makeJDBCConnection();
        TattooModel tattoo;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                tattoo = new TattooModel(resultSet.getString("a_name"), resultSet.getString("a_surname"),
                        resultSet.getString("c_name"), resultSet.getString("s_name"),
                        resultSet.getString("s_size"), resultSet.getInt("p_amount"));
                tattooSaloon.add(tattoo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisplayTattooSaloon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tattooSaloon;
    }

    // construct the table and fill it with obtained TattooSaloon
    public static void constructTable(ArrayList<TattooModel> arrayList) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        Object[] rowData = new Object[6];
        // установка названий колонок таблицы JTable table
        tableModel.setColumnIdentifiers(columnNames); // устанавливает названия колонок
        tableModel.setRowCount(0);
        for (TattooModel tattooModel : arrayList) {
            rowData[0] = tattooModel.getArtistName();
            rowData[1] = tattooModel.getArtistSurname();
            rowData[2] = tattooModel.getCategory();
            rowData[3] = tattooModel.getSizeName();
            rowData[4] = tattooModel.getSizeNumbers();
            rowData[5] = tattooModel.getPrice();
            tableModel.addRow(rowData);
        }
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));// Изменение шрифта в заголовке JTable
        table.setFont(new Font("Arial", Font.PLAIN, 15));// Изменение шрифта в JTable

        // https://colorscheme.ru/
        Color foreGround = new Color(0xFFFD73);
        table.getTableHeader().setBackground(foreGround);
        table.setBackground(new Color(0x7ff7f4));
        table.setForeground(new Color(0x007f36));
        table.setModel(tableModel); // таблице передается подготовленная модель
        table.setAutoCreateRowSorter(true); // sorting

        frame1 = new JFrame("Database Search Result");
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        frame1.add(scroll);
        frame1.setIconImage(new ImageIcon("src/VigulearJDBC/tattoo.png").getImage());
        frame1.setVisible(true);
        frame1.setSize(700, 400);
    }

    // display a table with data depending on chosen criteria
    static void getTattooSaloonTableBasedOnQuery() {
        from = (String) comboBox1.getSelectedItem();
        assert from != null;
        if (from.equals(options.get(0))) {
            query = "select a.a_name, a.a_surname, c.c_name, s.s_name, s.s_size, " +
                    "p.p_amount from artists a join tattoos t on a.a_id = t.a_id " +
                    "join categories c on t.c_id = c.c_id join sizes s on t.s_id = s.s_id join prices p on t.p_id = p.p_id";
            constructTable(getTattooSaloon(query));
        } else if (from.equals(options.get(1))) {
                query = "select a.a_name, a.a_surname, c.c_name, s.s_name, s.s_size, " +
                        "p.p_amount from artists a join tattoos t on a.a_id = t.a_id " +
                        "join categories c on t.c_id = c.c_id join sizes s on t.s_id = s.s_id join prices p on t.p_id = p.p_id where p.p_amount < 500";
            constructTable(getTattooSaloon(query));
        } else if (from.equals(options.get(2))) {
            query = "select a.a_name, a.a_surname, c.c_name, s.s_name, s.s_size, " +
                    "p.p_amount from artists a join tattoos t on a.a_id = t.a_id " +
                    "join categories c on t.c_id = c.c_id join sizes s on t.s_id = s.s_id join prices p on t.p_id = p.p_id " +
                    "where c.c_name = 'Cartoon' and p.p_amount = (select min(p.p_amount) from prices p) ";
            constructTable(getTattooSaloon(query));
        } else if (from.equals(options.get(3))) {
            String query = "select a.a_name, a.a_surname, c.c_name, s.s_name, s.s_size, " +
                    "p.p_amount from artists a join tattoos t on a.a_id = t.a_id " +
                    "join categories c on t.c_id = c.c_id join sizes s on t.s_id = s.s_id join prices p on s.p_id = p.p_id " +
                    "where a.a_name = 'Joey' and  a.a_surname = 'Pang' ";
            constructTable(getTattooSaloon(query));
        } else if (from.equals(options.get(4))) {
            comboBox2.setVisible(true);
            button1.setVisible(true);
        }
    }

    public static void addDataToDatabase() {
        frame2 = new JFrame("Data to add to Database");
        from = (String) comboBox2.getSelectedItem();
        assert from != null;
        if (from.equals(subOptions.get(0))) {
            label3 = new JLabel("Enter Data:");
            label3.setBounds(10, 10, 100, 20);
            label3.setFont(new Font("Serif", Font.BOLD, 20));

            fieldName = new PTextField("Artist Name");
            fieldCategory = new PTextField("Category");
            fieldSizeName = new PTextField("Size Name");

            fieldName.setBounds(20, 40, 150, 20);
            fieldCategory.setBounds(20, 70, 150, 20);
            fieldSizeName.setBounds(20, 100, 150, 20);

            frame2.add(fieldName);
            frame2.add(fieldCategory);
            frame2.add(fieldSizeName);
            frame2.add(button2);
            frame2.add(label3);
            frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame2.setLayout(new BorderLayout());
            frame2.setIconImage(new ImageIcon("src/VigulearJDBC/tattoo.png").getImage());
            frame2.setSize(320, 210);
            frame2.setVisible(true);

        } else {
            button3.setVisible(true);
        }
    }

    public static void insertFromGUI() {
        TattooModel insertTattooModel = new TattooModel(fieldName.getText(), "", fieldCategory.getText(), fieldSizeName.getText(),
                "", 0);
        query = "insert into tattoos values ((select c_id from categories where c_name = '" +
                insertTattooModel.getCategory() + "'), (select s_id from sizes where s_name = '" +
                insertTattooModel.getSizeName() + "'), (select a_id from artists where a_name = '" +
                insertTattooModel.getArtistName() + "'))";
        connection = makeJDBCConnection();
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println(query);
            JOptionPane.showConfirmDialog(null, "Your Data Has been Inserted", "Result", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, "Error: Invalid Data", "Result", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
        }
    }

    public static void insertFromFile() {

        File file = fileChooser.getSelectedFile();
        ArrayList<String> fileContent = new ArrayList<>();
        try (Scanner input = new Scanner(file)) {
            while (input.hasNext()) {
                fileContent.add(input.next());
            }
        } catch (IOException | IllegalStateException exception) {
            exception.printStackTrace();
        }
        TattooModel insertTattooModel = new TattooModel();
        for (int i = 0; i < fileContent.size(); i += 3) {
            insertTattooModel.setArtistName(fileContent.get(i));
            insertTattooModel.setCategory(fileContent.get(i + 1));
            insertTattooModel.setSizeName(fileContent.get(i + 2));
        }
        query = "insert into tattoos values ((select c_id from categories where c_name = '" +
                insertTattooModel.getCategory() + "'), (select s_id from sizes where s_name = '" +
                insertTattooModel.getSizeName() + "'), (select a_id from artists where a_name = '" +
                insertTattooModel.getArtistName() + "'))";
        connection = makeJDBCConnection();
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println(query);
            JOptionPane.showConfirmDialog(null, "Your Data Has been Inserted", "Result", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, "Error: Such entry already exists", "Result", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
        }

    }

    public static class PTextField extends JTextField {

        public PTextField(final String promptText) {
            super(promptText);
            addFocusListener(new FocusListener() {

                @Override
                public void focusLost(FocusEvent e) {
                    if (getText().isEmpty()) {
                        setText(promptText);
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {
                    if (getText().equals(promptText)) {
                        setText("");
                    }
                }
            });
        }
    }

    public static void main(String[] args) {
        new DisplayTattooSaloon();
    }
}
