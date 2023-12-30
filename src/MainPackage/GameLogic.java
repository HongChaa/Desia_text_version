package MainPackage;
import java.util.Scanner;

public class GameLogic {
	static Scanner scan=new Scanner(System.in);
	static Scanner scan2=new Scanner(System.in);
	
	static Player player;
	
	public static boolean isRunning;
	
	//랜덤 인카운터; 랜덤 40%, 상점 10%, 휴식 10%, 서브 스토리 10%, 메인 시나리오 10%
	public static String[] encounters= {"랜덤","랜덤","랜덤","랜덤","상점","상점","휴식","서브","서브","메인"};
	//현 상황에 대한 설명을 문자형 변수로 선언
		
	//스토리 요소
	public static int act=1;
	
	//Player 클래스로 리스트 번호를 전달하는 변수
	static int inputToPC;
	//Place 클래스로 배열 번호를 전달하는 변수
	static int PNL;	//Place Number List

	//전투 메소드와 CheckHp간 상호작용을 만드는 변수
	private static String enemyname;
	private static double enemyhp;
	private static double enemyxp;
	private static int battleLooprun;
	private static String name;
	
	//버프 및 디버프 변수. 일단은 플레이어 전용으로 구현.
	//여신의 축복
	public static int blessed=0;
	//여신의 축복
	public static void Blessing() {
		blessed=1;
		player.atk*=1.1;
		player.intel*=1.1;
		player.pdef*=1.1;
		player.mdef*=1.1;
		player.spd*=1.1;
	}
	//레벨업 시 재설정을 위해 축복을 해제하기 위한 메소드
	public static void RemoveBlessing() {
		player.atk/=1.1;
		player.intel/=1.1;
		player.pdef/=1.1;
		player.mdef/=1.1;
		player.spd/=1.1;
		blessed=0;
	}
	
	
	//유저 입력을 콘솔에서 받기 위한 메소드
	public static int readInt(String prompt,int userChoices) {
		int input;
		do{
			System.out.println(prompt);
			try {
				input=Integer.parseInt(scan.next());	//Integer.parseInt는 문자형 숫자를 정수형으로 변환시킴. 이렇게 하면 굳이 misinput예외처리를 할 필요가 없어짐.
			}catch(Exception e) {
				input= -1;
				System.out.println("정수를 입력하세요");
			}
		}while(input < 1 || input > userChoices);
		return input;
	}
	
	// (공백 출력하는 방식으로) 콘솔 청소하기
	public static void clearConsole() {
		for(int i=0; i<50; i++) {
			System.out.println();
		}
	}
	
	// 길이가 n인 문단 구분자 만들기
	public static void printSeperator(int n) {
		for(int i=0;i<n;i++) {
			System.out.print("-");
		}System.out.println();
	}
	
	// 제목 출력하기
	public static void printHeading(String title) {
		printSeperator(30);
		System.out.println(title);
		printSeperator(30);
	}
	
	// 플레이어가 누르기 전까지 대기하게 하는 메소드
	public static void anythingToContinue() {
		System.out.println("\n\n계속하려면 아무 키나 입력하세요...\n");
		scan.next();
	}
	
	//게임 시작 메소드
	public static void startGame() {
		boolean nameSet = false;
		//제목 화면 출력
		clearConsole();
		printSeperator(40);
		printSeperator(30);
		System.out.println("어느 모험가 이야기");
		System.out.println("텍스트 RPG by PYJ");
		printSeperator(30);
		printSeperator(40);
		anythingToContinue();
		
		//플레이어 이름 입력받기
		do {
			clearConsole();
			printHeading("당신의 이름은?");
			name=scan2.nextLine();
			//입력받은 이름을 수정할 것인지 묻기
			printHeading("입력한 이름["+name+"]가 정확합니까?");
			System.out.println("(1) 그렇다");
			System.out.println("(2) 아니다");
			int input=readInt(">>>",2);
			if(input==1) {
				nameSet=true;
			}
		}while(!nameSet);
		
		// 스토리 인트로 출력
		// 올 스킵
		int skip=readInt("시나리오 1e까지 모두 스킵하시겠습니까?\n(1)예\n(2)아니오",2);
		if(skip==2) {
			int input=readInt("프롤로그 스토리를 스킵하시겠습니까?\n(1)예\n(2)아니오",2);
			if(input==2) {
				Story.printIntro();
			}
		}
				
		//해당 이름의 새 플레이어 객체 생성
		Player.Make_Player_Preset();
		player=new Player(name,Player.playerSet[0].lv,Player.playerSet[0].maxhp,Player.playerSet[0].maxmp,
				Player.playerSet[0].atk,Player.playerSet[0].intel,Player.playerSet[0].spd,
				Player.playerSet[0].pdef,Player.playerSet[0].mdef,Player.playerSet[0].xp);
		
		//스킬 배열, 적 스텟 증가율 배열, 적 배열, 장소 배열 등 생성
		Skill.Make_Skill_List();
		Enemy.Make_EnemySR_List();
		Enemy.Make_Enemy_List();
		Place.Make_Place_List();
		Enemy.Make_Enemy_SkillsetList();
		Consumable.Make_Consumable_List();
		Equipment.Make_Equipment_List();
		Quest.Make_Quest_List();
		
		Skill.getSkill("파이어 볼");
		Skill.getSkill("파이어 애로우");
		Skill.getSkill("라이징 플레어");
		player.gold+=10000;
		
		// 시나리오 1 출력
		if (skip==2) {
			int inputa=readInt("시나리오 1을 스킵하시겠습니까?\n(1)예\n(2)아니오",2);
			if(inputa==2) {
				Story.Mainscenario_1();
			}
			int input1=readInt("(1)달려든다\n(2)(불쾌한 표정을 지으며)참는다\n(3)나무아미타불 관세음보살...",3);
			if(input1==1) {
				Story.Mainscenario_1a();
			}else if(input1==2) {
				Story.Mainscenario_1b();
			}else if(input1==3) {
				Story.Mainscenario_1c();
				player.numgoddessBlessing+=1;
				Blessing();
			}
			int input1e=readInt("시나리오 1e를 생략하시겠습니까?\n(1)예\n(2)아니오",2);
			if(input1e==2) {
			Story.Mainscenario_1e1();
			characterInfo();
			Story.Mainscenario_1e2();
			}
		}
		
		//게임 루프가 계속되도록 isRunning을 true로 설정함.
		isRunning=true; 
		
		//메인 게임 루프 시작
		//시작 장소를 Place.places[0] 마른 마을로 설정
		PNL=0;
		gameLoop();
	}
	
