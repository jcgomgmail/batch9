package steps;

import  io.cucumber.java.Before;
import  io.cucumber.java.After;
import utils.CommonMethods;

public class Hooks extends CommonMethods {

    @Before
    public void start(){
        setUp();
    }

    @After
    public void end(){
        tearDown();
    }
}
