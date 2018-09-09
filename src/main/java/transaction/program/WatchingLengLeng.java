package transaction.program;


import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import transaction.entity.PersonInfor;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class WatchingLengLeng {
    @PersistenceContext
    private EntityManager entityManager;
    @Resource
    private PlatformTransactionManager transactionManager;

    public void serviceB() {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus transactionStatus = null;

        try {
            transactionStatus = transactionManager.getTransaction(definition);

            PersonInfor personInfor = new PersonInfor();
            personInfor.setId(110);
            personInfor.setAge(26);
            personInfor.setName("huke");

            entityManager.persist(personInfor);
//            throw new RuntimeException("let me see.");

            transactionManager.commit(transactionStatus);

        } catch (Exception e) {
            System.out.println("serviceB encounter one exception.");
            transactionManager.rollback(transactionStatus);
        }

    }
}
