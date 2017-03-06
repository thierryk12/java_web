/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfitness.utils;

import com.supinfo.supfitness.dao.UserDao;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Leo
 */
@FacesValidator("usernameExistValidator")
public class UsernameExistValidator implements Validator{
    
    @EJB
    private UserDao userDao;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
       
        for (int i = 0; i < userDao.retrieveAllUsers().size(); i++) {
            if (value.equals(userDao.retrieveAllUsers().get(i).getUsername())) {
                context.addMessage(null, new FacesMessage("This name was used by another user,please use another one"));
                context.validationFailed();
            ((UIInput) component).setValid(false);
            }
            
        }
    }
    
}
