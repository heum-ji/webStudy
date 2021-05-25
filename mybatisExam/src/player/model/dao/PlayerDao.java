package player.model.dao;

import org.apache.ibatis.session.SqlSession;

import player.model.vo.Player;

public class PlayerDao {

	// 로그인
	public Player selectOnePlayer(SqlSession session, Player player) {
		return session.selectOne("player.selectOnePlayer", player);
	}

}
