package voteSystem.Ui.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import voteSystem.Pojo.Option;
import voteSystem.Pojo.Subject;
import voteSystem.Pojo.User;
import voteSystem.Service.SubjectService;
import voteSystem.Service.impl.SubjectServiceImpl;
import voteSystem.util.exception.RuleException;
import voteSystem.util.format.DateUtil;

/**
 * Servlet implementation class DoAddServlet
 */

public class DoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
  //处理表单中填写的项目标题等数据，完成登录
  	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  	//1 获取提交数据
  		    String sid = request.getParameter("id");
  			String title=request.getParameter("title");
  			String number = request.getParameter("number");
  			String[] options=request.getParameterValues("options");
  			String startTime = request.getParameter("startTime");
  	        String endTime = request.getParameter("endTime");
  			Subject subject = new Subject();
  			subject.setTitle(title);
  			subject.setNumber(Integer.parseInt(number));
  			for(String content:options){
  				Option option = new Option();
  				option.setContent(content);
  				subject.getOptions().add(option);
  			}
  			try {
  				//2 创建业务逻辑对象，调用业务方法处理数据			
  				SubjectService subjectService =new SubjectServiceImpl();
  				if(sid==null || sid.trim().length()==0){
  					subjectService.add(subject,(User)request.getSession().getAttribute(User.SESSION_NAME));
  				}
  				else{
  					subject.setId(Integer.parseInt(sid));
  					subject.setStartTime(DateUtil.toLong(startTime));
  					subject.setEndTime(DateUtil.toLong(endTime));
  					subjectService.modify(subject,(User)request.getSession().getAttribute(User.SESSION_NAME));
  				}
  				response.sendRedirect(request.getContextPath() + "/list.do");
  			} catch(RuleException re){
  				HttpSession session = request.getSession();
  				session.setAttribute("subject", subject);
  				session.setAttribute("message", re.getMessage());
  				response.sendRedirect(request.getContextPath() + "/m/add.do");
  			}catch (Exception e) {
  				//交给全局的异常处理过滤器
  				throw new RuntimeException(e);
  			}
  	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
