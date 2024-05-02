package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import controller.BookController;
import model.Book;

public class Form extends JPanel  {

	/**
	 *  GUI Form
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel title;
	private JTextField titleField;
	private JLabel authorName;
	private JTextField authorNameField;
	private JLabel yearOfPublication;
	private JTextField yearOfPublicationField;
	private Dimension dim;
	private JButton btn;
	private FormEvent ev;
	

	private JTable booktable;
	private DefaultTableModel model;
	
	
	private JButton delButton;
	private JButton upButton;
	private JButton searchButton;
	private BookController bookController;
	
	private Book currentBook = null;

	public Form(BookController bookController) {		
	
		
		dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);

		title = new JLabel("Title:");
		titleField = new JTextField(15);

		authorName = new JLabel("AName:");
		authorNameField = new JTextField(15);

		yearOfPublication = new JLabel("PubYear:");
		yearOfPublicationField = new JTextField(15);

		btn = new JButton("Submit");

		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String bname = titleField.getText();
				String aname = authorNameField.getText();
				int yop = Integer.valueOf(yearOfPublicationField.getText());
				
				if(currentBook == null) {
					Book b = new Book(e.getID(), bname, aname, yop);
					if (ev != null) {
						ev.executeEvent(b);
					}
				}else {
					Book b = new Book(currentBook.getId(), bname, aname, yop);
					if (ev != null) {
						ev.updateEvent(b);
					}
				}
				
			}
		});

		Border innerBorder = BorderFactory.createTitledBorder("BookForm");
		Border outerborder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerborder, innerBorder));

		GridBagConstraints gc = new GridBagConstraints();

		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		title.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		gc.anchor = GridBagConstraints.LINE_END;
		add(title, gc);

		gc.gridy = 0;
		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;

		add(titleField, gc);

		gc.gridy = 1;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		authorName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		gc.anchor = GridBagConstraints.LINE_END;

		add(authorName, gc);
		gc.gridx = 1;
		gc.gridy = 1;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;

		add(authorNameField, gc);

		gc.gridy = 2;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		yearOfPublication.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		gc.anchor = GridBagConstraints.LINE_END;

		add(yearOfPublication, gc);

		gc.gridy = 2;
		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(yearOfPublicationField);

		gc.gridy = 3;
		gc.gridx = 0;
		gc.weightx = 2;
		gc.weighty = 2;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(btn);
		
	
	}

	

	public void setTitleField(String title) {
		titleField.setText(title);
	}

	

	public void setAuthorNameField(String nameField) {
		
		authorNameField.setText(nameField);
		
	}

	

	public void setYearOfPublicationField(String yearOfPublication) {
		yearOfPublicationField.setText(yearOfPublication);
		
		
	}

	public void addEvent(FormEvent ev) {
		this.ev = ev;
	}



	//---------Is Used tio clear text after clicking submit button
	public void clearFormFields() {
        currentBook = null;
		titleField.setText("");
		authorNameField.setText("");
		yearOfPublicationField.setText("");
        
    }
	
	public void setCurrentBook(Book book) {
		currentBook = book;
		
		setTitleField(currentBook.getBookTitle());
		setAuthorNameField(currentBook.getAuthorName());
		setYearOfPublicationField(Integer.valueOf(currentBook.getYearOfPublication()).toString());
	}
}
