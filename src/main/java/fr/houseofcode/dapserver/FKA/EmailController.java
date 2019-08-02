/**
 * 
 */
package fr.houseofcode.dapserver.FKA;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dapserver.FKA.google.CalendarService;
import fr.houseofcode.dapserver.FKA.google.GmailService;

/**
 * @author lenovo
 *
 */
@RestController
public class EmailController {

    /** A ready to use Gmail Service. **/
    @Autowired
    private GmailService gmService;
    private CalendarService getService;

    @RequestMapping("email/nbUnread")
    public Integer displayNbUnreadEmail(@RequestParam final String userKey)
            throws IOException, GeneralSecurityException {
        return gmService.getNbUnreadEmail(userKey);
    }

    @RequestMapping("email/labels")
    public String displaylabels(@RequestParam final String userKey) throws IOException, GeneralSecurityException {
        return gmService.getLabels(userKey);
    }

    @RequestMapping("email/afficherNextEvent")
    public String displayafficherNextEvent(@RequestParam final String userKey)
            throws IOException, GeneralSecurityException {
        return getService.afficherNextEvent(userKey);
    }
}
