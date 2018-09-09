package learncache.learnspringcache;

import learncache.Person;
import learncache.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


@Service("userService")
public class UserService {

    // 下面这些Keygenerator根本不需要注入，@Cacheable注解会根据bean名自己去找
//    @Autowired
//    // 这种方式是可以的， 按名字没找到，按照类型找，找到，OK
//    private MykeyGenerator keyGeneratorA;
//
////    @Autowired
////    // 这种方式不行，因为默认是将对象名作为bean名去查找，没找到，再按照类型找
////    // 结果找到两个，不知道用哪个
////    private KeyGenerator keyGeneratorB;
//
//    @Autowired
//    // 这种方式是可以的,指定了bean名，不会找错
//    @Qualifier("myKeyGenerator")
//    private KeyGenerator keyGeneratorC;
//
//    @Autowired
//    // 这种方式也是可以的,按照名字可以找到
//    private KeyGenerator myKeyGenerator;
//
//    @Resource(name = "anotherKey")
//    private KeyGenerator anotherKeyGenerator;

    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    public void setUp() {
        Cache usersCache = cacheManager.getCache("users");
        usersCache.put(SimpleKey.EMPTY, new User("000", "000huke", 27));

        cacheManager.getCache("users").put("getPerson", new Person("cuixiaoyu", 24));
    }

    @Cacheable(cacheNames = "users", condition = "#userId.startsWith('0')", keyGenerator = "myKeyGenerator")
    public User getUserById(String userId) {
        User result = getFromDB(userId);

        return result;
    }

    /**
     * 同一个cache可以存放不同类型的数据，但是要主要key值不要混淆，不然会出现类型转换异常
     * 所以同一个cache最好只存放同一种类型的数据
     *
     * @param userId
     * @return
     */
    @Cacheable(cacheNames = "users", condition = "#userId.startsWith('1')", keyGenerator = "anotherKey")
    public Person getPerson(String userId) {
        Person result = getFromDBPerson(userId);

        return result;
    }

    private User getFromDB(String userId) {
        System.out.println("query user from DB......" + userId);
        return new User(userId, userId+"hu", 27);
    }

    private Person getFromDBPerson(String userId) {
        System.out.println("query person from DB......" + userId);
        return new Person(userId,27);
    }

}
