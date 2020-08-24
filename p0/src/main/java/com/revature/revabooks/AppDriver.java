package com.revature.revabooks;

import com.revature.revabooks.util.AppState;

public class AppDriver {

    public static AppState app = new AppState();

    public static void main(String[] args) {

        while(app.isAppRunning()) {
            app.getRouter().navigate("/home");
        }

    }

}
