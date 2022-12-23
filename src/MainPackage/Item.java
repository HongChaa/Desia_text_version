package MainPackage;

public abstract class Item {
	//내구도 따위는... 나중에 생각하자.
	String itemName, itemtype, itemInfo;	//아이템 이름, 아이템 타입(장비, 소모품, 기타 등...), 아이템 정보
	int itemNum,num;	//아이템 식별번호(ID),보유수
	
	//장비나 소모품처럼 스텟을 변화시키는 아이템일 경우 필요한 변수
	//fluctuation(수치의 변화): 최대체력,최대마나,	물리방어력,마법저항력,공격력,주문력,스피드,		아이템 구매가, 판매가
	double fmaxhp,fmaxmp,	fpdef,fmdef,fatk,fintel,fspd,	pprice,sprice;	//purchase price, selling price
	
	//소모품 생성자
	public Item(String itemName, String itemtype,double fmaxhp,double fmaxmp,double fpdef,double fmdef,
			double fatk,double fintel,double fspd,double pprice,double sprice,int num,int itemNum,String itemInfo) {
	}
	/*	굳이 구매가(pprice)와 판매가(sprice)를 따로 설정한 이유는, 스토리 내에서 재미있는 요소로 써먹을 여지가 있기 때문이다.
	 *	(특정 이벤트에서 포션을 많이 가지게 되면 너무 쉬워지게 됨. 이것을 방지해서 포션 가격이 폭등했다는 설정으로 난이도를 조정)
	 * 	일단은 아이템 배열에 객체를 설정해서 넣을 때 공식을 적용해, 판매가는 구매가의 일정 비율로 계산하도록 하는, 
	 * 	많은 게임들에서 흔히 채택되는 방식으로 설정하자.
	*/
	
}