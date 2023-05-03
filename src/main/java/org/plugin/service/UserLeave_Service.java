package org.plugin.service;

import org.plugin.db.DB_Connect;

public class UserLeave_Service {

    DB_Connect db = DB_Connect.getInstance();

    public void user_leave(String uuid){
        db.leave_user(uuid);
    }

}
