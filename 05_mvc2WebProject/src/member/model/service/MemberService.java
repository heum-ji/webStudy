package member.model.service;

import java.sql.Connection;

import common.JdbcTemplate;
import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {

	// 로그인
	public Member selectOneMember(String memberId, String memberPw) {
		// 커넥션 생성
		Connection conn = JdbcTemplate.getConnection();
		Member m = new MemberDao().selectOneMember(conn, memberId, memberPw);

		JdbcTemplate.close(conn);
		return m;
	}

	// 아이디 중복 체크
	public Member selectOneMember(String memberId) {
		Connection conn = JdbcTemplate.getConnection();
		Member m = new MemberDao().selectOneMember(conn, memberId);

		JdbcTemplate.close(conn);
		return m;
	}

	// 회원가입
	public int insertMember(Member member) {
		// 커넥션 생성
		Connection conn = JdbcTemplate.getConnection();
		int result = new MemberDao().insertMember(conn, member);

		if (result > 0) {
			JdbcTemplate.commit(conn);
		} else {
			JdbcTemplate.rollback(conn);
		}
		JdbcTemplate.close(conn);

		return result;
	}

	// 회원 정보 수정
	public int updateMember(Member m) {
		Connection conn = JdbcTemplate.getConnection();
		int result = new MemberDao().updateMember(conn, m);

		if (result > 0) {
			JdbcTemplate.commit(conn);
		} else {
			JdbcTemplate.rollback(conn);
		}
		JdbcTemplate.close(conn);

		return result;
	}

}