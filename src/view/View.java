package view;

import Controller.DBController;
import controller.AdminController;
import controller.BookController;
import controller.MemberController;
import controller.MemberManagementPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Book;
import model.Member;
import model.MembershipCard;

public class View extends javax.swing.JFrame {

    private DBController dbController;
    private AdminController adc;
    private MemberController mdc;
    private BookController bdc;
    private MemberManagementPanel memberManagementPanel;

    public View() throws ParseException {
        try {
            initComponents();
            initializeControllers();
            setupInitialLayout();
            centerWindow();
        } catch (SQLException e) {
            handleInitializationError(e);
        }
    }

    private void initializeControllers() throws SQLException, ParseException {
        // Initialize database and controllers
        dbController = DBController.getInstance();
        fetchAndDisplayStudents();
        adc = new AdminController();
        bdc = new BookController();
        mdc = new MemberController(dbController); // Create MemberController instance
        memberManagementPanel = new MemberManagementPanel(dbController); // Create MemberManagementPanel instance
    }

    private void setupInitialLayout() {
        // Set initial visibility of panels
        Book_Manager.setVisible(false);
        Memeber_manager.setVisible(true);
        setResizable(true);
        Memeber_manager.add(memberManagementPanel, BorderLayout.CENTER);

    }

    private void fetchAndDisplayMembers() {
        try {
            if (dbController == null) {
                throw new SQLException("Database controller not initialized");
            }

            try (var connection = dbController.getConnection(); var pstmt = connection.prepareStatement(
                    "SELECT name, contact, address, course, expire_date FROM members"); var rs = pstmt.executeQuery()) {
                // Get the table model and clear existing rows
                var model = (javax.swing.table.DefaultTableModel) Member_table.getModel();
                model.setRowCount(0);

                // Set column headers
                String[] columnHeaders = {
                    "Name", "Contact", "Address", "Course", "Expiry Date"
                };
                model.setColumnIdentifiers(columnHeaders);

                // Add rows to table
                while (rs.next()) {
                    Object[] row = {
                        rs.getString("name"),
                        rs.getString("contact"),
                        rs.getString("address"),
                        rs.getString("course"),
                        rs.getString("expire_date")
                    };
                    model.addRow(row);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error fetching data: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
            // Log the error
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void centerWindow() {
        // Get screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 1356;
        int height = 659;

        // Calculate the position to center the window
        int x = (screenSize.width - width) / 2;
        int y = (screenSize.height - height) / 2;

        // Set window bounds (position and size)
        setBounds(x, y, width, height);
    }

    private void handleInitializationError(SQLException e) {
        JOptionPane.showMessageDialog(
                this,
                "Error initializing application: " + e.getMessage(),
                "Initialization Error",
                JOptionPane.ERROR_MESSAGE
        );
        // Log the error
        System.err.println("Initialization error: " + e.getMessage());
        e.printStackTrace();
        // Close the application since we couldn't initialize properly
        System.exit(1);
    }

    private void clearData() {
        //BOOK MANAGER
        book_title_txt.setText("");
        author_txt.setText("");
        publication_year_jDateChooser1.setDate(null);
        copies_book_title_txt1.setText("");

        if (language_field.getItemCount() > 0) {
            language_field.setSelectedIndex(0);
        }
        if (genre_item_combox.getItemCount() > 0) {
            genre_item_combox.setSelectedIndex(0);
        }
        if (format_jComboBox1.getItemCount() > 0) {
            format_jComboBox1.setSelectedIndex(0);
        }
        //MEMEBER MANAGER
//        id_txt1.setText("");
        name_txt1.setText("");
        contact_txt1.setText("");
        address_txt1.setText("");
        memCardNo_txt1.setSelectedIndex(0);
        expireDate_txt1.setDate(null);
    }

    private void fetchAndDisplayStudents() {
        try {
            if (dbController == null) {
                throw new SQLException("Database controller not initialized");
            }

            try (var connection = dbController.getConnection(); var pstmt = connection.prepareStatement(
                    "SELECT book_id, book_title, author, publication_year, "
                    + "language, genre, copies_available, format FROM books"); var rs = pstmt.executeQuery()) {

                // Get the table model and clear existing rows
                var model = (javax.swing.table.DefaultTableModel) Book_table.getModel();
                model.setRowCount(0);

                // Set column headers
                String[] columnHeaders = {
                    "ID", "Title", "Author", "Year", "Language",
                    "Genre", "Copies", "Format"
                };
                model.setColumnIdentifiers(columnHeaders);

                // Add rows to table
                while (rs.next()) {
                    Object[] row = {
                        rs.getInt("book_id"),
                        rs.getString("book_title"),
                        rs.getString("author"),
                        rs.getString("publication_year"),
                        rs.getString("language"),
                        rs.getString("genre"),
                        rs.getInt("copies_available"),
                        rs.getString("format")
                    };
                    model.addRow(row);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error fetching data: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
            // Log the error
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Books_btn = new javax.swing.JButton();
        Members_btn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Book_Manager = new javax.swing.JPanel();
        Book_panel = new javax.swing.JPanel();
        BookManger_lbl = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Book_details_panel = new javax.swing.JPanel();
        book_tile_lbl = new javax.swing.JLabel();
        boo_author_lbl = new javax.swing.JLabel();
        publication_year_lbl = new javax.swing.JLabel();
        author_txt = new javax.swing.JTextField();
        book_title_txt = new javax.swing.JTextField();
        Add_Book_btn = new javax.swing.JButton();
        View_Book_btn = new javax.swing.JButton();
        Reset_book_btn1 = new javax.swing.JButton();
        delete_book_btn = new javax.swing.JButton();
        language_lbl = new javax.swing.JLabel();
        genre_lbl = new javax.swing.JLabel();
        publication_year_jDateChooser1 = new com.toedter.calendar.JDateChooser();
        genre_item_combox = new javax.swing.JComboBox<>();
        language_field = new javax.swing.JComboBox<>();
        book_tile_lbl1 = new javax.swing.JLabel();
        book_tile_lbl2 = new javax.swing.JLabel();
        copies_book_title_txt1 = new javax.swing.JTextField();
        format_jComboBox1 = new javax.swing.JComboBox<>();
        book_table_panel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Book_table = new javax.swing.JTable();
        Memeber_manager = new javax.swing.JPanel();
        member_panel = new javax.swing.JPanel();
        Member_Mnger_lbl = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Table_panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Member_table = new javax.swing.JTable();
        Details_panel = new javax.swing.JPanel();
        name_lbl1 = new javax.swing.JLabel();
        age_lbl1 = new javax.swing.JLabel();
        address_lbl1 = new javax.swing.JLabel();
        address_txt1 = new javax.swing.JTextField();
        contact_txt1 = new javax.swing.JTextField();
        name_txt1 = new javax.swing.JTextField();
        Add_member_btn = new javax.swing.JButton();
        View_member_btn = new javax.swing.JButton();
        reset_member_btn = new javax.swing.JButton();
        id_lbl2 = new javax.swing.JLabel();
        delete_member_btn = new javax.swing.JButton();
        id_lbl3 = new javax.swing.JLabel();
        memCardNo_txt1 = new javax.swing.JComboBox<>();
        expireDate_txt1 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(175, 23, 64));

        jPanel1.setBackground(new java.awt.Color(116, 9, 56));
        jPanel1.setForeground(new java.awt.Color(255, 153, 153));

        jLabel1.setBackground(new java.awt.Color(102, 102, 102));
        jLabel1.setFont(new java.awt.Font("SimSun-ExtG", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Library  ");

        Books_btn.setBackground(new java.awt.Color(9, 16, 87));
        Books_btn.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        Books_btn.setForeground(new java.awt.Color(255, 255, 255));
        Books_btn.setText("Members");
        Books_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Books_btnActionPerformed(evt);
            }
        });

        Members_btn.setBackground(new java.awt.Color(9, 16, 87));
        Members_btn.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        Members_btn.setForeground(new java.awt.Color(255, 255, 255));
        Members_btn.setText("Books");
        Members_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Members_btnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 255));
        jLabel2.setText("Management System");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Books_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Members_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(55, 55, 55)
                .addComponent(Members_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Books_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(484, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(175, 23, 64));

        Book_Manager.setBackground(new java.awt.Color(175, 23, 64));

        Book_panel.setBackground(new java.awt.Color(175, 23, 64));

        BookManger_lbl.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        BookManger_lbl.setForeground(new java.awt.Color(9, 16, 87));
        BookManger_lbl.setText("Book");

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Manager");

        javax.swing.GroupLayout Book_panelLayout = new javax.swing.GroupLayout(Book_panel);
        Book_panel.setLayout(Book_panelLayout);
        Book_panelLayout.setHorizontalGroup(
            Book_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Book_panelLayout.createSequentialGroup()
                .addContainerGap(1042, Short.MAX_VALUE)
                .addGroup(Book_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BookManger_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        Book_panelLayout.setVerticalGroup(
            Book_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Book_panelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(BookManger_lbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        Book_details_panel.setBackground(new java.awt.Color(175, 23, 64));

        book_tile_lbl.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        book_tile_lbl.setForeground(new java.awt.Color(255, 255, 255));
        book_tile_lbl.setText("Book Title");

        boo_author_lbl.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        boo_author_lbl.setForeground(new java.awt.Color(255, 255, 255));
        boo_author_lbl.setText("Author");

        publication_year_lbl.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        publication_year_lbl.setForeground(new java.awt.Color(255, 255, 255));
        publication_year_lbl.setText("Publication year");

        Add_Book_btn.setBackground(new java.awt.Color(9, 16, 87));
        Add_Book_btn.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        Add_Book_btn.setForeground(new java.awt.Color(255, 255, 255));
        Add_Book_btn.setText("Add");
        Add_Book_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_Book_btnActionPerformed(evt);
            }
        });

        View_Book_btn.setBackground(new java.awt.Color(9, 16, 87));
        View_Book_btn.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        View_Book_btn.setForeground(new java.awt.Color(255, 255, 255));
        View_Book_btn.setText("View");
        View_Book_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                View_Book_btnActionPerformed(evt);
            }
        });

        Reset_book_btn1.setBackground(new java.awt.Color(9, 16, 87));
        Reset_book_btn1.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        Reset_book_btn1.setForeground(new java.awt.Color(255, 255, 255));
        Reset_book_btn1.setText("Reset");
        Reset_book_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Reset_book_btn1ActionPerformed(evt);
            }
        });

