package cn.disruptive.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.disruptive.common.util.Constants;


public class ApplicationServlet extends HttpServlet {
	private static final Logger log = LoggerFactory.getLogger(ApplicationServlet.class);
	private static final long serialVersionUID = 1L;

	// private SecurityService securityService = SpringConfigTool.getBean("securityService");
	public void init() throws ServletException {
		// 常量赋值
		Constants.getInstance();

		log.info("平台启动成功");
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
