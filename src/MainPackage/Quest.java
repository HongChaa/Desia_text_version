package MainPackage;

import java.util.ArrayList;

public class Quest {
	String questname, questInfo,onoff;
	
	//퀘스트명, 내용, [시작가능/진행중/완료가능/완료됨]여부
	public Quest(String questname,String questInfo,String onoff) {
		this.questInfo=questInfo;
		this.questname=questname;
		this.onoff=onoff;
	}
	
	//플레이어가 진행중인 퀘스트 리스트
	static ArrayList<Quest> quests=new ArrayList<Quest>();
	//플레이어가 진행중인 퀘스트 수
	static int qus=quests.size();
	
	public static void DisplayQuest(){
		qus=quests.size();
		while(true) {
			GameLogic.clearConsole();
			GameLogic.printHeading("\t<퀘스트>");
			if(qus==0) {
				System.out.println("현재 진행중인 퀘스트가 없습니다.");
				GameLogic.anythingToContinue();
				break;
			}else {
				System.out.println("상세정보 확인하기(돌아가려면 "+(qus+1)+"입력)\n");
				for(int i=1;i<qus+1;i++) {
					System.out.println("("+i+")"+quests.get(i-1).questname+"("+quests.get(i-1).onoff+")");
				}
				int input = GameLogic.readInt(">>>", qus+1);
				if(input==qus+1) {
					break;
				}else {
					GameLogic.clearConsole();
					GameLogic.printHeading(quests.get(input-1).questname);
					System.out.println(quests.get(input-1).questInfo);
					GameLogic.anythingToContinue();
				}
			}
		}
	}


	public static Quest[]questList=new Quest[10];
	public static void Make_Quest_List(){
		questList[0]=new Quest("테스트용 퀘스트 제목","테스트용 퀘스트 내용","시작가능");
		
		questList[1]=new Quest("르망 잡화점 주인의 부탁","마나 물약의 재료인 '엔글루시아' 약초를 구해오자."
				+ "\n몬스터가 출몰하는 줄무늬 숲 깊은 곳에서만 자생하는 식물이라고 하니 단단히 무장해야한다.","시작가능");
	}
}
