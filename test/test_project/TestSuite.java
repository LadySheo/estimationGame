package test_project;
import test_project.model.*;
import test_project.controller.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        test_project.model.DeckTest.class,
        test_project.model.HandTest.class,
        test_project.controller.PlayerTest.class,
        test_project.controller.AIPlayerTest.class,
        test_project.controller.TrickTest.class
})
public class TestSuite {

}
