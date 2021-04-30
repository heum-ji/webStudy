package board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import board.model.dao.BoardDao;
import board.model.vo.Board;
import board.model.vo.BoardPageData;
import common.JdbcTemplate;

public class BoardService {

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
		// 전체 페이지 수 계산
		int totalPage = (totalCount % numPerPage == 0) ? totalCount / numPerPage : (totalCount / numPerPage) + 1;
		// pageNavi 길이 지정
		int pageNaviSize = 5;

		// 페이지 시작 번호 설정
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;

		// pageNavi 시작
		String pageNavi = "<ul class='pagination pagination-lg'>";
		// 이전 버튼 생성 - 이전 페이지의 last
		if (pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/boardList?reqPage=" + (pageNo - 1) + "'>&lt</a></li>"; // &lt : <
		}
		// 페이지 숫자 생성
		for (int i = 0; i < pageNaviSize; i++) {
			// 현재 페이지가 요청한 페이지 인 경우
			if (pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/boardList?reqPage=" + pageNo + "'>" + pageNo + "</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/boardList?reqPage=" + pageNo + "'>" + pageNo + "</a></li>";
			}
			pageNo++; // 페이지 수 증가

			if (pageNo > totalPage) {
				break;
			}
		}
		// 다음 버튼 생성 - 다음 페이지의 first
		if (pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/boardList?reqPage=" + pageNo + "'>&gt</a></li>"; // &gt : >
		}
		pageNavi += "</ul>";

		JdbcTemplate.close(conn);

		// data 전송
		BoardPageData bpd = new BoardPageData(list, pageNavi);
		return bpd;
	}
}
