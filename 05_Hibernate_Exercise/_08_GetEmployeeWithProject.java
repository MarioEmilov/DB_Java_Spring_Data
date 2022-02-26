import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _08_GetEmployeeWithProject {
    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        Integer employeeId = Integer.parseInt(scanner.nextLine());
        Employee employee = entityManager.find(Employee.class, employeeId);

        System.out.printf("%s %s - %s%n",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getJobTitle());

        employee.getProjects().stream()
                .map(Project::getName)
                .sorted()
                .forEach(project -> System.out.printf("\t%s%n", project));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
