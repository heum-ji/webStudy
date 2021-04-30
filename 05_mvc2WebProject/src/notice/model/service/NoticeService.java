package notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JdbcTemplate;
import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;

public class NoticeService {

	public ArrayList<Notice> selectNoticeList(int reqPage) {
		Connection conn = JdbcTemplate.getConnection();
		// 1. 한페이지에 게시물을 몇개 보여줄지 : 한 페이지당 10개씩 보여줌
		int numPerPage = 10;
		// 2. reqPage를 통해서 게시물 시작 rnum / 끝 rnum 계산
		// 1페이지 : start : 1, end : 10, 2페이지 : start : 11, end : 20
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		ArrayList<Notice> list = new NoticeDao().selectNoticeList(conn, start, end);

		// 타이틀 확인 테스트용
		for (Notice n : list) {
			System.out.println(n.getNoticeTitle());
		}
		return list;
	}

}
