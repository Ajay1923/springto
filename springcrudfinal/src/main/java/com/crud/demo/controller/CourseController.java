package com.crud.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.crud.demo.entity.Course;
import com.crud.demo.service.CourseService;

@Controller
public class CourseController {

    private final CourseService service;

    @Autowired
    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String findAll(Model model) {
        model.addAttribute("courses", service.findAllCourses());
        return "index";
    }

    @GetMapping("/add")
    public String launchAddCoursePage(Model model) {
        model.addAttribute("course", new Course());
        return "webpage";
    }

    @PostMapping("/addcourse")
    public String createCourse(@Valid @ModelAttribute Course course, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "webpage"; // Return to the form with validation errors
        }
        service.addCourse(course);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String launchEditPage(Model model, @PathVariable("id") int id) {
        Course course = service.findCourseById(id);
        model.addAttribute("course", course);
        return "editcourse";
    }

    @PostMapping("/updateCourse")
    public String updateCourse(@Valid @ModelAttribute Course course, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "editcourse"; // Return to the form with validation errors
        }
        service.updateCourse(course);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") int id) {
        service.deleteById(id);
        return "redirect:/";
    }
    
    @GetMapping("/logout-success")
    public String logoutSuccess() {
        return "logout"; 
    }
}
