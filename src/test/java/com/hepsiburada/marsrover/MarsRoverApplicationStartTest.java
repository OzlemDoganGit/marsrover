package com.hepsiburada.marsrover;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MarsroverApplication.class)
public class MarsRoverApplicationStartTest{

    @Test
    public void applicationStarts() {
    	MarsroverApplication.main(new String[] {});
        assertTrue(true);
    }

}