	public static void Lvcheck() {
		boolean lvloop=true;
		while(lvloop)
		if(player.xp>=player.maxxp) {
			player.lv+=1;
			player.xp-=player.maxxp;
			if(player.lv==2) {
				Skill.getSkill("파이어");
			}else if(player.lv==3) {
				Skill.getSkill("워터 스플래시");
			}else if(player.lv==4) {
				Skill.getSkill("파이어 애로우");
			}else if(player.lv==5) {
				Skill.getSkill("파이어 볼");
			}else if(player.lv==6) {
				Skill.getSkill("파이어 버스트");
			}else if(player.lv==7) {
				Skill.getSkill("라이징 플레어");
			}
			//최대 경험치 증가량은 레벨 구간에 따라 차등적인 공식 적용
			if(player.lv<10) {
				player.maxxp*=1.5;
			}else if(player.lv>=10) {
				player.maxxp*=1.2;
			}else if(player.lv>=20) {
				player.maxxp*=1.1;
			}
			player.maxhp+=15;
			player.maxmp+=10;
			//플레이어가 그동안 전투에서 선택한 공격 유형(물리, 마법)의 수를 각각 세고, 그에 따라 증가량 범위 내에서 자동으로 분배되도록 만들어보자.
			//여신의 축복 버프가 존재할 경우. 비율 증가이기 때문에 버프를 제거 후 다시 적용해야 함.
			//!!!주의!!! 착용한 장비로 붙은 추가 스탯에는 버프가 적용되지 않도록 조정.
			if(blessed==1) {
				RemoveBlessing();
				player.atk+=3;	player.intel+=5;	player.spd+=2;
				player.pdef+=2;	player.mdef+=3.5;
				Blessing();
			}else {
			player.atk+=3;	player.intel+=5;	player.spd+=2;
			player.pdef+=2;	player.mdef+=3.5;
			}
			//체력과 마나 일부 회복
			player.hp+=player.maxhp/2;	player.mp+=player.maxmp/2;
			//최대 체력, 마나 초과 방지
			if(player.hp>player.maxhp) {player.hp=player.maxhp;}
			if(player.mp>player.maxmp) {player.mp=player.maxmp;}
			
			System.out.println("레벨 업 했습니다!\t( "+Math.round(player.xp*10)/10.0+"/"+Math.round(player.maxxp*10)/10.0+" )");
			//player.chooseTrait();
		}else {
			lvloop=false;
		}
	}
	
	//플레이어의 레벨 관리 및 act값에 따른 변화
	public static void checkAct() {
		//xp에 따른 변화
		if(player.xp>=player.maxxp && act==1) {
			//act와 장소 값 증가
			act=2;
			//스토리
			//레벨업
			System.out.println("레벨 업 했습니다!");
			player.lv+=1;
			player.maxxp+=10;	//20
			player.chooseTrait();
			//스토리
			Story.Mainscenario_2();
			//인카운터들에게 새로운 값 할당
			encounters[0]="전투";
			encounters[1]="전투";
			encounters[2]="전투";
			encounters[3]="전투";
			encounters[4]="상점";
			encounters[5]="상점";
			encounters[6]="휴식";
			encounters[7]="서브";
			encounters[8]="서브";
			encounters[9]="메인";
		}else if(player.xp>=player.maxxp && act==2){
			//레벨업
			System.out.println("레벨 업 했습니다!");
			player.lv+=1;
			player.maxxp+=15;	//35
			player.chooseTrait();
			/*//적들에게 새로운 값 할당
			enemies[0]="악의 용병";
			enemies[1]="고블린";
			enemies[2]="오크";
			enemies[3]="마왕의 심복";
			enemies[4]="으스스한 이방인";*/
			//암튼 이런식으로 늘리기...
		}else if(player.xp>=player.maxxp && act==2){
			//레벨업
			System.out.println("레벨 업 했습니다!");
			player.lv+=1;
			player.maxxp+=20;	//55
			player.chooseTrait();
		}else if(player.xp>=player.maxxp && act==2){
			//레벨업
			System.out.println("레벨 업 했습니다!");
			player.lv+=1;
			player.maxxp+=25;	//80
			player.chooseTrait();
		}else if(player.xp>=100 && act==3){
			//마지막 전투
			finalBattle();
		}
	}
	
	//랜덤 인카운터 계산 메소드
	public static void randomEncounter() {
		// 0부터 랜덤 인카운터 배열값 범위의 무작위 수
		int encounter=(int)(Math.random()*encounters.length);
		// 각각의 메소드 호출
		if(encounters[encounter].equals("랜덤")) {
			//randomBattle();
			setBattle();
		}else if(encounters[encounter].equals("휴식")) {
			takeRest();
		}else if(encounters[encounter].equals("상점")) {
			shop();
		}else if(encounters[encounter].equals("서브")) {
			//서브 스토리
			System.out.println("서브 스토리");
			anythingToContinue();
		}else if(encounters[encounter].equals("메인")) {
			//메인 시나리오 진행
			System.out.println("메인 스토리");
			anythingToContinue();
		}else {System.out.println("오류 발생. 랜덤 인카운터 오류");}
	}
	
	// 캐릭터 스테이터스 오픈
	public static void characterInfo() {	// Math.round(	// )*10/10.0
		status: while(true) {
			boolean info=true;
			do {
				clearConsole();
				printHeading("\t<<캐릭터 정보>>");
				//이름, 레벨
				System.out.println(player.name+"\tLv."+player.lv);
				System.out.println("XP: "+Math.round(player.xp*10)/10.0+"/"+Math.round(player.maxxp*10)/10.0);
				printSeperator(40);
				System.out.println("HP: "+Math.round(player.hp*10)/10.0+"/"+Math.round(player.maxhp*10)/10.0+"\tMP: "+Math.round(player.mp*10)/10.0
				+"/"+Math.round(player.maxmp*10)/10.0+"\n공격력: "+Math.round(player.atk*10)/10.0+"\t주문력: "+Math.round(player.intel*10)/10.0
				+"\t스피드: "+Math.round(player.spd*10)/10.0+"\n물리 방어력: "+Math.round(player.pdef*10)/10.0+"\t마법 저항력: "+Math.round(player.mdef*10)/10.0);
				printSeperator(40);
				//플레이어 소지금
				System.out.println("골드: "+player.gold);
				printSeperator(40);
				System.out.println("스킬 (1 입력)");
				printSeperator(40);
				//선택한 특성 출력
				System.out.println("[신의 대리인]\n대상을 꿰뚫어볼 수 있는 권능, 모든 언어를 습득(일부 대상과 언어의 경우 적용되지 않습니다).");
				if(player.numgoddessBlessing==1) {
					System.out.println("["+player.goddessBlessing[player.numgoddessBlessing -1]+"]"
					+"\n경험치 획득량이 33%, 공격력, 주문력, 물리 방어력, 마법 저항력, 스피드가 10% 증가하며, 마나 소모량이 10% 감소합니다.");
				}
				printSeperator(40);
				int input=readInt("\n(1)스킬 정보\n(2)돌아가기",2);
				if(input==1) {
					boolean skinfo=true;
					do {
					clearConsole();
					Skill.DisplaySkill();
					printHeading("\t<스킬 일람>");
					input=readInt("돌아가려면 "+(Skill.sks+1)+"입력)>>>",Skill.sks+1);
					if(input==(Skill.sks)+1) {
						skinfo=false;
					}else {
						GameLogic.clearConsole();
						printHeading(Skill.skills.get(input-1).skillname);
						System.out.println("속성: "+Skill.skills.get(input-1).elements+"\t타입: "+Skill.skills.get(input-1).type);
						System.out.println("위력: "+Skill.skills.get(input-1).skdmg+"\t마나 소모량: "+Skill.skills.get(input-1).conmp);
						System.out.println("\n"+Skill.skills.get(input-1).skinfo);
						anythingToContinue();}
					}while(skinfo);
				}else if(input==2){
				info=false;
				break status;
				}
			}while(info);
		}
	}
	
