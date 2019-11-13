package fr.enssat.leave_manager;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={LeaveManagerApplication.class})
@Profile("test")
class LeaveManagerApplicationTests {

	@Test
	void contextLoads() {
	}

}
