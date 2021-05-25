package player.model.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import player.model.dao.PlayerDao;
import player.model.vo.Player;

public class PlayerService {

	// Sqlsession 생성
	private SqlSession getSqlSession() {
		SqlSession session = null;
		String resource = "/mybatis-config.xml";

		try {
			InputStream is = Resources.getResourceAsStream(resource);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory ft = builder.build(is);

			session = ft.openSession(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return session;
	}

	// 로그인
	public Player selectOnePlayer(Player player) {
		SqlSession session = getSqlSession();
		Player p = new PlayerDao().selectOnePlayer(session, player);

		session.close();
		return p;
	}

}
