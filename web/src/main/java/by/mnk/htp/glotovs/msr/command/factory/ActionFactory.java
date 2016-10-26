package by.mnk.htp.glotovs.msr.command.factory;

import by.mnk.htp.glotovs.msr.command.ActionCommand;
import by.mnk.htp.glotovs.msr.command.EmptyCommand;
import by.mnk.htp.glotovs.msr.command.client.CommandEnum;
import by.mnk.htp.glotovs.msr.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Sefire on 25.10.2016.
 */
public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum =
                    CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action
                    + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}
