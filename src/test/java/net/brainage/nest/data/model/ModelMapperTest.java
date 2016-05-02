package net.brainage.nest.data.model;

import net.brainage.nest.web.form.SignupForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ms29.seo on 2016-05-03.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {ModelMapperTestContextConfiguration.class})
public class ModelMapperTest {

    @Autowired
    ModelMapper modelMapper;

    @Test
    public void convertFormToEntity() {
        SignupForm signupForm = new SignupForm();
        signupForm.setUsername("superman");
        signupForm.setEmail("superman@gmail.com");
        signupForm.setName("superman");
        signupForm.setPassword("!superman#12");

        User user = modelMapper.map(signupForm, User.class);
        assertThat(user.getUsername(), is(signupForm.getUsername()));
        assertThat(user.getEmail(), is(signupForm.getEmail()));
        assertThat(user.getName(), is(signupForm.getName()));
        assertThat(user.getPassword(), is(signupForm.getPassword()));
    }

}
