package codeu.controller;

import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.store.basic.UserStore;
import codeu.model.data.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by gavinlifrieri on 6/12/18.
 * Servlet fires when user navigates to /profiles and renders list of all profiles.
 */
public class ProfileListServlet extends HttpServlet {

    /**
     * Store class that gives access to Users.
     */
    private UserStore userStore;


    // Establishes UserStore using the Servlet
    void setUserStore(UserStore userStore) {
        this.userStore = userStore;
    }


    /* Set up state for handling profile requests.*/
    @Override
    public void init() throws ServletException {
        super.init();
        setUserStore(UserStore.getInstance());
    }

    /**
     * This function fires when a user navigates to the /profiles. It gets all of the
     * user's information and bio from the model and forwards to profiles.jsp for rendering the list.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

            List<User> list = userStore.getUsersList();
            request.setAttribute("users", list);
            request.getRequestDispatcher("/WEB-INF/view/profiles.jsp").forward(request, response);

    }
}