package board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import board.model.dao.BoardDao;
import board.model.vo.Board;
import board.model.vo.BoardPageData;
import common.JdbcTemplate;

public class BoardService {

	// 게시물 목록 조회
	public BoardPageData selectBoardList(int reqPage) {
		Connection conn = JdbcTemplate.getConnection();
		// 한페이지에 보여줄 게시물 개수
		int numPerPage = 15;
		// reqPage를 통해서 게시물 시작 rnum / 끝 rnum 계산
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		// 요청한 페이지의 게시물을 조회
		BoardDao dao = new BoardDao();
		ArrayList<Board> list = dao.selectBoardList(conn, start, end);

		// 페이지 네비게이션 제작
		// 전체 게시물 수 조회
		int totalCount = dao.totalCount(conn);
		// 전체 페이지 수 계산 - 게시물이 1개라도 있으면 페이지 생성
		int totalPage = (totalCount % numPerPage == 0) ? totalCount / numPerPage : (totalCount / numPerPage) + 1;
		// pageNavi 길이 지정 : 보여줄 페이지 수
		int pageNaviSize = 5;

		// 페이지 시작 번호
		int pageNo = 0;

		// 페이지 시작 번호 설정 - 3페이지 이하인 경우 1
		if (reqPage <= 3) {
			pageNo = 1;
		} else { // 그밖은 현제 페이지 -2 : 항상 페이지 번호 2개 생성 : 현재 페이지 중앙 처리
			pageNo = reqPage - 2;
		}

		// pageNavi 시작
		String pageNavi = "<ul class='pagination pagination-lg'>";
		// 이전 버튼 생성 - 현재 페이지가 1페이지가 아닌 경우
		if (reqPage != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/boardList?reqPage=" + (reqPage - 1) + "'>&lt</a></li>"; // &lt : <
		}
		// 페이지 숫자 생성
		for (int i = 0; i < pageNaviSize; i++) {
			// 현재 페이지가 요청한 페이지 인 경우 - active 효과 주기
			if (pageNo == reqPage) {
				pageNavi += "<li class='page-item active'><a class='page-link' href='/boardList?reqPage=" + pageNo
						+ "'>" + pageNo + "</a></li>";
			} else {
				pageNavi += "<li class='page-item'><a class='page-link' href='/boardList?reqPage=" + pageNo + "'>"
						+ pageNo + "</a></li>";
			}
			pageNo++; // 페이지 수 증가

			// 마지막 페이지 생성 후 for문 나가기
			if (pageNo > totalPage) {
				break;
			}
		}

		// 다음 버튼 생성 - 현재 페이지가 마지막 페이지가 아닌 경우
		if (reqPage < totalPage) {
			pageNavi += "<li class='page-item'><a class='page-link' href='/boardList?reqPage=" + (reqPage + 1)
					+ "'>&gt</a></li>";
		}

		pageNavi += "</ul>";

		JdbcTemplate.close(conn);

		// data 전송
		BoardPageData bpd = new BoardPageData(list, pageNavi);
		return bpd;
	}

	// 게시물 작성
	public int insertBoard(Board b) {
		Connection conn = JdbcTemplate.getConnection();
		int result = new BoardDao().insertBoard(conn, b);

		if (result > 0) {
			JdbcTemplate.commit(conn);
		} else {
			JdbcTemplate.rollback(conn);
		}
		JdbcTemplate.close(conn);

		return result;
	}

	// 게시물 조회 - 게시물 번호
	public Board selectOneBoard(int boardNo) {
		Connection conn = JdbcTemplate.getConnection();
		Board b = new BoardDao().selectOneBoard(conn, boardNo);

		JdbcTemplate.close(conn);

		return b;
	}

	// 게시물 삭제
	public int deleteBoard(int boardNo) {
		Connection conn = JdbcTemplate.getConnection();
		int result = new BoardDao().deleteBoard(conn, boardNo);

		if (result > 0) {
			JdbcTemplate.commit(conn);
		} else {
			JdbcTemplate.rollback(conn);
		}
		JdbcTemplate.close(conn);

		return result;
	}

	// 게시물 수정
	public int updateBoard(Board b) {
		Connection conn = JdbcTemplate.getConnection();
		int result = new BoardDao().updateBoard(conn, b);

		if (result > 0) {
			JdbcTemplate.commit(conn);
		} else {
			JdbcTemplate.rollback(conn);
		}
		JdbcTemplate.close(conn);

		return result;
	}
}