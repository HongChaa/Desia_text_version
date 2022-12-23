package MainPackage;

public class Enemy extends Character{
	//String droptem;	//드롭 아이템(배열로 만드는 게 좋을듯)
		//String enemyinfo;	//나중에 딕셔너리 클래스를 만들어서 거기에 구현. 배열이니까 에너미 넘버는 구현할 필요 없음.
	//enemyList의 배열 번호에 해당하는 적 생성
	static int EL;
	//String droptem;	//드롭 아이템(배열로 만드는 게 좋을듯)
	//String enemyinfo;	//나중에 딕셔너리 클래스를 만들어서 거기에 구현. 배열이니까 에너미 넘버는 구현할 필요 없음.
	//적 생성자
	// 이름,레벨,최대 체력,최대 마나,	공격력,주문력,스피드,		물리방어,마법방어,드롭 경험치
	public Enemy(String name,int lv,double maxhp,double maxmp,double atk,double intel,double spd,double pdef,double mdef,double xp) {
		super("생성 초기 상태",0,0,0, 0,0,0, 0,0,0);
		this.name=name;									//이름
		this.xp=xp; this.lv=lv;							//경험치,레벨
		this.hp=maxhp; this.maxhp=maxhp;				//체력,최대 체력
		this.mp=maxmp;	this.maxmp=maxmp;				//마나,최대 마나
		this.mdef=mdef;	this.pdef=pdef;					//마법 저항력,물리 방어력
		this.atk=atk; this.intel=intel; this.spd=spd;
		
		/*super(enemyList[EL].name,enemyList[EL].lv,enemyList[EL].maxhp,enemyList[EL].maxmp, 
				enemyList[EL].atk,enemyList[EL].intel,enemyList[EL].spd, 
				enemyList[EL].pdef,enemyList[EL].mdef,enemyList[EL].xp);
				[오답노트] 이러면 당연히 안되죠... 이건 Enemy 생성자가 완비되기도 전에 생성시켜야 가능한, 말 그대로 역설적인 상황입니다.
				해결법은 이전에 자바 강의시간에서 한 예제를 보고 영감을 얻어 떠올렸습니다.
				슈퍼 클래스인 Charactor에서 this.~~ 같은 지정자를 모두 지우고, 해당 클래스의 하위 클래스인 
				플레이어 클래스와 에너미 클래스에서 따로 this.으로 재지정했습니다.
				유레카...유레카...! 유레카아아아아아아!!!!!!!!!!우오오오옷!!!*/
		//this.droptem=Item.머시기...;	//Item 클래스에서 추후 구현
	}
	
	//스텟 증감률
	private Enemy(String name,double hprate,double mprate,double atkrate,double intelrate,double spdrate,double pdefrate,double mdefrate,double xprate) {
		// 이름,체력 증가율,마나 증가율,	공격력 증가율,주문력 증가율,스피드 증가율,		물리방어 증가율,마법저항 증가율,드롭 경험치 증가율
		super("생성 초기 상태",0,0, 0,0,0, 0,0,0);
		this.name=name;		this.xprate=xprate;				//이름(개발자 식별용), 드롭 경험치 증가율
		 this.hprate=hprate;	this.mprate=mprate;			//최대 체력, 마나 증가율
		this.mdefrate=mdefrate;	this.pdefrate=pdefrate;		//마법 저항력,물리 방어력	증가율		
		this.atkrate=atkrate; this.intelrate=intelrate; this.spdrate=spdrate;	//공격력, 주문력, 스피드 증가율
	}
	
	//적이 시전 가능한 스킬 목록 생성자
	public Enemy(String skname1,String skname2,String skname3,String skname4,String skname5) {
		super("","","","","");
		this.skname1=skname1; this.skname2=skname2; this.skname3=skname3;
		this.skname4=skname4; this.skname5=skname5;
	}
	public Enemy(String skname1,String skname2,String skname3,String skname4) {
		super("","","","");
		this.skname1=skname1; this.skname2=skname2; this.skname3=skname3;
		this.skname4=skname4;
	}
	public Enemy(String skname1,String skname2,String skname3) {
		super("","","");
		this.skname1=skname1; this.skname2=skname2; this.skname3=skname3;
	}
	public Enemy(String skname1,String skname2) {
		super("","");
		this.skname1=skname1; this.skname2=skname2;
	}
	public Enemy(String skname1) {
		super("");
		this.skname1=skname1;
	}
	public Enemy() {}
	
