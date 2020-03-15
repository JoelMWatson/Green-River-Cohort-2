package joel.greenrivertech.net.quizapp;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static Context context;

    /**
     * This method sets the context of the application
     */
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    /**
     * This method gets the Context from the application
     *
     * @return the context
     */
    public static Context getContext(){
        return context;
    }
}
