import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;


public class _09_FindLatestTenProjects {
    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Project> projectList = entityManager.createQuery("SELECT p FROM Project p ORDER BY p.startDate DESC ",
                        Project.class)
                .setMaxResults(10).getResultList();
        projectList.stream().sorted(Comparator.comparing(Project::getName))
                .forEach(project -> System.out.printf("Project name: %s%n\tProject Description: %s...%n\tProject Start Date: %s%n" +
                                "\tProject End Date: %s%n"
                        , project.getName(), project.getDescription().substring(0, 35), project.getStartDate()
                        , project.getEndDate()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
