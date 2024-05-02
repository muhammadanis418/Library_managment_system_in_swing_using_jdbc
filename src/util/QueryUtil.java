package util;

public class QueryUtil {

	public static String insertBookQuery() {
		return "Insert Into book_info(bookTitle,authorName,yearOfPublication) values (?,?,?)";
	}

	public static String showAllBooks() {
		return "Select * from book_info";
	}

	public static String updateBook() {
		return "Update book_info SET bookTitle = ?,authorName= ?,yearOfPublication = ? where id =? ";
	}

	public static String deleteBook(int id) {
		return "Delete from book_info where id = " +id;
	}

	public static String searchByBookName(String bookTitle) {
		return "Select * from book_info where  bookTitle = '"+bookTitle+"'";
	}
//	public static String searchByBookName() {
//		return "Select * from book_info where  bookTitle = ?" ;
//	}
}
