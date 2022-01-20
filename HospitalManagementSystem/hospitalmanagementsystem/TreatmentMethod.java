package hospitalmanagementsystem;
/**
 * This class generates the treatment and holds those information in database
 *
 * @author Maruf Þatur
 * @author Erdem Ege Eroðlu
 * @date 2019.12.9
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;

public class TreatmentMethod extends javax.swing.JFrame {

    private ArrayList<String> patientNo = new ArrayList<String>();
    private ArrayList<String> doctorNo = new ArrayList<String>();
    private int startCount;
    private int startCountPatient;

    /**
     * Creates new form TreatmentMethod
     */
    public TreatmentMethod() {
        startCount = 0;
        startCountPatient = 0;
        connect();
        initComponents();
        loadAll();
        getAutoId();
        displayOnTable();

        doctorNoComboBox.setSelectedItem(-1);
        patientNoComboBox.setSelectedItem(-1);
    }

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private int spin = 0;

    /**
     * this metod is used for show information from database on the jTable
     */
    private void loadAll() {
        try {
            pst = con.prepareStatement("select * from doctor");
            rs = pst.executeQuery();

            while (rs.next()) {
                doctorNo.add(rs.getString("doctorid"));
            }

            for (String name : doctorNo) {
                //	System.out.println("ikinci for "+name);
                doctorNoComboBox.addItem(name);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            pst = con.prepareStatement("select * from patient");
            rs = pst.executeQuery();

            while (rs.next()) {
                System.out.println();
                patientNo.add(rs.getString("patientid"));
            }

            for (String name : patientNo) {
                //	System.out.println("ikinci for "+name);
                patientNoComboBox.addItem(name);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * this metod is used for show information from database onthe jTable
     */
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/hospitalmanagementsystem", "root", "");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * this metod is used for incrementation in database
     */
    public void getAutoId() {
        try {
            java.sql.Statement s = con.createStatement();
            rs = s.executeQuery("select MAX(treatmentno) from treatment");
            rs.next();
            rs.getString("MAX(treatmentno)");

            if (rs.getString("MAX(treatmentno)") == null) {
                jLabel3.setText("TR001");
            } else {
                long id = Long.parseLong(rs.getString("MAX(treatmentno)").substring(2, rs.getString("MAX(treatmentno)").length()));
                id++;
                jLabel3.setText("TR" + String.format("%03d", id));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * this metod is used for show information from database onthe jTable
     */
    public void displayOnTable() {

        try {
            pst = con.prepareStatement("select * from treatment");
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columns = rsmd.getColumnCount();

            DefaultTableModel def = (DefaultTableModel) jTable1.getModel();
            def.setRowCount(0);
            double totals = 0;
            while (rs.next()) {

                Vector vec2 = new Vector();
                for (int i = 1; i <= columns; i++) {
                    vec2.add(rs.getString("treatmentno"));
                    vec2.add(rs.getString("patientno"));
                    vec2.add(rs.getString("doctorno"));
                    vec2.add(rs.getString("diseasename"));
                    vec2.add(rs.getString("treatmentmethods"));
                    vec2.add(rs.getString("medicinename"));
                    vec2.add(rs.getInt("frequency"));
                    vec2.add(rs.getString("comment"));
                }
                def.addRow(vec2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExpencesAndIncomes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        patientNoLabel = new javax.swing.JLabel();
        doctorNoLabel = new javax.swing.JLabel();
        diseaseNameLabel = new javax.swing.JLabel();
        treatmentMethodLabel = new javax.swing.JLabel();
        medicineNameLabel = new javax.swing.JLabel();
        frequencyLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        commentsLabel = new javax.swing.JLabel();
        diseaseNameText = new javax.swing.JTextField();
        treatmentMethodsBox = new javax.swing.JComboBox<>();
        medicineNameText = new javax.swing.JTextField();
        frequencySpinner = new javax.swing.JSpinner();
        jScrollPane2 = new javax.swing.JScrollPane();
        commentsArea = new javax.swing.JTextArea();
        patientNoComboBox = new javax.swing.JComboBox<>();
        doctorNoComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Treatment No", "Patient No", "Doctor No", "Disease Name", "Treatment Methods", "Medicine Name", "Frequency", "Comments"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Treatment");

        patientNoLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        patientNoLabel.setText("Patient No");

        doctorNoLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        doctorNoLabel.setText("Doctor No");

        diseaseNameLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        diseaseNameLabel.setText("Disease Name");

        treatmentMethodLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        treatmentMethodLabel.setText("Treatment Methods");

        medicineNameLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        medicineNameLabel.setText("Medicine Name");

        frequencyLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        frequencyLabel.setText("Frequency");

        backButton.setBackground(new java.awt.Color(51, 102, 255));
        backButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        addButton.setBackground(new java.awt.Color(51, 102, 255));
        addButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        addButton.setText("ADD");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        commentsLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        commentsLabel.setText("Comments");

        treatmentMethodsBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Outpatient treatment", "Inpatient Treatment", "Surgery operation", "Chemotherapy"}));
        treatmentMethodsBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                treatmentMethodsBoxActionPerformed(evt);
            }
        });

        commentsArea.setColumns(20);
        commentsArea.setRows(5);
        jScrollPane2.setViewportView(commentsArea);

        patientNoComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientNoComboBoxActionPerformed(evt);
            }
        });

        doctorNoComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String doctorNamee = "";
                doctorNoComboBoxActionPerformed(evt);
                if (startCount != 0) {
                    try {
                        pst = con.prepareStatement("select * from doctor where doctorid = " + "'" + doctorNoComboBox.getSelectedItem().toString() + "'");
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    try {
                        rs = pst.executeQuery();
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    try {

                        if (rs.next()) {

                            doctorNamee = rs.getString("name");
                        }
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println(doctorNamee);
                    jLabel5.setText(doctorNamee);
                }
                startCount = 1;

            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Treatment No");

        frequencySpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                frequencySpinnerStateChanged(evt);
            }

            private void frequencySpinnerStateChanged(ChangeEvent evt) {
                spin = (int) frequencySpinner.getValue();

            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(commentsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(210, 210, 210))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(treatmentMethodLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(frequencyLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(diseaseNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(patientNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(patientNoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(medicineNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addComponent(doctorNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(doctorNoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(frequencySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(medicineNameText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(treatmentMethodsBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(diseaseNameText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(4, 4, 4))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(27, 27, 27))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(75, 75, 75)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(455, 455, 455))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 18, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(patientNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(patientNoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(doctorNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(doctorNoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(diseaseNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(diseaseNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(treatmentMethodLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(treatmentMethodsBox, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(medicineNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(medicineNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(frequencyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(frequencySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(commentsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(2, 2, 2)
                                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(7, 7, 7)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane1)))
                                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 1000, 550));
        setResizable(true);
        setSize(new java.awt.Dimension(1000, 575));
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
    }

    /**
     * this metod is used for addingnew component
     */
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {

        String trNo = jLabel3.getText();
        String patientNo = patientNoComboBox.getSelectedItem().toString();
        String doctorNo = doctorNoComboBox.getSelectedItem().toString();
        String diseaseName = diseaseNameText.getText();
        String treatmentMethod = treatmentMethodsBox.getSelectedItem().toString();;
        String medicineName = medicineNameText.getText();
        int frequency = (int) frequencySpinner.getValue();
        System.out.println(frequency);
        String comments = commentsArea.getText();

        connect();
        try {

            pst = con.prepareStatement("insert into treatment(treatmentno,patientno,doctorno, diseasename,treatmentmethods,medicinename,frequency,comment)values(?,?,?,?,?,?,?,?)");
            pst.setString(1, trNo);
            pst.setString(2, patientNo);
            pst.setString(3, doctorNo);
            pst.setString(4, diseaseName);
            pst.setString(5, treatmentMethod);
            pst.setString(6, medicineName);
            pst.setInt(7, spin);
            pst.setString(8, comments);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "treatment added..");

            patientNoComboBox.setSelectedIndex(-1);
            doctorNoComboBox.setSelectedIndex(-1);
            diseaseNameText.setText("");
            treatmentMethodsBox.setSelectedIndex(-1);
            medicineNameText.setText("");
            frequencySpinner.setValue(-1);
            commentsArea.setText("");
            getAutoId();

        } catch (SQLException ex) {
            Logger.getLogger(ExpencesAndIncomes.class.getName()).log(Level.SEVERE, null, ex);
        }
        displayOnTable();
    }

    private void treatmentMethodsBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void patientNoComboBoxActionPerformed(java.awt.event.ActionEvent evt) {

        String patientNamee = "";
        if (startCountPatient != 0) {
            try {
                pst = con.prepareStatement("select * from patient where patientid = " + "'" + patientNoComboBox.getSelectedItem().toString() + "'");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                rs = pst.executeQuery();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {

                if (rs.next()) {

                    patientNamee = rs.getString("namesurname");
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            jLabel4.setText(patientNamee);

        }

        startCountPatient = 1;
    }

    private void doctorNoComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(TreatmentMethod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TreatmentMethod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TreatmentMethod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TreatmentMethod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TreatmentMethod().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JTextArea commentsArea;
    private javax.swing.JLabel commentsLabel;
    private javax.swing.JLabel diseaseNameLabel;
    private javax.swing.JTextField diseaseNameText;
    private javax.swing.JComboBox<String> doctorNoComboBox;
    private javax.swing.JLabel doctorNoLabel;
    private javax.swing.JLabel frequencyLabel;
    private javax.swing.JSpinner frequencySpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel medicineNameLabel;
    private javax.swing.JTextField medicineNameText;
    private javax.swing.JComboBox<String> patientNoComboBox;
    private javax.swing.JLabel patientNoLabel;
    private javax.swing.JLabel treatmentMethodLabel;
    private javax.swing.JComboBox<String> treatmentMethodsBox;
    // End of variables declaration                   
}
