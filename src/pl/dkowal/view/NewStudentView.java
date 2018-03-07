package pl.dkowal.view;

import pl.dkowal.database.DBManager;
import pl.dkowal.model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewStudentView extends JDialog {

    private JButton addButton;
    private JLabel cityLabel;
    private JTextField cityTextField;
    private JLabel idLabel;
    private JLabel lessonDateLabel;
    private JTextField lessonDateTextField;
    private JLabel levelLabel;
    private JTextField levelTextField;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel priceLabel;
    private JTextField priceTextField;
    private JLabel plnLabel;

    public NewStudentView(SelectView parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {

        nameLabel = new JLabel();
        nameTextField = new JTextField();
        cityLabel = new JLabel();
        cityTextField = new JTextField();
        levelLabel = new JLabel();
        levelTextField = new JTextField();
        lessonDateLabel = new JLabel();
        lessonDateTextField = new JTextField();
        priceLabel = new JLabel();
        priceTextField = new JTextField();
        addButton = new JButton();
        idLabel = new JLabel();
        plnLabel = new JLabel();
        plnLabel.setText("PLN");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nameLabel.setText("Imię:");

        cityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        cityLabel.setText("Miasto:");

        levelLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        levelLabel.setText("Poziom:");

        lessonDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lessonDateLabel.setText("Data lekcji:");
        lessonDateTextField.setText("rrrr-mm-dd");

        priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        priceLabel.setText("Cena:");

        addButton.setText("Dodaj");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(nameLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(cityLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cityTextField))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(levelLabel)
                                                                        .addComponent(lessonDateLabel)
                                                                        .addComponent(priceLabel))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(levelTextField)
                                                                        .addComponent(lessonDateTextField)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(plnLabel)
                                                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(idLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cityLabel, idLabel, lessonDateLabel, levelLabel, nameLabel, priceLabel});

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nameLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cityLabel)
                                        .addComponent(cityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(levelLabel)
                                        .addComponent(levelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lessonDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lessonDateLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(priceLabel)
                                        .addComponent(plnLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(addButton)
                                        .addComponent(idLabel))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(SwingConstants.VERTICAL, new Component[] {cityLabel, cityTextField, idLabel, lessonDateLabel, lessonDateTextField, levelLabel, levelTextField, nameLabel, nameTextField, priceLabel, priceTextField});

        pack();
    }

    private void addButtonActionPerformed(ActionEvent evt) {
        if(!fieldsFilled()) {
            JOptionPane.showMessageDialog(null, "Wszystkie pola muszą być uzupełnione!");
        } else {
            Student student;
            DBManager dbm = new DBManager();
            dbm.openConnection();
            student = new Student(nameTextField.getText(), cityTextField.getText(), levelTextField.getText(), lessonDateTextField.getText(), Double.parseDouble(priceTextField.getText()));
            dbm.addStudent(student);
            JOptionPane.showMessageDialog(null, "Pomyślnie dodano nowego ucznia!");
            this.setVisible(false);
        }
    }
    private boolean fieldsFilled() {
        return (!nameTextField.getText().equals("") && !cityTextField.getText().equals("") &&
                !levelTextField.getText().equals("") && !lessonDateTextField.getText().equals("")
                && !priceTextField.getText().equals(""));
    }
}