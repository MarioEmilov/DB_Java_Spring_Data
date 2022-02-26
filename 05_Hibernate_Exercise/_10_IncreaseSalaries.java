import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class _10_IncreaseSalaries {
    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        int affectedRows = entityManager.createQuery(
                "UPDATE Employee e SET e.salary = e.salary*1.12 WHERE e.department.id IN(1,2,4,11)")
                .executeUpdate();
        entityManager.getTransaction().commit();
        List<Employee> employeeList = entityManager.createQuery("SELECT e FROM Employee e " +
                "WHERE e.department.id IN(1,2,4,11)", Employee.class).getResultList();
        System.out.printf("%d employees have received salary raise!%n", affectedRows);
        for (Employee employee : employeeList) {
            System.out.printf("%s %s ($%.2f)%n",
                    employee.getFirstName(), employee.getLastName(), employee.getSalary());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
