package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import configs.Dbconnection;
import services.ProductServices;
import utils.StringUtils;

/**
 * Servlet implementation class ModifyProductServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = StringUtils.SERVLET_URL_MODIFY_USER)
public class ModifyProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	  private final Dbconnection dbObj = new Dbconnection();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String updateId = request.getParameter(StringUtils.UPDATE_ID);
		String deleteId = request.getParameter(StringUtils.DELETE_ID);
		System.out.println("post triggered" + deleteId);

		if (updateId != null && !updateId.isEmpty()) {
			doPut(request, response);
		}
		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
		}
	}

@Override
protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	System.out.println("put triggered");
}

@Override
protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String productId = req.getParameter(StringUtils.DELETE_ID);
    if (productId != null && !productId.isEmpty()) {
        try {
            if (ProductServices.deleteProduct(productId) == 1) {
                req.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_DELETE);
            } else {
                req.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_DELETE);
            }
        } catch (ClassNotFoundException e) {
            req.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
            e.printStackTrace();
        }
    }
    resp.sendRedirect(req.getContextPath() + StringUtils.PRODUCT_LIST_PAGE);
}

}
