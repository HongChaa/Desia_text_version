package MainPackage;

import java.util.ArrayList;

public class Equipment extends Item {
	double shield;
	
	//장비 생성자
	public Equipment(String itemName, String itemtype,double fmaxhp,double fmaxmp,double shield,double fpdef,double fmdef,
			double fatk,double fintel,double fspd,double pprice,double sprice,int num,int itemNum,String itemInfo) {
		//아이템명,유형,최대체력,최대마나,실드,	물리방어력,마법저항력,공격력,주문력,스피드, 	구매가,판매가,보유수,아이템 넘버,정보
		super("","",0,0,	0,0,0,0,0, 0,0,0,0,"");
		this.itemName=itemName;	this.itemtype=itemtype;
		this.fmaxhp=fmaxhp;	this.fmaxmp=fmaxmp;	this.shield=shield;
		this.fpdef=fpdef;	this.fmdef=fmdef;	this.fatk=fatk;	this.fintel=fintel;
		this.fspd=fspd;	this.pprice=pprice;	this.sprice=sprice; this.itemNum=itemNum; this.itemInfo=itemInfo;
	}
	/*	장비의 유형은 "투구","흉갑","방패","무기"를 가장 기본으로 하고, "각반","신발"을 2순위, 그 외에 "반지","목걸이","팔찌"
	 * 
	*/
	
	//플레이어가 보유한 장비를 리스트로 구현
	static ArrayList<Equipment> equipments=new ArrayList<Equipment>();
	//플레이어가 보유한 장비의 수
	static int ems=equipments.size();
	// 이 클래스에서만 사용할 변수. readInt용으로 써먹는다든가.
	private static int check;
	private static int possessed;
	private static int purc;
	private static boolean nopos;
	// 상점의 판매중인 장비 리스트
	static ArrayList<Equipment> shopequip=new ArrayList<Equipment>();
	// 판매중인 소포품 리스트의 크기
	static int sems=shopequip.size();
	
	//아이템 습득 메소드. //구매할 때랑 습득할 때랑 다르게 출력되도록 새 메소드를 만들든지 하자.
	public static void PurchaseItem(String itemName) {
		switch(Place.places[GameLogic.PNL].placeName) {
		case("르망 마을 대장간"):
			for(int i=0;i<Place.Reumangsmith.length;i++) {
				for(int k=0;k<equipmentList.length;k++) {
					if(Place.Reumangsmith[i]==equipmentList[k].itemName) {
						shopequip.add(new Equipment(equipmentList[k].itemName,equipmentList[k].itemtype
						,equipmentList[k].fmaxhp,equipmentList[k].fmaxmp,equipmentList[k].shield,equipmentList[k].fpdef,equipmentList[k].fmdef
						,equipmentList[k].fatk,equipmentList[k].fintel,equipmentList[k].fspd,equipmentList[k].pprice,equipmentList[k].sprice
						,equipmentList[k].num,equipmentList[k].itemNum,equipmentList[k].itemInfo));
						break;
					}
				}
			}
			sems=shopequip.size();
			break;
		}
		//이거 안해주면 고장남
		ems=equipments.size();
		nopos=true;
		//이미 습득했는지 체크
		for(int i=0;i<ems;i++) {
			if(itemName==equipments.get(i).itemName) {
				possessed=i;
				nopos=false;
				break;
			}
		}
		if(nopos){
			System.out.println(itemName+"(보유하고 있지 않음)");
		}else {
			System.out.println(itemName+"("+equipments.get(possessed).itemNum+" 개 보유)");}
		check=GameLogic.readInt("구매하시겠습니까?\n(1)구매한다\n(2)구매하지 않는다", 2);
		//아이템 획득
		//해당 이름의 아이템을 배열에서 검색해서 찾은 후 추가
		if(check==1) {
			for(int i=0;i<sems;i++) {
				if(itemName==shopequip.get(i).itemName) {
					purc=i;
					break;
				}
			}
			if(GameLogic.player.gold>=shopequip.get(purc).pprice) {
				GameLogic.player.gold-=shopequip.get(purc).pprice;
				if(nopos) {
					for(int k=0;k<equipmentList.length;k++) {
						if(equipmentList[k].itemName.equals(itemName)) {
							equipments.add(new Equipment(equipmentList[k].itemName,equipmentList[k].itemtype
							,equipmentList[k].fmaxhp,equipmentList[k].fmaxmp,equipmentList[k].shield,equipmentList[k].fpdef,equipmentList[k].fmdef
							,equipmentList[k].fatk,equipmentList[k].fintel,equipmentList[k].fspd,equipmentList[k].pprice,equipmentList[k].sprice
							,equipmentList[k].num,1,equipmentList[k].itemInfo));
							//고장 방지
							ems=equipments.size();
							e_ItemRearrangement();
							System.out.println(itemName+"을(를) "+equipmentList[k].pprice+"골드에 구매했습니다.");
							GameLogic.anythingToContinue();
							break;
						}
					}	
				}else {
					equipments.get(possessed).itemNum+=1;
					System.out.println(itemName+"을(를) "+equipments.get(possessed).pprice+"골드에 구매했습니다.");
					GameLogic.anythingToContinue();
				}
			}else {
			GameLogic.printHeading("해당 물품을 구매하기 위한 골드가 부족합니다.");
			GameLogic.anythingToContinue();
			}
		}
	}
	
