package MainPackage;

import java.util.ArrayList;

public class Place {
	
	double placeNum;
	String placeName;
	String placeInfo;	//이건 장소에 대한 설명을 출력할 용도인데, 일단 지금은 틀만 만들어두고 추후 업데이트로 추가하자.
	//String placeType	//장소 타입. 상점, 던전, 마을/도시 등등. 상점 만들다가 로직이 어지러워질 것 같아서 추가 바람.
	
	double tmpNum;	/*	아래의 mapFinder 메소드의 맵 인덱스 시스템의 핵심 요소 중 하나로, 배열에서 추출된 맵에 임시 번호를 부여하기 위한 필드...였지만,
					*	현재는 아무런 역할을 수행하지 않고 있다. 삭제하지 않는 이유는 게임 상의 이벤트 관련해서 써먹을 수 있다고 생각하기 때문.
					*	퀘스트의 유무를 확인하는 변수로 활용한다든지? 아니면 퀘스트를 포함하는 추가적인 행동의 가능 여부를 나타낼 수도 있고.
					*/
	
	public Place(String placeName,String placeInfo,final double placeNum,double tmpNum) {
		this.placeName=placeName;
		this.placeInfo=placeInfo;
		this.placeNum=placeNum;
		this.tmpNum=tmpNum;
	}
	
	//임시 리스트를 따로 생성. 아래의 mapFinder 메소드에서 모든 배열 검색으로 인해 발생하는 오류를 방지하는 기능.
	static ArrayList<Place> tmpplaces=new ArrayList<Place>();
	
	/*맵 번호를 실수형으로 선언한 이유:
	 *	예를 들어 장소가 르망 마을이라고 했을 때, 르망 마을 내의 여러 장소가 있을 것임. 마을 내에 있는 장소가 어딘지를 식별하는 
	 *	방법 중 하나로, 하위 맵을 식별 번호.xxx~로 설정해두고 그 값이 
	 *
	 *	(상위 맵의 식별번호< (해당 하위 맵 식별번호) <(상위 맵의 식별번호+1)
	 *	
	 *	일 경우 하위 맵으로 인식하게 만드는 시스템임.
	 *
	 *	그래서 르망 마을의 큰 번호를 1로 두고, 길드 건물은 1.1, 마을회관은 1.2, 마을회관 내 장로 사무실은 1.21로 설정.
	*/
	
