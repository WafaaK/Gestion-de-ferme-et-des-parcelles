/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaines;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import services.AdminService;

/**
 *
 * @author samia
 */
@ManagedBean(name="adminBean")
@SessionScoped
public class AdminController implements Serializable {
    private String userName;
    private String password;
    private boolean loggedIn;

    @EJB
    private AdminService adminService;

  public String login() {
    if (adminService.validateLogin(userName, password)) {
        loggedIn = true;
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInAdmin", true);

        return "/faces/home.xhtml?faces-redirect=true";
       
        // Rediriger vers la page d'accueil après une connexion réussie
    } else {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", "Invalid credentials"));
        return null; // Restez sur la même page
    }
}



    public String logout() {
        loggedIn = false;
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("loggedInAdmin");

        // Rediriger vers la page de déconnexion ou une autre page
        return "/index?faces-redirect=true";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    
}

