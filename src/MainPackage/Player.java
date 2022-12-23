package MainPackage;

public class Player extends Character {

	// 업그레이드/스킬의 각 단계를 저장할 정수 목록
	/*public int numAtkUpgrades, numDefUpgrades;*/
	public int numgoddessBlessing;
	
	// 추가 플레이어 스텟
	int restsLeft;
	double gold, maxxp;
	
	//특성명 저장을 위한 배열
	/*public String[] atkUpgrades={"강인함","힘","무력","권능"};
	public String[] defUpgrades={"통뼈","바위피부","비늘 갑옷","신성한 오라"};*/
	public String[] goddessBlessing={"여신의 축복","신의 권능"};
	
	// 플레이어 특정 생성자
	public Player(String name,int lv,double maxhp,double maxmp,double atk,double intel,double spd,double pdef,double mdef,double xp) {
		//슈퍼 클래스의 호출 생성자
		super(name,0,0,0,	0,0,0,0,	0,0);		//이름,레벨,최대 체력,최대 마나,	공격력,주문력,스피드,물리방어,	마법방어,현재 경험치
		
		this.name=name;									//이름
		this.xp=xp; this.lv=lv;							//경험치,레벨
		this.hp=maxhp; this.maxhp=maxhp;				//체력,최대 체력
		this.mp=maxmp;	this.maxmp=maxmp;				//마나,최대 마나
		this.mdef=mdef;	this.pdef=pdef;					//마법 저항력,물리 방어력
		this.atk=atk; this.intel=intel; this.spd=spd;
		
		this.numgoddessBlessing=0;
		// 추가 스텟 설정
		this.gold=5;
		this.restsLeft=1;
		this.maxxp=10;
		//플레이어가 새 캐릭터를 만들 때 특성을 선택하도록 하기
		//chooseTrait();
	}
	
	//스텟 증감률
	private Player(String name,double hprate,double mprate,double atkrate,double intelrate,double spdrate,double pdefrate,double mdefrate,double xprate) {
		// 이름,체력 증가율,마나 증가율,	공격력 증가율,주문력 증가율,스피드 증가율,		물리방어 증가율,마법저항 증가율,드롭 경험치 증가율
		super("",0,0, 0,0,0, 0,0,0);
		this.name=name;		this.xprate=xprate;				//이름(개발자 식별용), 드롭 경험치 증가율
		 this.hprate=hprate;	this.mprate=mprate;			//최대 체력, 마나 증가율
		this.mdefrate=mdefrate;	this.pdefrate=pdefrate;		//마법 저항력,물리 방어력	증가율		
		this.atkrate=atkrate; this.intelrate=intelrate; this.spdrate=spdrate;	//공격력, 주문력, 스피드 증가율
	}
	
	static Player[] playerSet=new Player[2];
	static Player[] playerstSet=new Player[2];
	
	//플레이어블 캐릭터 프리셋
	public static void Make_Player_Preset() {
		// 이름,레벨,최대 체력,최대 마나,	공격력,주문력,스피드,물리방어,	마법방어,현재 경험치
		playerSet[0]=new Player("name",1,100,70,	3,5,5,10,	10,0);
		playerSet[1]=new Player("name",1,100,50,	0,0,0,0,	0,0);
	}
	
	//플레이어블 캐릭터 스텟 증감률 프리셋
	public static void Make_Player_stPreset() {
		// 이름,체력 증가율,마나 증가율,	공격력 증가율,주문력 증가율,스피드 증가율,		물리방어 증가율,마법저항 증가율,드롭 경험치 증가율
		playerstSet[0]=new Player("name",10,10,	3,5,2,	5,0,0);
		playerstSet[1]=new Player("",0,0,	0,0,0,	0,0,0);
	}
	
	// 플레이어 기본공격 메소드
	@Override
	public double attack() {
		return Math.round(GameLogic.player.atk)*Math.floor((((Math.random()*52)+230)/255)*100)/100.0; /*+숙련도*/	//숙련도 상수를 도입해서 "본인이 가진 잠재력을 온전히 끌어내는 경지~"같은 설정을 넣어볼까?
	}
	
	//스킬 공격 데미지 정의
	@Override
	public double skillAtk() {
		//얘들은 초기화 해줘야 고장 안 남
		double skilldmg=0;
			//여신의 축복 버프 여부에 따라 소모량을 다르게 적용
			if(GameLogic.blessed==1) {
				GameLogic.player.mp-=Math.round((double)(0.9*(Skill.skills.get(GameLogic.inputToPC).conmp)));
			}else {
				GameLogic.player.mp-=Skill.skills.get(GameLogic.inputToPC).conmp;}
				skilldmg=Math.round((double)(Skill.skills.get(GameLogic.inputToPC).skdmg)+(Skill.skills.get(GameLogic.inputToPC).pcoeif*GameLogic.player.atk)
				+(Skill.skills.get(GameLogic.inputToPC).mcoeif*GameLogic.player.intel));
			//0.9~1.1의 난수로 데미지에 운적인 요소 추가
		return skilldmg*Math.floor((((Math.random()*52)+230)/255)*100)/100.0;
	}

	@Override	//방어력이 양수
	public double Pdefp() {
		return Math.floor((100/(100+GameLogic.player.pdef))*100)/100.0;
	}
	@Override //방어력이 음수
	public double Pdefn() {
		return Math.floor((2-(100/(100-GameLogic.player.pdef)))*100)/100.0;
	}
	
	@Override	//마법방어력이 양수
	public double Mdefp() {
		return Math.floor((100/(100+GameLogic.player.mdef))*100)/100.0;
	}
	@Override	//마법방어력이 음수
	public double Mdefn() {
		return Math.floor((2-(100/(100-GameLogic.player.mdef)))*100)/100.0;
	}
	
	/*//레벨 업에 따른 스텟 증가
	public void LevelUp() {
		//10,20,35,55,80
	}*/
	
	//플레이어가 스킬 계열을 선택하도록 하기. 음... 추가될 플레이어블 캐릭터 종족에 따라 '드래곤 스킨(용 피부)'같은 거 만들면 좋겠다. 일단 첫 번째 주인공은 그냥 두자.
	public void chooseTrait() {
		GameLogic.clearConsole();
		System.out.println("특성은 업데이트 준비 중");
		/*GameLogic.printHeading("특성을 선택하세요: ");
		System.out.println("(1) "+atkUpgrades[numAtkUpgrades]);
		System.out.println("(2) "+defUpgrades[numDefUpgrades]);
		int input=GameLogic.readInt(">>>",2);
		GameLogic.clearConsole();
		
		//두 가지 케이스
		if(input==1) {
			GameLogic.printHeading(atkUpgrades[numAtkUpgrades]+"를 선택했습니다.");
			numAtkUpgrades++;
		}else{
			GameLogic.printHeading(defUpgrades[numDefUpgrades]+"를 선택했습니다.");
			numDefUpgrades++;
		}*/
		GameLogic.anythingToContinue();
	}

}
