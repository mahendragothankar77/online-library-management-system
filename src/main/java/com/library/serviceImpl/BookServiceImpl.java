package com.library.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.entity.Book;
import com.library.entity.BookStatus;
import com.library.exception.ResourceNotFoundException;
import com.library.repository.BookRepository;
import com.library.services.BookService;
@Service
public class BookServiceImpl implements BookService{
	
	@Autowired 
	private BookRepository repo;

	@Override
	 public Book addBook(Book book) {
        book.setStatus(BookStatus.AVAILABLE);
       
        return repo.save(book);
    }
	@Override
    public Book getBook(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }
	@Override
    public List<Book> search(String name, String author, String category, BookStatus status) {
        if (name != null) return repo.findByNameContaining(name);
        if (author != null) return repo.findByAuthorContaining(author);
        if (category != null) return repo.findByCategory(category);
        if (status != null) return repo.findByStatus(status);
        return repo.findAll();
    }
	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
