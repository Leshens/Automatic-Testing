package com.RestaurantOptimize.RestaurantOptimizebackend;
import com.RestaurantOptimize.RestaurantOptimizebackend.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RestaurantOptimizeBackendApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private UserRepository userRepository;



}
