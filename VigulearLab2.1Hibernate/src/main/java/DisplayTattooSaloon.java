/*
Создать приложение, работающее с БД посредством ГПИ(GUI) во фреймворке Hibernate.
Пользователь должен иметь возможность в окне приложения:
•        вводить данные в БД (из полей GUI) и из текстового файла;
•        выводить данные из БД в окне;
•        выводить данные из БД по определённым критериям (минимум 2);
•        выводить отсортированные данные.
В БД должно быть минимум 3 таблицы.
При проектировании графического пользовательского интерфейса использовать модель MVC
1. БД содержит данные об услугах tattoo салона, включая татуировки по категориям, размерам, ценам, мастерам, способным их выполнять etc.
*/

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import services.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;
import java.util.List;

import static utils.HibernateSessionFactoryUtil.getSessionFactory;

public class DisplayTattooSaloon extends JFrame implements ActionListener {
    static List<Tattoo> tattoos; // to contain objects obtained from data base
    static List<Price> prices;
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
    static String from;
    static String[] columnNames = {"ArtistName", "ArtistSurname", "Category", "SizeName", "SizeNumber", "Price"};
    static PTextField fieldName, fieldCategory, fieldSizeName, fieldPriceAmount;
    static String query;
    static final JFileChooser fileChooser = new JFileChooser();

    // constructor for class DisplayTattooSaloon wih java frame
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
        button2.setBounds(55, 160, 75, 20);
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

