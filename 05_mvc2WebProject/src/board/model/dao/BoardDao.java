package board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.model.vo.Board;
import common.JdbcTemplate;

public class BoardDao {

	public ArrayList<Board> selectBoardList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM(SELECT ROWNUM AS RNUM, N.* FROM (SELECT * FROM BOARD ORDER BY BOARD_NO DESC) N) WHERE RNUM BETWEEN ? AND ?";
		ArrayList<Board> list = new ArrayList<Board>();

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(setBoard(rset));
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
		String query = "select count(*) as cnt from board";
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

	// 게시물 저장용
	public Board setBoard(ResultSet rset) {
		Board board = new Board();

		try {
			board.setFilename(rset.getString("filename"));
			board.setFilepath(rset.getString("filepath"));
			board.setBoardContent(rset.getString("board_content"));
			board.setBoardDate(rset.getString("board_date"));
			board.setBoardNo(rset.getInt("board_no"));
			board.setBoardTitle(rset.getString("board_title"));
			board.setBoardWriter(rset.getString("board_writer"));
			board.setRnum(rset.getInt("rnum"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return board;
	}
}
