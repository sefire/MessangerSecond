package by.mnk.htp.glotovs.msr.command.client;

import by.mnk.htp.glotovs.msr.command.*;

/**
 * Created by Sefire on 24.10.2016.
 */
public enum CommandEnum {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATIONFORM(new RegistrationFormCommand()),
    PASSREGISTRATION(new PassRegistrationCommand()),
    AFTERREGISTRATION(new AfterRegistrationCommand()),
    GETUSERINFO(new GetUserInfoCommand());

    ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