	//GameLogic 클래스로 현재 맵 번호를 전달하는 변수
	static int PPN;	//Present Place Number
	//mapFinder 메소드에서 프로그램의 실수(real number)처리로 인한 치명적 버그를 방지하는 데 사용하는 변수
	static double killbug;
	static int tps=tmpplaces.size();
	static boolean selectP;
	//readInt로 입력받은 값을 mapFinder와 DisplayMaps간 상호작용에 사용하기 위한 변수
	static int D2F;
	static int mapdsp;
	
	
	//현재 있는 맵에서 이동 가능한 맵을 찾고, 이동(맵 번호를 변경. PPN 이용)하도록 하는 메소드.
	//각각의 맵에서만 볼 수 있는 이벤트와 상호작용은 따로 작성하자.
	public static void mapFinder(){
		MapLoop: while(true) {
			boolean selectP=false;
			int premap=0;	//이전 맵의 배열번호를 임시로 저장할 필드
			//임시 리스트 초기화. 필수.
			tmpplaces.clear();
			do {
				//치명적 버그 방지. 아래 중분류 식별 전 필요.
				killbug=places[GameLogic.PNL].placeNum % 0.1;
				if(killbug>0.09 || killbug<0.01) {
					killbug=0;
				}
		//현재 위치의 맵 식별번호 분류(대분류, 중분류)를 식별
			//현재 맵의 식별번호가 대분류일 경우
			if(places[GameLogic.PNL].placeNum % 1 ==0) {
				//해당 대분류의 하위 중분류 맵들과 현재 대분류 맵에서 이동 가능한 맵들을 리스트에 추가. 그 후, tmpplaces 리스트에 추가
				for(int i=0;i<places.length;i++) {
					if(places[GameLogic.PNL].placeNum < places[i].placeNum && places[i].placeNum < (places[GameLogic.PNL].placeNum+1) 
						&& places[i].placeNum*10%1==0) {
						tmpplaces.add(new Place(places[i].placeName,places[i].placeInfo,places[i].placeNum,places[i].tmpNum));
					}
				}
				//다른 쪽 대분류 맵
				if(places[GameLogic.PNL].placeNum==2) {
					tmpplaces.add(new Place(places[6].placeName,places[6].placeInfo,places[6].placeNum,places[6].tmpNum));
				}
			}
			//현재 맵의 식별번호가 중분류일 경우. 해당 중분류의 하위 소분류 맵들을 추출 후, 현재 중분류 맵의 상위 대분류 맵을 리스트의 마지막에 추가
			else if(killbug==0) {
				for(int i=0;i<places.length;i++) {
					//더하기도 제대로 안 된다. 1.1+0.1이 1.2000000000001인가 나와서 버그걸림. floor함수로 해결.
					if(places[GameLogic.PNL].placeNum < places[i].placeNum && Math.floor((places[GameLogic.PNL].placeNum+0.1)*10)/10.0 > places[i].placeNum) {
						tmpplaces.add(new Place(places[i].placeName,places[i].placeInfo,places[i].placeNum,places[i].tmpNum));
					}
				}
				tmpplaces.add(new Place(places[premap].placeName,places[premap].placeInfo,places[premap].placeNum
				,places[premap].tmpNum));
				if(places[GameLogic.PNL].placeNum==1.3) {
					tmpplaces.add(new Place(places[11].placeName,places[11].placeInfo,places[11].placeNum,places[11].tmpNum));
				}
			}//else {System.out.println("맵 파인더 오류 발생. 디버깅 필요.");}
			/*//현재 맵의 식별번호가 소분류일 경우(현재 존재하지 않음)
			else if(killbug==0) {
				for(int i=0;i<places.length;i++) {
					if(places[GameLogic.PNL].placeNum < places[i].placeNum && places[i].placeNum < places[GameLogic.PNL].placeNum+0.01) {
						tmpplaces.add(new Place(places[i].placeName,places[i].placeInfo,places[i].placeNum,places[i].tmpNum));
					}
				}
			}*/
			//출력
			DisplayMaps();
			//소분류(이전의 맵으로 돌아가는 것 이외에는 없는 맵)가 아닌 경우
			//취소
			if(tps!=0) {
				if(mapdsp==(tps+1)) {
					//몬스터와 조우하지 않도록 함
					GameLogic.bat='n';
					selectP=true;
					break MapLoop;
				}else {
				//맵 이동
				//이전 대분류 맵의 배열번호를 저장. 몬스터와 조우 가능하게 함.
					premap=GameLogic.PNL;
				//PNL값을, 임시 리스트의 선택된 맵 식별번호와 일치하는 배열상 맵의 배열번호로 초기화. 쉽게 말해 맵 이동.
					for(int i=0;i<places.length;i++) {
						if(places[i].placeNum==tmpplaces.get(D2F).placeNum) {
							GameLogic.PNL=i;
							break;
						}else {continue;}
					}
					if(places[GameLogic.PNL].placeNum==places[14].placeNum) {
						Quest.qus=Quest.quests.size();
						for(int i=0;i<Quest.qus;i++) {
							if(Quest.quests.get(i).questname=="르망 잡화점 주인의 부탁") {
								Quest.quests.get(i).onoff="완료 가능";
								Quest.quests.get(i).onoff="완료 가능";
								break;
							}
						}
					}
					if(GameLogic.PNL==11 || GameLogic.PNL==12 || GameLogic.PNL==13 || GameLogic.PNL==14) {
						GameLogic.bat='y';
					}else {
						GameLogic.bat='n';
					}
				}
			}else if(tps==0) {
			//소분류 맵인 경우
				//취소
				if(D2F==2) {
					selectP=true;
					break MapLoop;
				}else if(D2F==1){
				// 상위 맵 식별번호(중분류 맵)를 계산 후 배열상에서 그와 일치하는 식별번호를 가진 맵으로 이동
					for(int i=0;i<places.length;i++) {
						//round는 반올림이라 쓰면 안 됨. 중분류인 소수점 첫째자리만 뽑을거니까 floor 쓰기.
						if(places[i].placeNum==Math.floor(places[GameLogic.PNL].placeNum*10)/10.0) {
							GameLogic.PNL=i;
							break;
						}else {continue;}
					}
					GameLogic.clearConsole();
					GameLogic.printHeading(Place.places[GameLogic.PNL].placeName);
				}
			}
			selectP=true;
			break MapLoop;
			}while(!selectP);
		}
	}
	
