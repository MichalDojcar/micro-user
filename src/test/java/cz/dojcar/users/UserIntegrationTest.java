package cz.dojcar.users;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;

import cz.dojcar.configuration.BeanProducer;
import cz.dojcar.users.boundary.UserResource;
import cz.dojcar.users.control.UserConnector;
import cz.dojcar.users.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserIntegrationTest {

    private UserResource userResource;

    @BeforeEach
    void setUp() {
        BeanProducer beanProducer = new BeanProducer();
        Client client = beanProducer.produceClient();
        UserConnector userConnector = new UserConnector(client);
        userResource = new UserResource(userConnector);
    }

    @Test
    void user_with_id_1_has_all_personal_data_and_10_posts() {
        User user = userResource.getUser(1L);
        assertNotNull(user);
        assertEquals("Leanne Graham", user.getName());
        assertEquals("Bret", user.getUsername());
        assertEquals("Sincere@april.biz", user.getEmail());
        assertEquals(10, user.getPosts().size());
    }

    @Test
    void user_without_id_is_not_found() {
        assertThrows(
                NotFoundException.class,
                () -> userResource.getUser(null));
    }

    @Test
    void user_with_not_existing_id_is_not_found() {
        assertThrows(
                NotFoundException.class,
                () -> userResource.getUser(-1L));
    }
}
