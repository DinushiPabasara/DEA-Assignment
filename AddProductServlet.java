/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
@WebServlet(urlPatterns = {"/AddProductServlet"})
public class AddProductServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
           try (PrintWriter out = response.getWriter()) {
       String itemid=request.getParameter("itemid");
       String itemname=request.getParameter("itemname");
       float price=Float.parseFloat(request.getParameter("price"));
       String description=request.getParameter("description");
       String image=request.getParameter("image");
       
       
       
         if(!(itemid.equals("")))
       { try{
         String driver="com.mysql.jdbc.Driver";
         Class.forName(driver);
         String url="jdbc:mysql://localhost:3306/productdb";
         Connection con=DriverManager.getConnection(url,"root","");
         String sql;
           sql = "INSERT INTO product  VALUES('"+itemid+"', '"+itemname+"' , '"+description+"', '"+image+"','"+price+"')";
          Statement st=con.createStatement();
         st.executeUpdate(sql);
         
        
        
          
       
    }
       catch(ClassNotFoundException | SQLException ex){
         out.println(ex);
       }
       }
       ProductDb p=new ProductDb();
      
        ArrayList vlist=p.viewItem();
        request.setAttribute("item_result", vlist);
        RequestDispatcher rd=request.getRequestDispatcher("ViewItem.jsp");
        rd.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
