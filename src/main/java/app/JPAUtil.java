package app;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE"; // String variable that store our persistence
                                                                       // name we set in the persistence.xml in persistence-unit)
    private static EntityManagerFactory factory; // object that contains the database connection

    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null){ // retrieving db connection
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME); // using static method createEntityManagerFactory
                                                                                     // from class persistence to set our persistence name
        }
        return factory; // returns object with connection attributes
    }

    public static void shutdown(){
        if (factory != null){ // closing db connection
            factory.close();
        }
    }

}
