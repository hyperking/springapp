package com.springapi.utils;
import com.springapi.entities.Person;
import com.springapi.models.Developer;
import com.springapi.utils.SpringMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SpringMapperTest {

//    private Person person = new Person();
//    private Developer developer = new Developer();
//    private SpringMapper transform = SpringMapper.INSTANCE;

//    @Before
//    public void setup(){
//        developer.setFirstName("Derry");
//        developer.setLastName("Spann");
//    }

    @Test
    public void testMapper(){
        SpringMapper transform = SpringMapper.INSTANCE;
        Developer developer = new Developer();
//        Person mockPerson = new Person();
        developer.setFirstName("Derry");
        developer.setLastName("Spann");
        developer.parseName();
//        developer.setName(developer.parseName());
        Person mockPerson = transform.modelToPerson(developer);

        Assert.assertEquals(mockPerson.getFullName(), developer.getName());
    }
}
