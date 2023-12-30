package MainPackage;

import java.util.ArrayList;

public class Consumable extends Item {
	//장비와 달리 소모품은 xp,lv,현재 체력과 마나를 변화시킨다는 설정 채택.
	//xp와 lv을 변화시키는 소모품??: 인챈트 병(마인크래프트), 이상한사탕(포켓몬스터 시리즈) 등이 있음.
	double fxp,flv,	fhp,fmp;
	//소모품 생성자
	public Consumable(String itemName, String itemtype, double fxp,double flv,double fhp,double fmaxhp,double fmp,double fmaxmp
			,double fpdef,double fmdef,double fatk,double fintel,double fspd,double pprice,double sprice,int num,int itemNum,String itemInfo) {
		//아이템명,유형,최대체력,최대마나,	물리방어력,마법저항력,공격력,주문력,스피드, 	구매가,판매가,보유수,아이템넘버,정보
		super("","",0,0,	0,0,0,0,0,	0,0,0,0,"");
		this.itemName=itemName;	this.itemtype=itemtype;	this.fxp=fxp;	this.flv=flv;
		this.fhp=fhp;	this.fmp=fmaxhp;	this.fmp=fmp;	this.fmaxmp=fmaxmp;
		this.fpdef=fpdef;	this.fmdef=fmdef;	this.fatk=fatk;	this.fintel=fintel;
		this.fspd=fspd;	this.pprice=pprice;	this.sprice=sprice; this.itemNum=itemNum; this.itemInfo=itemInfo;
	}
	
	/*	소모품의 유형 필드(인스턴스)는 "회복"이 반드시 포함되어야 합니다. 그 이유는 GameLogic에서 전투를 진행할 때, 소지한 아이템 사용을 누르면 
	 * 	포션과 그 외 소모품을 따로 출력하도록 하는 알고리즘을 만들 것이기 때문입니다. 유형이 "회복"인 객체들만 배열에서 불러와서 출력, 그 외 소모품은 
	 * 	유형이 "회복"이 아닌 객체들만 불러와서 출력하도록 합니다. 이것은 사실 포켓몬스터의 전투 시스템과 같습니다.
	 * 
	 *	따로 출력하는건 나중 업데이트로 추가...
	*/
	
	//플레이어가 소지한 소포품을 리스트로 구현
	static ArrayList<Consumable> consumables=new ArrayList<Consumable>();
	//플레이어가 보유한 소모품 수
	static int css=consumables.size();
	// 이 클래스에서만 사용할 변수. readInt용으로 써먹는다든가.
	private static int check;
	private static int possessed;
	private static int purc;
	private static boolean nopos;
	// 상점의 판매중인 소모품 리스트
	static ArrayList<Consumable> shopconsums=new ArrayList<Consumable>();
	// 판매중인 소포품 리스트의 크기
	static int scss=shopconsums.size();
	
	//아이템 습득 메소드. //구매할 때랑 습득할 때랑 다르게 출력되도록 새 메소드를 만들든지 하자.
	public static void PurchaseItem(String itemName) {
		switch(Place.places[GameLogic.PNL].placeName) {
		case("르망 마을 잡화점"):
			for(int i=0;i<Place.Reumang.length;i++) {
				for(int k=0;k<consumableList.length;k++) {
					if(Place.Reumang[i]==consumableList[k].itemName) {
						shopconsums.add(new Consumable(consumableList[k].itemName,consumableList[k].itemtype,consumableList[k].fxp,consumableList[k].flv
						,consumableList[k].fhp,consumableList[k].fmaxhp,consumableList[k].fmp,consumableList[k].fmaxmp,consumableList[k].fpdef,consumableList[k].fmdef
						,consumableList[k].fatk,consumableList[k].fintel,consumableList[k].fspd,consumableList[k].pprice,consumableList[k].sprice
						,consumableList[k].num,consumableList[k].itemNum,consumableList[k].itemInfo));
						break;
					}
				}
			}
			scss=shopconsums.size();
			break;
		}
		//이거 안해주면 고장남
		css=consumables.size();
		nopos=true;
		//이미 습득했는지 체크
		for(int i=0;i<css;i++) {
			if(itemName==consumables.get(i).itemName) {
				possessed=i;
				nopos=false;
				break;
			}
		}
		if(nopos){
			System.out.println(itemName+"(보유하고 있지 않음)");
		}else {
			System.out.println(itemName+"("+consumables.get(possessed).itemNum+" 개 보유)");}
		check=GameLogic.readInt("구매하시겠습니까?\n(1)구매한다\n(2)구매하지 않는다", 2);
		//아이템 획득
		//해당 이름의 아이템을 배열에서 검색해서 찾은 후 추가
		if(check==1) {
			for(int i=0;i<scss;i++) {
				if(itemName==shopconsums.get(i).itemName) {
					purc=i;
					break;
				}
			}
			if(GameLogic.player.gold>=shopconsums.get(purc).pprice) {
				GameLogic.player.gold-=shopconsums.get(purc).pprice;
				if(nopos) {
					for(int k=0;k<consumableList.length;k++) {
						if(consumableList[k].itemName.equals(itemName)) {
							consumables.add(new Consumable(consumableList[k].itemName,consumableList[k].itemtype,consumableList[k].fxp,consumableList[k].flv
							,consumableList[k].fhp,consumableList[k].fmaxhp,consumableList[k].fmp,consumableList[k].fmaxmp,consumableList[k].fpdef,consumableList[k].fmdef
							,consumableList[k].fatk,consumableList[k].fintel,consumableList[k].fspd,consumableList[k].pprice,consumableList[k].sprice
							,consumableList[k].num,1,consumableList[k].itemInfo));
							//고장 방지
							css=consumables.size();
							c_ItemRearrangement();
							System.out.println(itemName+"을(를) "+consumableList[k].pprice+"골드에 구매했습니다.");
							GameLogic.anythingToContinue();
							break;
						}
					}	
				}else {
					consumables.get(possessed).itemNum+=1;
					System.out.println(itemName+"을(를) "+consumables.get(possessed).pprice+"골드에 구매했습니다.");
					GameLogic.anythingToContinue();
				}
			}else {
			GameLogic.printHeading("해당 물품을 구매하기 위한 골드가 부족합니다.");
			GameLogic.anythingToContinue();
			}
		}
	}
	
