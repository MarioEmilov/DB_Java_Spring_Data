import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _11_FindEmployeesByFirstName {
    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        String pattern = scanner.nextLine() + "%";
        entityManager.createQuery("SELECT e FROM Employee e " +
                                "WHERE e.firstName LIKE :p_like",
                        Employee.class)
                .setParameter("p_like", pattern)
                .getResultStream()
                .forEach(employee -> System.out.printf("%s %s - %s - ($%.2f)%n",
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getJobTitle(),
                        employee.getSalary()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
