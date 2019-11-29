package test_project;
import test_project.model.*;
import test_project.controller.*;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class RunTests {
    public static void main(String[] args){
        Result result = JUnitCore.runClasses(test_project.TestSuite.class);
        System.out.println("Test has been run successfully.");
        for (Failure failure : result.getFailures()){
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
