import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class _12_EmployeesMaximumSalaries {
    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Employee> employeeList = entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.salary NOT BETWEEN 30000 AND 70000",
                        Employee.class)
                .getResultList();

        Set<Department> departmentList = employeeList.stream()
                .map(Employee::getDepartment)
                .collect(Collectors.toSet());

        for (Department d : departmentList) {
            BigDecimal max = BigDecimal.valueOf(0);

            for (Employee e : employeeList) {
                if (e.getDepartment().getId().equals(d.getId())) {
                    if (max.compareTo(e.getSalary()) < 1) {
                        max = e.getSalary();
                    }
                }
            }
            System.out.printf("%s - $%.2f%n", d.getName(), max);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
