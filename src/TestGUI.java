import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
public class TestGUI {

    private JFrame frame;
    private DefaultListModel<String> libraryListModel;
    private DefaultListModel<String> cartListModel;
    private Bookazon bookazon;
    private JTextField titleField, authorField, yearField, priceField, paperbackField, quantityField;
    private JTextField line1Field, line2Field, cityField, stateField, zipField, countryField;

    public TestGUI() {
        bookazon = new Bookazon();
        Cart TestCart = new Cart();
        bookazon.addUser(new User("TestUser", new NormalSubscription(), TestCart));
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Bookazon Store");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(3, 1));

        JPanel libraryPanel = new JPanel(new BorderLayout());
        libraryListModel = new DefaultListModel<>();
        JList<String> libraryList = new JList<>(libraryListModel);
        JScrollPane libraryScrollPane = new JScrollPane(libraryList);
        libraryPanel.add(new JLabel("Library"), BorderLayout.NORTH);
        libraryPanel.add(libraryScrollPane, BorderLayout.CENTER);

        JPanel cartPanel = new JPanel(new BorderLayout());
        cartListModel = new DefaultListModel<>();
        JList<String> cartList = new JList<>(cartListModel);
        JScrollPane cartScrollPane = new JScrollPane(cartList);
        cartPanel.add(new JLabel("Cart"), BorderLayout.NORTH);
        cartPanel.add(cartScrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        titleField = new JTextField();
        authorField = new JTextField();
        yearField = new JTextField();
        priceField = new JTextField();
        paperbackField = new JTextField();
        quantityField = new JTextField();

        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Author:"));
        inputPanel.add(authorField);
        inputPanel.add(new JLabel("Year:"));
        inputPanel.add(yearField);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Is Paperback (true/false):"));
        inputPanel.add(paperbackField);


        JButton addBookButton = new JButton("Add Book to Library");
        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBookToLibrary();
            }
        });

        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCart(libraryList.getSelectedIndex());
            }
        });

        JPanel addressPanel = new JPanel(new GridLayout(6, 2));
        line1Field = new JTextField();
        line2Field = new JTextField();
        cityField = new JTextField();
        stateField = new JTextField();
        zipField = new JTextField();
        countryField = new JTextField();
        addressPanel.add(new JLabel("Address Line 1:"));
        addressPanel.add(line1Field);
        addressPanel.add(new JLabel("Address Line 2:"));
        addressPanel.add(line2Field);
        addressPanel.add(new JLabel("City:"));
        addressPanel.add(cityField);
        addressPanel.add(new JLabel("State:"));
        addressPanel.add(stateField);
        addressPanel.add(new JLabel("Zip Code:"));
        addressPanel.add(zipField);
        addressPanel.add(new JLabel("Country:"));
        addressPanel.add(countryField);

        JButton checkDetailsButton = new JButton("Check Details");
        checkDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewOrderDetails();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addBookButton);
        buttonPanel.add(addToCartButton);
        buttonPanel.add(checkDetailsButton);

        frame.add(libraryPanel);
        frame.add(cartPanel);
        frame.add(inputPanel);
        frame.add(addressPanel);
        frame.add(buttonPanel);

        frame.setVisible(true);
    }

    private void addBookToLibrary() {
        String title = titleField.getText();
        String author = authorField.getText();
        int year = Integer.parseInt(yearField.getText());
        double price = Double.parseDouble(priceField.getText());
        boolean isPaperback = Boolean.parseBoolean(paperbackField.getText());

        Media newBook = new Book(title, author, year, price, 1, isPaperback);
        bookazon.addProduct(newBook);

        libraryListModel.addElement(title + " (" + year + ") - $" + price + " - IsPaperback: " + isPaperback);
    }

    private void addToCart(int selectedIndex) {
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(frame, "Invalid Selection");
            return;
        } else {

            Media selectedBook = bookazon.getProduct(selectedIndex);
            int quantity = 1;

            bookazon.getUser(0).addToCart(selectedBook, quantity);
            cartListModel.addElement(selectedBook.getTitle() + " - Qty: " + quantity);

        }
    }

    private Address makeAddressFromTextFields(){
        Address address = new Address(line1Field.getText(), line2Field.getText(), cityField.getText(), stateField.getText(), zipField.getText(), countryField.getText());
        return address;
    }

    private LocalDate getCurrentDate() {
        LocalDate today = LocalDate.now();
        return today;
    }

    private void viewOrderDetails() {
        bookazon.getUser(0).setShippingAddress(new Address("123 Main St", "", "Springfield", "IL", "62701", "USA"));
        bookazon.getUser(0).setBillingAddress(new Address("123 Main St", "", "Springfield", "IL", "62701", "USA"));
        bookazon.getUser(0).checkout(makeAddressFromTextFields(), makeAddressFromTextFields(), new Date(getCurrentDate().getYear(), getCurrentDate().getMonthValue(), getCurrentDate().getDayOfMonth()));
        bookazon.getUser(0).viewOrders();
    }

    public static void main(String[] args) {
        new TestGUI();
    }
}