	//아이템 얻음
	public static void acquireItem(String itemName) {
		//이거 안해주면 고장남
		css=consumables.size();
		nopos=true;
		//이미 습득했는지 체크. 있으면 개수만 늘림.
		for(int i=0;i<css;i++) {
			if(itemName==consumables.get(i).itemName) {
				nopos=false;
				consumables.get(i).itemNum+=1;
				break;
			}
		}
		if(nopos){
			//아이템 획득
			//해당 이름의 아이템을 배열에서 검색해서 찾은 후 추가
			for(int k=0;k<consumableList.length;k++) {
				if(consumableList[k].itemName.equals(itemName)) {
					consumables.add(new Consumable(consumableList[k].itemName,consumableList[k].itemtype,consumableList[k].fxp,consumableList[k].flv
					,consumableList[k].fhp,consumableList[k].fmaxhp,consumableList[k].fmp,consumableList[k].fmaxmp,consumableList[k].fpdef,consumableList[k].fmdef
					,consumableList[k].fatk,consumableList[k].fintel,consumableList[k].fspd,consumableList[k].pprice,consumableList[k].sprice
					,consumableList[k].num,1,consumableList[k].itemInfo));
					//고장 방지
					css=consumables.size();
					c_ItemRearrangement();
					break;
				}
			}	
		}
		System.out.println(itemName+"을(를) 얻었습니다.");
		GameLogic.anythingToContinue();
	}
	
	//소모품 출력
	public static void DisplayConsum() {
		//이거 안 치면 고장남
		css=consumables.size();
		GameLogic.printHeading("아이템 선택(돌아가려면 "+(css+1)+"입력)");
		for(int i=1;i<css+1;i++) {
			if(i%5==0) {
				System.out.println("("+i+")"+consumables.get(i-1).itemName+" (보유수: "+consumables.get(i-1).itemNum+")");
			}else if((i)==css){
				System.out.println("("+i+")"+consumables.get(i-1).itemName+" (보유수: "+consumables.get(i-1).itemNum+")\n");
			}else {
				System.out.print("("+i+")"+consumables.get(i-1).itemName+" (보유수: "+consumables.get(i-1).itemNum+")\t");
			}
		}
	}
	
