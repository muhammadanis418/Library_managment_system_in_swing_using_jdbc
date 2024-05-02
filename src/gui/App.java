package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import controller.BookController;
import model.Book;

public class App extends JFrame {
	private Form form;
	private FormEvent fev;

	private TextPannel area;
	private BookController bookController;
	private DefaultTableModel tableModel;

	private JTable table;

	private JButton deleteButton;
	
	private JButton searchButton;
	
	private JButton updateButton;

	private JButton searchBtn;
	
	private JTextField searchField;
	
	public App() {
		bookController = new BookController();
		form = new Form(bookController);
		area = new TextPannel();
		deleteButton = new JButton("Delete  Row");
		updateButton= new JButton("Update Row");
		
		searchBtn= new JButton("Search");
		searchField = new JTextField(10);
		
		
		tableModel = new DefaultTableModel();

		table = new JTable(tableModel);
		table.setModel(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setPreferredSize(new Dimension(400, 400));

		fev = new FormEvent() {

			@Override
			public void executeEvent(Book b) {

				bookController.addBook(b);
				updateTable();
				form.clearFormFields();

			}
			
			@Override
			public void updateEvent(Book b) {

				bookController.updateBook(b);
				updateTable();
				form.clearFormFields();

			}
		};

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int rowIndex = table.getSelectedRow();

				if (rowIndex != -1) {
					int idToDelete = (int) table.getValueAt(rowIndex, 0);
					bookController.deleteBYId(idToDelete);
					updateTable();
				} else {
					JOptionPane.showMessageDialog(App.this, "Select a Row to delete");
				}

			}
		});
		
		updateButton.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int rowIndex = table.getSelectedRow();

				if (rowIndex != -1) {
					int id = (int) table.getValueAt(rowIndex, 0);
					String bookTitle = (String) table.getValueAt(rowIndex, 1);
					String authorName = (String) table.getValueAt(rowIndex, 2);
					int yearOfPublication = (int) table.getValueAt(rowIndex, 3);
					
					form.setCurrentBook(new Book(id, bookTitle, authorName, yearOfPublication));
					
					
				
				} else {
					JOptionPane.showMessageDialog(App.this, "Select a Row to update");
				}

			}
		});
		
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchQuery= searchField.getText().trim();
				if(!searchQuery.isEmpty()) {
					 List<Book> searchResult=bookController.searchBookByName(searchQuery);
					 //DefaultTableModel tableMo = (DefaultTableModel) table.getModel();
				tableModel.setRowCount(0);
				for(Book book: searchResult) {
					Object[] rowData= {book.getId(), book.getBookTitle(), book.getAuthorName(), book.getYearOfPublication()};
				tableModel.addRow(rowData);
				}
				}
			}
		});
		setLayout(new BorderLayout());
		form.addEvent(fev);

		add(form, BorderLayout.WEST);

		add(tableScrollPane, BorderLayout.CENTER);

		tableModel.addColumn("ID");
		tableModel.addColumn("Title");
		tableModel.addColumn("Author");
		tableModel.addColumn("Year");

		updateTable();

		JLabel titleLabel = new JLabel("Library Management System");
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel buttonPanel= new JPanel();
		buttonPanel.add(deleteButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(searchField);
		buttonPanel.add(searchBtn);
		
		add(titleLabel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);
		
		setVisible(true);
		setSize(900, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private void updateTable() {
		// Clear the table
		tableModel.setRowCount(0);

		// Get all books from the controller
		List<Book> books = bookController.getAllBooks();

		// Populate the table with book information
		for (Book b : books) {
			Object[] rowData = { b.getId(), b.getBookTitle(), b.getAuthorName(), b.getYearOfPublication() };
			tableModel.addRow(rowData);
		}
	}

}
