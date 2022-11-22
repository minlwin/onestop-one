package com.jdc.onestop.directory.services;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql({
	"/initialize.sql",
	"/state/data.sql",
	"/district/data.sql",
	"/township/data.sql"
})
public class TownshipServiceTest {

}
