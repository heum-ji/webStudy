package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JdbcTemplate;
import notice.model.vo.Notice;
import notice.model.vo.NoticeComment;

public class NoticeDao {

	// 댓글 불러오기
	public ArrayList<NoticeComment> selectNoticeCommentList(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NoticeComment> list = new ArrayList<NoticeComment>();
		String query = "select * from notice_comment where notice_ref = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(setNoticeComment(rset));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rset);
			JdbcTemplate.close(pstmt);
		}
		return list;
	}

	// 게시물 목록 불러오기
	public ArrayList<Notice> selectNoticeList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM(SELECT ROWNUM AS RNUM, N.* FROM (SELECT * FROM NOTICE ORDER BY NOTICE_NO DESC) N) WHERE RNUM BETWEEN ? AND ?";
		ArrayList<Notice> list = new ArrayList<Notice>();

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(setNotice(rset));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rset);
			JdbcTemplate.close(pstmt);
		}
		return list;
	}

	// 게시물 전체 수 조회
	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) as cnt from notice";
		int result = 0;

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rset);
			JdbcTemplate.close(pstmt);
		}
		return result;
	}

	// 게시물 1개 조회
	public Notice selectOneNotice(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from notice where notice_no = ?";
		Notice n = null;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				n = setOneNotice(rset);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(rset);
		}
		return n;
	}

	// 댓글 저장용
	public NoticeComment setNoticeComment(ResultSet rset) {
		NoticeComment nc = new NoticeComment();

		try {
			nc.setNcContent(rset.getString("nc_content"));
			nc.setNcDate(rset.getString("nc_date"));
			nc.setNcLevel(rset.getInt("nc_level"));
			nc.setNcNo(rset.getInt("nc_no"));
			nc.setNcRef(rset.getInt("nc_ref"));
			nc.setNcWriter(rset.getString("nc_writer"));
			nc.setNoticeRef(rset.getInt("notice_ref"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nc;
	}

	// 공지사항 저장용
	public Notice setNotice(ResultSet rset) {
		Notice notice = new Notice();

		try {
			notice.setFilename(rset.getString("filename"));
			notice.setFilepath(rset.getString("filepath"));
			notice.setNoticeContent(rset.getString("notice_content"));
			notice.setNoticeDate(rset.getString("notice_date"));
			notice.setNoticeNo(rset.getInt("notice_no"));
			notice.setNoticeTitle(rset.getString("notice_title"));
			notice.setNoticeWriter(rset.getString("notice_writer"));
			notice.setRnum(rset.getInt("rnum"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notice;
	}

	// 공지사항 조회용
	public Notice setOneNotice(ResultSet rset) {
		Notice notice = new Notice();

		try {
			notice.setFilename(rset.getString("filename"));
			notice.setFilepath(rset.getString("filepath"));
			notice.setNoticeContent(rset.getString("notice_content"));
			notice.setNoticeDate(rset.getString("notice_date"));
			notice.setNoticeNo(rset.getInt("notice_no"));
			notice.setNoticeTitle(rset.getString("notice_title"));
			notice.setNoticeWriter(rset.getString("notice_writer"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notice;
	}

	// 게시물 작성
	public int insertNotice(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into notice values(notice_seq.nextval,?,?,?,to_char(sysdate, 'yyyy-mm-dd'),?,?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeWriter());
			// 내용 공백 체크
			if (n.getNoticeContent().equals("")) {
				pstmt.setString(3, "내용없음");
			} else {
				pstmt.setString(3, n.getNoticeContent());
			}
			pstmt.setString(4, n.getFilename());
			pstmt.setString(5, n.getFilepath());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		return result;
	}

	// 게시물 삭제
	public int deleteNotice(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from notice where notice_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		return result;
	}

	// 게시물 업데이트
	public int updateNotice(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "update notice set notice_title = ?, notice_content = ?, filename = ?, filepath = ? where notice_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, n.getNoticeTitle());
			// 내용 공백 체크
			if (n.getNoticeContent().equals("")) {
				pstmt.setString(2, "내용없음");
			} else {
				pstmt.setString(2, n.getNoticeContent());
			}
			pstmt.setString(3, n.getFilename());
			pstmt.setString(4, n.getFilepath());
			pstmt.setInt(5, n.getNoticeNo());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		return result;
	}

	// 댓글 작성
	public int insertComment(Connection conn, NoticeComment nc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into notice_comment values(nc_seq.nextval,?,?,?,to_char(sysdate, 'yyyy-mm-dd'),?,?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, nc.getNcLevel());
			pstmt.setString(2, nc.getNcWriter());
			pstmt.setString(3, nc.getNcContent());
			pstmt.setInt(4, nc.getNoticeRef());

			// 참조가 0 이면 댓글 / 참조 null 처리
			pstmt.setString(5, (nc.getNcRef() == 0) ? null : String.valueOf(nc.getNcRef()));

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		return result;
	}

	// 댓글 수정
	public int updateNoticeComment(Connection conn, int ncNo, String ncContent) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "update notice_comment set nc_content = ? where nc_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, ncContent);
			pstmt.setInt(2, ncNo);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		return result;
	}

	// 댓글 삭제
	public int deleteNoticeComment(Connection conn, int ncNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from notice_comment where nc_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ncNo);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		return result;
	}
}