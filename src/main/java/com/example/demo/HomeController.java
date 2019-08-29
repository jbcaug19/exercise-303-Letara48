package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    TodoRepository TodoRepository;

    @RequestMapping("/")
    public String listTodos(Model model){
        model.addAttribute("todos", TodoRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String TodoForm(Model model){
        model.addAttribute("todo", new Todo());
        return "todoform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Todo todo,
        BindingResult result) {
        if (result.hasErrors()){
            return "todoform";
        }
        TodoRepository.save(todo);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showTodo(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("todo", TodoRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateTodo(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("todo", TodoRepository.findById(id).get());
        return "Todoform";
    }
    @RequestMapping("/delete/{id}")
    public String delTodo(@PathVariable("id") long id){
        TodoRepository.deleteById(id);
        return "redirect:/";
    }
}


