package com.riri.jbclibrary.repository;

import com.riri.jbclibrary.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookReturnRepo extends CrudRepository<Book, Long> {
}
