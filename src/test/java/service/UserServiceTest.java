package service;

import com.revature.dao.UserDao;
import com.revature.exceptions.UserNotFoundException;
import com.revature.model.User;
import com.revature.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest  {

    @Test
    public void Login_positive() throws SQLException, UserNotFoundException {
        // Arrange
        UserDao mockUserDao = mock(UserDao.class);
        User poser = new User(1, "fullpress", "Germany", "Jurgen", "Klopp", "jklopp@liverpooolfc.uk", "manager");

        when(mockUserDao.getUserByUserCredentials("fullpress", "Germany")).thenReturn(poser);

        UserService userService = new UserService(mockUserDao);

        // Act
        User real = userService.login("fullpress", "Germany");

        // Assert
        Assertions.assertEquals(poser, real);
    }

    @Test
    public  void Login_negative(){
        // Arrange
        UserDao mockUserDao = mock(UserDao.class);
        UserService userService = new UserService(mockUserDao);

        // Act and Assert
        Assertions.assertThrows(UserNotFoundException.class,()->{
            userService.login("test","test");
        });
    }

    @Test
    public void LoginWithWhitespace_positive() throws SQLException, UserNotFoundException {
        // Arrange
        UserDao mockUserDao = mock(UserDao.class);
        User poser = new User(1, "fullpress", "Germany", "Jurgen", "Klopp", "jklopp@liverpooolfc.uk", "manager");

        when(mockUserDao.getUserByUserCredentials("       fullpress       ", "        Germany      ")).thenReturn(poser);

        UserService userService = new UserService(mockUserDao);

        // Act
        User real = userService.login("fullpress", "Germany");

        // Assert
        Assertions.assertEquals(poser, real);
    }
}
