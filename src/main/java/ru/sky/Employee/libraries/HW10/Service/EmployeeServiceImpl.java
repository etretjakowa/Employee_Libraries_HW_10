package ru.sky.Employee.libraries.HW10.Service;

import org.springframework.stereotype.Service;
import ru.sky.Employee.libraries.HW10.Employee;
import ru.sky.Employee.libraries.HW10.EmployeeAlreadyAddedException;
import ru.sky.Employee.libraries.HW10.EmployeeDepartmentNotFoundException;
import ru.sky.Employee.libraries.HW10.InvalidNameException;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static org.apache.commons.lang3.StringUtils.isAlpha;
import static org.springframework.util.StringUtils.capitalize;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    List<Employee> employees = new ArrayList<>(List.of(
                new Employee("Пётр", "Петров", 2, 15000),
            new Employee("Иван", "Иванов", 3, 21000),
            new Employee("Семён", "Семёнов", 2, 24000),
            new Employee("Сергей", "Сидоров", 1, 38000),
            new Employee("Тимофей", "Тимофеев", 3, 31000),
            new Employee("Пётр", "Петров", 1, 26000),
            new Employee("Фёдор", "Фёдоров", 1, 27000),
            new Employee("Роман", "Романов", 3, 40000),
            new Employee("Евгений", "Крюков", 2, 25000),
            new Employee("Василий", "Васильев", 3, 38000)
    ));

    @Override
    public Employee addEmployee(String firstName, String lastName, int department, int salary) {
        validateNames(firstName, lastName);

        Employee employee = new Employee(capitalize(firstName), capitalize(lastName), department, salary);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует!");
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee getEmployeeWithMinSalaryOfDepartment(int department) {
        return this.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .min(comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeDepartmentNotFoundException("Отдел не найден"));
    }

    @Override
    public Employee getEmployeeWithMaxSalaryOfDepartment(int department) {
        return this.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .max(comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeDepartmentNotFoundException("Отдел не найден"));
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees);
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployees() {
        return this.findAll().stream()
                .collect(groupingBy(Employee::getDepartment));
    }

    @Override
    public Collection<Employee> getDepartmentEmployees(int department) {
        return this.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getDepartmentEmployeesMap() {
        return Map.of();
    }

    private void validateNames(String... names) {
        for (String name : names) {
            if (!isAlpha(name)) {
                throw new InvalidNameException(name);
            }
        }
    }
}