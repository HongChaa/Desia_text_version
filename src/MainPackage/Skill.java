package MainPackage;
import java.util.ArrayList;
// [추가 바람](순간이동 스킬)
// 순간이동 스킬이 존재하면 추가로 특정 맵에 바로 이동할 수 있도록 하는 선택지도 추가
// 순간이동 스킬의 마나 소모량은 (두 맵의 식별번호 차이값*마나소모공식)으로 정하면 될듯.
// 게임 후반부 또는 자주 플레이해본 사람은 맵 이동이 귀찮아지기 때문에 포켓몬스터의 공중날기같은 기능으로 사용.

public class Skill{
	String skillname,elements,type,skinfo;		//스킬명,스킬 속성(4원소, 아카샤: 빛과 어둠 속성을 통칭),스킬 타입(물리,마법)
	public int skdmg,conmp,sknum;	//스킬 데미지,공격력 계수,주문력 계수,마나 소모량,스킬 번호
	public double pcoeif,mcoeif;
	
	public Skill(String skillname,String elements,String type,int skdmg,double pcoeif,double mcoeif,int conmp,int sknum,String skinfo) {	// [추가바람]스킬 설명
		this.skillname=skillname;	this.elements=elements;
		this.type=type;	this.pcoeif=pcoeif;	this.mcoeif=mcoeif;
		this.skdmg=skdmg;	this.conmp=conmp;
		this.sknum=sknum;	this.skinfo=skinfo;
	}
	
	//플레이어가 획득한 스킬을 리스트로 구현
	static ArrayList<Skill> skills=new ArrayList<Skill>();
	//플레이어가 획득한 스킬의 수
	static int sks=skills.size();
	
	//적 객체에 부여하는 스킬의 임시 리스트
	static ArrayList<Skill> Enemyskills=new ArrayList<Skill>();
	//해당 적 객체가 가진 스킬의 수
	static int esks=Enemyskills.size();
	
	//스킬 목록을 출력하는 메소드
	public static void DisplaySkill() {
		//이거 안 치면 고장남
		sks=skills.size();
		GameLogic.printHeading("스킬 선택(돌아가려면 "+(sks+1)+"입력)");
		for(int i=1;i<sks+1;i++) {
			if(i%5==0) {
				System.out.println("("+i+")"+skills.get(i-1).skillname+" (mp cost: "+skills.get(i-1).conmp+")");
			}else if((i)==sks){
				System.out.println("("+i+")"+skills.get(i-1).skillname+" (mp cost: "+skills.get(i-1).conmp+")\n");
			}else {
				System.out.print("("+i+")"+skills.get(i-1).skillname+" (mp cost: "+skills.get(i-1).conmp+")\t");
			}
		}
	}
	
	//스킬 습득 메소드
	public static void getSkill(String skillname) {
		// 습득 체크 변수
		int check=0;
		//이거 안해주면 고장남
		sks=skills.size();
		//이미 습득했는지 체크
		for(int i=0;i<sks;i++) {
			if(skillname==skills.get(i).skillname) {
				check=1;
				System.out.println(skillname+"은(는) 이미 보유한 스킬입니다.");
				GameLogic.anythingToContinue();
				break;
			}
		}
		//스킬 획득
		//해당 이름의 스킬을 배열에서 검색해서 찾은 후 추가
		if(check!=1) {
		for(int k=0;k<skillList.length;k++) {
			if(skillList[k].skillname.equals(skillname)) {
				skills.add(new Skill(skillList[k].skillname,skillList[k].elements,skillList[k].type,skillList[k].skdmg,skillList[k].pcoeif,
				skillList[k].mcoeif,skillList[k].conmp,skillList[k].sknum,skillList[k].skinfo));
				System.out.println("스킬 ["+(skillList[k].skillname)+"]을(를) 배웠다!");
				//고장 방지
				sks=skills.size();
				//System.out.println(sks);	//디버그용으로 써먹기
				skillRearrangement();
				GameLogic.anythingToContinue();
				break;
			}
		}	
		}	
	}
	
	//스킬을 습득했을 시, 스킬 넘버(포켓몬스터 기술머신처럼) 오름차순으로 재정렬하는 메소드
	public static void skillRearrangement() {
		Skill tmp= new Skill("","","",0,0,0,0,0,"");	//스킬 객체를 저장할 임시 필드
		for(int i=sks;i>0;i--) {
			for(int j=0;j<(i-1);j++) {			//연속되는 두 리스트 원소의 스킬넘버를 비교해서 재정렬
				if(skills.get(j).sknum>skills.get(j+1).sknum) {	//앞의 원소가 뒤의 원소보다 스킬넘버가 클 때 뒤로 넘겨주기 n02 n01
					tmp=skills.get(j+1);	//뒤의 원소를 임시 필드에 저장	tmp=n01
					skills.add(j,tmp);		//임시 필드의 원소를 앞에 추가 n01 n02 n01
					skills.remove(j+2);		//뒤로 밀려있던 원래 [앞의 원소]를 삭제	n01 n02
				}else {
					continue;}
			}
		}
	}
	
