package MainPackage;

public abstract class Character {
	public String name;
	public double xp,hp,maxhp,mp,maxmp,mdef,pdef,atk,intel,spd;
	public int lv;
	//스텟증감율 생성자에서 쓸 변수
	public double hprate,mprate,atkrate,intelrate,spdrate,pdefrate,mdefrate,xprate;
	//Enemy 스킬 할당 생성자에서 쓸 필드
	String skname1,skname2,skname3,skname4,skname5;
	
	//캐릭터 생성자
	public Character(String name,int lv,double maxhp,double maxmp,double atk,double intel,double spd,double pdef,double mdef,double xp) {
	}
	//스텟의 증감을 구현하는 생성자.
	public Character(String name,double hprate,double mprate,double atkrate,double intelrate,double spdrate,double pdefrate,double mdefrate,double xprate){
	}
	
	//적의 스킬 배열을 포함한 생성자. 적이 사용하는 스킬의 수는 적에 따라 다르니 일단 최대를 5로 하고, 여러 생성자를 만들어두자.
	public Character(String skname1,String skname2,String skname3,String skname4,String skname5) {}
	public Character(String skname1,String skname2,String skname3,String skname4) {}
	public Character(String skname1,String skname2,String skname3) {}
	public Character(String skname1,String skname2) {}
	public Character(String skname1) {}
	public Character() {}
	
	//모든 캐릭터의 공통 메소드
	public abstract double skillAtk(); //스킬 공격
	public abstract double attack();	//기본 공격
	public abstract double Pdefp();	//물리 방어력, 양수
	public abstract double Mdefp();	//마법 저항력, 양수
	
	public abstract double Pdefn();	//물리 방어력, 음수
	public abstract double Mdefn();	//마법 저항력, 음수
}
