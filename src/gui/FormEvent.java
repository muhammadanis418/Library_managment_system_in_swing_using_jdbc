package gui;

import java.lang.ModuleLayer.Controller;
import java.util.EventListener;

import model.Book;

public interface FormEvent extends EventListener {

	void executeEvent(Book b);
	void updateEvent(Book b);
	//void executeEvent(Controller b);
//	void deleteEvent(int id);
}