        delete_book_btn.setBackground(new java.awt.Color(9, 16, 87));
        delete_book_btn.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        delete_book_btn.setForeground(new java.awt.Color(255, 255, 255));
        delete_book_btn.setText("Delete");
        delete_book_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_book_btnActionPerformed(evt);
            }
        });

        language_lbl.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        language_lbl.setForeground(new java.awt.Color(255, 255, 255));
        language_lbl.setText("Language");

        genre_lbl.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        genre_lbl.setForeground(new java.awt.Color(255, 255, 255));
        genre_lbl.setText("Genre");

        genre_item_combox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Adventure", "African Literature", "Anthology", "Art", "Autobiography", "Biography", "Business", "Children's", "Classic", "Comic Book", "Coming of Age", "Contemporary", "Cookbook", "Crime", "Drama", "Dystopian", "Education", "Epic Poetry", "Erotica", "Essay", "Fairy Tale", "Fantasy", "Feminist Literature", "Folklore", "Gothic", "Graphic Novel", "Health", "Historical Fiction", "History", "Horror", "Humor", "Inspirational", "Journal", "Literary Fiction", "LGBTQ+", "Magic Realism", "Manga", "Medical", "Memoir", "Metaphysical", "Military", "Multicultural", "Music", "Mystery", "Mythology", "New Adult", "Noir", "Paranormal", "Personal Development", "Philosophy", "Photography", "Play", "Poetry", "Political", "Psychology", "Religion", "Romance", "Satire", "Science", "Science Fiction", "Self-help", "Short Story", "Social Science", "Spirituality", "Sports", "Steampunk", "Superhero", "Suspense", "Technology", "Textbook", "Thriller", "Travel", "True Crime", "Urban Fantasy", "Utopian", "War", "Western", "Women's Fiction", "Young Adult" }));

        language_field.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "English", "Sinhala", "Mandarin Chinese", "Spanish", "Hindi", "Arabic", "Bengali", "Portuguese", "Russian", "Japanese", "Punjabi", "German", "Korean", "French", "Turkish", "Italian", "Thai", "Dutch", "Greek", "Swedish", "Polish", "Czech", "Hebrew", "Danish", "Finnish", " " }));

        book_tile_lbl1.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        book_tile_lbl1.setForeground(new java.awt.Color(255, 255, 255));
        book_tile_lbl1.setText("Format");

        book_tile_lbl2.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        book_tile_lbl2.setForeground(new java.awt.Color(255, 255, 255));
        book_tile_lbl2.setText("Copies Availabe");

        format_jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PDF", "Printed Book", "Kindle", "Online reader", "News paper", "Leaflet", "Paper" }));

        javax.swing.GroupLayout Book_details_panelLayout = new javax.swing.GroupLayout(Book_details_panel);
        Book_details_panel.setLayout(Book_details_panelLayout);
        Book_details_panelLayout.setHorizontalGroup(
            Book_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Book_details_panelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(Book_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(publication_year_lbl)
                    .addComponent(boo_author_lbl)
                    .addComponent(book_tile_lbl)
                    .addComponent(genre_lbl)
                    .addComponent(language_lbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Book_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(genre_item_combox, javax.swing.GroupLayout.Alignment.LEADING, 0, 290, Short.MAX_VALUE)
                    .addComponent(publication_year_jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(author_txt, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(language_field, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(book_title_txt, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(37, 37, 37)
                .addGroup(Book_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(book_tile_lbl2)
                    .addComponent(book_tile_lbl1))
                .addGap(25, 25, 25)
                .addGroup(Book_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(format_jComboBox1, 0, 304, Short.MAX_VALUE)
                    .addComponent(copies_book_title_txt1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(Book_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Add_Book_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(View_Book_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Reset_book_btn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(delete_book_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );
        Book_details_panelLayout.setVerticalGroup(
            Book_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Book_details_panelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(Book_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Book_details_panelLayout.createSequentialGroup()
                        .addComponent(Add_Book_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(View_Book_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(Book_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Book_details_panelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Reset_book_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(delete_book_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Book_details_panelLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(Book_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(genre_lbl)
                                    .addComponent(genre_item_combox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Book_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(language_field, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(language_lbl)))))
                    .addGroup(Book_details_panelLayout.createSequentialGroup()
                        .addGroup(Book_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(book_tile_lbl)
                            .addComponent(book_title_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(book_tile_lbl1)
                            .addComponent(format_jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Book_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(author_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boo_author_lbl)
                            .addComponent(book_tile_lbl2)
                            .addComponent(copies_book_title_txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Book_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(publication_year_lbl)
                            .addComponent(publication_year_jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        book_table_panel.setBackground(new java.awt.Color(175, 23, 64));

        Book_table.setBackground(new java.awt.Color(222, 124, 125));
        Book_table.setForeground(new java.awt.Color(0, 0, 0));
        Book_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Book Title", "Author", "Publication year", "Genre", "Language", "Title 7", "Title 8"
            }
        ));
        Book_table.setSelectionBackground(new java.awt.Color(255, 247, 209));
        Book_table.setSelectionForeground(new java.awt.Color(51, 51, 51));
        Book_table.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                Book_tableAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane2.setViewportView(Book_table);

        javax.swing.GroupLayout book_table_panelLayout = new javax.swing.GroupLayout(book_table_panel);
        book_table_panel.setLayout(book_table_panelLayout);
        book_table_panelLayout.setHorizontalGroup(
            book_table_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        book_table_panelLayout.setVerticalGroup(
            book_table_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Book_ManagerLayout = new javax.swing.GroupLayout(Book_Manager);
        Book_Manager.setLayout(Book_ManagerLayout);
        Book_ManagerLayout.setHorizontalGroup(
            Book_ManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Book_ManagerLayout.createSequentialGroup()
                .addGroup(Book_ManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Book_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Book_details_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(Book_ManagerLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(book_table_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Book_ManagerLayout.setVerticalGroup(
            Book_ManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Book_ManagerLayout.createSequentialGroup()
                .addComponent(Book_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(book_table_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Book_details_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Memeber_manager.setBackground(new java.awt.Color(175, 23, 64));

        member_panel.setBackground(new java.awt.Color(175, 23, 64));

        Member_Mnger_lbl.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        Member_Mnger_lbl.setForeground(new java.awt.Color(9, 16, 87));
        Member_Mnger_lbl.setText("Member");

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Manager");

        javax.swing.GroupLayout member_panelLayout = new javax.swing.GroupLayout(member_panel);
        member_panel.setLayout(member_panelLayout);
        member_panelLayout.setHorizontalGroup(
            member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, member_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Member_Mnger_lbl)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        member_panelLayout.setVerticalGroup(
            member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(member_panelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(Member_Mnger_lbl, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(jLabel4))
        );

        Table_panel.setBackground(new java.awt.Color(175, 23, 64));

        Member_table.setBackground(new java.awt.Color(222, 124, 125));
        Member_table.setForeground(new java.awt.Color(0, 0, 0));
        Member_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        Member_table.setSelectionForeground(new java.awt.Color(0, 0, 204));
        Member_table.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                Member_tableAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(Member_table);

        javax.swing.GroupLayout Table_panelLayout = new javax.swing.GroupLayout(Table_panel);
        Table_panel.setLayout(Table_panelLayout);
        Table_panelLayout.setHorizontalGroup(
            Table_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Table_panelLayout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        Table_panelLayout.setVerticalGroup(
            Table_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Table_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addContainerGap())
        );

        Details_panel.setBackground(new java.awt.Color(175, 23, 64));

        name_lbl1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        name_lbl1.setForeground(new java.awt.Color(255, 255, 255));
        name_lbl1.setText("Name");

        age_lbl1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        age_lbl1.setForeground(new java.awt.Color(255, 255, 255));
        age_lbl1.setText("Contact");

        address_lbl1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        address_lbl1.setForeground(new java.awt.Color(255, 255, 255));
        address_lbl1.setText("Address");

        address_txt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                address_txt1ActionPerformed(evt);
            }
        });

        contact_txt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contact_txt1ActionPerformed(evt);
            }
        });

        name_txt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_txt1ActionPerformed(evt);
            }
        });

        Add_member_btn.setBackground(new java.awt.Color(9, 16, 87));
        Add_member_btn.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        Add_member_btn.setForeground(new java.awt.Color(255, 255, 255));
        Add_member_btn.setText("Add");
        Add_member_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_member_btnActionPerformed(evt);
            }
        });

        View_member_btn.setBackground(new java.awt.Color(9, 16, 87));
        View_member_btn.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        View_member_btn.setForeground(new java.awt.Color(255, 255, 255));
        View_member_btn.setText("View");
        View_member_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                View_member_btnActionPerformed(evt);
            }
        });

        reset_member_btn.setBackground(new java.awt.Color(9, 16, 87));
        reset_member_btn.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        reset_member_btn.setForeground(new java.awt.Color(255, 255, 255));
        reset_member_btn.setText("Reset");
        reset_member_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_member_btnActionPerformed(evt);
            }
        });

        id_lbl2.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        id_lbl2.setForeground(new java.awt.Color(255, 255, 255));
        id_lbl2.setText("Course");

        delete_member_btn.setBackground(new java.awt.Color(9, 16, 87));
        delete_member_btn.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        delete_member_btn.setForeground(new java.awt.Color(255, 255, 255));
        delete_member_btn.setText("Delete");
        delete_member_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_member_btnActionPerformed(evt);
            }
        });

        id_lbl3.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        id_lbl3.setForeground(new java.awt.Color(255, 255, 255));
        id_lbl3.setText("Expire date");

        memCardNo_txt1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "High National Diploma Information Technology", "High National Diploma Accounting", "High National Diploma Business Studies", "High National Diploma Tourism & Hospitality", "High National Diploma English" }));
        memCardNo_txt1.setMinimumSize(new java.awt.Dimension(68, 26));
        memCardNo_txt1.setPreferredSize(new java.awt.Dimension(68, 26));
        memCardNo_txt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memCardNo_txt1ActionPerformed(evt);
            }
        });

        expireDate_txt1.setMinimumSize(new java.awt.Dimension(68, 26));
        expireDate_txt1.setPreferredSize(new java.awt.Dimension(68, 26));

        javax.swing.GroupLayout Details_panelLayout = new javax.swing.GroupLayout(Details_panel);
        Details_panel.setLayout(Details_panelLayout);
        Details_panelLayout.setHorizontalGroup(
            Details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Details_panelLayout.createSequentialGroup()
                .addGroup(Details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Details_panelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(delete_member_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Details_panelLayout.createSequentialGroup()
                        .addGroup(Details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Details_panelLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(name_lbl1)
                                .addGap(37, 37, 37))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Details_panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(Details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Details_panelLayout.createSequentialGroup()
                                        .addComponent(age_lbl1)
                                        .addGap(23, 23, 23))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Details_panelLayout.createSequentialGroup()
                                        .addComponent(address_lbl1)
                                        .addGap(18, 18, 18)))))
                        .addGroup(Details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(contact_txt1, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                            .addComponent(name_txt1)
                            .addComponent(address_txt1))
                        .addGap(91, 91, 91)
                        .addGroup(Details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(id_lbl3)
                            .addComponent(id_lbl2))
                        .addGroup(Details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(Details_panelLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(expireDate_txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Details_panelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(memCardNo_txt1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                        .addGroup(Details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(View_member_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(reset_member_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(Add_member_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(29, 29, 29))
        );
        Details_panelLayout.setVerticalGroup(
            Details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Details_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Details_panelLayout.createSequentialGroup()
                        .addGroup(Details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Details_panelLayout.createSequentialGroup()
                                .addComponent(name_txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(contact_txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(age_lbl1)))
                            .addGroup(Details_panelLayout.createSequentialGroup()
                                .addGroup(Details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(id_lbl2)
                                    .addComponent(memCardNo_txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(id_lbl3))
                            .addGroup(Details_panelLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(expireDate_txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(address_txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(address_lbl1)))
                    .addGroup(Details_panelLayout.createSequentialGroup()
                        .addGroup(Details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Details_panelLayout.createSequentialGroup()
                                .addComponent(Add_member_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(View_member_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(reset_member_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(name_lbl1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delete_member_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Memeber_managerLayout = new javax.swing.GroupLayout(Memeber_manager);
        Memeber_manager.setLayout(Memeber_managerLayout);
        Memeber_managerLayout.setHorizontalGroup(
            Memeber_managerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Memeber_managerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Memeber_managerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Table_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(member_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(Details_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Memeber_managerLayout.setVerticalGroup(
            Memeber_managerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Memeber_managerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(member_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Table_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Details_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1138, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addComponent(Book_Manager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Memeber_manager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 744, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Book_Manager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(Memeber_manager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(2, 2, 2)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1137, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(250, 250, 250)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Books_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Books_btnActionPerformed
        if (Book_Manager.isVisible()) {
            Book_Manager.setVisible(false);
            Memeber_manager.setVisible(true);
        } else {
            Memeber_manager.setVisible(false);
            Book_Manager.setVisible(true);
        }
    }//GEN-LAST:event_Books_btnActionPerformed

    private void Members_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Members_btnActionPerformed
        if (Memeber_manager.isVisible()) {
            Memeber_manager.setVisible(false);
            Book_Manager.setVisible(true);
        } else {
            Book_Manager.setVisible(false);
            Memeber_manager.setVisible(true);
        }

    }//GEN-LAST:event_Members_btnActionPerformed

    private void Add_Book_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_Book_btnActionPerformed
        String title = book_title_txt.getText(); // Book title
        String authorName = author_txt.getText(); // Author name
        String authorEmail = "default@example.com"; // If you have an email field, use it, else set a default email
        java.util.Date date = publication_year_jDateChooser1.getDate();
        String publishedYear = "";
        if (date != null) {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy");
            publishedYear = sdf.format(date);
        }
        String language = (String) language_field.getSelectedItem(); // Assuming a text field for language
        String genre = (String) genre_item_combox.getSelectedItem(); // Genre from combo box
        String format = (String) format_jComboBox1.getSelectedItem(); // Format from combo box
        int copiesAvailable;
        try {
            copiesAvailable = Integer.parseInt(copies_book_title_txt1.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number for copies available.");
            return; // Exit the method if invalid input is entered
        }
        try {
            // Create Book object
            Book book = new Book(authorName, authorEmail, title, publishedYear, language, genre, copiesAvailable, format);

            // Add book to the database using BookController
            BookController bookController = new BookController();
            bookController.add(book);

            // Confirmation message
            JOptionPane.showMessageDialog(null, "Book added successfully!");

            // Clear input fields
            clearData();
            if (language_field.getItemCount() > 0) {
                language_field.setSelectedIndex(0);
            }
            if (genre_item_combox.getItemCount() > 0) {
                genre_item_combox.setSelectedIndex(0);
            }
            if (format_jComboBox1.getItemCount() > 0) {
                format_jComboBox1.setSelectedIndex(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding book: " + e.getMessage());
        }
    }//GEN-LAST:event_Add_Book_btnActionPerformed

    private void View_Book_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_View_Book_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_View_Book_btnActionPerformed

    private void Reset_book_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Reset_book_btn1ActionPerformed
        book_title_txt.setText("");
        author_txt.setText("");
    }//GEN-LAST:event_Reset_book_btn1ActionPerformed

    private void delete_book_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_book_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_delete_book_btnActionPerformed

    private void address_txt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_address_txt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_address_txt1ActionPerformed

    private void contact_txt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contact_txt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contact_txt1ActionPerformed

    private void name_txt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_txt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_name_txt1ActionPerformed

    private void Add_member_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_member_btnActionPerformed
        String name = name_txt1.getText();
        String contact = contact_txt1.getText();
        String address = address_txt1.getText();
        String memberNo = memCardNo_txt1.getSelectedItem().toString();
        Date expireDate = expireDate_txt1.getDate();
        try {
            MembershipCard memberCard = new MembershipCard(memberNo, expireDate);
//            Member member1 = new Member(name, contact, address, memberCard);
            adc.addMember(new Member(name, contact, address, memberCard));

        } catch (NumberFormatException e) {
            System.out.println("Invalid member number. Please enter a numeric value.");
        }

        clearData();
    }//GEN-LAST:event_Add_member_btnActionPerformed

    private void View_member_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_View_member_btnActionPerformed
        try {
            adc.viewMemberDetails();
        } catch (ParseException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_View_member_btnActionPerformed

    private void reset_member_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_member_btnActionPerformed
        book_title_txt.setText("");
        author_txt.setText("");
    }//GEN-LAST:event_reset_member_btnActionPerformed

    private void delete_member_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_member_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_delete_member_btnActionPerformed

    private void memCardNo_txt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memCardNo_txt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_memCardNo_txt1ActionPerformed

    private void Book_tableAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_Book_tableAncestorAdded
        fetchAndDisplayStudents();
    }//GEN-LAST:event_Book_tableAncestorAdded

    private void Member_tableAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_Member_tableAncestorAdded
        fetchAndDisplayMembers();

    }//GEN-LAST:event_Member_tableAncestorAdded

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                View view = null;
                try {
                    view = new View();
                } catch (ParseException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
                view.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add_Book_btn;
    private javax.swing.JButton Add_member_btn;
    private javax.swing.JLabel BookManger_lbl;
    private javax.swing.JPanel Book_Manager;
    private javax.swing.JPanel Book_details_panel;
    private javax.swing.JPanel Book_panel;
    private javax.swing.JTable Book_table;
    private javax.swing.JButton Books_btn;
    private javax.swing.JPanel Details_panel;
    private javax.swing.JLabel Member_Mnger_lbl;
    private javax.swing.JTable Member_table;
    private javax.swing.JButton Members_btn;
    private javax.swing.JPanel Memeber_manager;
    private javax.swing.JButton Reset_book_btn1;
    private javax.swing.JPanel Table_panel;
    private javax.swing.JButton View_Book_btn;
    private javax.swing.JButton View_member_btn;
    private javax.swing.JLabel address_lbl1;
    private javax.swing.JTextField address_txt1;
    private javax.swing.JLabel age_lbl1;
    private javax.swing.JTextField author_txt;
    private javax.swing.JLabel boo_author_lbl;
    private javax.swing.JPanel book_table_panel;
    private javax.swing.JLabel book_tile_lbl;
    private javax.swing.JLabel book_tile_lbl1;
    private javax.swing.JLabel book_tile_lbl2;
    private javax.swing.JTextField book_title_txt;
    private javax.swing.JTextField contact_txt1;
    private javax.swing.JTextField copies_book_title_txt1;
    private javax.swing.JButton delete_book_btn;
    private javax.swing.JButton delete_member_btn;
    private com.toedter.calendar.JDateChooser expireDate_txt1;
    private javax.swing.JComboBox<String> format_jComboBox1;
    private javax.swing.JComboBox<String> genre_item_combox;
    private javax.swing.JLabel genre_lbl;
    private javax.swing.JLabel id_lbl2;
    private javax.swing.JLabel id_lbl3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> language_field;
    private javax.swing.JLabel language_lbl;
    private javax.swing.JComboBox<String> memCardNo_txt1;
    private javax.swing.JPanel member_panel;
    private javax.swing.JLabel name_lbl1;
    private javax.swing.JTextField name_txt1;
    private com.toedter.calendar.JDateChooser publication_year_jDateChooser1;
    private javax.swing.JLabel publication_year_lbl;
    private javax.swing.JButton reset_member_btn;
    // End of variables declaration//GEN-END:variables

}