	//적 스킬셋을 구성해주는 메소드 Make_Enemy_Skillset에 필요한 적 배열번호 저장 필드
	static int ENum;
	//적의 이름과 연동해서 적 배열번호를 찾는 메소드. GameLogic의 battle 메소드에서 SetEnemySkill 메소드를 이용하기 위해서만 필요하다면 위의 SetEnemySkill 합쳤겠지만, 몹 사전 등에서 쓸모가 있을것임.
	public static void EnemyNumFinder() {
		for(int i=0;i<enemyList.length;i++) {
			if(GameLogic.EName==enemyList[i].name) {
				ENum=i;
				break;}
		}
	}
	
	//적에게 스킬 부여
	public static void SetEnemySkill() {
		Skill.Enemyskills.clear();
		for(int k=0;k<Skill.skillList.length;k++) {
			if(Skill.skillList[k].skillname==Ensk[ENum].skname1 || Skill.skillList[k].skillname==Ensk[ENum].skname2 ||
					Skill.skillList[k].skillname==Ensk[ENum].skname3 || Skill.skillList[k].skillname==Ensk[ENum].skname4 ||
					Skill.skillList[k].skillname==Ensk[ENum].skname5) {
				Skill.Enemyskills.add(new Skill(Skill.skillList[k].skillname,Skill.skillList[k].elements,Skill.skillList[k].type,Skill.skillList[k].skdmg,Skill.skillList[k].pcoeif,
				Skill.skillList[k].mcoeif,Skill.skillList[k].conmp,Skill.skillList[k].sknum,Skill.skillList[k].skinfo));
			}else {continue;}
		}//고장 방지
		Skill.esks=Skill.Enemyskills.size();
	}
	
