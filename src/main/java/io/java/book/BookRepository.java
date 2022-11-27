package io.java.book;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import io.java.book.Book;

@Repository
public interface BookRepository extends CassandraRepository<Book,String> {


}