	public static void inventory() {
		inven: while(true) {
			clearConsole();
			printHeading("\t<인벤토리>");
			int input=readInt("(1)장비\t(2)소모품\n(3)돌아가기",3);
			//장비
			if(input==1) {
				boolean eqinfo=true;
				do {
					clearConsole();
					printHeading("\t<장비>");
					Equipment.DisplayEqm();
					input=readInt("돌아가려면 "+(Equipment.ems+1)+"입력)>>>",Equipment.ems+1);
					if(input==(Equipment.ems)+1) {
						eqinfo=false;
					}else {
						GameLogic.clearConsole();
						printHeading(Equipment.equipments.get(input-1).itemName+"\n("+Equipment.equipments.get(input-1).itemNum+"개 보유)");
						//유형,최대체력,최대마나,실드,	물리방어력,마법저항력,공격력,주문력,스피드, 	보유수,정보
						System.out.println("유형: "+Equipment.equipments.get(input-1).itemtype+" \t실드: "+Equipment.equipments.get(input-1).shield
						+"\n체력 증가: "+Equipment.equipments.get(input-1).fmaxhp+"\t마나 증가: "+Equipment.equipments.get(input-1).fmaxmp);
						System.out.println("물리 방어력: "+Equipment.equipments.get(input-1).fpdef+"\t마법 저항력: "+Equipment.equipments.get(input-1).fmdef);
						System.out.println("공격력: "+Equipment.equipments.get(input-1).fatk+"\t주문력: "+Equipment.equipments.get(input-1).fintel+"\t스피드: "+Equipment.equipments.get(input-1).fspd);
						System.out.println(Equipment.equipments.get(input-1).itemInfo);
						anythingToContinue();}
				}while(eqinfo);
			//소모품
			}else if(input==2) {
				boolean coninfo=true;
				do {
					clearConsole();
					printHeading("\t<소모품>");
					Consumable.DisplayConsum();
					input=readInt("돌아가려면 "+(Consumable.css+1)+"입력)>>>",(Consumable.css+1));
					if(input==(Consumable.css+1)) {
						coninfo=false;
					}else {
						boolean itemuse=true;
						do {
							GameLogic.clearConsole();
							printHeading(Consumable.consumables.get(input-1).itemName+"\n("+Consumable.consumables.get(input-1).itemNum+"개 보유)");
							//아이템명,유형,보유수,정보
							System.out.println("유형: "+Consumable.consumables.get(input-1).itemtype);
							System.out.println(Consumable.consumables.get(input-1).itemInfo);
							//소모품 사용
							//소모품 사용이 가능한 경우
							if(Consumable.consumables.get(input-1).itemtype=="능력치 변화" || Consumable.consumables.get(input-1).itemtype=="포션") {
								input=readInt("\n(1)사용하기\t(2)돌아가기",2);
								if(input==1) {
									//포션 사용
									if(Consumable.consumables.get(input-1).itemtype=="포션") {
										player.hp+=Consumable.consumables.get(input-1).fhp;
										player.hp+=Consumable.consumables.get(input-1).fmp;
										//최대수치를 초과하면 수정처리.
										if(player.hp>player.maxhp) {player.hp=player.maxhp;}if(player.mp>player.maxmp) {player.mp=player.maxmp;}
										clearConsole();
										printHeading(Consumable.consumables.get(input-1).itemName+"을(를) 마셨습니다.");
										if(Consumable.consumables.get(input-1).fhp>0) {
											System.out.println("\n체력이 "+Consumable.consumables.get(input-1).fhp+"만큼 회복되었습니다.");
										}if(Consumable.consumables.get(input-1).fmp>0) {
											System.out.println("\n마나가 "+Consumable.consumables.get(input-1).fmp+"만큼 회복되었습니다.");
										}
									}else if(Consumable.consumables.get(input-1).itemtype=="능력치 변화") {
										//이상한사탕, 스텟 상승 아이템 등~
										System.out.println("개발 중");
									}
									//아이템 소지 수 변동처리
									if(Consumable.consumables.get(input-1).itemNum==1) {
										Consumable.consumables.remove(input-1);
									}else {
										Consumable.consumables.get(input-1).itemNum-=1;
									}anythingToContinue();
								//아이템 안 씀.
								}else if(input==2) {
									itemuse=false;
								}
							}anythingToContinue();itemuse=false;
						}while(itemuse);
					}
				}while(coninfo);
			}else if(input==3) {
				break inven;
			}
		}
	}
	
	//랜덤 인카운트 배틀---보다는 특정 장소 조건을 붙이고 해당 장소에서 출현하는 몬스터를 랜덤 배열 번호로 출력해보자.
	
	//쇼핑 / 행상인 조우
	public static void shop() {
		clearConsole();
		printHeading("당신은 로브를 뒤집어쓴 사람과 마주쳤습니다. 그는 당신에게 거래를 제안합니다.");
		System.out.println("현재 개편중인 메소드");
		/*int price=(int)(Math.random()*(10+player.pots*3)+10+player.pots);
		System.out.println("- 체력 포션: "+price+" 골드");
		printSeperator(20);
		// 구매할 것인지 물어봄
		System.out.println("하나 구매하시겠습니까?\n(1) 구매한다\n(2) 구매하지 않는다");
		int input=readInt(">>>",2);
		// 확실히 구매할 것인지 물어봄
		if(input==1) {
			clearConsole();
			//충분한 골드를 소지했는지 확인
			if(player.gold>=price) {
				printHeading("체렉 포션 1개를 "+price+"에 구매했습니다.");
				player.pots++;
				player.gold-=price;
			}else {
				printHeading("해당 물품을 구매하기 위한 골드가 부족합니다.");
			}
		}*/anythingToContinue();
	}
	
