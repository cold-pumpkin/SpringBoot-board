package com.insight.springstartboard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringStartBoardApplicationTests {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSqlSession() throws Exception {
		System.out.println("\n>>>>>>>>>>>>>>>>>");
		System.out.println(sqlSession.toString());
		System.out.println(">>>>>>>>>>>>>>>>>");
	}

}
