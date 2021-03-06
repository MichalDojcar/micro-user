package cz.dojcar.users.control;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;

import cz.dojcar.users.entity.Post;
import cz.dojcar.users.entity.User;

@Stateless
public class UserConnector {

    static final String USER_SOURCE_BASE_URL = "https://jsonplaceholder.typicode.com";
    static final String USERS_PATH = "/users/";
    static final String POSTS_PATH = "/posts";

    private Client client;

    public UserConnector() {}

    @Inject
    public UserConnector(Client client) {
        this.client = client;
    }

    public User getById(Long id) throws Exception {
        CompletableFuture<User> userFuture = CompletableFuture.supplyAsync(
                () -> client.target(USER_SOURCE_BASE_URL + USERS_PATH + id)
                            .request().get(User.class));

        CompletableFuture<List<Post>> postsFuture = CompletableFuture.supplyAsync(
                () -> client.target(USER_SOURCE_BASE_URL + USERS_PATH + id + POSTS_PATH)
                            .request().get(new GenericType<List<Post>>() {}));

        return userFuture.thenCombine(postsFuture, this::updatePosts).get();
    }

    private User updatePosts(User user, List<Post> posts) {
        user.setPosts(posts);
        return user;
    }
}