	//휴식 취하기
	public static void takeRest() {
		clearConsole();
		if(player.restsLeft>=1) {
			printHeading("휴식을 취하겠습니까? (남은 휴식 포인트: "+player.restsLeft+")");
			System.out.println("(1) 휴식한다\n(2) 하지 않는다");
			int input=readInt(">>>",2);
			if(input==1) {
				// 플레이어가 휴식을 취함
				clearConsole();
				if(player.hp<player.maxhp) {
					int hpRestored=(int)(Math.random()*(player.xp/4+1)+10);
					player.hp+=hpRestored;
					//체력 회복으로 최대 체력을 초과하는 것을 방지
					if(player.hp>player.maxhp) {
						player.hp=player.maxhp;
					}System.out.println("당신은 푹 쉬었습니다. "+hpRestored+"만큼 회복했습니다.");
					System.out.println("(현재 체력: "+player.hp+"/"+player.maxhp+")");
					player.restsLeft--;
				}else {
					System.out.println("이미 체력이 최대입니다. 지금은 휴식을 취할 필요가 없습니다.");
				anythingToContinue();
				}
			}
		}
	}
	
	// 랜덤 배틀 만들기
	public static void randomBattle() {
		clearConsole();
		printHeading("적이 싸움을 걸어왔다! 나는 무기를 꺼내들었다.");
		anythingToContinue();
		//랜덤한 이름의 적 생성
		//battle(new Enemy(enemies[(int)(Math.random()*enemies.length)],player.xp));
	}
	
	//적 객체를 임시 저장. Enemy 클래스의 SkillAtk 메소드로 해당 적의 스탯 정보를 전송할 용도. tmpE: temporary Enemy
	static Enemy tmpE;
	public static void setBattle() {
		clearConsole();
		printHeading("당신은 적과 마주쳤다. 당신은 이에 맞서 싸워야 한다!");
		anythingToContinue();
		//조건 내에서 랜덤한 적 생성
		//실험용 코드. n번 몬스터 호출
		Enemy.EL=(int)(Math.random()*15+1);
		//name,lv,maxhp,maxmp,	atk,intel,spd,	pdef,mdef,xp
		int tmplv=(int)(Math.random()*10+1);
		tmpE=new Enemy(Enemy.enemyList[Enemy.EL].name,tmplv
				// 스텟	+	(레벨-1)	*	레벨 당 스텟 증가율
				,Enemy.enemyList[Enemy.EL].maxhp+(Enemy.enemyList[Enemy.EL].lv-1)*Enemy.EnemyStatRate[Enemy.EL].hprate
				,Enemy.enemyList[Enemy.EL].maxmp+(Enemy.enemyList[Enemy.EL].lv-1)*Enemy.EnemyStatRate[Enemy.EL].mprate
				,Enemy.enemyList[Enemy.EL].atk+(Enemy.enemyList[Enemy.EL].lv-1)*Enemy.EnemyStatRate[Enemy.EL].atkrate
				,Enemy.enemyList[Enemy.EL].intel+(Enemy.enemyList[Enemy.EL].lv-1)*Enemy.EnemyStatRate[Enemy.EL].intelrate
				,Enemy.enemyList[Enemy.EL].spd+(Enemy.enemyList[Enemy.EL].lv-1)*Enemy.EnemyStatRate[Enemy.EL].spdrate
				,Enemy.enemyList[Enemy.EL].pdef+(Enemy.enemyList[Enemy.EL].lv-1)*Enemy.EnemyStatRate[Enemy.EL].pdefrate
				,Enemy.enemyList[Enemy.EL].mdef+(Enemy.enemyList[Enemy.EL].lv-1)*Enemy.EnemyStatRate[Enemy.EL].mdefrate
				, Enemy.enemyList[Enemy.EL].xp+(Enemy.enemyList[Enemy.EL].lv-1)*Enemy.EnemyStatRate[Enemy.EL].xprate);
		battle(tmpE);
	}	
	
