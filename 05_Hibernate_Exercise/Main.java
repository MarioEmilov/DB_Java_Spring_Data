import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        //Creating DataBase with connection
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();
        //Заявка
        entityManager.getTransaction().begin();

        Town town = entityManager.find(Town.class, 1);
        System.out.println(town);
        /*
        Hibernate:
    select
        town0_.town_id as town_id1_5_0_,
        town0_.name as name2_5_0_
    from
        towns town0_
    where
        town0_.town_id=?
    Town{id=1, name='Redmond'}
         */

        entityManager.getTransaction().commit();
    }
}