    // construct the table and fill it with obtained tattoos
    public static void constructTable(List<Tattoo> arrayList) {
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
        for (Tattoo tattoo : arrayList) {
            rowData[0] = tattoo.getArtist().getA_name();
            rowData[1] = tattoo.getArtist().getA_surname();
            rowData[2] = tattoo.getCategory().getC_name();
            rowData[3] = tattoo.getSize().getS_name();
            rowData[4] = tattoo.getSize().getS_size();
            rowData[5] = tattoo.getPrice().getP_amount();
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
        frame1.setIconImage(new ImageIcon("src/main/java/tattoo.png").getImage());
        frame1.setVisible(true);
        frame1.setSize(700, 400);
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

    // retrieve all tattoos
    public static void findAllTattoos() {
        TattooService tattooService = new TattooService();
        tattoos = tattooService.findAllTattoos();
    }

    // display a table with data depending on chosen criteria
    // logic: retrieve all tattoos and delete those that do not comply
    static void getTattooSaloonTableBasedOnQuery() {
        from = (String) comboBox1.getSelectedItem();
        assert from != null;
        if (from.equals(options.get(0))) {
            findAllTattoos();
            constructTable(tattoos);
        } else if (from.equals(options.get(1))) {
            findAllTattoos();
            // lambda function
            tattoos.removeIf(tattoo -> tattoo.getPrice().getP_amount() >= 500);
            constructTable(tattoos);
        } else if (from.equals(options.get(2))) {
            TattooService tattooService = new TattooService();
            tattoos = tattooService.findAllTattoos();

            // obtain list of prices
            prices = tattooService.findAllPrices();

            // find minimal price
            final int minPrice = prices.stream().min(Comparator.comparingInt(Price::getP_amount)).get().getP_amount();
            tattoos.removeIf(tattoo -> !tattoo.getCategory().getC_name().equals("Cartoon"));
            tattoos.removeIf(tattoo -> tattoo.getPrice().getP_amount() != minPrice);
            constructTable(tattoos);
        } else if (from.equals(options.get(3))) {
            findAllTattoos();
            tattoos.removeIf(tattoo -> !tattoo.getArtist().getA_name().equals("Joey"));
            tattoos.removeIf(tattoo -> !tattoo.getArtist().getA_surname().equals("Pang"));
            constructTable(tattoos);
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
            fieldPriceAmount = new PTextField("Price Amount");

            fieldName.setBounds(20, 40, 150, 20);
            fieldCategory.setBounds(20, 70, 150, 20);
            fieldSizeName.setBounds(20, 100, 150, 20);
            fieldPriceAmount.setBounds(20, 130, 150, 20);

            frame2.add(fieldName);
            frame2.add(fieldCategory);
            frame2.add(fieldSizeName);
            frame2.add(fieldPriceAmount);
            frame2.add(button2);
            frame2.add(label3);
            frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame2.setLayout(new BorderLayout());
            frame2.setIconImage(new ImageIcon("src/VigulearJDBC/tattoo.png").getImage());
            frame2.setSize(320, 230);
            frame2.setVisible(true);

        } else {
            button3.setVisible(true);
        }
    }

    public static void insertFromGUI() {
        TattooService tattooService = new TattooService();
        SessionFactory factory = getSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            // All the action with DB via Hibernate must be located in one transaction.Start Transaction.
            session.getTransaction().begin();

            // Create an HQL statement, query the object.

            String sql1 = "Select e from " + Artist.class.getName() + " e ";
            // Create Query object.
            Query<Artist> query1 = session.createQuery(sql1);
            // Execute query.
            List<Artist> artists = query1.getResultList();
            artists.removeIf(artist1 -> !artist1.getA_name().equals(fieldName.getText()));
            Artist artist = artists.get(0);

            String sql2 = "Select e from " + Category.class.getName() + " e ";
            Query<Category> query2 = session.createQuery(sql2);
            List<Category> categories = query2.getResultList();
            categories.removeIf(category -> !category.getC_name().equals(fieldCategory.getText()));
            Category category = categories.get(0);

            String sql3 = "Select e from " + Size.class.getName() + " e ";
            Query<Size> query3 = session.createQuery(sql3);
            List<Size> sizes = query3.getResultList();
            sizes.removeIf(size -> !size.getS_name().equals(fieldSizeName.getText()));
            Size size = sizes.get(0);

            String sql4 = "Select e from " + Price.class.getName() + " e ";
            Query<Price> query4 = session.createQuery(sql4);
            List<Price> prices = query4.getResultList();
            prices.removeIf(price -> price.getP_amount() != Integer.parseInt(fieldPriceAmount.getText()));
            Price price = prices.get(0);

            Tattoo tattoo = new Tattoo(price, category, size, artist);

            tattooService.saveTattoo(tattoo);

            artist.addTattoo(tattoo);
            category.addTattoo(tattoo);
            size.addTattoo(tattoo);
            price.addTattoo(tattoo);
            // Commit data.
            session.getTransaction().commit();
            JOptionPane.showConfirmDialog(null, "Your Data Has been Inserted", "Result", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback in case of an error occurred.
            session.getTransaction().rollback();
            JOptionPane.showConfirmDialog(null, "Error: Invalid Data", "Result", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
        }
    }

    public static void insertFromFile() {
        TattooService tattooService = new TattooService();
        SessionFactory factory = getSessionFactory();

        Session session = factory.getCurrentSession();

        File file = fileChooser.getSelectedFile();
        ArrayList<String> fileContent = new ArrayList<>();
        try (Scanner input = new Scanner(file)) {
            while (input.hasNext()) {
                fileContent.add(input.next());
            }
        } catch (IOException | IllegalStateException exception) {
            exception.printStackTrace();
        }
        try {
            // All the action with DB via Hibernate must be located in one transaction.Start Transaction.
            session.getTransaction().begin();

            // Create an HQL statement, query the object.

            String sql1 = "Select e from " + Artist.class.getName() + " e ";
            // Create Query object.
            Query<Artist> query1 = session.createQuery(sql1);
            // Execute query.
            List<Artist> artists = query1.getResultList();
            artists.removeIf(artist1 -> !artist1.getA_name().equals(fileContent.get(0)));
            Artist artist = artists.get(0);

            String sql2 = "Select e from " + Category.class.getName() + " e ";
            Query<Category> query2 = session.createQuery(sql2);
            List<Category> categories = query2.getResultList();
            categories.removeIf(category -> !category.getC_name().equals(fileContent.get(1)));
            Category category = categories.get(0);

            String sql3 = "Select e from " + Size.class.getName() + " e ";
            Query<Size> query3 = session.createQuery(sql3);
            List<Size> sizes = query3.getResultList();
            sizes.removeIf(size -> !size.getS_name().equals(fileContent.get(2)));
            Size size = sizes.get(0);

            String sql4 = "Select e from " + Price.class.getName() + " e ";
            Query<Price> query4 = session.createQuery(sql4);
            List<Price> prices = query4.getResultList();
            prices.removeIf(price -> price.getP_amount() != Integer.parseInt(fileContent.get(3)));
            Price price = prices.get(0);

            Tattoo tattoo = new Tattoo(price, category, size, artist);

            tattooService.saveTattoo(tattoo);

            artist.addTattoo(tattoo);
            category.addTattoo(tattoo);
            size.addTattoo(tattoo);
            price.addTattoo(tattoo);
            // Commit data.
            session.getTransaction().commit();
            JOptionPane.showConfirmDialog(null, "Your Data Has been Inserted", "Result", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback in case of an error occurred.
            session.getTransaction().rollback();
            JOptionPane.showConfirmDialog(null, "Error: Invalid Data", "Result", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new DisplayTattooSaloon();
    }
}
