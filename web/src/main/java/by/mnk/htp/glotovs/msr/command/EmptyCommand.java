package by.mnk.htp.glotovs.msr.command;

import by.mnk.htp.glotovs.msr.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Sefire on 25.10.2016.
 */
public class EmptyCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
/* в случае ошибки или прямого обращения к контроллеру
* переадресация на страницу ввода логина */
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}
