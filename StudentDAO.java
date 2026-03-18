import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // 1. Add student
    public void addStudent(Student student) {
        String query = "INSERT INTO students (name, age, course) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getCourse());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("[SUCCESS] Student added successfully!");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Error adding student: " + e.getMessage());
        }
    }

    // 2. View all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("course")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Error fetching students: " + e.getMessage());
        }
        return students;
    }

    // 3. Update student details
    public void updateStudent(Student student) {
        String query = "UPDATE students SET name = ?, age = ?, course = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getCourse());
            pstmt.setInt(4, student.getId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("[SUCCESS] Student updated successfully!");
            } else {
                System.out.println("[WARNING] Student ID not found.");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Error updating student: " + e.getMessage());
        }
    }

    // 4. Delete student
    public void deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("[SUCCESS] Student deleted successfully!");
            } else {
                System.out.println("[WARNING] Student ID not found.");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Error deleting student: " + e.getMessage());
        }
    }
}
