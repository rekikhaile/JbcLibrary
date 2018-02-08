package com.riri.jbclibrary.controller;

import com.riri.jbclibrary.model.Book;
import com.riri.jbclibrary.repository.BookBorrowRepo;
import com.riri.jbclibrary.repository.BookRepo;
import com.riri.jbclibrary.repository.BookReturnRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class BookController {

    @Autowired
    BookRepo bookRepo;

    @Autowired
    BookBorrowRepo bookBorrowRepo;

    @Autowired
    BookReturnRepo bookReturnRepo;

    @RequestMapping("/")
    public String libraryHome(Model model){
        model.addAttribute("books",bookRepo.findAll());
        return "index";
    }

    @RequestMapping("/index")
    public String indexHome(Model model){
        model.addAttribute("books",bookRepo.findAll());
        return "index";
    }

    @GetMapping("/addbook")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
            return "addbook";
    }

    @PostMapping("/postaddbook")
    public String postAddBook(@Valid @ModelAttribute("book") Book book,
                              BindingResult result){
        if(result.hasErrors()){
            return "addbook";
        }
        bookRepo.save(book);
        return "redirect:/addbook";
    }

    @GetMapping("/listbook")
    public String listBook(Model model){
        model.addAttribute("books", bookRepo.findAll());
        return "listbook";
    }


    @GetMapping("/borrowbook/{id}")
    public String borrowBook(@PathVariable("id") long id, @ModelAttribute("book") Book book,Model model){
        model.addAttribute("booksavailable", bookBorrowRepo.findOne(id));
        bookReturnRepo.save(book);
        bookBorrowRepo.delete(id);

        model.addAttribute("availables", bookBorrowRepo.findAll());
        return "borrowbook";
    }

  /*  @PostMapping("/borrowbook/{id}")
    public String postBorrowBook(@Valid @ModelAttribute("book") Book book,
                                 BindingResult result){
        if(result.hasErrors()){
            return "borrowbook"
        }
        bookBorrowRepo.s
        return "redirect:/borrowbook/{id}";
    }*/

    @GetMapping("/returnbook")
    public String returnBook(){
        return "returnbook";
    }

    @PostMapping("/postreturnbook")
    public String postReturnBook(@PathVariable("id") long id, @ModelAttribute("book") Book book,Model model){
        model.addAttribute("book", bookReturnRepo.findAll());
        return "redirect:/";
    }

}
