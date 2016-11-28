package org.grizz.session;

import com.crozin.wykop.sdk.Command;

public interface Session {
    String execute(Command command);
}
