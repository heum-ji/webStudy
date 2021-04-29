package member.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.JdbcTemplate;
import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {

	// Admin 관리자용 - 전체 회원 조회
	public ArrayList<Member> selectAllMember() {
		Connection conn = JdbcTemplate.getConnection();
		ArrayList<Member> list = new MemberDao().selectAllMember(conn);

		JdbcTemplate.close(conn);
		return list;
	}

	// Admin 관리자용 - 회원 등급 변경
	public int changeLevel(int memberNo, int memberLevel) {
		// 커넥션 생성
		Connection conn = JdbcTemplate.getConnection();
		int result = new MemberDao().changeLevel(conn, memberNo, memberLevel);

		if (result > 0) {
			JdbcTemplate.commit(conn);
		} else {
			JdbcTemplate.rollback(conn);
		}
		JdbcTemplate.close(conn);

		return result;
	}

	// Admin 관리자용 - 선택된 회원 등급 전체 변경
	public boolean checkedChangeLevel(String num, String level) {
		Connection conn = JdbcTemplate.getConnection();
		StringTokenizer sT1 = new StringTokenizer(num, "/");
		StringTokenizer sT2 = new StringTokenizer(level, "/");
		MemberDao dao = new MemberDao();
		boolean result = true;

		// 회원 등급 변경 처리
		while (sT1.hasMoreTokens()) {
			int memberNo = Integer.parseInt(sT1.nextToken());
			int memberLevel = Integer.parseInt(sT2.nextToken());
			int changeResult = dao.changeLevel(conn, memberNo, memberLevel);

			// 하나라도 실패하면 끝내기
			if (changeResult == 0) {
				result = false;
				break;
			}
		}
		
		// 최종 DB 처리
		if (result) {
			JdbcTemplate.commit(conn);
		} else {
			JdbcTemplate.rollback(conn);
		}
		JdbcTemplate.close(conn);
		return result;
	}

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

	// 회원 탈퇴
	public int deleteMember(int memberNo) {
		Connection conn = JdbcTemplate.getConnection();
		int result = new MemberDao().deleteMember(conn, memberNo);

		if (result > 0) {
			JdbcTemplate.commit(conn);
		} else {
			JdbcTemplate.rollback(conn);
		}
		JdbcTemplate.close(conn);

		return result;
	}

}