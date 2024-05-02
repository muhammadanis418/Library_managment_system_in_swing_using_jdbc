package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Book;
import util.DataBaseConfig;
import util.QueryUtil;

public class BookController {

	private Connection con;

	private DataBaseConfig dbconfig;
	private PreparedStatement ps;

	public BookController() {
		this.dbconfig = new DataBaseConfig();
		this.con = DataBaseConfig.getDbConnection();
	}

	public void addBook(Book b) {
		try {
			PreparedStatement p = con.prepareStatement(QueryUtil.insertBookQuery());
			p.setString(1, b.getBookTitle());
			p.setString(2, b.getAuthorName());
			p.setInt(3, b.getYearOfPublication());

			int row = p.executeUpdate();

			if (row > 0) {

				System.out.println(row + " Book added Successfully");

			}

		} catch (SQLException ex) {

		}

	}

	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<Book>();
		try {
			ps = con.prepareStatement(QueryUtil.showAllBooks());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				int bookId = rs.getInt("id");
				String title = rs.getString("bookTitle");
				String author = rs.getString("authorName");
				int year = rs.getInt("yearOfPublication");

				books.add(new Book(bookId, title, author, year));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println(books.toString());
		return books;
	}

	public void deleteBYId(int id) {
		try {
			Statement st = con.createStatement();
			int row = st.executeUpdate(QueryUtil.deleteBook(id));

			if (row > 0) {
				System.out.println(row + "Row deleted");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int updateBook(Book book) {
		try {
			ps = con.prepareStatement(QueryUtil.updateBook());
			
//           System.out.println(ps);
           
			ps.setString(1, book.getBookTitle());
			ps.setString(2, book.getAuthorName());
			ps.setInt(3, book.getYearOfPublication());
			ps.setInt(4, book.getId());
			System.out.println(ps);
			int row = ps.executeUpdate();
			
			if (row > 0) {
				System.out.println(row + "Row updated Successfully");
				
			} 
			//con.close();
			return row;
		} catch (SQLException ex) {
			System.err.print("Error in updateBook method");
			return 0;
		}
	}

	public List< Book> searchBookByName(String bookTitle) {
       List<Book> result= new ArrayList<Book>();
		try {

			ps = con.prepareStatement(QueryUtil.searchByBookName(bookTitle));
			
			System.out.println(ps);

			ResultSet rs = ps.executeQuery();
			
			Book b = null;
			while (rs.next()) {
				b = new Book();
			    b.setId(rs.getInt("id"));
				b.setBookTitle(rs.getString("bookTitle"));
				b.setAuthorName(rs.getString("authorName"));
				b.setYearOfPublication(rs.getInt("yearOfPublication"));
				result.add(b);
			}
			
			ps.close();
			return result;
			
		} catch (SQLException ex) {
			System.err.print("Error in searchBookByName Method"+ex);
			return null;
		}

	}

}
