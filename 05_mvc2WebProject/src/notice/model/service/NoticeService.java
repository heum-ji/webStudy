package notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JdbcTemplate;
import notice.model.vo.Notice;

public class NoticeService {

	public ArrayList<Notice> selectNoticeList(int reqPage) {
		Connection conn = JdbcTemplate.getConnection();
		
//		ArrayList<Notice> list = NoticeDao.selectNoticeList(conn, reqPage);

		return null;
	}

}
