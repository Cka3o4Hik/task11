package Service;

import Entity.UsersEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest extends BaseIntegrationTest{
    UserService service;

    @BeforeAll
    void init() {
        service = new UserService(factory);
    }


    @Test
    void testAll() {
        UsersEntity newUser = new UsersEntity();
        newUser.setFullName("testname");
        newUser.setPassword("1245874");
        Integer id = service.add(newUser);
        assertNotNull(id);
        assertTrue(id > 0);

        newUser.setFullName("newtestname");
        service.update(newUser);
        UsersEntity userById = service.getById(id);
        assertNotNull(userById);
        assertEquals(userById.getFullName(), "newtestname");

        List<UsersEntity> allUsers = service.getAll();
        assertNotNull(allUsers);
        int size = allUsers.size();

        service.deleteById(id);
        allUsers = service.getAll();
        assertEquals(size - allUsers.size(), 1);
    }

}