	//맵 목록을 출력하는 메소드
	public static void DisplayMaps() {
		//고장 방지
		tps=tmpplaces.size();
		//출력
		//소분류(이전의 맵으로 돌아가는 것 이외에는 없는 맵)가 아닌 경우
		if(tps!=0) {
			System.out.println("어디로 이동할까?\n");
			for(int i=1;i<tps+1;i++) {
				if(i%5==0) {
					System.out.println("("+i+")"+tmpplaces.get(i-1).placeName);
				}else if((i)==tps){
					System.out.println("("+i+")"+tmpplaces.get(i-1).placeName+"\n");
				}else {
					System.out.print("("+i+")"+tmpplaces.get(i-1).placeName+"\t");
				}
			}
			mapdsp=GameLogic.readInt("(취소: "+(tps+1)+"입력)>>>", tps+1);
			D2F=mapdsp-1;
		}else if(tps==0) {
		//소분류 맵인 경우
			//상점
			if(Place.places[GameLogic.PNL].placeNum==1.11 || Place.places[GameLogic.PNL].placeNum==1.12) {
				D2F=1;
			}else {
			mapdsp=GameLogic.readInt("(1)이전 맵으로 돌아가기\n(2)취소",2);
			D2F=mapdsp;
			}
		}
	}
	
	public static String[]Reumang = {"소형 체력 포션","체력 포션","소형 마나 포션","마나 포션","연막탄"};
	public static String[]Reumangsmith = {"가죽 투구","가죽 흉갑","가죽 각반","가죽 부츠","나무 방패","롱소드"};
	public static String[]Reumangwitch = {"보급형 룬 모자","로브","조잡한 마력 부츠","나무 완드","가죽 실프"};
	