	//아이템을 획득했을 시 아이템 넘버로 재정렬하는 메소드
	public static void c_ItemRearrangement() {
		Consumable tmp= new Consumable("","",0,0, 0,0,0,0, 0,0,0,0,0, 0,0,0,0,"");	//아이템 객체를 저장할 임시 필드
		for(int i=css;i>0;i--) {
			for(int j=0;j<(i-1);j++) {			//연속되는 두 리스트 원소의 item넘버를 비교해서 재정렬
				if(consumables.get(j).itemNum>consumables.get(j+1).itemNum) {	//앞의 원소가 뒤의 원소보다 item넘버가 클 때 뒤로 넘겨주기 n02 n01
					tmp=consumables.get(j+1);	//뒤의 원소를 임시 필드에 저장	tmp=n01
					consumables.add(j,tmp);		//임시 필드의 원소를 앞에 추가 n01 n02 n01
					consumables.remove(j+2);		//뒤로 밀려있던 원래 [앞의 원소]를 삭제	n01 n02
				}else {
					continue;}
			}
		}
	}
	//소모품 배열. 인덱스 역할.
	static Consumable[]consumableList=new Consumable[11];
	public static void Make_Consumable_List() {
		//아이템명,유형,xp,lv,	체력,최대체력,마나,최대마나,	물리방어력,마법저항력,공격력,주문력,스피드, 	구매가,판매가,보유수,아이템 넘버,정보
		consumableList[0]=new Consumable("개발자용 소모품","디버깅 데이터",0,0, 10000,0,0,0, 0,0,0,0,0, 0,0,0,0,"개발자는 체력이 중요합니다.");
		
		consumableList[1]=new Consumable("소형 체력 포션","포션",0,0, 50,0,0,0, 0,0,0,0,0, 20,12,0,1,"맑고 투명한 붉은색의 액체. 마시면 체력이 50만큼 회복된다.");
		consumableList[2]=new Consumable("체력 포션","포션",0,0, 100,0,0,0, 0,0,0,0,0, 50,30,0,2,"맑고 투명한 붉은색의 액체. 마시면 체력이 100만큼 회복된다.");
		consumableList[3]=new Consumable("대형 체력 포션","포션",0,0, 250,0,0,0, 0,0,0,0,0, 150,90,0,3,"맑고 투명한 붉은색의 액체. 마시면 체력이 250만큼 회복된다.");
		consumableList[4]=new Consumable("소형 마나 포션","포션",0,0, 0,0,40,0, 0,0,0,0,0, 20,12,0,4,"맑고 투명한 파란색의 액체. 마시면 마나가 40만큼 회복된다.");
		consumableList[5]=new Consumable("마나 포션","포션",0,0, 0,0,80,0, 0,0,0,0,0, 50,30,0,5,"맑고 투명한 파란색의 액체. 마시면 마나가 80만큼 회복된다.");
		
		consumableList[6]=new Consumable("대형 마나 포션","포션",0,0, 0,0,200,0, 0,0,0,0,0, 150,90,0,6,"맑고 투명한 파란색의 액체. 마시면 마나가 200만큼 회복된다.");
		consumableList[7]=new Consumable("연막탄","도주",0,0, 0,0,0,0, 0,0,0,0,0, 0,0,0,7,"터지면서 다량의 연기를 내뿜는 도구. 대부분의 상황에서 도망칠 수 있다.");
		consumableList[8]=new Consumable("트리얼 고대사","능력치 변화",0,0, 0,0,0,0, 0,0,0,1,0, 0,0,0,8,"트리얼 왕국의 역사서. 고대의 역사를 다루고 있다.");
		consumableList[9]=new Consumable("쿠룰렌타 구이","능력치 변화",0,0, 100,10,20,5, 0,0,0,0,1, 3500,3000,0,9,"극지방의 빙상 근처에서만 서식하는 쿠룰렌타 요리. 굉장히 귀한 음식으로, 임금님도 먹는 보양식이라고 한다.");
		consumableList[10]=new Consumable("이상한사탕","능력치 변화",0,1, 0,0,0,0, 0,0,0,0,0, 70000,50000,0,10,"먹으면 왠지 강해질 것 같은 사탕이다. 아무튼 그렇다.");
		/*
		consumableList[11]=new Consumable("","",0,0, 0,0,0,0, 0,0,0,0,0, 0,0,0,11,"");
		consumableList[12]=new Consumable("","",0,0, 0,0,0,0, 0,0,0,0,0, 0,0,0,12,"");
		consumableList[13]=new Consumable("","",0,0, 0,0,0,0, 0,0,0,0,0, 0,0,0,13,"");
		consumableList[14]=new Consumable("","",0,0, 0,0,0,0, 0,0,0,0,0, 0,0,0,14,"");
		consumableList[15]=new Consumable("","",0,0, 0,0,0,0, 0,0,0,0,0, 0,0,0,15,"");
		
		consumableList[16]=new Consumable("","",0,0, 0,0,0,0, 0,0,0,0,0, 0,0,0,16,"");
		consumableList[17]=new Consumable("","",0,0, 0,0,0,0, 0,0,0,0,0, 0,0,0,17,"");
		consumableList[18]=new Consumable("","",0,0, 0,0,0,0, 0,0,0,0,0, 0,0,0,18,"");
		consumableList[19]=new Consumable("","",0,0, 0,0,0,0, 0,0,0,0,0, 0,0,0,19,"");
		consumableList[20]=new Consumable("","",0,0, 0,0,0,0, 0,0,0,0,0, 0,0,0,20,"");
		*/
	}

}
