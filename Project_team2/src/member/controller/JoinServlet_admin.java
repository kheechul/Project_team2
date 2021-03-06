package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.dao.MemDAO;
import member.dto.MemVO;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/join_admin.do")
public class JoinServlet_admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServlet_admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher=request.getRequestDispatcher("admin/member/JoinForm_admin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		request.setCharacterEncoding("UTF-8");
		
		String id=request.getParameter("id");
		String password1=request.getParameter("password1");
		String password2=request.getParameter("password2");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		
		
		MemVO mvo=new MemVO();
		mvo.setId(id);
		mvo.setPassword1(password1);
		mvo.setPassword2(password2);
		mvo.setName(name);
		mvo.setEmail(email);
		mvo.setPhone(phone);
		
		MemDAO mdao=MemDAO.getInstance(); // ?????? ?????? ??????(private=>static??? ?????????.????????? ?????? ????????????)/ ???????????? ???????????? ????????????
		int result=mdao.insertMember(mvo);
		
		HttpSession session=request.getSession(); //???????????? ?????? ??? ?????? ??????
		
		if(result == 1) {
			session.setAttribute("id",mvo.getId());
			request.setAttribute("message","?????? ????????? ??????????????????");
		}else {
			request.setAttribute("message","?????? ????????? ??????????????????");
		}
		
		//RequestDispatcher dispatcher=request.getRequestDispatcher("index.jsp?pageChange=MemberList_admin.jsp");
		RequestDispatcher dispatcher=request.getRequestDispatcher("admin/MemberJoinResult_admin.jsp"); // ?????? ???????????? ?????? ?????? ?????? ??????
		dispatcher.forward(request, response); //forward????????? login.jsp??? ????????????
		
		//response.sendRedirect("main.jsp");
	}

}