	//거래가 가능한 NPC가 있는 맵에 위치할 경우. 여기는 노가다가 좀 필요. 더 좋은 알고리즘 구조는 일단 나중에 생각하자.
	public static void Shopping() {
		//뭔가 퀘스트가 있는 NPC가 존재할 경우. tmpNum에 따라 다르게 출력되도록 분기를 만들...까?
		switch(places[GameLogic.PNL].placeName) {
		case("르망 마을 잡화점"):
			Reumang: while(true) {
			boolean shopset=true;
			do {
				GameLogic.clearConsole();
				GameLogic.printHeading(Place.places[GameLogic.PNL].placeName);
				System.out.println("(1)거래하기");
				// 0==퀘스트 없음 1==퀘스트 아직 진행 중 2==퀘스트 완료 가능
				if(Quest.questList[1].onoff=="시작가능" || Quest.questList[1].onoff=="진행중") {
					System.out.println("(2)나르빅과 대화(퀘스트)");
				}else if(Quest.questList[1].onoff=="완료가능"){
					System.out.println("(2)나르빅과 대화(퀘스트 완료)");
				}else {
					System.out.println("(2)나르빅과 대화");}
				System.out.println("(3)몰리스와 대화");
				System.out.println("(4)나가기");
				int input=GameLogic.readInt(">>>",4);
				if(input==1) {
					GameLogic.clearConsole();
					GameLogic.printHeading(Place.places[GameLogic.PNL].placeName);
					System.out.println("(1)구매");
					System.out.println("(2)판매");
					System.out.println("(3)돌아가기");
					input=GameLogic.readInt(">>>", 3);
					if(input==1) {
						Consumable.shopconsums.clear();
						for(int k=0;k<Consumable.consumableList.length;k++) {
							for(int j=0;j<Reumang.length;j++) {
								if(Reumang[j]==Consumable.consumableList[k].itemName) {
									Consumable.shopconsums.add(new Consumable(Consumable.consumableList[k].itemName,Consumable.consumableList[k].itemtype,Consumable.consumableList[k].fxp,Consumable.consumableList[k].flv
									,Consumable.consumableList[k].fhp,Consumable.consumableList[k].fmaxhp,Consumable.consumableList[k].fmp,Consumable.consumableList[k].fmaxmp,Consumable.consumableList[k].fpdef,Consumable.consumableList[k].fmdef
									,Consumable.consumableList[k].fatk,Consumable.consumableList[k].fintel,Consumable.consumableList[k].fspd,Consumable.consumableList[k].pprice,Consumable.consumableList[k].sprice
									,Consumable.consumableList[k].num,Consumable.consumableList[k].itemNum,Consumable.consumableList[k].itemInfo));
								}
							}
						}
						//고장 방지
						Consumable.scss=Consumable.shopconsums.size();
						//상품 목록 출력
						System.out.println("소지금: "+GameLogic.player.gold);
						GameLogic.printSeperator(30);
						for(int i=1;i<Consumable.scss+1;i++) {
							System.out.println("("+i+")"+Consumable.shopconsums.get(i-1).itemName+"("+Consumable.shopconsums.get(i-1).pprice+"골드)");
						}
						System.out.println("("+(Consumable.scss+1)+")취소");
						GameLogic.printSeperator(30);
						input=GameLogic.readInt(">>>",Consumable.scss+1);
						if(input!=Consumable.scss+1) {
							Consumable.PurchaseItem(Consumable.shopconsums.get(input-1).itemName);
						}else {
							shopset=false;
						}
					}else if(input==2){
						//show player item
						System.out.println("미구현");
						shopset=false;
					}else if(input==3) {
						shopset=false;
					}
				//나르빅의 엔글루시아 약초 퀘스트
				}else if(input==2) {
					if(Quest.questList[1].onoff=="시작가능") {
						Story.subque_1();
						input=GameLogic.readInt(">>>", 2);
						if(input==1) {
							Story.subque_1_1();
							input=GameLogic.readInt(">>>", 2);
							if(input==1) {
								Story.subque_1_2();
								//퀘스트 추가되는 메소드
								shopset=false;
								break Reumang;
							}else if(input==2) {
								Story.subque_1x();
								shopset=false;
								break Reumang;
							}
						}else if(input==2) {
							Story.subque_1x();
							shopset=false;
							break Reumang;
						}
					}else if(Quest.questList[1].onoff=="진행중") {
						System.out.println("\"약초는 캐 온겐가? 부탁하네...\"");
						GameLogic.anythingToContinue();
					}else if(Quest.questList[1].onoff=="완료 가능") {
						Story.subque_1o();
						//스토리 클래스에서 다루도록 하자...
					}else if(Quest.questList[1].onoff=="완료됨") {
						System.out.println("\"자네에게 항상 고마울 따름이라네. 차 한잔 할 텐가?\"");
						GameLogic.anythingToContinue();
					}
				//몰리스와 대화
				}else if(input==3) {
					Story.molis();
				//나가기
				}else if(input==4) {
					break Reumang;
				}
			}while(shopset);
			}
			mapFinder();
			break;
		case("르망 마을 대장간"):
			Reumangsmith: while(true) {
				boolean shopset=true;
				do {
					GameLogic.clearConsole();
					GameLogic.printHeading(Place.places[GameLogic.PNL].placeName);
					System.out.println("(1)거래하기");
					System.out.println("(2)나가기");
					int input=GameLogic.readInt(">>>",2);
					if(input==1) {
						GameLogic.clearConsole();
						GameLogic.printHeading(Place.places[GameLogic.PNL].placeName);
						System.out.println("(1)구매");
						System.out.println("(2)판매");
						System.out.println("(3)돌아가기");
						input=GameLogic.readInt(">>>", 3);
						if(input==1) {
							Equipment.shopequip.clear();
							for(int k=0;k<Equipment.equipmentList.length;k++) {
								for(int j=0;j<Reumangsmith.length;j++) {
									if(Reumangsmith[j]==Equipment.equipmentList[k].itemName) {
										Equipment.shopequip.add(new Equipment(Equipment.equipmentList[k].itemName,Equipment.equipmentList[k].itemtype
										,Equipment.equipmentList[k].fmaxhp,Equipment.equipmentList[k].fmaxmp,Equipment.equipmentList[k].shield,Equipment.equipmentList[k].fpdef,Equipment.equipmentList[k].fmdef
										,Equipment.equipmentList[k].fatk,Equipment.equipmentList[k].fintel,Equipment.equipmentList[k].fspd,Equipment.equipmentList[k].pprice,Equipment.equipmentList[k].sprice
										,Equipment.equipmentList[k].num,Equipment.equipmentList[k].itemNum,Equipment.equipmentList[k].itemInfo));
									}
								}
							}
							//고장 방지
							Equipment.sems=Equipment.shopequip.size();
							//상품 목록 출력
							System.out.println("소지금: "+GameLogic.player.gold);
							GameLogic.printSeperator(30);
							for(int i=1;i<Equipment.sems+1;i++) {
								System.out.println("("+i+")"+Equipment.shopequip.get(i-1).itemName+"("+Equipment.shopequip.get(i-1).pprice+"골드)");
							}
							System.out.println("("+(Equipment.sems+1)+")취소");
							GameLogic.printSeperator(30);
							input=GameLogic.readInt(">>>",Equipment.sems+1);
							if(input!=Equipment.sems+1) {
								Equipment.PurchaseItem(Equipment.shopequip.get(input-1).itemName);
							}else {
								shopset=false;
							}
						}else if(input==2){
							//show player item
							System.out.println("미구현");
							shopset=false;
						}else if(input==3) {
							shopset=false;
						}
					//나가기
					}else if(input==2) {
						break Reumangsmith;
					}
				}while(shopset);
				}
			mapFinder();
			break;
		default:
			mapFinder();
			break;
		}
	}
	
