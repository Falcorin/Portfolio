import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Purchaselist")
public class Purchase {
    @EmbeddedId
    private Key id;
    @Column(name = "student_id", insertable = false, updatable = false, nullable = true)
    private int studentId;
    @Column(name = "course_id", insertable = false, updatable = false, nullable = true)
    private int courseId;
    @Column(name = "student_name", nullable = true)
    private String studentName;
    @Column(name = "course_name", nullable = true)
    private String courseName;
    private int price;
    @Column(name = "subscription_date", nullable = true)
    private Date subscriptionsDate;

    public int getPrice() {
        return price;
    }

    public void setPrice(int prise) {
        this.price = prise;
    }

    public Date getSubscriptionsDate() {
        return subscriptionsDate;
    }

    public void setSubscriptionsDate(Date subscriptionsDate) {
        this.subscriptionsDate = subscriptionsDate;
    }

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
