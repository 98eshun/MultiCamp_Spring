package chap08.model;

public class VersionPrinter {

	private int majorVersion;
	private int minorVersion;
	
	public VersionPrinter() {
	}
	
	public void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}
	
	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}
	
	public void print() {
		System.out.printf("\n 이 프로그램의 버전은 %d.%d 입니다.\n\n", this.majorVersion, this.minorVersion);
	}
}
