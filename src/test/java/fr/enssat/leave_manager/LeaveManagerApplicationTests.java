package fr.enssat.leave_manager;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={LeaveManagerApplication.class})
@TestPropertySource(locations = "classpath:application.yml")
@Profile("test")
class LeaveManagerApplicationTests {
	@Test
	void contextLoads() {
	}
}
