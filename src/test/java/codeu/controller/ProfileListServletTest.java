package codeu.controller;

import codeu.model.data.User;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.UserStore;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

public class ProfileListServletTest {

    private ProfileListServlet profileListServlet;
    private UserStore mockUserStore;
    private HttpServletRequest mockRequest;
    private HttpServletResponse mockResponse;
    private RequestDispatcher mockRequestDispatcher;
    private ConversationStore mockConversationStore;


    @Before
    public void setup() {
        profileListServlet = new ProfileListServlet();
        mockRequest = Mockito.mock(HttpServletRequest.class);
        mockResponse = Mockito.mock(HttpServletResponse.class);
        mockRequestDispatcher = Mockito.mock(RequestDispatcher.class);
        Mockito.when(mockRequest.getRequestDispatcher("/WEB-INF/view/profiles.jsp"))
                .thenReturn(mockRequestDispatcher);
        mockResponse = Mockito.mock(HttpServletResponse.class);
    }

    @Test
    public void testDoGet() throws IOException, ServletException {


        User testUser = new User(UUID.randomUUID(), "Test User", "Test Password", Instant.now(), "This is my bio", "English");

        List <User> testList = new ArrayList();
        testList.add(testUser);

        mockConversationStore = Mockito.mock(ConversationStore.class);

        mockUserStore = Mockito.mock(UserStore.class);
        profileListServlet.setUserStore(mockUserStore);
        Mockito.when(mockUserStore.getUser("Test User")).thenReturn(testUser);
        Mockito.when(mockUserStore.getUsersList()).thenReturn(testList);

        String username = mockRequest.getParameter("username");
        Mockito.when(username).thenReturn("Test User");

        Mockito.when(mockRequest.getParameter("password")).thenReturn("Test Password");
        Mockito.when(mockRequest.getRequestURI()).thenReturn("/users/test Test User");

        profileListServlet.doGet(mockRequest, mockResponse);
        Mockito.verify(mockRequestDispatcher).forward(mockRequest, mockResponse);
    }
}
