package bookStore.controller;

import bookStore.domain.Book;
import bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BookController
{
    @Autowired
    private BookService bookService;

    @RequestMapping("/details/{bookId}")
    public ModelAndView go2Detail(@PathVariable String bookId){
        Book book=bookService.getBook(bookId);
        ModelAndView modelAndView=new ModelAndView();
        if (book==null){
            modelAndView.setViewName("detail/404");
            return modelAndView;
        }

        modelAndView.addObject("book",book);
        modelAndView.setViewName("detail/detail");
        return modelAndView;
    }

    @RequestMapping("/category/{bookCategory}")
    public ModelAndView go2Category()
    {
        List<Book> bookList = bookService.getAllBooks();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("bookList", bookList);
        modelAndView.setViewName("category/category");
        return modelAndView;
    }

    @RequestMapping("foresearch")
    public ModelAndView serach(HttpServletRequest httpServletRequest)
    {
        Book book = bookService.search(httpServletRequest);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("book", book);
        modelAndView.setViewName("detail/" + book.getId());
        return  modelAndView;
    }
}
