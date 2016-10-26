package by.mnk.htp.glotovs.msr.command.client;

import by.mnk.htp.glotovs.msr.command.*;

/**
 * Created by Sefire on 25.10.2016.
 */
public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },

    REGISTRATIONFORM{
        {
            this.command = new RegistrationFormCommand();
        }
    },

    PASSREGISTRATION {
        {
            this.command = new PassRegistrationCommand();
        }
    },
    GETUSERINFO {
        {
            this.command = new GetUserInfoCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