	//데미지 계산식에 필요한 필드
	private static double dmgTook;
	private static double dmg;
	//배틀 루프 조작에 필요한 필드
	public static boolean battleSet;
	public static boolean battleSet0;
	//버프에 따른 마나 코스트 변화량 계산에 필요
	static double mpcost;
	//적 배열번호를 찾는 메소드 EnemyNumFinder에 적 이름을 넘겨주기 위한 필드
	static String EName;
	//적이 시전한 스킬 리스트넘버를 Enemy클래스로 전송하기 위한 필드. ESLN: Enemy SkillList Number
	static int ESLN;
	//적이 시전할 수 있는 스킬이 하나라도 있을 경우를 판별. ATC: Able To Cast
	static boolean ATC;
	//적이 실제로 스킬을 시전했는가? EACS: Enemy Actually Cast Skill
	static boolean EACS;
	//방어력으로 감소하기 전 적이 가한 데미지
	static double Edmg;
	//이 메소드 사용 직전에(장소나 스토리 흐름에 맞는) 배열 번호 변수를 미리 설정해두고, 그에 맞추는 게 좋을듯.
	// 메인 전투 메소드
	public static void battle(Enemy enemy) {
		EName=enemy.name;
		Enemy.EnemyNumFinder();
		Enemy.SetEnemySkill();
		/*for(int i=0;i<Skill.esks;i++) {
			System.out.println(Skill.Enemyskills.get(i).skillname);
		}*/
		//CheckHp 메소드로 적 객체의 정보를 넘겨주기 위한 변수
		battleLooprun=1;
		//메인 전투 루프
		battleLoop: while(true) {
			boolean battleSet0=false;
			do {
			clearConsole();
			//적과 플레이어의 체력을 소수점 아래 첫째자리까지 반올림하여 출력. 플레이어는 같은 방식으로 마나량도 출력. 적의 마나는 존재하지만 공개하지 않음. 디버그용으로만 출력하기.
			printHeading(enemy.name+"\tLv."+enemy.lv+"\nHP: "+Math.round(enemy.hp*10)/10.0+"/"+Math.round(enemy.maxhp*10)/10.0
			+"\tMP: "+Math.round(enemy.mp*10)/10.0+"/"+Math.round(enemy.maxmp*10)/10.0);
			printHeading(player.name+"\tLv."+player.lv+"\nHP: "+Math.round(player.hp*10)/10.0+"/"+Math.round(player.maxhp*10)/10.0
			+"\tMP: "+Math.round(player.mp*10)/10.0+"/"+Math.round(player.maxmp*10)/10.0);
			System.out.println("무엇을 할까?");
			printSeperator(20);
			System.out.println(Skill.esks+"(적 보유 스킬 수)");
			
			//적의 행동을 미리 결정(기본 공격, 스킬 공격)
			//시전 가능한 스킬이 있는가?
			//없는 경우
			if(Skill.Enemyskills.size()==0) {
				ATC=false;
			//있는 경우	
			}else {
				//스킬 시전하기에 충분한 마나가 있는지 체크.
				for(int h=0;h<Skill.esks;h++) {
					if(Skill.Enemyskills.get(h).conmp<=enemy.mp) {
						ATC=true;
						break;
					}else {ATC=false; continue;}
				}
			}
			//적이 하나 이상의 스킬을 보유했고, 시전 가능한 스킬이 있는 경우, 90%의 확률로 스킬 공격. 그 외 10%는 기본 공격.
			if(Skill.esks!=0 && ATC && (int)(Math.random()*100 + 1)>10) {
				//사용 가능한 스킬 중 하나를 무작위로 시전한다
				while(true) {
					//보유한 스킬을 랜덤으로 선택 후, 해당 스킬의 마나 소모량만큼의 마나가 있을 경우 스킬 시전, 그렇지 않을 경우 다시 랜덤 선택.
					int p=(int)(Math.random()*Skill.esks);
					if(Skill.Enemyskills.get(p).conmp<=enemy.mp) {
						ESLN=p;
						break;
					}else {continue;}
				}
				EACS=true;//Enemy Actually Casted Skill 적이 실제로 스킬을 사용함
				Edmg=tmpE.skillAtk();
			}else {EACS=false; Edmg=tmpE.attack();}	//사용 안 함. [적의 데미지=기본 공격 데미지]로 설정.
			
			System.out.println("(1)싸운다\n(2)아이템 사용\n(3)도망치기");
			int input=readInt(">>>",3);
			//플레이어의 선택에 따른 실행
			if(input==1) {
				//기본 공격
				boolean battleSet=false;
				dmg=0; dmgTook=0;
				do {
					printHeading("(1)기본 공격\n(2)스킬\n(3)돌아가기");
					int input1=readInt(">>>",3);
					if(input1==1) {
						//플레이어의 기본 공격
						//[추가 바람]여기에 착용한 무기의 공격력도 추가 요청함.
						if(enemy.pdef>=0) {
							dmg=player.attack()*enemy.Pdefp();
						}else if(enemy.pdef<0) {
							dmg=player.attack()*enemy.Pdefn();
						}
						//적의 공격
						if(EACS) {
							if(Skill.Enemyskills.get(ESLN).type=="물리") {
								if(player.pdef>=0) {
									dmgTook = Edmg*player.Pdefp();
								}else if(player.pdef<0) {
									dmgTook = Edmg*player.Pdefn();
								}
							}else if(Skill.Enemyskills.get(ESLN).type=="마법"){
								if(player.mdef>=0) {
									dmgTook = Edmg*player.Mdefp();
									System.out.println(Edmg+" "+player.Mdefp()+" "+dmgTook+"계산 전 데미지, 플레이어 마법 저항력, 실제 받은 데미지");
								}else if(player.mdef<0) {
									dmgTook = Edmg*player.Mdefn();
								}
							}
						}else {
							if(player.pdef>=0) {
								dmgTook = Edmg*player.Pdefp();
							}else if(player.pdef<0) {
								dmgTook = Edmg*player.Pdefn();
							}
						}
						//데미지 값이 음수가 아닌지 확인
						if(dmgTook<0) {
							dmgTook=0;
						//플레이어가 가한 데미지가 음수가 되는 것을 방지
						}if(dmg<0) {
							dmg=0;
						}
						//데미지 수치 적용(HP 변동)
						//해당 배틀 스텝의 처리 결과 출력
						clearConsole();
						if(player.spd<enemy.spd) {
							player.hp-=dmgTook;
							if(EACS) {
								System.out.println(enemy.name+"은(는) "+Skill.Enemyskills.get(ESLN).skillname+"을(를) 시전했다!");}
							System.out.println(enemy.name+"의 공격으로 "+Math.round(dmgTook*10)/10.0+"의 피해를 입었습니다.");
							printSeperator(20);
							anythingToContinue();
							enemyname=enemy.name;
							enemyhp=enemy.hp;
							enemyxp=enemy.xp;
							CheckHp();
							if(battleLooprun==0) {
								break battleLoop;
							}
							enemy.hp-=dmg;
							System.out.println("당신의 공격으로 "+enemy.name+"에게 "+Math.round(dmg*10)/10.0+"의 피해를 입렸습니다.");
							printSeperator(20);
							anythingToContinue();
							enemyname=enemy.name;
							enemyhp=enemy.hp;
							enemyxp=enemy.xp;
							CheckHp();
							if(battleLooprun==0) {
								break battleLoop;
							}
							battleSet=true;
						}else if(player.spd>=enemy.spd){	//스피드가 동급이면 어떻게 처리할지는 나중 업데이트에 구현하고, 일단은 플레이어 우선권을 주자.
							enemy.hp-=dmg;
							System.out.println("당신의 공격으로 "+enemy.name+"에게 "+Math.round(dmg*10)/10.0+"의 피해를 입렸습니다.");
							printSeperator(20);
							anythingToContinue();
							enemyname=enemy.name;
							enemyhp=enemy.hp;
							enemyxp=enemy.xp;
							CheckHp();
							if(battleLooprun==0) {
								break battleLoop;
							}
							player.hp-=dmgTook;
							if(EACS) {
								System.out.println(enemy.name+"은(는) "+Skill.Enemyskills.get(ESLN).skillname+"을(를) 시전했다!");}
							System.out.println(enemy.name+"의 공격으로 "+Math.round(dmgTook*10)/10.0+"의 피해를 입었습니다.");
							printSeperator(20);
							anythingToContinue();
							enemyname=enemy.name;
							enemyhp=enemy.hp;
							enemyxp=enemy.xp;
							CheckHp();
							if(battleLooprun==0) {
								break battleLoop;
							}
							battleSet=true;
						}//else {}	//스피드가 같으면 같은 유닛들끼리의 순서를 랜덤으로 정하는 시스템을 만들고 싶은데, 지금은 완성이 급하니 나중에 하자.
					}
					if(input1==2) {
						//보유한 스킬이 리스트(배열아님)에 없을 경우 발생하는 IndexOutOfBounds 예외처리를 해결
						try {
						Skill.DisplaySkill();
						System.out.println(Skill.sks);
						}catch(IndexOutOfBoundsException e) {
							System.out.println("사용할 수 있는 스킬이 없습니다.");
							anythingToContinue();
							battleSet0=true;
							battleSet=true;
						}int inputsk=GameLogic.readInt("\n>>>",Skill.sks+1);
						//스킬 시전을 취소하고 이전 선택지로 돌아가기
						if(inputsk==(Skill.sks)+1) {
							GameLogic.battleSet=true;
						}
						else {
							//스킬 시전
							//(inputsk-1)을 Player 클래스로 넘김. inputToPC는 public static int형 변수.
							inputToPC=inputsk-1;
							int inputL=inputsk-1;
							//고장 방지
							dmg=0;
							//스킬 시전에 필요한 마나가 충분한지 체크 - 여신의 축복 버프 유무 확인
							if(blessed==1) {
								mpcost=Skill.skills.get(inputL).conmp*0.9;
							}else if(blessed==0) {
								mpcost=Skill.skills.get(inputL).conmp;
							}
							if(mpcost<=player.mp) {
								//스킬 유형에 따라 다른 공식 적용
								if(Skill.skills.get(inputL).type=="물리") {
									if(enemy.pdef>=0) {
										dmg = player.skillAtk()*enemy.Pdefp();
									}else if(enemy.pdef<0) {
										dmg = player.skillAtk()*enemy.Pdefn();
									}
								}else if(Skill.skills.get(inputL).type=="마법")	{
									if(enemy.mdef>=0) {
										dmg = player.skillAtk()*enemy.Mdefp();
									}else if(enemy.mdef<0) {
										dmg = player.skillAtk()*enemy.Mdefn();
									}
								}
								//적의 공격
								if(EACS) {
									if(Skill.Enemyskills.get(ESLN).type=="물리") {
										if(player.pdef>=0) {
											dmgTook = Edmg*player.Pdefp();
										}else if(player.pdef<0) {
											dmgTook = Edmg*player.Pdefn();
										}
									}else if(Skill.Enemyskills.get(ESLN).type=="마법"){
										if(player.mdef>=0) {
											dmgTook = Edmg*player.Mdefp();
										}else if(player.mdef<0) {
											dmgTook = Edmg*player.Mdefn();
										}
									}
								}else {
									if(player.pdef>=0) {
										dmgTook = Edmg*player.Pdefp();
									}else if(player.pdef<0) {
										dmgTook = Edmg*player.Pdefn();
									}
								}
								
								//데미지 값이 음수가 아닌지 확인
								//원본 버전의 흔적. Desia에서는 받는 데미지가 0인 경우를 아직 구현 못했음... 더미 데이터지만 추후 이용할 예정.
								if(dmgTook<0) {
									dmg-=dmgTook/2;
									dmgTook=0;
								//플레이어가 가한 데미지가 음수가 되는 것을 방지
								}if(dmg<0) {
									dmg=0;
								}
								//데미지 수치 적용(HP 변동)
								clearConsole();
								if(player.spd<enemy.spd) {
									player.hp-=dmgTook;
									if(EACS) {
										System.out.println(enemy.name+"은(는) "+Skill.Enemyskills.get(ESLN).skillname+"을(를) 시전했다!");}
									System.out.println(enemy.name+"의 공격으로 "+Math.round(dmgTook*10)/10.0+"의 피해를 입었다.");
									printSeperator(20);
									anythingToContinue();
									enemyname=enemy.name;
									enemyhp=enemy.hp;
									enemyxp=enemy.xp;
									CheckHp();
									if(battleLooprun==0) {
										break battleLoop;
									}
									enemy.hp-=dmg;
									System.out.println(player.name+"은(는) "+Skill.skills.get(inputL).skillname+"을(를) 시전했다!");
									System.out.println("당신의 공격으로 "+enemy.name+"에게 "+Math.round(dmg*10)/10.0+"의 피해를 입혔다.");
									printSeperator(20);
									anythingToContinue();
									enemyname=enemy.name;
									enemyhp=enemy.hp;
									enemyxp=enemy.xp;
									CheckHp();
									if(battleLooprun==0) {
										break battleLoop;
									}
								}else if(player.spd>=enemy.spd) {
									enemy.hp-=dmg;
									System.out.println(player.name+"은(는) "+Skill.skills.get(inputL).skillname+"을(를) 시전했다!");
									System.out.println("당신의 공격으로 "+enemy.name+"에게 "+Math.round(dmg*10)/10.0+"의 피해를 입혔습니다.");
									printSeperator(20);
									anythingToContinue();
									enemyname=enemy.name;
									enemyhp=enemy.hp;
									enemyxp=enemy.xp;
									CheckHp();
									if(battleLooprun==0) {
										break battleLoop;
									}
									player.hp-=dmgTook;
									if(EACS) {
										System.out.println(enemy.name+"은(는) "+Skill.Enemyskills.get(ESLN).skillname+"을(를) 시전했다!");}
									System.out.println(enemy.name+"의 공격으로 "+Math.round(dmgTook*10)/10.0+"의 피해를 입었습니다.");
									printSeperator(20);
									anythingToContinue();
									enemyname=enemy.name;
									enemyhp=enemy.hp;
									enemyxp=enemy.xp;
									CheckHp();
									if(battleLooprun==0) {
										break battleLoop;
									}
								}
								
								battleSet0=true;
								battleSet=true;
							//마나 부족
							}else if(mpcost>player.mp) {
								System.out.println("해당 스킬 시전에 필요한 마나가 부족합니다.");
								anythingToContinue();
								battleSet=true;
							}						
						}				
					}
					if(input1==3){
						battleSet0=true;
						battleSet=true;
					}
				}while(!battleSet);
				
			}else if(input==2){
				//소모품 사용
				boolean itemdisp=false;
				do {
				//고장방지
				Consumable.css=Consumable.consumables.size();
				if(Consumable.css>0) {
					//소모품 사용이 가능한 경우
						Consumable.DisplayConsum();
						input=readInt(">>>",(Consumable.css+1));
						if(Consumable.consumables.get(input-1).itemNum>0 && (Consumable.consumables.get(input-1).itemtype=="포션"
						||Consumable.consumables.get(input-1).itemtype=="도주")) {
							boolean useorcan=false;
							do {
								printHeading(Consumable.consumables.get(input-1).itemInfo
								+"\n남은 수: "+Consumable.consumables.get(input-1).itemNum);
								input=readInt("아이템을 사용하시겠습니까?\n(1)사용한다\t(2)돌아가기",2);
								if(input==1) {
									//포션 사용
									if(Consumable.consumables.get(input-1).itemtype=="포션") {
										player.hp+=Consumable.consumables.get(input-1).fhp;
										player.hp+=Consumable.consumables.get(input-1).fmp;
										//최대수치를 초과하면 수정처리.
										if(player.hp>player.maxhp) {player.hp=player.maxhp;}if(player.mp>player.maxmp) {player.mp=player.maxmp;}
										clearConsole();
										if(Consumable.consumables.get(input-1).fhp>0) {
											printHeading(Consumable.consumables.get(input-1).itemName+"을(를) 마셨습니다."
											+ "\n체력이 "+Consumable.consumables.get(input-1).fhp+"만큼 회복되었습니다.");
										}else if(Consumable.consumables.get(input-1).fmp>0) {
											printHeading(Consumable.consumables.get(input-1).itemName+"을(를) 마셨습니다."
											+ "\n마나가 "+Consumable.consumables.get(input-1).fmp+"만큼 회복되었습니다.");
										}useorcan=true;
									//도주용 아이템 사용
									}else if(Consumable.consumables.get(input-1).itemtype=="도주") {
										printHeading(Consumable.consumables.get(input-1).itemName+"을(를) 사용했다!");
										//if(act==스토리상 넘기지 못하는 전투일 경우){sysout(도망칠 때가 아니야. 여기서 결착을 지어야 한다.);}
										System.out.println("무사히 도망쳤다.");
										//아이템 소지 수 변동처리
										if(Consumable.consumables.get(input-1).itemNum==1) {
											Consumable.consumables.remove(input-1);
										}else {
											Consumable.consumables.get(input-1).itemNum-=1;
										}anythingToContinue();
										break battleLoop;
									}
									//아이템 소지 수 변동처리
									if(Consumable.consumables.get(input-1).itemNum==1) {
										Consumable.consumables.remove(input-1);
									}else {
										Consumable.consumables.get(input-1).itemNum-=1;
									}anythingToContinue();
								//아이템 안 씀.
								}else {
									useorcan=true;
								}
							}while(!useorcan);
						}else if(input==(Consumable.css+1)) {
							itemdisp=true;
						}
						//충분한 스피드가 아니라면 적에게 피격당함. 최소 25 이상의 스피드 차이 필요. 그러나 적의 스피드가 80 이상이면 그냥 맞음.
						if(player.spd<=enemy.spd+25 || enemy.spd>=80) {
							if(EACS) {
								if(Skill.Enemyskills.get(ESLN).type=="물리") {
									if(player.pdef>=0) {
										dmgTook = Edmg*player.Pdefp();
									}else if(player.pdef<0) {
										dmgTook = Edmg*player.Pdefn();
									}
								}else if(Skill.Enemyskills.get(ESLN).type=="마법"){
									if(player.mdef>=0) {
										dmgTook = Edmg*player.Mdefp();
									}else if(player.mdef<0) {
										dmgTook = Edmg*player.Mdefn();
									}
								}
							}else {
								if(player.pdef>=0) {
									dmgTook = Edmg*player.Pdefp();
								}else if(player.pdef<0) {
									dmgTook = Edmg*player.Pdefn();
								}
							}
							//데미지 수치 적용(HP 변동)
							player.hp-=dmgTook;
							if(EACS) {
								System.out.println(enemy.name+"은(는) "+Skill.Enemyskills.get(ESLN).skillname+"을(를) 시전했다!");}
							System.out.println(enemy.name+"의 공격으로 "+Math.round(dmgTook*10)/10.0+"의 피해를 입었습니다.");
							anythingToContinue();
							CheckHp();
							itemdisp=true;
						}else {
							itemdisp=true;
							anythingToContinue();
						}
				}else {
					//소모품이 없는 경우
					System.out.println("소지한 아이템이 없습니다.");
					itemdisp=true;
					anythingToContinue();
				}
				}while(!itemdisp);
			}else{
				//도망치기
				clearConsole();
				//플레이어가 마지막 act(최종 전투)에 있는지 체크하기, 상대의 스피드 이상인지 확인하기
				//[추가 바람]이건 나중에 상황(스토리 진행에 필수라거나)에 따라 도주하지 못하게 하는 코드로 재작성하자.
				if(player.spd>=enemy.spd) {	
				// 적과의 스피드 스텟 차이에 비례하는 확률로 도주 성공
					if((Math.random()*1)>=0.1+((player.spd-enemy.spd)/50)) {	//난수 [0 ~ 0.99] > 기본 도주 보정 상수+(플레이어와 적 스피드 차이)/50
						//스피드 차이가 45 이상일 때 100% 도주 성공
						printHeading(enemy.name+"(으)로부터 무사히 도망쳤습니다!");
						anythingToContinue();
						break battleLoop;
					}else {
						printHeading(enemy.name+"은(는) 끈질기게 당신을 추격합니다!");
							if(EACS) {
							if(Skill.Enemyskills.get(ESLN).type=="물리") {
								if(player.pdef>=0) {
									dmgTook = Edmg*player.Pdefp();
								}else if(player.pdef<0) {
									dmgTook = Edmg*player.Pdefn();
								}
							}else if(Skill.Enemyskills.get(ESLN).type=="마법"){
								if(player.mdef>=0) {
									dmgTook = Edmg*player.Mdefp();
								}else if(player.mdef<0) {
									dmgTook = Edmg*player.Mdefn();
								}
							}
						}else {
							if(player.pdef>=0) {
								dmgTook = Edmg*player.Pdefp();
							}else if(player.pdef<0) {
								dmgTook = Edmg*player.Pdefn();
							}
						}
						//데미지 수치 적용(HP 변동)
						player.hp-=dmgTook;
						if(EACS) {
							System.out.println(enemy.name+"은(는) "+Skill.Enemyskills.get(ESLN).skillname+"을(를) 시전했다!");}
						System.out.println(enemy.name+"의 공격으로 "+Math.round(dmgTook*10)/10.0+"의 피해를 입었습니다.");
						anythingToContinue();
						CheckHp();
					}
				}else {
					printHeading(enemy.name+"은(는) 끈질기게 당신을 추격합니다!");
					if(EACS) {
					if(Skill.Enemyskills.get(ESLN).type=="물리") {
						if(player.pdef>=0) {
							dmgTook = Edmg*player.Pdefp();
						}else if(player.pdef<0) {
							dmgTook = Edmg*player.Pdefn();
						}
					}else if(Skill.Enemyskills.get(ESLN).type=="마법"){
						if(player.mdef>=0) {
							dmgTook = Edmg*player.Mdefp();
						}else if(player.mdef<0) {
							dmgTook = Edmg*player.Mdefn();
						}
					}
				}else {
					if(player.pdef>=0) {
						dmgTook = Edmg*player.Pdefp();
					}else if(player.pdef<0) {
						dmgTook = Edmg*player.Pdefn();
					}
				}
				//데미지 수치 적용(HP 변동)
				player.hp-=dmgTook;
				if(EACS) {
					System.out.println(enemy.name+"은(는) "+Skill.Enemyskills.get(ESLN).skillname+"을(를) 시전했다!");}
				System.out.println(enemy.name+"의 공격으로 "+Math.round(dmgTook*10)/10.0+"의 피해를 입었습니다.");
				anythingToContinue();
				CheckHp();
				}
			}
			}while(!battleSet0);
		}
	}
	//얻은 경험치량
	static double getxp;
	//전투 중 생존 확인 메소드
	public static void CheckHp() {
		if(player.hp<=0) {
			playerDied();
			//battleLooprun이 0이 되면 위의 전투 메소드에서 break battleLoop;가 실행됨.
			isRunning=false;
			battleLooprun=0;
		}else if(enemyhp<=0) {
			//플레이어 승리
			clearConsole();
			printHeading(enemyname+"을(를) 쓰러뜨렸습니다!");
			//경험치 획득
			//여신의 축복 유무에 따른 획득량 차등 적용
			if(blessed==1) {
				getxp=enemyxp*1.33;
			}else if(blessed==0) {
				getxp=enemyxp;
			}
			player.xp+=getxp;
			System.out.println(Math.round(getxp)*10/10.0+"의 경험치를 얻었습니다.");
			Lvcheck();
			//랜덤 드롭
			boolean addRest=(Math.random()*5 + 1 <= 2.25);
			//여신의 축복은 골드 획득량은 늘려주지 않는다. getxp가 이닌 enemyxp로 적용.
			double goldEarned=Math.round((Math.random()*enemyxp)*10)/10.0;
			if(addRest) {
				player.restsLeft++;
				System.out.println("휴식 포인트를 얻었습니다.");
			}
			if(goldEarned>0) {
				player.gold+=goldEarned;
				System.out.println(enemyname+"에게서 "+goldEarned+"의 골드를 얻었습니다.");
			}
			anythingToContinue();
			battleLooprun=0;
		}
	}
	
