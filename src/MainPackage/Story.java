package MainPackage;

//어떤 기능을 수행하는 클래스는 아니다. 스토리의 부분들을 저장 및 출력하기 위한 메소드들을 담고 있다.
public class Story {
	public static void printIntro() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(20);
		System.out.println("프롤로그");
		GameLogic.printSeperator(20);
		System.out.println("그날은 유독 쌀쌀한 날이었다.\n\n여느 때처럼 침대에 누워 잠을 청했다.\n쏟아지는 졸음에 금방 잠이 들고 얼마 지나지 않아 알 수 없는 광경이 펼쳐졌다."
		+"\n북유럽의 해안가에서나 볼 법한 널찍한 해변과 들판이 펼쳐진 신천지였다.\n아름다운 풍경은 둘째치고 꿈이라고 하기에는 너무도 생생한 감각에 이상하게 느낄 때쯤, "
		+"해변에서 사람의 형체가 수면을 뚫고 공중으로 날아올랐다. \n그 자태는 신화 속의 여신이라고 해도 좋을법한 신성함과 아름다움을 자아내고 있었다.");
		System.out.println("그녀는 허공에서 날 가만히 내려다보더니 무어라고 중얼거렸다.\n");
		System.out.println("\"또 잘못 왔어? 금방 돌려보내줄게.\"");
		GameLogic.anythingToContinue();
		System.out.println("\n\n그녀가 손짓하자 몸이 두둥실 뜨는 느낌이 들더니 방금 전과는 또 전혀 다른 풍경이었다."
		+"\n낮선 숲 속에 떨어진 나는 필사적으로 꿈에서 깨려고 노력했다.\n볼도 꼬집어보고, 뺨도 때려봤으며, 땅에 누워서 잠을 청해보기도 했지만, 이게 꿈이 아니라는 사실만 더욱 공고해질 뿐이었다.\n");
		System.out.println("아니... 돌려보내준다며요......");	
		GameLogic.anythingToContinue();
	}
	
	public static void Mainscenario_1() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(20);
		System.out.println("\t챕터 I\n\t호접지몽");
		GameLogic.printSeperator(20);
		System.out.println("전혀 알 수 없는 곳에 떨어진 불안감이란 이루 말할 수 없었다. 가까스로 마음을 다잡고 날이 저물기 전에 마을이나 도시를 찾아보기로 했다."
		+"\n그렇게 몇십 분을 돌아다녔을까. 어디선가 고함 소리와 함께 금속 파찰음이 들려왔다. 나는 서둘러 그곳으로 달려갔다.\n하지만 날 반기는 건 사람이 아니라 그보다 조금 작은 녹색 괴물이었다.");
		System.out.println("\n왠지 낮설지 않은 모습의 그 괴생명체는 날 보자마자 '케에엑!' 괴성을 지르며 날붙이를 휘둘렀고, 나는 공격을 막기 위해 왼팔을 들었다가 베여버렸다."
		+"\n더러운 날붙이에 깊게 베인 상처에서는 피가 멈추질 않았고, '켈켈켈'웃으며 다시 한 번 칼을 휘두르려는 괴물에게 이렇게 영문도 모르고 죽어야 하나 싶은 그때였다."
		+"\n닭처럼 생긴 동물을 탄 기사가 순식간에 달려와 괴물을 베어넘겼고, 괴물은 그대로 절명했다.\n\"괜찮소? 빨리 응급처치를!\"\n\n나는 안도의 한숨과 함께 정신을 잃었다.");
		GameLogic.anythingToContinue();
		System.out.println("'뭐지...죽은건가'\n어딘지 모를 오두막집에서 나는 나 자신의 몸을 내려다보고 있었다. 이게 그 말로만 듣던 유체이탈인가. 밖을 보아하니 밤인 것 같다."
		+"\n난롯불에 비친 내 몸의 얼굴을 보아하니 생기가 돌고 있어서 아직 죽은 것 같진 않았다. 아마 아까 그 기사가 구해다 준 거겠지.\n\n\"얘!\"\n\n우왓, 깜짝이야. 소리가 난 쪽을 보니 아까 본 여자가 있었다."
		+"\n\n\"그, 뭔가 착오가 있었던 모양이야. 그게 말하자면 복잡한데, 결론만 말하자면 내가 실수해서 너를 다른 세계로 보내버렸어.\""
		+"\n\n이 여자의 얘기를 듣자 하니, 자기는 이 세계를 관리하는 신인데, 지구에서 멀쩡히 잘 살고 있던 나를 실수로 데려왔다는 것이다. 심지어 지구도 아니라네. 허...참."
		+"\n\n\"그러면 나는 어떻게 돌아가라고요?\"\n\"어...그게, 비유하자면 단방향 통신같은거라서 안 된달...까나? 영혼만 온 게 아니라 육체까지 와버려서 말이지...\""
		+"\n여신이랍시고 어리버리한 인간처럼 말하는 투도 짜증나고, 무책임한 태도로 일관하는 그녀의 행동으로 나는 끓어오르는 분노를 주체할 수 없을 것만 같았다. "
		+"\n잠들어 있는 내 몸이 끙끙대며 부들부들 떨리는 것이 보일 정도였다.");
		GameLogic.anythingToContinue();
	}
	
	public static void Mainscenario_1a() {
		GameLogic.printSeperator(20);
		System.out.println("결국 화를 억누르지 못한 나는 주먹을 쥐고 냅다 달려들었다.\n\n\"이 x발 xx놈아!\"\n\n");
		System.out.println("하지만 나의 발악은 신의 손짓 한 번에 제압당했다. 그러더니 갑자기 진지한 말투로 날 꾸짖었다.\n\n\"무례한 놈... 원래같았으면 넌 그대로 소멸이지만 나의 잘못으로 비롯된 일이니 이번만 넘어가도록 하겠다."
		+"깨어나면 '스테이터스 오픈'이라고 외쳐라. 그러면 내 넓은 아량에 감사하게 될 것이니라.\"");
		System.out.println("\n\n무력하게 바닥에 찌그러져있던 내 영혼은 여신이 사라지자 다시 몸으로 이끌려 돌아갔고, 얼마 지나지 않아 창밖으로 비쳐오는 햇빛과 함께 일어났다.");
		GameLogic.anythingToContinue();
	}
	public static void Mainscenario_1b() {
		GameLogic.printSeperator(20);
		System.out.println("\n\n\"......\"\n\n하지만 상대는 신. 손짓만으로 날 이세계로 날려버릴 권능을 가진 존재에게 대드는 것은 누가 봐도 멍청한 짓이었다."
		+"\n그래도 무슨 감정 표현이라도 해야겠다고 느낀 나는, 최소한 천벌을 받지 않을 정도의 불만스런 표정을 지으며 침묵했다."
		+"\n그러자 이 무책임한 여신도 눈치라는 건 있는지, 미안한 기색을 내비치며 사과했다.\n\n\"정말 미안해. 이름이 "+GameLogic.player.name+"맞지? 너도 이것저것 혼란스러울 테니까 "
		+"본론만 빨리 말해줄게. 네가 여기 잘못 왔다는 건 너와 짝이 되는 오류를 가진 또 한 명의 사람도 왔다는 뜻이야. \n그 사람과 만나서 함께 소멸해야 육체채로 돌아갈 수 있게 돼.\"");
		System.out.println("\"그 사람은 어디 있는데요?\"\n\"너랑 비슷한 시기에 왔을거야. 그리고 다시 한 번 정말 미안하지만, 나라고 너를 전부 도울 수는 없어. 그런 사정이 있으니 이해해 줘..."
		+"\n나도 내가 가능한 선 내에서 최대한 널 도울거야. 그럼 행운을 빌게. 깨어나면 '스테이터스 오픈'이라고 외쳐보렴. 내 작은 선물이야.\"");
		System.out.println("\n그녀는 빛무리를 남기며 일순간에 사라졌다. 동시에 내 영혼도 육체에 이끌려 돌아갔다. 눈을 떴을 때는 얼굴 위로 햇빛이 내리앉아 있었다.");
		GameLogic.anythingToContinue();
	}
	public static void Mainscenario_1c() {
		System.out.println("\n\n나는 눈을 감고 가부좌 자세로 붓다의 가르침을 떠올리며, 마침내 분노라는 감정의 속박으로부터 벗어나 자유롭게 되었다."
		+"\n\n\"비록 불의의 사고로 낮선 땅에 던져지게 되었으나, 범인(凡人)은 평생 살아생전 만날 수도 없는 여신님을 만나고 새로운 삶을 살 기회를 얻은 것 또한 운명인데 어찌 불평하겠습니까.\""
		+"\n\n세상 해탈한 듯한 나를 보고 여신은 감명을 받은 듯, 온화한 표정으로 부드럽게 말했다.\n");
		System.out.println("\"지금까지의 인생을 송두리째 다시 시작해야 할 상황에 그 좌절감과 허탈함, 분노는 이루 말할 수 없을 터인데 믿을 수 없는 정신력을 가졌구나. 내 너를 기특히 여겨 "
		+"너의 새로운 앞날을 축복하노라.\n깨어나면 '스테이터스 오픈'이라고 외쳐보아라. 내 작은 선물이다.\"\n");
		System.out.println("여신 치고는 경박한 투로 말하다가 갑자기 신의 위엄에 걸맞는 어조로 말하니 좀 어이가 없었다. 그녀는 내 영체의 머리에 손을 얹었다."
		+"\n\n[알 수 없는 힘이 흘러들어오는 것이 느껴집니다...]");
		System.out.println("\n여신은 어느새 사라져 있었고, 그녀가 있던 자리에는 희미한 빛무리만 남아 있었다.\n곧이어 내 영체는 어느새 몸으로 돌아왔고, 눈을 떴을 때는 얼굴 위로 햇빛이 내리앉아 있었다.");
		GameLogic.anythingToContinue();
	}
	
	public static void Mainscenario_1e1() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(20);
		System.out.println("나는 일어나자마자 여신의 말을 기억하고, 영문은 모르겠지만 일단 '스테이터스 오픈'이라고 외쳐보았다.");
		GameLogic.anythingToContinue();
	}
	public static void Mainscenario_1e2() {
		System.out.println("\"아니, 이게 무슨...\"\n\n마치 x이플 스토리처럼 내 스텟창이 허공에 뜨는 걸 보니 뭐랄까. 게임 속에 들어와있는 것 같아서 설레거나 기대가 되기도 했지만, \n"
		+"동시에 어쩌다 이렇게 되었는지 혼란스럽고 씁쓸하기도 한, 굉장히 복잡한 감정이 몰려왔다.");
		System.out.println("잠시 후 문이 열리며 한 남자가 들어왔다. 가죽 갑옷에 검을 차고 있는 것을 보아하니 여기가 다른 세계라는 사실이 더욱 실감나는 순간이었다."
		+"\n그는 날 보더니 몸은 괜찮냐고 물어본 뒤, 대장님을 불러오겠다며 다시 나갔다."
		+"\n별다른 반응이 없는 걸로 봐서 이 스텟창은 나만 볼 수 있는 것 같았다. 그건 그렇고 말이 통하는 걸로 봐서 [전생자]특성의 효과인 것 같다. 외국어 공부는 안 해도 되겠네.");
		System.out.println("\n몇 분 뒤 들어온 남자는 어제 나를 구해주었던 그 기사였다. 그는 내가 어쩌다 그 숲에 홀로 있었느냐고 물었고, 나는 잘 모르겠고 정신을 잃었다고 답했다."
		+"\n그는 자신을 '알렉스'라고 소개했고, 르망 마을의 경비대장을 하고 있다고 말했다. 알렉스는 내 손에 돈 몇 푼을 쥐어주더니, 이걸로 마을에서 배라도 채우라며 격려해주었다."
		+ "\n나는 그에게 감사함을 표하고 오두막을 나섰다.");
		GameLogic.anythingToContinue();
	}
	
	public static void Mainscenario_2() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(20);
		System.out.println("메인 시나리오2");
		GameLogic.printSeperator(20);
		System.out.println("시나리오 설명1");
		System.out.println("시나리오 설명2");
		System.out.println("\n시나리오 설명3");
		GameLogic.anythingToContinue();
	}
	
	public static void Mainscenario_2e() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(20);
		System.out.println("메인 시나리오2 끝맺음");
		GameLogic.printSeperator(20);
		System.out.println("시나리오 설명1");
		System.out.println("시나리오 설명2");
		System.out.println("\n시나리오 설명3");
		GameLogic.anythingToContinue();
	}
	
	public static void Mainscenario_3() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(20);
		System.out.println("메인 시나리오3");
		GameLogic.printSeperator(20);
		System.out.println("시나리오 설명1");
		System.out.println("시나리오 설명2");
		System.out.println("\n시나리오 설명3");
		GameLogic.anythingToContinue();
	}
	
	public static void Mainscenario_3e() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(20);
		System.out.println("메인 시나리오3 끝맺음");
		GameLogic.printSeperator(20);
		System.out.println("시나리오 설명1");
		System.out.println("시나리오 설명2");
		System.out.println("\n시나리오 설명3");
		GameLogic.anythingToContinue();
	}
	
	public static void Mainscenario_4() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(20);
		System.out.println("메인 시나리오4");
		GameLogic.printSeperator(20);
		System.out.println("시나리오 설명1");
		System.out.println("시나리오 설명2");
		System.out.println("\n시나리오 설명3");
		GameLogic.anythingToContinue();
	}
	
	public static void Mainscenario_4e() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(20);
		System.out.println("메인 시나리오4 끝");
		GameLogic.printSeperator(20);
		System.out.println("시나리오 설명1");
		System.out.println("시나리오 설명2");
		System.out.println("\n시나리오 설명3");
		GameLogic.anythingToContinue();
	}
	
	public static void Mainscenario_E(Player player) {
		GameLogic.clearConsole();
		GameLogic.printSeperator(30);
		System.out.println("당신은 마왕을 무찌르고 세계를 구했습니다!");
		GameLogic.printSeperator(30);
		System.out.println(player.name+"의 이름은 영웅으로서 대대로 전해질 것입니다.");
		System.out.println("\n\t\t THE END\t\t");
		GameLogic.anythingToContinue();
	}
	
	//서브 스토리
	
	public static void Subnario_01() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(30);
		System.out.println("");
		GameLogic.printSeperator(30);
		System.out.println("");
		System.out.println("");
		GameLogic.anythingToContinue();
	}
	
	public static void Subnario_02() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(30);
		System.out.println("");
		GameLogic.printSeperator(30);
		System.out.println("");
		System.out.println("");
		GameLogic.anythingToContinue();
	}

	public static void Subnario_03() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(30);
		System.out.println("");
		GameLogic.printSeperator(30);
		System.out.println("");
		System.out.println("");
		GameLogic.anythingToContinue();
	}
	
	public static void Subnario_04() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(30);
		System.out.println("");
		GameLogic.printSeperator(30);
		System.out.println("");
		System.out.println("");
		GameLogic.anythingToContinue();
	}
	
	public static void Subnario_05() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(30);
		System.out.println("");
		GameLogic.printSeperator(30);
		System.out.println("");
		System.out.println("");
		GameLogic.anythingToContinue();
	}
	
	public static void subque_1() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(30);
		System.out.println("상점 주인은 무언가 다급하게 편지를 쓰는 중이었다. 그는 이내 분주하게 움직이던 펜을 내려놓더니 한숨을 쉬었다.");
		System.out.println("\n\"세상에 이게 무슨 날벼락이다냐... 그 위험한 곳에 직접 갈 수도 없고...\"");
		System.out.println("\n대충 이야기를 듣자하니 마왕의 부활로 인한 몬스터의 대량 발생으로 물류 수송단이 습격을 받은 모양이다.");
		System.out.println("\n(1)어디선가 돈 냄새가 나는 것 같은 느낌인데...");
		System.out.println("(2)아무튼 나는 지금 피곤하다.");
	}
	public static void subque_1_1() {
		GameLogic.printSeperator(30);
		System.out.println("나는 일단 그에게 사정을 물어보았다.");
		System.out.println("\n\"음 그래. 모험가 양반이구먼. '엔글루시아'라는 약초가 마나 물약 재료인디, 그걸 싣고 오는 마차가 몬스터의 습격을 받은 모양이야."
		+"직접 구하려고 해도 엔글루시아는 줄무늬 숲 깊은 곳에서만 자란다네. 나같은 노인네가 갈 수도 없지.\"");
		System.out.println("\n(1)상점 주인을 도와준다");
		System.out.println("(2)\"음, 그렇군요. 유감입니다.\"");
	}
	public static void subque_1_2() {
		GameLogic.printSeperator(30);
		System.out.println("\"정말인가? 도와준다면 고맙구먼. 너무 많이는 말고, 이 바구니에 가득 찰 정도로만 캐다 주게. 조심하고 모험가 양반.\"");
		System.out.println("\n나는 상점에서 나와 줄무늬 숲으로 가기로 했다. 아니 근데, 체력 포션 하나 정도는 챙겨줘도 되는 거 아닌가?\n생각해보니 조금 언짢았지만 그러려니 하기로 했다.");
		System.out.println("\n\n[르망 마을 잡화점 주인의 의뢰] 퀘스트가 추가되었습니다.");
		System.out.println("\n\n진짜 게임하는 것 같아서 적응 안되네 이거.");
		Quest.quests.add(new Quest(Quest.questList[1].questname,Quest.questList[1].questInfo,"진행중"));
		Quest.questList[1].onoff="진행중";
		GameLogic.anythingToContinue();
	}
	public static void subque_1x() {
		GameLogic.printSeperator(30);
		System.out.println("난 별로 피곤한 일에 끼어들고 싶지 않았다. 오늘도 죽어라 약초를 잔뜩 캤기 때문이다.");
		System.out.println("나는 어깨가 축 늘어진 상점 주인을 뒤로 하고, 비명을 질러대는 몸뚱아리를 이끌고 오두막으로 돌아갔다.");
		GameLogic.anythingToContinue();
	}
	public static void subque_1o() {
		GameLogic.printSeperator(30);
		System.out.println("정말 가져왔구먼! 덕분에 살았네. 정말 고맙다네. 이건 내 성의니까 사양하지 말아주게나.");
		Consumable.acquireItem("체력 포션");
		Consumable.acquireItem("마나 포션");
		System.out.println("나는 어깨가 축 늘어진 상점 주인을 뒤로 하고, 비명을 질러대는 몸뚱아리를 이끌고 오두막으로 돌아갔다.");
		GameLogic.anythingToContinue();
	}
	//랜덤 인카운터
	
	//잡담(및 게임 초반 정보)
	public static void molis() {
		GameLogic.clearConsole();
		System.out.println("\"당신이 이번에 숲에서 구조됐다던 분이군요?\"");
		System.out.println("\"아, 네. 제가 멀리서 온 이방인이라 이곳 정보를 잘 몰라서요. 물건은 어떤 걸 팔고 있고 어떻게 구매하죠?\"");
		System.out.println("\n그녀는 친절하게 설명해주기 시작했다.");
		System.out.println("\n\"우리 가게에는 양조 전문가가 계셔서 포션을 제조해 팔고 있어요. 모험가들이 자주 들러서 사 가는 편이죠."
				+ "\n어떻게 구매하냐...라는 건 역시 화폐를 써본 적 없어서 그런거겠죠? 물건 값에 해당하는 금액의 골드랑 물건을 교환하면 돼요.\""
				+ "\n\"아...... 네.\"");
		System.out.println("\n내가 질문을 좀 이상하게 했나. 이 사람은 내가 화폐도 쓰지 않는 어디 원시부족에서 온 걸로 오해한 모양이다.");
		GameLogic.anythingToContinue();
	}
}
