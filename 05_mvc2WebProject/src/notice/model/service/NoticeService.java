package notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JdbcTemplate;
import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;
import notice.model.vo.NoticeComment;
import notice.model.vo.NoticePageData;
import notice.model.vo.NoticeViewData;

public class NoticeService {

	// 공지사항 목록 불러오기
	public NoticePageData selectNoticeList(int reqPage) {
		Connection conn = JdbcTemplate.getConnection();
		// 1. 한페이지에 게시물을 몇개 보여줄지 : 한 페이지당 10개씩 보여줌
		int numPerPage = 10;
		// 2. reqPage를 통해서 게시물 시작 rnum / 끝 rnum 계산
		// 1페이지 : start : 1, end : 10, 2페이지 : start : 11, end : 20
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		// 요청한 페이지의 게시물을 조회
		NoticeDao dao = new NoticeDao();
		ArrayList<Notice> list = dao.selectNoticeList(conn, start, end);

		// 페이지 네비게이션 제작
		// 1) 전체 페이지수를 구해야 함 : 85/10
		// 전체 게시물 수 조회
		int totalCount = dao.totalCount(conn);
		// 전체 페이지 수 계산
		int totalPage = (totalCount % numPerPage == 0) ? totalCount / numPerPage : (totalCount / numPerPage) + 1;

		// pageNavi 길이 지정
		int pageNaviSize = 5;
		// 페이지 시작 번호 설정 - 항상 pageNavi의 처음 ex) 1~5 : 1 // 6~10 : 6
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;

		// pageNavi 시작
		String pageNavi = "<ul class='pagination pagination-lg'>";

		// 이전 버튼 생성 - 이전 페이지의 last
		if (pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/noticeList?reqPage=" + (pageNo - 1) + "'>&lt</a></li>"; // &lt : <
		}

		for (int i = 0; i < pageNaviSize; i++) {
			// 현재 페이지가 요청한 페이지 인 경우
			if (pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/noticeList?reqPage=" + pageNo + "'>" + pageNo + "</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/noticeList?reqPage=" + pageNo + "'>" + pageNo + "</a></li>";
			}
			pageNo++; // 페이지 수 증가

			if (pageNo > totalPage) {
				break;
			}
		}
		// 다음 버튼 생성 - 다음 페이지의 first
		if (pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/noticeList?reqPage=" + pageNo + "'>&gt</a></li>"; // &gt : >
		}
		pageNavi += "</ul>";

		JdbcTemplate.close(conn);

		// 완성된 data 전달
		NoticePageData npd = new NoticePageData(list, pageNavi);
		return npd;
	}

	// 게시물 작성
	public int insertNotice(Notice n) {
		Connection conn = JdbcTemplate.getConnection();
		int result = new NoticeDao().insertNotice(conn, n);

		if (result > 0) {
			JdbcTemplate.commit(conn);
		} else {
			JdbcTemplate.rollback(conn);
		}
		JdbcTemplate.close(conn);

		return result;
	}

	// 게시물 조회 - 게시물 번호
	public Notice selectOneNotice(int noticeNo) {
		Connection conn = JdbcTemplate.getConnection();
		Notice n = new NoticeDao().selectOneNotice(conn, noticeNo);

		JdbcTemplate.close(conn);

		return n;
	}

	// 댓글 불러오기
	public NoticeViewData selectNoticeView(int noticeNo) {
		Connection conn = JdbcTemplate.getConnection();
		NoticeDao dao = new NoticeDao();

		Notice n = new NoticeDao().selectOneNotice(conn, noticeNo);
		ArrayList<NoticeComment> list = dao.selectNoticeCommentList(conn, noticeNo);

		JdbcTemplate.close(conn);

		NoticeViewData nvd = new NoticeViewData(n, list);
		return nvd;
	}

	// 게시물 삭제
	public int deleteNotice(int noticeNo) {
		Connection conn = JdbcTemplate.getConnection();
		int result = new NoticeDao().deleteNotice(conn, noticeNo);

		if (result > 0) {
			JdbcTemplate.commit(conn);
		} else {
			JdbcTemplate.rollback(conn);
		}

		JdbcTemplate.close(conn);

		return result;
	}

	// 게시물 수정
	public int updateNotice(Notice n) {
		Connection conn = JdbcTemplate.getConnection();
		int result = new NoticeDao().updateNotice(conn, n);

		if (result > 0) {
			JdbcTemplate.commit(conn);
		} else {
			JdbcTemplate.rollback(conn);
		}
		JdbcTemplate.close(conn);

		return result;
	}

	// 댓글 생성
	public int insertComment(NoticeComment nc) {
		Connection conn = JdbcTemplate.getConnection();

		int result = new NoticeDao().insertComment(conn, nc);

		if (result > 0) {
			JdbcTemplate.commit(conn);
		} else {
			JdbcTemplate.rollback(conn);
		}
		JdbcTemplate.close(conn);

		return result;
	}

}
