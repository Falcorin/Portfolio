import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Purchase purchase = new Purchase();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
            Root<Course> root = criteriaQuery.from(Course.class);
            criteriaQuery.select(root);
            Query<Course> query = session.createQuery(criteriaQuery);
            List<Course> courseList = query.getResultList();
            for (Course course : courseList) {
                List<Student> studentsFoCourse = course.getStudents();
                for (Student student : studentsFoCourse) {
                    purchase.setId(new Key());
                    purchase.setCourseName(course.getName());
                    purchase.setStudentName(student.getName());
                    purchase.setPrice(course.getPrice());
                    purchase.setSubscriptionsDate(student.getRegistrationDate());
                    session.update(purchase);
                }
            }
        }
    }
}
