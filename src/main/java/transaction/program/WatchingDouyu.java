package transaction.program;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import transaction.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class WatchingDouyu {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private WatchingLengLeng watchingLengLeng;

    @Transactional
    public void serviceA() {

        watchingLengLeng.serviceB();

        Person person = new Person();
        person.setId(110);
        person.setAge(26);
        person.setName("huke");

        entityManager.persist(person);



//        throw new RuntimeException("exception of serviceA.");


    }
}
