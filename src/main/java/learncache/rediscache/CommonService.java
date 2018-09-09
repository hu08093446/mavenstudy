package learncache.rediscache;

import learncache.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Service;


@Service("commonService")
public class CommonService {
    private static final Logger LOG = LoggerFactory.getLogger(CommonService.class);

    @Autowired
    @Qualifier("myKeyGenerator")
    private KeyGenerator myKeyGenerator;

    @Cacheable(cacheNames = "queryuser", condition = "#userId.startsWith('0')", keyGenerator = "myKeyGenerator")
    public User queryUser(final String userId) {
        if (StringUtils.isEmpty(userId)) {
            String tempUserId = "0001";
            return new User(tempUserId, tempUserId+"hu", 27);
        } else {
            System.out.println("query from DB");
            return new User(userId, userId+"hu", 27);
        }
    }
}
