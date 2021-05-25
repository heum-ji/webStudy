package member.model.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {

	// session 생성
	private SqlSession getSqlSession() {
		SqlSession session = null;
		String resource = "/mybatis-config.xml";

		try {
			InputStream is = Resources.getResourceAsStream(resource); // mybatis-config.xml 읽기
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(is); // sqlSession 생성

			session = factory.openSession(false); // auto commit 해제를 위한 false 값 전달
		} catch (IOException e) {
			e.printStackTrace();
		}
		return session;
	}

	public Member selectOneMember(Member member) {
		SqlSession session = getSqlSession(); // Connection 대신 SqlSession 사용

		Member m = new MemberDao().selectOneMember(session, member);

		session.close();
		return m;
	}

}