	// 게임의 최종 전투
	public static void finalBattle() {
		//마왕을 생성하고 전투에 돌입하기
		//battle(new Enemy("마왕",300));
		//적절한 엔딩 출력
		Story.Mainscenario_E(player);
		isRunning=false;
	}
	
	// 플레이어 사망 시 호출할 메소드
	public static void playerDied() {
		clearConsole();
		printHeading("당신은 끝내 쓰러졌습니다. 알 수 없는 공간 저편에서 밝은 빛이 보입니다...");
		printHeading("당신은 이번 여정에서의 누적 경험치는 "+player.xp+"입니다.");
		isRunning=false;
	}
	
	// continueJourney에서 맵 파인더에서 맵 이동을 하지 않고 취소했을 경우 몬스터를 조우하지 않음. 기본값은 'y'
	static char bat='y';
	// 게임 진행(스토리 상의 여정)을 위한 메소드
	public static void continueJourney() {
		//여기서 쇼핑할 수 있는 맵이 아니라면 자동으로 맵 파인더 메소드 실행함
		Place.Shopping();
		//if(몬스터가 나오는 맵에 진입할 경우~~){}
		if(bat=='y') {
			setBattle();
		}
		//	setBattle();//전투 실험용으로 임시 배치해둠.
		//}
		/*//act가 증가해야 할 때 체크하기
		checkAct();
		//게임이 마지막 act에 있지 않을 때 체크하기
		if(act !=4) {
			randomEncounter();	
		}*/	
	}
	
