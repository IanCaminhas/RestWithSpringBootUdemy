package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.model.Book;
import br.com.erudio.request.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository repository;

	public BookVO create(BookVO bookVO) {

		var entity = DozerConverter.parseObject(bookVO, Book.class);
		var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		return vo;
	}

	public BookVO update(BookVO book) {

		var entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		
		var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		return vo;
	}

	public void delete(Long id) {
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

	public BookVO findById(Long id) {
		var entity =  repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		return DozerConverter.parseObject(entity, BookVO.class);
	}

	public List<BookVO> findAll() {

		return DozerConverter.parseListObjects(repository.findAll(), BookVO.class);

	}

}
