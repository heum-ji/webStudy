package member.model.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

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
		String resource = "/mybatis-config.xml"; // sourceFolder 전체에서 해당 파일 서치

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

	// 로그인
	public Member selectOneMember(Member member) {
		SqlSession session = getSqlSession(); // Connection 대신 SqlSession 사용
		Member m = new MemberDao().selectOneMember(session, member);

		session.close();
		return m;
	}

	// 회원가입
	public int insertMember(Member m) {
		SqlSession session = getSqlSession();
		int result = new MemberDao().insertMember(session, m);

		if (result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();

		return result;
	}

	// 아이디로 회원 조회
	public Member selectOneMember(String memberId) {
		SqlSession session = getSqlSession();
		Member m = new MemberDao().selectOneMember(session, memberId);

		session.close();
		return m;
	}

	// 회원 정보 수정
	public int updateMember(Member m) {
		SqlSession session = getSqlSession();
		int result = new MemberDao().updateMember(session, m);

		if (result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();

		return result;
	}

	// 회원 탈퇴
	public int deleteMember(String memberId) {
		SqlSession session = getSqlSession();
		int result = new MemberDao().deleteMember(session, memberId);

		if (result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();

		return result;
	}

	// 전체 회원 조회
	public List<Member> selectAllMember() {
		SqlSession session = getSqlSession();
		List<Member> list = new MemberDao().selectAllMember(session);

		session.close();
		return list;
	}

	// 아이디 찾기
	public String selectOneMember(String memberName, String phone) {
		SqlSession session = getSqlSession();
		// int num1 = 10;
		// int num2 = 200;
		HashMap<String, Object> map = new HashMap<String, Object>(); // 어떠한 값이라도 담기 위해 Object로 받기 ex) 숫자, 문자 등
		map.put("memberName", memberName);
		map.put("phone", phone);
		// map.put("num1", num1);
		// map.put("num1", num2);
		String memberId = new MemberDao().selectOneMember(session, map);

		session.close();

		return memberId;
	}

}