package college.courses.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import college.courses.data.Course;
import college.courses.data.Professor;
import college.courses.exceptions.CourseNotFoundException;
import college.courses.exceptions.DuplicateCourseException;
import college.courses.exceptions.InvalidDataException;
import college.courses.model.CatalogManager;

/**
 * Servlet implementation class displayCourse
 */
@WebServlet("/core")
public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CoreServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.invalidate();

		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		if (session.getAttribute("userName") == ""
				|| session.getAttribute("userName") == null) {
			session.invalidate();

			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
			return;
		}
		RequestDispatcher rd = null;

		String action = request.getParameter("action");
		String cCode = request.getParameter("courseCode");

		CatalogManager cm = CatalogManager.getInstance();
		if (action.equals("Display Course")) {
			if (cCode.isEmpty() || cCode == null) {
				request.setAttribute("noCourse", "data");
				// response.sendRedirect("addCourse.jsp");
			} else {

				Course data = null;
				try {
					data = cm.getCourse(request.getParameter("courseCode"));
				} catch (CourseNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("course", data);
				request.setAttribute("CALLER", "Display Course");
				rd = request.getRequestDispatcher("/displayCourse.jsp");
			}
		} else if (action.equals("Delete Course")) {
			String cTitle = request.getParameter("courseTitleEmpty");
			if (cTitle.isEmpty() || cTitle == null) {
				response.sendRedirect("error.jsp");
			} else {
				Course data = null;
				try {
					data = cm.deleteCourse(request.getParameter("courseCode"));
				} catch (CourseNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("course", data);
				request.setAttribute("CALLER", "Delete Course");
				rd = request.getRequestDispatcher("/displayCourse.jsp");
			}
		} else if (action.equals("Add Course")) {

			Professor prof = null;
			try {
				prof = new Professor(Integer.parseInt(request
						.getParameter("professorId")),
						request.getParameter("firstName"),
						request.getParameter("lastName"));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Course course = new Course(request.getParameter("courseCode"),
						request.getParameter("courseTitle"), prof);
				Course addedCourse = cm.addCourse(course);
				request.setAttribute("course", addedCourse);
				request.setAttribute("CALLER", "Add Course");
				rd = request.getRequestDispatcher("/displayCourse.jsp");
			} catch (InvalidDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DuplicateCourseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("Update Course")) {
			String cTitle = request.getParameter("courseTitleEmpty");
			if (cTitle.isEmpty() || cTitle == null) {
				response.sendRedirect("error.jsp");
			} else {
				Professor prof = null;
				try {
					prof = new Professor(Integer.parseInt(request
							.getParameter("professorId")),
							request.getParameter("firstName"),
							request.getParameter("lastName"));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Course course = new Course(
							request.getParameter("courseCode"),
							request.getParameter("courseTitle"), prof);
					Course updatedCourse = null;
					try {
						updatedCourse = cm.updateCourse(course);
					} catch (CourseNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					request.setAttribute("course", updatedCourse);
					request.setAttribute("CALLER", "Update Course");
					rd = request.getRequestDispatcher("/displayCourse.jsp");
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		rd.forward(request, response);
	}

}
