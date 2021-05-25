package player.model.vo;

public class Player {
	private String playerId;
	private String playerPw;
	private String nickname;
	private String playerTier;
	private int playerIp;
	private int playerRp;
	private int playerLevel;

	public Player() {
		super();
	}

	public Player(String playerId, String playerPw, String nickname, String playerTier, int playerIp, int playerRp, int playerLevel) {
		super();
		this.playerId = playerId;
		this.playerPw = playerPw;
		this.nickname = nickname;
		this.playerTier = playerTier;
		this.playerIp = playerIp;
		this.playerRp = playerRp;
		this.playerLevel = playerLevel;
	}

	public String getplayerId() {
		return playerId;
	}

	public void setplayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getplayerPw() {
		return playerPw;
	}

	public void setplayerPw(String playerPw) {
		this.playerPw = playerPw;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getplayerTier() {
		return playerTier;
	}

	public void setplayerTier(String playerTier) {
		this.playerTier = playerTier;
	}

	public int getplayerIp() {
		return playerIp;
	}

	public void setplayerIp(int playerIp) {
		this.playerIp = playerIp;
	}

	public int getplayerRp() {
		return playerRp;
	}

	public void setplayerRp(int playerRp) {
		this.playerRp = playerRp;
	}

	public int getplayerLevel() {
		return playerLevel;
	}

	public void setplayerLevel(int playerLevel) {
		this.playerLevel = playerLevel;
	}

}