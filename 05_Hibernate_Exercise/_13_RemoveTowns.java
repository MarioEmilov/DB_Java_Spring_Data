import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;


public class _13_RemoveTowns {
    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        String cityName = scanner.nextLine();

        entityManager.getTransaction().begin();

        List<Address> addresses = entityManager
                .createQuery("SELECT a FROM Address a Where a.town.name = :townName", Address.class)
                .setParameter("townName", cityName)
                .getResultList();


        System.out.printf("%d address in %s deleted%n", addresses.size(), cityName);
        if (addresses.size() == 0) {
            System.exit(0);
        }

        for (Address address : addresses) {
            for (Employee e : address.getEmployees()) {
                e.setAddress(null);
            }
            entityManager.flush();
            entityManager.remove(address);
        }
        Town town = addresses.get(0).getTown();
        entityManager.remove(town);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
