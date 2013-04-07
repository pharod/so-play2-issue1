import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.H2Platform;
import com.avaje.ebeaninternal.api.SpiEbeanServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;
import models.User;
import org.codehaus.jackson.JsonNode;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;

import static org.junit.Assert.assertEquals;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest extends WithApplication {
    @Before
    public void setup() {
        start(fakeApplication(inMemoryDatabase("default-test"), fakeGlobal()));
    }

    @Test
    public void UserModify(){
        // create user (User extends Model)
        User user = new User();
        user.setId(1);
        user.setName("name1");
        user.save();

        // modify
        user.setName("name2");
        user.update();

        // look-up
        User retrieve = Ebean.find(User.class, 1L);
        assertEquals("name2", retrieve.getName());
    }
}
