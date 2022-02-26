import entities.Town;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateMain {
    public static void main(String[] args) {
        //Creating DataBase with connection
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session currentSession = sessionFactory.openSession();

        //Заявка
        currentSession.beginTransaction();

        Town town = currentSession.get(Town.class, 1);
        System.out.println(town);

        /* Същата заявка с Хайбърнате
        Hibernate:
    select
        town0_.town_id as town_id1_0_0_,
        town0_.name as name2_0_0_
    from
        towns town0_
    where
        town0_.town_id=?
    Town{id=1, name='Redmond'}
         */

        currentSession.getTransaction().commit();
    }
}
