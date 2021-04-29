package kr.or.iei.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JdbcTemplate;
import kr.or.iei.member.vo.Member;

public class MemberDao {

	public ArrayList<Member> selectAllMember() {
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member";
		ArrayList<Member> list = new ArrayList<Member>();

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
			JdbcTemplate.close(conn);
		}
		return list;
	}

	public Member selectOneMember(String id, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_id=? and member_pw=?";
		Member member = null;

		conn = JdbcTemplate.getConnection();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				member = setMember(rset);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rset);
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
		return member;
	}

	public int insertMember(Member member) {
		Connection conn = JdbcTemplate.getConnection();
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

			if (result > 0) {
				JdbcTemplate.commit(conn);
			} else {
				JdbcTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
		return result;
	}

	public Member setMember(ResultSet rset) {
		Member member = new Member();

		try {
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