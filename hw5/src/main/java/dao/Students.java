package dao;

import entity.Student;
import hibernate.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Students {

    private final SessionFactory sessionFactory;
    private Session session;

    public Students() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    public void addNewStudent(Student student) {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Student getStudentById(Long id) {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Student studentFromDb = session.get(Student.class, id);
            session.getTransaction().commit();
            return studentFromDb;
        } finally {
            sessionFactory.close();
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteStudent(Long id) {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.delete(session.get(Student.class, id));
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
            if (session != null) {
                session.close();
            }
        }
    }

    public void updateStudent(Long id, String name, int mark) {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Student student = session.createQuery("SELECT s FROM Students s WHERE s.id = :id", Student.class)
                    .setParameter("id", id)
                    .getSingleResult();
            student.setFio(name);
            student.setMark(mark);
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
            if (session != null) {
                session.close();
            }
        }
    }




}
