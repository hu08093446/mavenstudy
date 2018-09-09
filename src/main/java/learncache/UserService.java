package learncache;

public class UserService {
    private MyCacheManager<User> myCacheManager;

    public UserService() {
        myCacheManager = new MyCacheManager<>();
    }

    public User getUserById(String userId) {
        User result = myCacheManager.getValue(userId);

        if(null != result) {
            System.out.println("get from cache......" + userId);

            return result;
        }

        result = getFromDB(userId);
        if(null != result) {
            myCacheManager.addOrUpdateCache(userId, result);
        }
        return result;
    }

    public void reload() {
        myCacheManager.evictCache();
    }

    private User getFromDB(String userId) {
        System.out.println("query from DB......" + userId);
        return new User(userId, userId+"hu", 27);
    }

}
