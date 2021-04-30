package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JdbcTemplate;
import notice.model.vo.Notice;

public class NoticeDao {

	// 공지사항 목록 불러오기
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

}
