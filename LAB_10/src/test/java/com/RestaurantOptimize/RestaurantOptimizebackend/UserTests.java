package com.RestaurantOptimize.RestaurantOptimizebackend;

import com.RestaurantOptimize.RestaurantOptimizebackend.controller.UserController;
import com.RestaurantOptimize.RestaurantOptimizebackend.model.User;
import com.RestaurantOptimize.RestaurantOptimizebackend.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserTests {

    private MockMvc mvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    User TestUser_1 = new User(11L,"Kacper","","Kamm","+48 69969696","kamm@gmail.com","Kacper",true,false,false,false,"2022-05-23 12:12:12","2022-06-22 12:12:12");
    User TestUser_2 = new User(12L,"Kacper","","Kamm","+48 69969696","kamm@gmail.com","Kacper",true,false,false,false,"2022-05-23 12:12:12","2022-06-22 12:12:12");
    User TestUser_3 = new User(13L,"Kacper","","Kamm","+48 69969696","kamm@gmail.com","Kacper",true,false,false,false,"2022-05-23 12:12:12","2022-06-22 12:12:12");

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getAllUsers_success() throws Exception{
        List<User> records = new ArrayList<>(Arrays.asList(TestUser_1,TestUser_2,TestUser_3));

        Mockito.when(userRepository.findAll()).thenReturn(records);

        mvc.perform(MockMvcRequestBuilders
                .get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[0].firstName").value("Kacper")
                );
    }

    @Test
    public void getUserByEmail_success() throws Exception{
        Mockito.when(userRepository.findById(TestUser_1.getUserId())).thenReturn(Optional.of(TestUser_1));

        mvc.perform(MockMvcRequestBuilders
                        .get("/user/11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.userId").value(TestUser_1.getUserId())
                );
    }

    @Test
    public void createUser_success() throws Exception{
        User TestUser_4 = new User(14L,"Kacper","","Kamm","+48 69969696","kamm@gmail.com","Kacper",true,false,false,false,"2022-05-23 12:12:12","2022-06-22 12:12:12");
        Mockito.when(userRepository.save(TestUser_4)).thenReturn(TestUser_4);

        String content = objectWriter.writeValueAsString(TestUser_4);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$").value(TestUser_4));
    }

    @Test
    public void updateUser_success() throws Exception{
        User TestUser_4 = new User(11L,"Kacper","","Kam","+48 69969696","kamm@gmail.com","Kacper",true,false,false,false,"2022-05-23 12:12:12","2022-06-22 12:12:12");
        Mockito.when(userRepository.findById(TestUser_1.getUserId())).thenReturn(Optional.of(TestUser_1));
        Mockito.when(userRepository.save(TestUser_4)).thenReturn(TestUser_4);


    }

    @Test
    public void deleteUser_fail() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                .delete("/deleteuser/11")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}
