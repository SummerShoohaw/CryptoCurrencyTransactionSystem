/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RequestTest;

import Business.Email;
import javax.mail.MessagingException;
import org.junit.Test;

/**
 *
 * @author lm
 */
public class TestEmail {
    @Test
    public void testEmail(){
        String[] emailReceipeint = {"langmomo147@gmail.com"};
        Email smtpMailSender = new Email();
        try {
            smtpMailSender.postMail(emailReceipeint, "langman", "22222", Email.getSMTP_AUTH_USER());
        } catch (Exception ex) {
//            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
                    
        }
        System.out.println("Sucessfully Sent mail to All Users");
    }
    
}