	/*//적에게 스킬 부여
	public static void SetEnemySkill() {
		Enemyskills.clear();
		Enemyskills.add(new Skill(skillList[1].skillname,skillList[1].elements,skillList[1].type,skillList[1].skdmg
		,skillList[1].pcoeif,skillList[1].mcoeif,skillList[1].conmp,skillList[1].sknum,skillList[1].skinfo));
		for(k1=0;k1<skillList.length;k1++) {
			if(skillList[k1].skillname==Ensk[ENum].skname1) {
				Enemyskills.add(new Skill(skillList[k1].skillname,skillList[k1].elements,skillList[k1].type,skillList[k1].skdmg,skillList[k1].pcoeif,
				skillList[k1].mcoeif,skillList[k1].conmp,skillList[k1].sknum,skillList[k1].skinfo));
			}
		}//고장 방지
		esks=Enemyskills.size();
	}*/
	
	//시스템상의 스킬 넘버. 포켓몬스터의 기술머신과 같음.
	static Skill[]skillList=new Skill[31];
	//스킬 목록(배열). 백과사전같은 기능으로, 플레이어나 적이 보유한 스킬은 리스트로 따로 구현.
	//주의!!! getskill로 스킬을 배울 때, 배열 '중간'에 빈 공간이 있으면 NullPointer예외가 발생한다. 그러므로 [1]다음으로 [20]처럼 배열번호를 건너뛰어 선언하면 안 됨.
	public static void Make_Skill_List() {
		//스킬명,속성,타입,	위력,공격력 계수,주문력 계수,마나소모값,스킬넘버,	스킬 설명
		skillList[0]=new Skill("테스트용 스킬","빛","마법",100000,0,0,0,0,"개발자용 테스트 스킬");
		
		skillList[1]=new Skill("파이어","불","마법",10,0,0.2,1,1,"대상 위치에 불 원소를 집중시켜 발화시킨다.");
		skillList[2]=new Skill("파이어 애로우","불","마법",20,0,0.3,5,2,"불의 화살을 만들어 발사한다.");
		skillList[3]=new Skill("파이어 볼","불","물리",50,0,0.35,1,3,"작열하는 화염 구체를 만들어 발사한다.");
		skillList[4]=new Skill("파이어 버스트","불","마법",80,0,0.6,1,4,"주변을 순식간에 불사르는 폭발적 위력의 화염 마법.");	//광역피해도...만들까? 그럼 다중 전투 시스템도....아...
		skillList[5]=new Skill("라이징 플레어","불","마법",100,0,0.8,1,5,"항성의 플레어를 방불케 하는 강력한 화염 폭발을 일으킨다.");
		
		skillList[6]=new Skill("워터 스플래시","물","마법",5,0,0,10,6,"");
		skillList[7]=new Skill("워터 스피어","물","",0,0,0,0,7,"");
		skillList[8]=new Skill("워터 볼","물","",0,0,0,0,8,"");
		skillList[9]=new Skill("하이드로펌프","물","",0,0,0,0,9,"");
		skillList[10]=new Skill("쓰나미","물","",0,0,0,0,10,"");
	
		skillList[11]=new Skill("에어 슬래시","바람","",0,0,0,0,11,"");
		skillList[12]=new Skill("바람 장막","바람","",0,0,0,0,12,"");
		skillList[13]=new Skill("윈드 스트라이크","바람","",0,0,0,0,0,"");
		skillList[14]=new Skill("폭풍","바람","",0,0,0,0,0,"");
		skillList[15]=new Skill("토네이도","바람","",0,0,0,0,0,"");
	
		skillList[16]=new Skill("스톤 크래시","땅","",0,0,0,0,0,"");
		skillList[17]=new Skill("스톤 핸드","땅","",0,0,0,0,0,"");
		skillList[18]=new Skill("스톤 골렘","땅","",0,0,0,0,0,"");
		skillList[19]=new Skill("지진","땅","",0,0,0,0,0,"");
		skillList[20]=new Skill("더스트 블래스트","땅","",0,0,0,0,0,"");
	
		skillList[21]=new Skill("지폭천성","아카샤","물리",3000,0,2,10,50,"지면을 붕괴시킨 파편으로 시전 대상을 핵으로 삼는 구체를 만드는 궁극의 봉인술.");	//공격력 계수는 없지만 피해 유형은 물리형
		skillList[22]=new Skill("만상천인","아카샤","마법",3000,0,2,10,50,"");
		skillList[23]=new Skill("신라천정","아카샤","마법",3000,0,2,10,50,"");
		skillList[24]=new Skill("천애진성","아카샤","마법",3000,0,2,10,50,"");
		skillList[25]=new Skill("람둔광아","아카샤","마법",100,0, 0.9,75,0,"어떤 것이라도 절단내버리는 에너지 광선을 입에서 발사하는 기술.");
		
		skillList[26]=new Skill("트리플 큐빅 버스트","아카샤","마법",300,0,0.99,120,50,"");
		skillList[27]=new Skill("큐빅 스트라이크","아카샤","마법",100,0,0.33,30,50,"");
		skillList[28]=new Skill("인드라의 번개","아카샤","마법",3000,0,2,10,50,"");
		skillList[29]=new Skill("암흑차원사신격","아카샤","마법",3000,0,2,10,50,"");
		skillList[30]=new Skill("사악한 손길","아카샤","마법",10,0,0.1,10,29,"");
		

		//skillList[51]=new Skill("만상천인","노말","물리",100,0,1,1,51,"시전 대상에게 강한 인력을 발생시켜 주변의 모든 물체를 끌어당긴다.");	//이런 스킬들은 기본 피해량은 낮추고 스킬 계수를 높이는 것이 합당해보임.
		//skillList[52]=new Skill("신라천정","노말","물리,변화?(방어계 스킬)",100,1,0,0,52,"자신으로부터 강한 척력을 발생시켜 모든 물체를 밀어낸다.");*/
	
	}
	
}
