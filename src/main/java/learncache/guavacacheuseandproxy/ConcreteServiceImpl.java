package learncache.guavacacheuseandproxy;

import learncache.Person;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("concreteService")
public class ConcreteServiceImpl implements ConcreteService{

    @Override
    @Cacheable(cacheNames = "guavacacheA", keyGenerator = "guavaKey")
    public Person getPerson(String userId) {
        return getFromDBPerson(userId);
    }


    private Person getFromDBPerson(String userId) {
        System.out.println("query person from DB......" + userId);
        return new Person(userId,27);
    }

}
