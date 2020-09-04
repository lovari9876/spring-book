package com.springbook.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// 특정 DB와의 Connection(Session) 객체; default: 자동 commit된다.

// A connection (session) with a specific database. 
// SQL statements are executed and results are returned within the context of a connection. 
// A Connection object's database is able to provide information describing its tables,
// its supported SQL grammar, its stored procedures, the capabilities of this connection, 
// and so on. This information is obtained with the getMetaData method. 
// Note: When configuring a Connection, JDBC applications should use the appropriate 
// Connection method such as setAutoCommit or setTransactionIsolation.
// Applications should not invoke SQL commands directly to change 
// the connection'sconfiguration when there is a JDBC method available. 
// By default a Connection object is in auto-commit mode, which means that 
// it automatically commits changes after executing each statement. 
// If auto-commit mode has been disabled, the method commit must be called 
// explicitly in order to commit changes; 
// otherwise, database changes will not be saved. 

// utility: 유용성, (유용한)소프트웨어들
public class JDBCUtil {
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // cj 패키지 이하의 드라이버로 6.x.x대에 변경!
			return DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/spring_quick_start_schema?"
					+ "serverTimezone=UTC",
					"root", "54321");
			// java.sql.DriverManager.getConnection(String url, String user, String
			// password)
			// throws SQLException; returns a connection to URL; 해당 주소로의 connection 리턴
			// => url - a database url of the form jdbc:subprotocol:subname
			// => jdbc:mysql://{ip주소}:{port번호}/{databaseName}?serverTimezone=UTC
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void close(PreparedStatement stmt, Connection conn) {
		// An object that represents a precompiled SQL statement.
		// A SQL statement is precompiled and stored in a PreparedStatement object.
		// This object can then be used to efficiently execute this statement multiple
		// times.
		// => precompile: 컴파일 전에 사전작업하다

		// If a statement is used multiple times in a session, precompiling it provides
		// better performance than sending it to the database and compiling it for each
		// use.
		// The more complex the statement, the greater the performance benefit.
		// If a statement is likely to be used only a few times, precompiling it may
		// be inefficient because of the overhead involved in precompiling, saving,
		// and later deallocating it in the database. Precompiling a dynamic SQL
		// statement
		// for execution and saving it in memory uses time and resources.
		// If a statement is not likely to be used multiple times during a session,
		// the costs of doing a database prepare may outweigh its benefits.
		// Another consideration is that once a dynamic SQL statement is prepared
		// in the database, it is very similar to a stored procedure. In some cases,
		// it may be preferable to create stored procedures and have them
		// reside on the server, rather than defining prepared statements in the
		// application.
		// ==> 하나의 statement가 한 세션에서 여러 번 쓰이면, 데이터베이스에 statement를 매번 전송해서 컴파일하는 것보다는,
		// 프리컴파일하는 게 성능이 더 낫다.(PreparedStatement 쓰는 이유) statement가 복잡할수록, 더 높은 성능을 보여준다.
		// 이후 요약: 몇번 안쓰면 프리컴파일하는게 비용이 많이 들 수 있어서, 오히려 동적 sql문은 디비에 프로시저 저장해두고서
		// 쓰는게 나을 수 있다.

		if (stmt != null) {
			try {
				if (!stmt.isClosed())
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				stmt = null;
			}
		}

		if (conn != null) {
			try {
				if (!conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}

	}

	public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
		if (rs != null) {
			try {
				if (!rs.isClosed())
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}

		if (stmt != null) {
			try {
				if (!stmt.isClosed())
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				stmt = null;
			}
		}

		if (conn != null) {
			try {
				if (!conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}

}