	public static Enemy[]EnemyStatRate=new Enemy[100];
	// 이름,(증가율)체력,마나, 공격력,주문력,스피드, 물리방어력,마법저항력,드롭 경험치
	public static void Make_EnemySR_List() {
		EnemyStatRate[0]=new Enemy("테스트용 유닛",1,10,	1,0,3,	0,0,0);	//프로토타입이니 레벨은 일단 1로 설정해둠.
		
		EnemyStatRate[1]=new Enemy("고블린",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[2]=new Enemy("스켈레톤",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[3]=new Enemy("슬라임",8,0, 1.5,0,0.8, 1,0.3,1.2);
		EnemyStatRate[4]=new Enemy("좀비",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[5]=new Enemy("매드 프리스트",1,10,	1,0,3,	0,0,0);
		
		EnemyStatRate[6]=new Enemy("나이트워커",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[7]=new Enemy("망령",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[8]=new Enemy("스톤 골렘",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[9]=new Enemy("변질된 엘리멘터리",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[10]=new Enemy("와이번",1,10,	1,0,3,	0,0,0);
		
		EnemyStatRate[11]=new Enemy("도적",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[12]=new Enemy("약탈자",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[13]=new Enemy("홉고블린",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[14]=new Enemy("고블린 샤먼",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[15]=new Enemy("트롤",1,10,	1,0,3,	0,0,0);
		
		EnemyStatRate[16]=new Enemy("불 원소 정령",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[17]=new Enemy("물 원소 정령",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[18]=new Enemy("공기 원소 정력",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[19]=new Enemy("흙 원소 정령",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[20]=new Enemy("원소 정령 복합체",1,10,		1,0,3,	0,0,0);
		/*
		EnemyStatRate[21]=new Enemy("엘프 전사",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[22]=new Enemy("엘프 궁수",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[23]=new Enemy("엘프 마법사",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[24]=new Enemy("엘프 지휘관",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[25]=new Enemy("엘프 척후병",1,10,	1,0,3,	0,0,0);
		
		EnemyStatRate[26]=new Enemy("오크 전사",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[27]=new Enemy("오크 궁수",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[28]=new Enemy("오크 돌격병",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[29]=new Enemy("오크 지휘관",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[30]=new Enemy("메탈 리플렉트 슬라임",1,10,	1,0,3,	0,0,0);
		
		EnemyStatRate[31]=new Enemy("가디언 슬라임",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[32]=new Enemy("갓 슬라임",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[33]=new Enemy("종말의 선구자",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[34]=new Enemy("핏빛 갈퀴",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[35]=new Enemy("스켈레톤 프리스트",1,10,	1,0,3,	0,0,0);
		
		EnemyStatRate[36]=new Enemy("고블린 챔피언",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[37]=new Enemy("고블린 로드",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[38]=new Enemy("트롤 돌격병",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[39]=new Enemy("피의 영혼",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[40]=new Enemy("플레시 콜로서스",1,10,	1,0,3,	0,0,0);
		
		EnemyStatRate[41]=new Enemy("레드 드래곤",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[42]=new Enemy("화이트 드래곤",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[43]=new Enemy("블랙 드래곤",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[44]=new Enemy("메탈 드래곤",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[45]=new Enemy("수호 영룡",1,10,	1,0,3,	0,0,0);
		
		EnemyStatRate[46]=new Enemy("라미아",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[47]=new Enemy("라미아 여왕",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[48]=new Enemy("지옥의 변견",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[49]=new Enemy("나가(Naga)",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[50]=new Enemy("헬 드래곤",1,10,	1,0,3,	0,0,0);
		
		EnemyStatRate[51]=new Enemy("케르베로스",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[52]=new Enemy("암흑차원신 크림즌 노바",100,10,	1,0,3,	0,0,0);
		EnemyStatRate[53]=new Enemy("암흑차원사신 크림즌 노바 트리니티",300,45,	1,0,3,	0,0,0);
		EnemyStatRate[54]=new Enemy("이차원수 다크 가넥스",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[55]=new Enemy("이차원수 블레이드 가르디아",1,10,	1,0,3,	0,0,0);
		
		EnemyStatRate[56]=new Enemy("이차원초수 버스터 간다일",100,50,	1,0,2,	0,0,35);
		EnemyStatRate[57]=new Enemy("이차원제 게이라 가일",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[58]=new Enemy("이차원제 발칸 드라그니",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[59]=new Enemy("이차원초제 인디오라 데스볼트",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[60]=new Enemy("이차원 파수기 듀자",1,10,0,	1,0,	0,0,0);
		
		EnemyStatRate[61]=new Enemy("이차원정령 비잠",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[62]=new Enemy("뱀파이어",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[63]=new Enemy("뱀파이어 로드",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[64]=new Enemy("드워프 전사",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[65]=new Enemy("드워프 수호자",1,10,	1,0,3,	0,0,0);
		
		EnemyStatRate[66]=new Enemy("드워프 돌격병",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[67]=new Enemy("드워프 지휘관",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[68]=new Enemy("드워프 중전차",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[69]=new Enemy("에인션트 드래곤",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[70]=new Enemy("사천왕 듀란",1,10,	1,0,3,	0,0,0);
		
		EnemyStatRate[71]=new Enemy("사천왕 콘빅트",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[72]=new Enemy("사천왕 아몬",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[73]=new Enemy("사천왕 아스모데우스",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[74]=new Enemy("마왕 앨리스",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[75]=new Enemy("용사 베르투스",1,10,	1,0,3,	0,0,0);
		
		EnemyStatRate[76]=new Enemy("슈미트",1,10,	1,0,3,	0,0,0); //최종보스이자 주인공과 같은 지구에서의 전생자, 그리고 주인공의 대척점
		EnemyStatRate[77]=new Enemy("암살자",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[78]=new Enemy("수인 전사",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[79]=new Enemy("하르모니아 수호자",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[80]=new Enemy("마족 전사",1,10,	1,0,3,	0,0,0);
		
		EnemyStatRate[81]=new Enemy("트리얼 보병",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[82]=new Enemy("트리얼 궁병",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[83]=new Enemy("트리얼 마검사",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[84]=new Enemy("트리얼 매지션",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[85]=new Enemy("트리얼 지휘관",1,10,	1,0,3,	0,0,0);
		
		EnemyStatRate[86]=new Enemy("트리얼 척후병",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[87]=new Enemy("트리얼 척탄병",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[88]=new Enemy("트리얼 기병",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[89]=new Enemy("트리얼 왕실 친위대",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[90]=new Enemy("트리얼 로열 가디언",1,10,	1,0,3,	0,0,0);
		
		EnemyStatRate[91]=new Enemy("트리얼 ",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[92]=new Enemy("해를 가리는 자",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[93]=new Enemy("마신 그레토스크",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[94]=new Enemy("마신 실러캔스",1,10,	1,0,3,	0,0,0);
		EnemyStatRate[95]=new Enemy("마신 듀브",1,10,	1,0,3,	0,0,0);*/
	}
	
	//적들이 가진 스킬 리스트
	public static Enemy[]Ensk=new Enemy[21];
	public static void Make_Enemy_SkillsetList() {
		Ensk[0]=new Enemy();
		
		Ensk[1]=new Enemy();
		Ensk[2]=new Enemy();
		Ensk[3]=new Enemy();
		Ensk[4]=new Enemy();
		Ensk[5]=new Enemy("사악한 손길","워터 스플래시","파이어");
		
		Ensk[6]=new Enemy();
		Ensk[7]=new Enemy();
		Ensk[8]=new Enemy();
		Ensk[9]=new Enemy("파이어","워터 스플래시");
		Ensk[10]=new Enemy();
		
		Ensk[11]=new Enemy();
		Ensk[12]=new Enemy();
		Ensk[13]=new Enemy();
		Ensk[14]=new Enemy("파이어","워터 스플래시","사악한 손길");
		Ensk[15]=new Enemy();
		
		Ensk[16]=new Enemy("파이어","워터 스플래시");
		Ensk[17]=new Enemy("파이어","워터 스플래시");
		Ensk[18]=new Enemy("파이어","워터 스플래시");
		Ensk[19]=new Enemy("파이어","워터 스플래시");
		Ensk[20]=new Enemy("파이어","워터 스플래시");
		/*
		Ensk[21]=new Enemy();
		Ensk[22]=new Enemy();
		Ensk[23]=new Enemy();
		Ensk[24]=new Enemy();
		Ensk[25]=new Enemy("사악한 손길");
		
		Ensk[26]=new Enemy();
		Ensk[27]=new Enemy();
		Ensk[28]=new Enemy();
		Ensk[29]=new Enemy();
		Ensk[30]=new Enemy("사악한 손길");
		
		Ensk[31]=new Enemy();
		Ensk[32]=new Enemy();
		Ensk[33]=new Enemy();
		Ensk[34]=new Enemy();
		Ensk[35]=new Enemy("사악한 손길");
		
		Ensk[36]=new Enemy();
		Ensk[37]=new Enemy();
		Ensk[38]=new Enemy();
		Ensk[39]=new Enemy();
		Ensk[40]=new Enemy("사악한 손길");
		
		Ensk[41]=new Enemy();
		Ensk[42]=new Enemy();
		Ensk[43]=new Enemy();
		Ensk[44]=new Enemy();
		Ensk[45]=new Enemy("사악한 손길");
		
		Ensk[46]=new Enemy();
		Ensk[47]=new Enemy();
		Ensk[48]=new Enemy();
		Ensk[49]=new Enemy();
		Ensk[50]=new Enemy("사악한 손길");
		
		Ensk[51]=new Enemy();
		Ensk[52]=new Enemy();
		Ensk[53]=new Enemy();
		Ensk[54]=new Enemy();
		Ensk[55]=new Enemy("사악한 손길");
		
		Ensk[56]=new Enemy("트리플 큐빅 버스트","큐빅 스트라이크");
		Ensk[57]=new Enemy();
		Ensk[58]=new Enemy();
		Ensk[59]=new Enemy();
		Ensk[60]=new Enemy("사악한 손길");*/
	}
	
	public static Enemy[]enemyList=new Enemy[21];
	public static void Make_Enemy_List() {
		//이름,레벨,최대 체력,최대 마나,	공격력,주문력,스피드,		물리방어,마법방어,드롭 경험치
		 //드롭 경험치는 나중에 round로 반올림하자. xp표기가 너무 더러워짐.
		enemyList[0]=new Enemy("테스트용 유닛",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));	//프로토타입이니 레벨은 일단 1로 설정해둠.
		
		enemyList[1]=new Enemy("고블린",1,10,0,	2,0,3,	1,1,5);
		enemyList[2]=new Enemy("스켈레톤",1,20,0,	4,0,1,	0,0,8);
		enemyList[3]=new Enemy("슬라임",1,10,0,	1,0,2,	0,0,3);
		enemyList[4]=new Enemy("좀비",1,20,0,		3,0,1,	0,0,2);
		enemyList[5]=new Enemy("매드 프리스트",1,20,100,	2,10,3,	0,0,(double)(Math.random()*20+1));
		
		enemyList[6]=new Enemy("나이트워커",1,30,50,	8,0,5,	0,0,(double)(Math.random()*15+1));
		enemyList[7]=new Enemy("망령",1,20,50,	1,0,3,	0,0,(double)(Math.random()*10+1));
		enemyList[8]=new Enemy("스톤 골렘",1,80,30,	1,0,3,	0,0,(double)(Math.random()*20+1));
		enemyList[9]=new Enemy("변질된 엘리멘터리",1,20,100,	1,22,3,	0,0,(double)(Math.random()*8+1));
		enemyList[10]=new Enemy("와이번",1,40,0,	10,0,6,	0,0,(double)(Math.random()*9+1));
		
		enemyList[11]=new Enemy("도적",1,50,10,	5,0,5,	0,0,(double)(Math.random()*3+1));
		enemyList[12]=new Enemy("약탈자",1,50,10,	5,0,3,	1,1,(double)(Math.random()*3+1));
		enemyList[13]=new Enemy("홉고블린",1,80,0,	10,0,3,	2,2,(double)(Math.random()*3+1));
		enemyList[14]=new Enemy("고블린 샤먼",1,50,50,	3,10,3,	0,0,(double)(Math.random()*3+1));
		enemyList[15]=new Enemy("트롤",1,100,0,	15,0,2,	4,4,(double)(Math.random()*30+1));
		
		enemyList[16]=new Enemy("불 원소 정령",1,20,100,	1,10,3,	0,0,(double)(Math.random()*10+10));
		enemyList[17]=new Enemy("물 원소 정령",1,20,100,	1,10,3,	0,0,(double)(Math.random()*10+10));
		enemyList[18]=new Enemy("공기 원소 정력",1,20,100,	1,10,3,	0,0,(double)(Math.random()*10+10));
		enemyList[19]=new Enemy("흙 원소 정령",1,20,100,	1,10,3,	0,0,(double)(Math.random()*10+10));
		enemyList[20]=new Enemy("원소 정령 복합체",1,50,100,	1,20,3,	0,0,(double)(Math.random()*40+20));
		/*
		enemyList[21]=new Enemy("엘프 전사",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[22]=new Enemy("엘프 궁수",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[23]=new Enemy("엘프 마법사",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[24]=new Enemy("엘프 지휘관",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[25]=new Enemy("엘프 척후병",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		
		enemyList[26]=new Enemy("오크 전사",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[27]=new Enemy("오크 궁수",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[28]=new Enemy("오크 돌격병",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[29]=new Enemy("오크 지휘관",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[30]=new Enemy("메탈 리플렉트 슬라임",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		
		enemyList[31]=new Enemy("가디언 슬라임",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[32]=new Enemy("갓 슬라임",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[33]=new Enemy("종말의 선구자",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[34]=new Enemy("핏빛 갈퀴",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[35]=new Enemy("스켈레톤 프리스트",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		
		enemyList[36]=new Enemy("고블린 챔피언",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[37]=new Enemy("고블린 로드",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[38]=new Enemy("트롤 돌격병",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[39]=new Enemy("피의 영혼",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[40]=new Enemy("플레시 콜로서스",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		
		enemyList[41]=new Enemy("레드 드래곤",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[42]=new Enemy("화이트 드래곤",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[43]=new Enemy("블랙 드래곤",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[44]=new Enemy("메탈 드래곤",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[45]=new Enemy("수호 영룡",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		
		enemyList[46]=new Enemy("라미아",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[47]=new Enemy("라미아 여왕",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[48]=new Enemy("지옥의 변견",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[49]=new Enemy("나가(Naga)",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[50]=new Enemy("헬 드래곤",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		
		enemyList[51]=new Enemy("케르베로스",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[52]=new Enemy("암흑차원신 크림즌 노바",1,3000,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[53]=new Enemy("암흑차원사신 크림즌 노바 트리니티",1,4500,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[54]=new Enemy("이차원수 다크 가넥스",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[55]=new Enemy("이차원수 블레이드 가르디아",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		
		enemyList[56]=new Enemy("이차원초수 버스터 간다일",30,100,0,	10,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[57]=new Enemy("이차원제 게이라 가일",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[58]=new Enemy("이차원제 발칸 드라그니",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[59]=new Enemy("이차원초제 인디오라 데스볼트",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[60]=new Enemy("이차원 파수기 듀자",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		
		enemyList[61]=new Enemy("이차원정령 비잠",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[62]=new Enemy("뱀파이어",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[63]=new Enemy("뱀파이어 로드",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[64]=new Enemy("드워프 전사",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[65]=new Enemy("드워프 수호자",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		
		enemyList[66]=new Enemy("드워프 돌격병",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[67]=new Enemy("드워프 지휘관",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[68]=new Enemy("드워프 중전차",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[69]=new Enemy("에인션트 드래곤",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[70]=new Enemy("사천왕 듀란",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		
		enemyList[71]=new Enemy("사천왕 콘빅트",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[72]=new Enemy("사천왕 아몬",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[73]=new Enemy("사천왕 아스모데우스",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[74]=new Enemy("마왕 앨리스",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[75]=new Enemy("용사 베르투스",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		
		enemyList[76]=new Enemy("슈미트",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1)); //최종보스이자 주인공과 같은 지구에서의 전생자, 그리고 주인공의 대척점
		enemyList[77]=new Enemy("암살자",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[78]=new Enemy("수인 전사",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[79]=new Enemy("하르모니아 수호자",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[80]=new Enemy("마족 전사",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		
		enemyList[81]=new Enemy("트리얼 보병",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[82]=new Enemy("트리얼 궁병",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[83]=new Enemy("트리얼 마검사",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[84]=new Enemy("트리얼 매지션",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[85]=new Enemy("트리얼 지휘관",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		
		enemyList[86]=new Enemy("트리얼 척후병",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[87]=new Enemy("트리얼 척탄병",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[88]=new Enemy("트리얼 기병",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[89]=new Enemy("트리얼 왕실 친위대",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[90]=new Enemy("트리얼 로열 가디언",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		
		enemyList[91]=new Enemy("트리얼 ",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[92]=new Enemy("해를 가리는 자",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[93]=new Enemy("마신 그레토스크",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[94]=new Enemy("마신 실러캔스",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1));
		enemyList[95]=new Enemy("마신 듀브",1,10,0,	1,0,3,	0,0,(double)(Math.random()*3+1)); *///여포 duvh 듀브. 여포의 영혼이 마신이 됐다는 설정.		
	}
	//적의 기본 공격
	@Override
	public double attack() {
		return Math.round((GameLogic.tmpE.atk)*Math.floor((((Math.random()*52)+230)/255)*100)/100.0);
	}
		
	//스킬 공격 데미지 정의
	@Override
	public double skillAtk() {
		//얘들은 초기화 해줘야 고장 안 남
		double skilldmg=0;
			/*if(GameLogic.blessed==1) {
				GameLogic.tmpE.mp-=Math.round((double)(0.8*(Skill.skills.get(GameLogic.ESLN).conmp)));
			}else {*/	//추후에 '마신의 가호'같은 버프 추가하면 쓸지도?
			GameLogic.tmpE.mp-=Skill.Enemyskills.get(GameLogic.ESLN).conmp;
				skilldmg=Math.round((double)(Skill.Enemyskills.get(GameLogic.ESLN).skdmg)+(Skill.Enemyskills.get(GameLogic.ESLN).pcoeif*GameLogic.tmpE.atk)
				+(Skill.Enemyskills.get(GameLogic.ESLN).mcoeif*GameLogic.tmpE.intel));
		return skilldmg*Math.floor((((Math.random()*52)+230)/255)*100)/100.0;
	}
	
	@Override	//물리 방어력이 양수
	public double Pdefp() {
		return Math.floor((100/(100+GameLogic.tmpE.pdef))*100)/100.0;
	}
	@Override	//음수일 때
	public double Pdefn() {
		return Math.floor((2-(100/(100-GameLogic.tmpE.pdef)))*100)/100.0;
	}
	
	@Override	//마법 방어력이 양수
	public double Mdefp() {
		return Math.floor((100/(100+GameLogic.tmpE.mdef))*100)/100.0;
	}
	@Override	//음수일 때
	public double Mdefn() {
		return Math.floor((2-(100/(100-GameLogic.tmpE.mdef)))*100)/100.0;
	}
	
	

}

