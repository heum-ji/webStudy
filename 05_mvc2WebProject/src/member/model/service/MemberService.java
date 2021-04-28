package member.model.service;

import java.sql.Connection;

import common.JdbcTemplate;
import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {

	public Member selectOneMember(String memberId, String memberPw) {
		// 커넥션 생성
		Connection conn = JdbcTemplate.getConnection();
		Member m = new MemberDao().selectOneMember(conn, memberId, memberPw);

		JdbcTemplate.close(conn);
		return m;
	}

	public int insertMember(Member member) {
		// 커넥션 생성
		Connection conn = JdbcTemplate.getConnection();
		int result = new MemberDao().insertMember(conn, member);
		
		if(result > 0) {
			JdbcTemplate.commit(conn);
		} else {
			JdbcTemplate.rollback(conn);
		}
		
		JdbcTemplate.close(conn);
		
		return result;
	}

}