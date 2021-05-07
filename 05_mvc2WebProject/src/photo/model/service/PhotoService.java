package photo.model.service;

import java.sql.Connection;

import common.JdbcTemplate;
import photo.model.dao.PhotoDao;
import photo.model.vo.Photo;

public class PhotoService {
	// 사진 게시물 총 갯수 조회
	public int totalCount() {
		Connection conn = JdbcTemplate.getConnection();
		int totalCount = new PhotoDao().totalCount(conn);

		JdbcTemplate.close(conn);

		return totalCount;
	}

	public int insertPhoto(Photo p) {
		Connection conn = JdbcTemplate.getConnection();
		int result = new PhotoDao().insertPhoto(conn, p);
		
		if(result > 0) {
			JdbcTemplate.commit(conn);
		} else {
			JdbcTemplate.rollback(conn);
		}
		JdbcTemplate.close(conn);
		
		return result;
	}
}