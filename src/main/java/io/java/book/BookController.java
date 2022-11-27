package io.java.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class BookController {

    private final String COVER_IMAGE_ROOT = "http://covers.openlibrary.org/b/id/";

    @Autowired
    BookRepository bookRepository;


    @GetMapping(value = "/")
    public String index()
    {
        return "index";
    }

    @GetMapping(value = "/books/{bookId}")
    public String getBook(@PathVariable String bookId , Model model)
    {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isPresent())
        {
            Book book = optionalBook.get();
            String coverImageUrl ="/images/no-img.png";
            if(book.getCoverID() != null && book.getCoverID().size() >0)
            {
                coverImageUrl = COVER_IMAGE_ROOT + book.getCoverID().get(0) + "-L.jpg";
            }
            model.addAttribute("coverImage",coverImageUrl);
            model.addAttribute("book",book);
            return "book";
        }
        return "book-not-found";
    }


}
