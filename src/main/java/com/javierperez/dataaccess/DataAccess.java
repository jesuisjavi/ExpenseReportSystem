package com.javierperez.dataaccess;

import com.javierperez.workplace.*;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;
import com.javierperez.utils.*;

public class DataAccess {
    private SessionFactory sessionFactory;
    private static DataAccess dataAccess = new DataAccess();

    private DataAccess() {
       ConfigureDBConnection();
    }

    public static DataAccess getInstance(){
        return dataAccess;
    }

    private void ConfigureDBConnection() {
        Configuration config = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
        sessionFactory = config.buildSessionFactory(builder.build());
    }

    public LoginInfo Login(String username, String password){
        Employee employee = GetEmployeeByUsername(username);
        if (employee != null && employee.getPassword().equals(password))
            return new LoginInfo(true, employee.getJobPosition(), employee.getId());
        return new LoginInfo(false, "", 0);
    }

    public Employee GetEmployeeByUsername(String username){
        try{
            Session session = this.sessionFactory.openSession();
            String hql = "from Employee where username = :u";
            Query query = session.createQuery(hql);
            query.setString("u", username);

            List employee = query.list();
            session.close();
            if (employee.size() != 0)
                return (Employee) employee.get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Employee GetEmployeeById(int Id){
        try{
            Session session = this.sessionFactory.openSession();
            String hql = "from Employee where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", Id);

            List employee = query.list();
            session.close();
            if (employee.size() != 0)
                return (Employee) employee.get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Employee> GetAllEmployees(){
        try{
            Session session = this.sessionFactory.openSession();
            return session.createCriteria(Employee.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Email> GetEmployeeEmails(int Id){
        try{
            Session session = this.sessionFactory.openSession();
            String hql = "from Email where receiverid = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", Id);

            List emails = query.list();
            session.close();
            return emails;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<ExpenseRequest> GetRequestsByEmployee(int requesterId){
        try{
            Session session = this.sessionFactory.openSession();
            String hql = "from ExpenseRequest where requesterid = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", requesterId);

            List requests = query.list();
            session.close();
            return requests;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<ExpenseRequest> GetAllRequests(){
        try{
            Session session = this.sessionFactory.openSession();
            return session.createCriteria(ExpenseRequest.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ExpenseRequest GetRequestById(int id){
        try{
            Session session = this.sessionFactory.openSession();
            String hql = "from ExpenseRequest where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", id);

            List requests = query.list();
            session.close();
            return (ExpenseRequest) requests.get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void SaveEntity(Object entity){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }

    public void UpdateEntity(Object entity){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        session.close();
    }
}

