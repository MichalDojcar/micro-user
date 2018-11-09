package cz.dojcar.users.control;


import static cz.dojcar.users.control.UserConnector.POSTS_PATH;
import static cz.dojcar.users.control.UserConnector.USERS_PATH;
import static cz.dojcar.users.control.UserConnector.USER_SOURCE_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import cz.dojcar.users.entity.Post;
import cz.dojcar.users.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserConnectorTest {

    @Mock
    private Client client;

    @InjectMocks
    private UserConnector userConnector;

    @Test
    void testGetById() {
        Long userId = 1L;

        User expectedUser = new User();
        mockUserCall(USER_SOURCE_BASE_URL + USERS_PATH  + userId, expectedUser);

        List<Post> expectedPosts = Collections.singletonList(new Post());
        mockPostsCall(USER_SOURCE_BASE_URL + USERS_PATH  + userId + POSTS_PATH, expectedPosts);


        User user = userConnector.getById(userId);


        assertEquals(expectedUser, user);
        assertEquals(expectedPosts, user.getPosts());
    }

    private void mockUserCall(String url, User returnedUser) {
        Invocation.Builder invocationBuilder = mockClient(url);
        when(invocationBuilder.get(User.class)).thenReturn(returnedUser);
    }

    @SuppressWarnings("unchecked")
    private void mockPostsCall(String url, List<Post> returnedPosts) {
        Invocation.Builder invocationBuilder = mockClient(url);
        when(invocationBuilder.get(any(GenericType.class))).thenReturn(returnedPosts);
    }

    private Invocation.Builder mockClient(String url) {
        WebTarget target = mock(WebTarget.class);
        doReturn(target).when(client).target(url);
        Invocation.Builder builder = mock(Invocation.Builder.class);
        when(target.request()).thenReturn(builder);
        return builder;
    }
}
