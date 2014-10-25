package college.courses.model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import college.courses.data.Course;
import college.courses.data.Professor;
import college.courses.exceptions.CourseNotFoundException;
import college.courses.exceptions.DuplicateCourseException;
import college.courses.exceptions.InvalidDataException;

public class CatalogManager implements CourseCatalog {

	private static CatalogManager cm = null;

	public synchronized static CatalogManager getInstance() {
		if (cm == null) {
			cm = new CatalogManager();
		}
		return cm;
	}

	public static Collection<Course> getAllCourses() {

		ArrayList<Course> courses = new ArrayList<Course>();
		java.sql.Connection con = null;

		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/COLLEGE", "root", "admin");

			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select COURSECODE,COURSETITLE,course.PROFID,GIVENNAME,FAMILYNAME from course,professor where course.PROFID=professor.PROFID ");

			while (rs.next()) {
				try {
					courses.add(new Course(rs.getString(1), rs.getString(2),
							new Professor(Integer.parseInt(rs.getString(3)), rs
									.getString(4), rs.getString(5))));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (InvalidDataException e) {
					e.printStackTrace();
				}
			}

			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return courses;
	}

	@Override
	public Course getCourse(String courseCode) throws CourseNotFoundException {
		if (courseCode == null) {
			throw new CourseNotFoundException("No course with null courseCode");
		}
		int flag = 0;

		Course course = null;
		java.sql.Connection con = null;
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/COLLEGE", "root", "admin");
			// getConnection();

			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select COURSECODE,COURSETITLE,course.PROFID,GIVENNAME,FAMILYNAME from course,professor where course.PROFID=professor.PROFID and COURSECODE like '"
							+ courseCode + "'");

			while (rs.next()) {
				flag = 1;
				try {
					course = (new Course(rs.getString(1), rs.getString(2),
							new Professor(Integer.parseInt(rs.getString(3)),
									rs.getString(4), rs.getString(5))));
					// System.out.println(new Course(rs.getString(1),
					// rs.getString(2), new
					// Professor(Integer.parseInt(rs.getString(3)),
					// rs.getString(4), rs.getString(5))));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (flag != 0) {

			return course;
		} else {
			throw new CourseNotFoundException("No course with code "
					+ courseCode);
		}
	}

	@Override
	public Course addCourse(Course c) throws DuplicateCourseException {

		if (c == null) {
			throw new DuplicateCourseException("Cannot add a null Course");
		}

		String code = c.getCourseCode();

		boolean courseAlreadyExist = false;

		java.sql.Connection con = null;
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/COLLEGE", "root", "admin");
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select COURSECODE from course where  COURSECODE like '"
							+ code + "'");

			if (rs.next()) {
				courseAlreadyExist = true;
			}

			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (courseAlreadyExist) {
			throw new DuplicateCourseException("Duplicate course code " + code);
		} else {
			// Add records......
			try {
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/college", "root", "admin");
				// getConnection();

				Statement st = con.createStatement();
				st.executeUpdate("Insert into professor(FAMILYNAME,GIVENNAME,PROFID) values('"
						+ c.getProfessor().getLastName()
						+ "','"
						+ c.getProfessor().getFirstName()
						+ "','"
						+ c.getProfessor().getProfId() + "')");
				int res = st
						.executeUpdate("Insert into course(COURSECODE,COURSETITLE,CAPACITY,ENROLLED,PROFID) values('"
								+ c.getCourseCode()
								+ "','"
								+ c.getCourseTitle()
								+ "',100,0,'"
								+ c.getProfessor().getProfId() + "')");

				st.close();
				con.close();
				if (res != 0) {
					System.out.println("Added Successfully......");
				} else {
					System.out.println("Adding course failed......");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return c;
	}

	@Override
	public Course updateCourse(Course c) throws CourseNotFoundException {
		if (c == null) {
			throw new CourseNotFoundException("Cannot update a null Course");
		}
		Course oldC = getCourse(c.getCourseCode());
		if (c.equals(oldC)) {
			// no change - nothing to do
			return c;
		}
		// insert changed course
		java.sql.Connection con = null;
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/COLLEGE", "root", "admin");
			// getConnection();
			Statement st = con.createStatement();
			st.executeUpdate("Update course set COURSETITLE='"
					+ c.getCourseTitle() + "',PROFID='"
					+ c.getProfessor().getProfId() + "' where COURSECODE='"
					+ c.getCourseCode() + "'");
			st.executeUpdate("Update professor set FAMILYNAME='"
					+ c.getProfessor().getLastName() + "',GIVENNAME='"
					+ c.getProfessor().getFirstName() + "'where PROFID='"
					+ c.getProfessor().getProfId() + "'");

			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// courses.put(c.getCourseCode(), c);
		// retrieve again to get derived fields, if there are any
		return getCourse(c.getCourseCode());
	}

	@Override
	public Course deleteCourse(String courseCode)
			throws CourseNotFoundException {
		if (courseCode == null) {
			throw new CourseNotFoundException("No course with null courseCode");
		}

		boolean courseExist = false;

		java.sql.Connection con = null;
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/COLLEGE", "root", "admin");
			// getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select COURSECODE from course where  COURSECODE like '"
							+ courseCode + "'");

			if (rs.next()) {
				courseExist = true;
			}

			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (courseExist) {
			Course c = cm.getCourse(courseCode);

			try {
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/COLLEGE", "root", "admin");
				//
				Statement st = con.createStatement();
				st.executeUpdate("Delete from course where COURSECODE='"
						+ courseCode + "'");

				st.close();
				con.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return c;
		} else {

			throw new CourseNotFoundException("No course with code "
					+ courseCode);
		}
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		CatalogManager cm = CatalogManager.getInstance();
	}

	public String numOfCourses() {
		String numOfCorse = "0";
		java.sql.Connection con = null;
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/COLLEGE", "root", "admin");
			// getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM course");

			while (rs.next()) {
				numOfCorse = String.valueOf(rs.getInt(1));
			}
			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numOfCorse;

	}
}
