package member.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import member.model.vo.Member;

public class MemberDao {

	// 로그인
	public Member selectOneMember(SqlSession session, Member member) {
		// member -> mapper 매핑
		// .selectOneMember -> id 지정
		Member m = session.selectOne("member.selectOneMember", member);
		return m;
	}

	// 회원 가입
	public int insertMember(SqlSession session, Member m) {
		return session.insert("member.insertMember", m);
	}

	// 아이디로 회원 조회
	public Member selectOneMember(SqlSession session, String memberId) {
		return session.selectOne("member.selectOneMemberId", memberId);
	}

	// 회원 정보 수정
	public int updateMember(SqlSession session, Member m) {
		return session.update("member.updateMember", m);
	}

	// 회원 탈퇴
	public int deleteMember(SqlSession session, String memberId) {
		return session.update("member.deleteMember", memberId);
	}

	// 전체 회원 조회
	public List<Member> selectAllMember(SqlSession session) {
		return session.selectList("member.selectAllMember");
	}

	// 아이디 찾기
	public String selectOneMemberId(SqlSession session, HashMap<String, Object> map) {
		return session.selectOne("member.idSearch", map);
	}

	// 비밀번호 찾기
	public String selectOneMemberPw(SqlSession session, HashMap<String, String> map) {
		return session.selectOne("member.pwSearch", map);
	}

	// checkBox 선택 조회
	public ArrayList<Member> ifTest(SqlSession session, HashMap<String, Object> map) {
		List<Member> list = session.selectList("member.iftest", map);
		return (ArrayList<Member>) list;
	}

	// 타입 + 키워드 검색
	public ArrayList<Member> chooseTest(SqlSession session, HashMap<String, Object> map) {
		List<Member> list = session.selectList("member.chooseTest", map);
		return (ArrayList<Member>) list;
	}

	// 이름 + 주소 검색
	public ArrayList<Member> searchTest(SqlSession session, HashMap<String, Object> map) {
		List<Member> list = session.selectList("member.searchTest", map);
		return (ArrayList<Member>) list;
	}

	public ArrayList<Member> foreachTest(SqlSession session, String[] name) {
		List<Member> list = session.selectList("member.foreachTest", name);
		return (ArrayList<Member>) list;
	}

}