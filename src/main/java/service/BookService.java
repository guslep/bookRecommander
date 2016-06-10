package service;

import com.mkyong.Model.Book;
import com.mkyong.Model.BookRating;
import com.mkyong.Model.EnrichedBook;
import com.mkyong.Model.EnrichedBookRating;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.BooksRepository;

import java.util.*;

/**
 * Created by Guillaume on 2016-05-21.
 */
public class BookService {
    private static BookService ourInstance = new BookService();
        BooksRepository booksRepository;
    Map<String,Book> bookMap;
    private Map<String,EnrichedBook> enrichedBook;


    public static BookService getInstance() {
        return ourInstance;
    }

    private BookService() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Module.xml");

        booksRepository = (BooksRepository) context.getBean("bookRepository");    }


    public Collection<Book> getBookrs(){
        if(bookMap==null){
            generateMap(booksRepository.getAllBook());
        }
        return bookMap.values() ;
    }
    public  Map<String,Book> getBookrsMap(){
        if(bookMap==null){
            generateMap(booksRepository.getAllBook());
        }
        return bookMap ;
    }

    public Book getBook(String ISBN){
        if(bookMap==null){
            generateMap(booksRepository.getAllBook());
        }
        return bookMap.get(ISBN);
    }

    private  void generateMap(List<Book> bookList){
        bookMap  = new HashMap<String, Book>();
        for (Book book : bookList){
            bookMap.put(book.getISBN(),book);
        }


    }


    public  Map<String, EnrichedBook> generateEnrichedBook(){
        enrichedBook  = new HashMap<String, EnrichedBook>();
      Iterator<Book> iter=bookMap.values().iterator();
        while (iter.hasNext()){

            Book book=iter.next();
            EnrichedBook enriched=new EnrichedBook();
            enriched.setAuthor(book.getAuthor());
            enriched.setBookTitle(book.getBookTitle());
            enriched.setISBN(book.getISBN());
            enriched.setPublicationYear(book.getPublicationYear());
            enriched.setPublisher(book.getPublisher());
            enriched.setTags(book.getTags());
            enrichedBook.put(book.getISBN(),enriched);
        }

        List<BookRating> ratings=RatingService.getInstance().getRatings();
        for(BookRating bookRating:ratings){
            enrichedBook.get(bookRating.getISBN()).getAssociatedRtings().put(bookRating.getISBN()+bookRating.getUserId(),bookRating);
        }

            return enrichedBook;
    }
}
