package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@FacesValidator
public class PasswordValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object password) throws ValidatorException {
        StringBuilder md5Password = new StringBuilder();
        FacesMessage wrongPasswordMsg = new FacesMessage("Wrong Password");
        wrongPasswordMsg.setSeverity(FacesMessage.SEVERITY_ERROR);
        try {
            byte[] bytesOfPassword = password.toString().getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(bytesOfPassword);
            for (byte b : digest) {
                md5Password.append(String.format("%02x", b & 0xff));
            }
            if (!md5Password.toString().equals("86cc2cf2e5c5db491cf3fd5e0ed75f00")) {
                throw new ValidatorException(wrongPasswordMsg);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new ValidatorException(wrongPasswordMsg);
        }
        // password correct
    }
}
