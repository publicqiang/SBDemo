package com.example.demo.controller;

import com.example.demo.dao.BookDao;
import com.example.demo.entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.UUID;

@Controller
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookDao bookDao;

    /**
     * 查询所有图书
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView findList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("bookList",bookDao.findAll());
        modelAndView.setViewName("bookList");
        return modelAndView;
    }

    /**
     * 添加图书
     * @param book 图书
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(Book book){

        book.setId(UUID.randomUUID().toString());
        bookDao.save(book);
        return "forward:/book/list";
    }

    /**
     * 根据id查询book
     * @PathVariable可以用来映射URL中的占位符到目标方法的参数中
     * @param id 图书id
     * @return
     */
    @RequestMapping("/preUpdate/{id}")
    public ModelAndView preUpdate(@PathVariable("id") String id){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("book",bookDao.getOne(id));
        modelAndView.setViewName("bookUpdate");
        return modelAndView;
    }

    /**
     * 修改图书
     * @param book 图书
     * @return
     */
    @RequestMapping("/update")
    public String update(Book book){
        bookDao.save(book);
        return "forward:/book/list";
    }

    /**
     * 删除图书
     * @param id 图书id
     * @return
     */
    @RequestMapping("/delete")
    public String delete(String id){
        bookDao.deleteById(id);
        return "forward:/book/list";

    }
}
