package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JdbcTemplate;
import member.model.vo.Member;

public class MemberDao {

	// Admin 관리자용 - 전체 회원 조회
	public ArrayList<Member> selectAllMember(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "select * from member";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(setMember(rset));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rset);
			JdbcTemplate.close(pstmt);
		}
		return list;
	}

	// 로그인
	public Member selectOneMember(Connection conn, String memberId, String memberPw) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_id = ? and member_pw = ?";
		Member m = null;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				m = setMember(rset);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rset);
			JdbcTemplate.close(pstmt);
		}
		return m;
	}

	// ID 중복 체크
	public Member selectOneMember(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_id = ?";
		Member m = null;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				m = setMember(rset);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rset);
			JdbcTemplate.close(pstmt);
		}
		return m;
	}

	// 회원 가입
	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into member values(mem_seq.nextval,?, ?, ?, ?, ?, 3, to_char(sysdate, 'yyyy-mm-dd'))";

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getAddress());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		return result;
	}

	// 회원정보 수정
	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set member_pw=?, phone = ?, address = ? where member_id = ?";

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, m.getMemberPw());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getAddress());
			pstmt.setString(4, m.getMemberId());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		return result;
	}

	// 회원 탈퇴
	public int deleteMember(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from member where member_no = ?";

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, memberNo);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		return result;
	}

	// 회원 저장용
	public Member setMember(ResultSet rset) {
		Member member = new Member();

		try {
			member = new Member();

			member.setMemberNo(rset.getInt("member_no"));
			member.setMemberId(rset.getString("member_id"));
			member.setMemberPw(rset.getString("member_pw"));
			member.setMemberName(rset.getString("member_name"));
			member.setPhone(rset.getString("phone"));
			member.setAddress(rset.getString("address"));
			member.setMemberLevel(rset.getInt("member_level"));
			member.setEnrollDate(rset.getString("enroll_date"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

}