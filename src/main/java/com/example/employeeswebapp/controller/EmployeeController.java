package com.example.employeeswebapp.controller;

import com.example.employeeswebapp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.employeeswebapp.service.EmployeeService;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // display list of employees
    @GetMapping("/")
    public String viewHomePage(Model model) {
        String keyword = null;
        return findPaginated(1, "name", "asc", keyword, model);
    }


    @GetMapping("/showNewProductForm")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }

    @PostMapping("/saveProduct")
        public String saveEmployee(@ModelAttribute("product") Product product) {
            // save employee to database
            employeeService.saveEmployee(product);
            return "redirect:/";
        }

        @GetMapping("/showFormForUpdate/{id}")
        public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {

            // get employee from the service
            Product product = employeeService.getEmployeeById(id);

            // set employee as a model attribute to pre-populate the form
            model.addAttribute("product", product);
            return "update_product";
        }

        @GetMapping("/deleteEmployee/{id}")
        public String deleteEmployee(@PathVariable (value = "id") long id) {

            // call delete employee method
            this.employeeService.deleteEmployeeById(id);
            return "redirect:/";
        }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam(required=false, name="sortDir") String sortDir,
                                @RequestParam(required=false, name = "keyword") String keyword,
                                Model model) {
        int pageSize = 5;

        Page<Product> page = employeeService.listAll(pageNo, pageSize, sortField, sortDir, keyword);
        List<Product> listProducts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("keyword", keyword);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listEmployees", listProducts);
        return "index";
    }


}