	//장비 얻음
	public static void acquireItem(String itemName) {
		//이거 안해주면 고장남
		ems=equipments.size();
		nopos=true;
		//이미 습득했는지 체크. 있으면 개수만 늘림.
		for(int i=0;i<ems;i++) {
			if(itemName==equipments.get(i).itemName) {
				nopos=false;
				equipments.get(i).itemNum+=1;
				break;
			}
		}
		if(nopos){
			//아이템 획득
			//해당 이름의 아이템을 배열에서 검색해서 찾은 후 추가
			for(int k=0;k<equipmentList.length;k++) {
				if(equipmentList[k].itemName.equals(itemName)) {
					equipments.add(new Equipment(equipmentList[k].itemName,equipmentList[k].itemtype
						,equipmentList[k].fmaxhp,equipmentList[k].fmaxmp,equipmentList[k].shield,equipmentList[k].fpdef,equipmentList[k].fmdef
						,equipmentList[k].fatk,equipmentList[k].fintel,equipmentList[k].fspd,equipmentList[k].pprice,equipmentList[k].sprice
						,equipmentList[k].num,1,equipmentList[k].itemInfo));
					//고장 방지
					ems=equipments.size();
					e_ItemRearrangement();
					break;
				}
			}	
		}
		System.out.println(itemName+"을(를) 얻었습니다.");
		GameLogic.anythingToContinue();
	}
	
	public static void DisplayEqm() {
		//이거 안 치면 고장남
		ems=equipments.size();
		GameLogic.printHeading("장비 선택(돌아가려면 "+(ems+1)+"입력)");
		for(int i=1;i<ems+1;i++) {
			if(i%5==0) {
				System.out.println("("+i+")"+equipments.get(i-1).itemName+" (보유 수: "+equipments.get(i-1).itemNum+")");
			}else if((i)==ems){
				System.out.println("("+i+")"+equipments.get(i-1).itemName+" (보유 수: "+equipments.get(i-1).itemNum+")\n");
			}else {
				System.out.print("("+i+")"+equipments.get(i-1).itemName+" (보유 수: "+equipments.get(i-1).itemNum+")\t");
			}
		}
	}
	
	//아이템을 획득했을 시 아이템 넘버로 재정렬하는 메소드
	public static void e_ItemRearrangement() {
		Equipment tmp= new Equipment("","",0,0,0, 0,0,0,0,0, 0,0,0,0,"");	//아이템 객체를 저장할 임시 필드
		for(int i=ems;i>0;i--) {
			for(int j=0;j<(i-1);j++) {			//연속되는 두 리스트 원소의 스킬넘버를 비교해서 재정렬
				if(equipments.get(j).itemNum>equipments.get(j+1).itemNum) {	//앞의 원소가 뒤의 원소보다 스킬넘버가 클 때 뒤로 넘겨주기 n02 n01
					tmp=equipments.get(j+1);	//뒤의 원소를 임시 필드에 저장	tmp=n01
					equipments.add(j,tmp);		//임시 필드의 원소를 앞에 추가 n01 n02 n01
					equipments.remove(j+2);		//뒤로 밀려있던 원래 [앞의 원소]를 삭제	n01 n02
				}else {
					continue;}
			}
		}
	}
	//장비 배열. 인덱스 역할.
	static Equipment[]equipmentList=new Equipment[16];
	public static void Make_Equipment_List() {
		//아이템명,유형,최대체력,최대마나,실드,	물리방어력,마법저항력,공격력,주문력,스피드, 	구매가,판매가,보유수,아이템 넘버,정보
		equipmentList[0]=new Equipment("디버깅 장비","디버깅",0,0,0, 0,0,0,0,0, 0,0,0,0,"디버깅 용도로 쓰는 장비 객체");
		
		equipmentList[1]=new Equipment("가죽 투구","투구",0,0,0, 3,3,0,0,-0.5, 30,0,0,1,"");
		equipmentList[2]=new Equipment("가죽 흉갑","흉갑",0,0,0, 8,5,0,0,-0.8, 100,0,0,2,"");
		equipmentList[3]=new Equipment("가죽 각반","각반",0,0,0, 7,5,0,0,-0.7, 80,0,0,5,"");
		equipmentList[4]=new Equipment("가죽 부츠","부츠",0,0,0, 2,1,0,0,-0.4, 20,0,0,6,"");
		
		equipmentList[5]=new Equipment("판금 투구","투구",0,0,0, 7,8,0,0,-0.7, 100,0,0,7,"");
		equipmentList[6]=new Equipment("판금 흉갑","흉갑",0,0,0, 20,18,0,0,-1.4, 200,0,0,8,"");
		equipmentList[7]=new Equipment("판금 각반","각반",0,0,0, 15,15,0,0,-1.2, 170,0,0,9,"");
		equipmentList[8]=new Equipment("판금 부츠","부츠",0,0,0, 7,4,0,0,-1, 80,0,0,10,"");
		
		equipmentList[9]=new Equipment("보급형 룬 모자","투구",0,15,0, 1,3,0,10,-0.2, 0,0,0,11,"");
		equipmentList[10]=new Equipment("로브","망토",0,10,0, 2,10,0,8,-0.6, 0,0,0,12,"");
		equipmentList[11]=new Equipment("조잡한 마력 부츠","부츠",0,5,0, 1,3,0,3,-0.3, 0,0,0,13,"");
		equipmentList[12]=new Equipment("나무 완드","양손 무기",0,5,0, 0,0,0,20,-1, 0,0,0,14,"");
		equipmentList[13]=new Equipment("가죽 실프","방패",0,-10,20, 0,0,0,0,0, 0,0,0,15,"가죽 재질의 실프. 사용자의 마나를 사용하여 방어막을 생성한다.");
		
		equipmentList[14]=new Equipment("나무 방패","방패",0,0,15, 0,0,0,0,-0.7, 35,0,0,3,"");
		equipmentList[15]=new Equipment("롱소드","양손 무기",0,0,0, 0,0,10,0,-1, 50,0,0,4,"");
	}

}
