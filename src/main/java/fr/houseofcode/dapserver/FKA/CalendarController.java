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

/**
 * @author lenovo
 *
 */
@RestController
public class CalendarController {

    @Autowired
    private CalendarService calService;

    @RequestMapping("/event/next")
    public String displayNextEvent(@RequestParam final String userKey) throws IOException, GeneralSecurityException {
        return calService.afficherNextEvent(userKey);
    }

}
