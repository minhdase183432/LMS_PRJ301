/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author msi2k
 */
public class UserError {
    private String emailError;
    private String nameError;
    private String passwordError;
    private String confirmpassError;
    private String roleError;
    private String error;

    public UserError() {
        this.emailError = "";
        this.nameError = "";
        this.passwordError = "";
        this.confirmpassError = "";
        this.roleError = "";
        this.error = "";
    }

    public UserError(String emailError, String nameError, String passwordError, String confirmpassError, String roleError, String error) {
        this.emailError = emailError;
        this.nameError = nameError;
        this.passwordError = passwordError;
        this.confirmpassError = confirmpassError;
        this.roleError = roleError;
        this.error = error;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmPassError() {
        return confirmpassError;
    }

    public void setConfirmPassError(String confirmpassError) {
        this.confirmpassError = confirmpassError;
    }

    public String getRoleError() {
        return roleError;
    }

    public void setRoleError(String roleError) {
        this.roleError = roleError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
    
    
}
