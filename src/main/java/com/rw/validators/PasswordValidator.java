package com.rw.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Zacharie BARBECOT <z.barbecot@gmail.com>
 */
@FacesValidator(value = "passwordValidator")
public class PasswordValidator implements Validator {

    private static final String confirmPassword = "confirmPassword";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        UIInput passwordc = (UIInput) component.getAttributes().get(PasswordValidator.confirmPassword);
        String password = (String) passwordc.getValue();
        String confirm = (String) value;
        if (confirm != null && !confirm.equals(password)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Passwords are differents", null));
        }
    }

}