	static Place[] places= new Place[50];
	//장소 배열 생성 메소드
	public static void Make_Place_List() {
		//맵 이름, 맵 정보, 맵 식별번호(인덱스 번호), 임시 배정번호(tmpNum): tmpNum의 초기값은 0으로 설정해두자.
		places[0]= new Place("르망 마을 입구","",1,0);
		places[1]= new Place("르망 마을 잡화점","",1.11,0);
		places[2]= new Place("르망 마을 대장간","",1.12,0);
		places[3]= new Place("르망 마을 길드","",1.13,0);
		places[4]= new Place("르망 마을 중앙 광장","",1.1,0);
		
		places[5]= new Place("르망 마을 동쪽 입구 초소","",1.2,0);
		places[6]= new Place("르망 마을 남쪽 입구 초소","",1.3,0);
		places[7]= new Place("르망 마을 경비대 본부","",1.4,0);
		places[8]= new Place("르망 마을 의원","",1.14,0);
		places[9]= new Place("르망 마을 여관","",1.15,0);
		
		places[10]= new Place("르망 마을 마을회관","",1.5,0);
		places[11]= new Place("줄무늬 숲 외곽","",2,0);
		places[12]= new Place("줄무늬 숲 1","",2.1,0);
		places[13]= new Place("줄무늬 숲 2","",2.2,0);
		places[14]= new Place("깊은 줄무늬 숲","",2.21,0);
		
		places[15]= new Place("301번 도로 1","",3,0);
		places[16]= new Place("301번 도로 2","",4,0);
		places[17]= new Place("301번 도로 3","",5,0);
		places[18]= new Place("301번 도로 4","",6,0);
		places[19]= new Place("툴로라 서문","",7,0);
		
		places[20]= new Place("툴로라 서쪽지구 시가지","",8,0);
		places[21]= new Place("툴로라 강 중앙 대교","",9,0);
		places[22]= new Place("툴로라 동쪽지구 시가지","",10,0);
		places[23]= new Place("툴로라 동문","",11,0);
		places[24]= new Place("툴로라 항구","",12,0);
		
		places[25]= new Place("302번 도로 1","",13,0);
		places[26]= new Place("302번 도로 2","",14,0);
		places[27]= new Place("메르헨 숲 서쪽","",15,0);
		places[28]= new Place("메르헨 숲 1","",15.1,0);
		places[29]= new Place("메르헨 숲 2","",15.2,0);
		
		places[30]= new Place("버려진 저택","",15.3,0);
		places[31]= new Place("버려진 저택 1층","",15.31,0);
		places[32]= new Place("버려진 저택 2층","",15.32,0);
		places[33]= new Place("302번 도로 3","",16,0);
		places[34]= new Place("302번 도로 4","",17,0);
		
		places[35]= new Place("","",0,0);
		places[36]= new Place("","",0,0);
		places[37]= new Place("","",0,0);
		places[38]= new Place("","",0,0);
		places[39]= new Place("","",0,0);
		
		places[40]= new Place("","",0,0);
		places[41]= new Place("","",0,0);
		places[42]= new Place("","",0,0);
		places[43]= new Place("","",0,0);
		places[44]= new Place("","",0,0);
		
		places[45]= new Place("","",0,0);
		places[46]= new Place("","",0,0);
		places[47]= new Place("","",0,0);
		places[48]= new Place("","",0,0);
		places[49]= new Place("","",0,0);
		
		
	//{"수도 퓌르니엘","종말의 천공","아드레스티아","엑세골 산맥","트리얼 동부 대평원"};
	}

}
