package chap04.member;

public class VersionPrinter {
	private int majorVersion;
	private int minorVersion;
	
	public void print() {
		System.out.printf("이 프로그래므이 버전은 %d.%d 입니다.\n\n", majorVersion, minorVersion);
	}
	
	public void setMajorVersion(int majorVersion) {
		this.majorVersion=majorVersion;
	}
	public void setMinorVersion(int minorVersion) {
		this.minorVersion=minorVersion;
	}
}
