package voteSystem.Ui.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import voteSystem.Pojo.Record;
import voteSystem.Pojo.User;
import voteSystem.Service.RecordService;
import voteSystem.Service.impl.RecordDaoServiceImpl;
import voteSystem.util.exception.RuleException;

/**
 * Servlet implementation class DoVoteServlet
 */

public class DoVoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoVoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1 获取浏览器提交的数据：账号，密码和确认密码
			int subjectId=Integer.parseInt(request.getParameter("subjectId"));
			String[] ids = request.getParameterValues("options");
			User user= (User)request.getSession().getAttribute(User.SESSION_NAME);
			List<Record> records = new ArrayList<Record>();
			if(ids!=null) {
				for(int i=0;i<ids.length;i++){
					Record record = new Record();
					record.setUserId(user.getId());
					record.setSubjectId(subjectId);
					record.setOptionId(Integer.parseInt(ids[i]));				
					records.add(record);
				}
			}
			try {
				//2 创建业务逻辑类的对象，调用业务方法处理
				RecordService recordService=new RecordDaoServiceImpl();
				recordService.vote(records,user,subjectId);
				//3 TODO 重定向到登录页面，暂时显示结果			
				response.sendRedirect(request.getContextPath()+"/list.do");
			}catch (RuleException e) {
				request.getSession().setAttribute("message", e.getMessage());
				response.sendRedirect(request.getContextPath()+"/m/vote.do?id="+subjectId);
			}catch(Exception ex){
				throw new RuntimeException(ex);
			}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
