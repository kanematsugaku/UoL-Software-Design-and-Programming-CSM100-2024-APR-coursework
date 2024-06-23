package com.example.game;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GameApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void myTest() {
		String result = "Goodbye";
		assert(result.equals("Goodbye"));
	}

}
