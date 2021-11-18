package services;

import dataaccess.UserDB;
import java.util.HashMap;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class AccountService {
    
    public User login(String email, String password, String path) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
//                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", email);
                
/*
                String to = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/emailtemplates/login.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());
                
                GmailService.sendMail(to, subject, template, tags);
*/
                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }

    public void sendPasswordResetEmail(String resetEmail, String webINFPath, String baseURL)
    {
        User supposedUser = new UserDB().get(resetEmail);
        
        if (supposedUser != null)
        {
            String newUUID = UUID.randomUUID().toString();
            supposedUser.setResetPasswordUuid(newUUID);
            
            try
            {
                new UserDB().update(supposedUser);
            }
            catch (Exception ex)
            {
                Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String resetLink = baseURL + "?resetUUID=" + supposedUser.getResetPasswordUuid();
            String destinationEmail = supposedUser.getEmail();
            String emailSubjectLine = "Notes App Password Reset";
            String template = webINFPath + "/emailtemplates/resetPassword.html";
            
            HashMap<String, String> templateVariableMap = new HashMap<>();
            templateVariableMap.put("passwordResetLink", resetLink);
            templateVariableMap.put("firstName", supposedUser.getFirstName());
            templateVariableMap.put("lastName", supposedUser.getLastName());
            
            try
            {
                GmailService.sendMail(destinationEmail, emailSubjectLine, template, templateVariableMap);
            }
            catch (Exception ex)
            {
                Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean resetPassword(String resetUUID, String resetPassword)
    {
        UserDB userConnection = new UserDB();
        boolean resetSuccess = false;
        try
        {
            User foundUser = userConnection.getByUUID(resetUUID);
            if (foundUser != null)
            {
                foundUser.setPassword(resetPassword);
                foundUser.setResetPasswordUuid(null);
                userConnection.update(foundUser);
                return true;
            }
        }
        catch (Exception ex)
        {

        }
        
        return resetSuccess;
    }
}
