package model;

import java.io.Serializable;


public class Book  implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private static int countBook;
	
	private int id;
	private String bookTitle;
	private String authorName;
	private int yearOfPublication;
			
	public Book(int id, String bookTitle, String authorName, int yearOfPublication) {
		
		this.id = id;
		  //   countBook++;
		this.bookTitle = bookTitle;
		this.authorName = authorName;
		this.yearOfPublication = yearOfPublication;
	}



	public Book(String bookTitle, String authorName, int yearOfPublication) {
		
		this.bookTitle = bookTitle;
		this.authorName = authorName;
		this.yearOfPublication = yearOfPublication;
	}
public Book() {
	// TODO Auto-generated constructor stub
}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public int getYearOfPublication() {
		return yearOfPublication;
	}
	public void setYearOfPublication(int yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}
	
	 
}
