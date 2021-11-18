package dataaccess;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.User;


public class UserDB {
    public User get(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }
    }
    
    public User getByUUID(String resetUUID)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        User user = null;
        try
        {
            user = em.createNamedQuery("User.findByResetPasswordUuid", User.class)
                                            .setParameter("resetPasswordUuid", resetUUID)
                                            .getSingleResult();
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        
        return user;
    }
    
    public void update(User inputUser) throws Exception 
    {
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        
        try
        {
            entityTransaction.begin();
            
            entityManager.merge(inputUser);
            
            entityTransaction.commit();
        }
        catch (Exception e)
        {
            entityTransaction.rollback();
        }
        finally
        {
            entityManager.close();
        }
    }
}