	/*
	// 순간이동 스킬이 존재하면 추가로 특정 맵에 바로 이동할 수 있도록 하는 선택지도 추가
	// 순간이동 스킬의 마나 소모량은 (두 맵의 식별번호 차이값*마나소모공식)으로 정하면 될듯.*/
	
	// 메인 메뉴 출력. 이것도 선택지 출력 중 하나가 될지도 모른다(게임의 구성이 치밀해지고 복잡해짐에 따라).
	public static void printMenu() {
		clearConsole();
		printHeading(Place.places[PNL].placeName);
		if(Place.places[GameLogic.PNL].placeNum==1.11 || Place.places[GameLogic.PNL].placeNum==1.12) {
			Place.Shopping();
		}
			//그 외 상호작용을 할 만한 것이 없는 경우, 기본(default) 출력 내용
			System.out.println("무엇을 할까?");
			printSeperator(20);
			System.out.println("(1)이동");
			System.out.println("(2)스테이터스");
			System.out.println("(3)인벤토리");
			System.out.println("(4)퀘스트 정보");
			//System.out.println("()게임 저장"); //나중에 추가
			System.out.println("(5)게임 종료");
		
		/*메소드 [printMenu의 대대적 개편 계획]
		 * 현재 있는 장소(맵, placeNum)에 따라 기존의 '계속하기, 스테이터스, 게임 종료'외에 
		 * 추가적인 선택지 출력. 예를 들어 어떤 도시에 있을 경우 '이동(기존의 [계속하기]를 대체), 
		 * (NPC 이름)에밀리와 대화, 브라운과 대화, 인벤토리, 스테이터스, 게임 종료'를 출력. 
		 * 그리고 해당 맵에 있을 때 플레이어가 특정 조건을 만족하고 있는 경우 새로운 선택지 출력. 
		 * 그 경우 예시를 들면 '이동, (npc와 대화), 인벤토리, 스테이터스, 웨이포인트 해제(리플레이스 스킬), 게임 종료'를 출력함.
		 */
		
	}
	
	// 메인 게임 루프. 이 게임 코드의 중추.
	public static void gameLoop() {
		while(isRunning) {
			printMenu();
			int input=readInt(">>>",5);
			if(input==1) {
				continueJourney();	//여기서 분기를 만들어야 할듯. 특정 장소로 이동한다든지. place도 더 만들어야 함.
			}else if(input==2){
				characterInfo();
			}else if(input==3){
				inventory();
			}else if(input==4){
				Quest.DisplayQuest();
			}else if(input==5) {
				isRunning=false;
			}/*else if(input==N){
				//게임 저장
			}*/
		}
	}
}

