package member.model.dao;

import org.apache.ibatis.session.SqlSession;

import member.model.vo.Member;

public class MemberDao {

	public Member selectOneMember(SqlSession session, Member member) {
		// member -> mapper 매핑
		// .selectOneMember -> id 지정
		Member m = session.selectOne("member.selectOneMember", member); 
		return m;
	}

}
