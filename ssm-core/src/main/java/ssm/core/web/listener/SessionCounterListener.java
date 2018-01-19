package ssm.core.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author jinqinghua@gmail.com
 * @version 2012/08/04
 */
public class SessionCounterListener implements HttpSessionListener {

    private static final String SESSION_COUNT = "ssm.core.web.listener.sessionCount";

    private int[] getCounter(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        ServletContext context = session.getServletContext();
        int[] counter = (int[]) context.getAttribute(SESSION_COUNT);
        if (null == counter) {
            counter = new int[1];
            context.setAttribute(SESSION_COUNT, counter);
        }
        return counter;
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        int[] counter = this.getCounter(httpSessionEvent);
        counter[0]++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        int[] counter = this.getCounter(httpSessionEvent);
        counter[0]--;
    }
}