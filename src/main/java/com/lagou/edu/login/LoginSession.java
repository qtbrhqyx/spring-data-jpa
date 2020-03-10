package com.lagou.edu.login;

import java.util.HashMap;
import java.util.Map;

public class LoginSession {
    private static LoginSession loginSession = new LoginSession();
    public Map m = new HashMap<>();
    public static LoginSession getLoginSession(){
        return loginSession;
    }
    public void putLoginSession(){
        m.put("admin",System.currentTimeMillis());
    }
}
