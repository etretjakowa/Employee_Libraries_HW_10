package ru.sky.Employee.libraries.HW10.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sky.Employee.libraries.HW10.Employee;
import ru.sky.Employee.libraries.HW10.Service.EmployeeServiceImpl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/max-salary")
    public Employee getEmployeeWithMaxSalaryOfDepartment(@RequestParam int department) {
        return employeeService.getEmployeeWithMaxSalaryOfDepartment(department);
    }

    @GetMapping(path = "/min-salary")
    public Employee getEmployeeWithMinSalaryOfDepartment(@RequestParam int department) {
        return employeeService.getEmployeeWithMinSalaryOfDepartment(department);
    }

    @GetMapping(path = "/all")
    public Map<Integer, List<Employee>> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(value = "/all", params = {"department"})
    public Collection<Employee> getDepartmentEmployees(@RequestParam int department) {
        return employeeService.getDepartmentEmployees(department);
    }
}