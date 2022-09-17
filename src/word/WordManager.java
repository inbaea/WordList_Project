package word;

import java.util.Scanner;

public class WordManager {
	Scanner s = new Scanner(System.in);
	WordCRUD wordCRUD;
	
	WordManager(){
		wordCRUD = new WordCRUD(s);
	}
	
	public int selectMenu() {
		System.out.print("    *** 영단어 마스터 ***\n"
				+ "**********************\n"
				+ "1. 모든 단어 보기\n"
				+ "2. 수준별 단어 보기\n"
				+ "3. 단어 검색\n"
				+ "4. 단어 추가\n"
				+ "5. 단어 수정\n"
				+ "6. 단어 삭제\n"
				+ "7. 파일 저장\n"
				+ "0. 나가기\n"
				+ "**********************\n"
				+ "=> 원하는 메뉴는 >> ");
		
		return s.nextInt();
	}
	
	public void start() {
		
		wordCRUD.loadFile();
		while(true) {
			int menunum = selectMenu();
			if(menunum == 0) break;
			else if(menunum == 1) {
				wordCRUD.listAll();
			}
			else if(menunum == 2) {
				wordCRUD.findLevel();
			}
			else if(menunum == 3) {
				wordCRUD.findWord();
			}
			else if(menunum == 4) {
				wordCRUD.addWord();
			}
			else if(menunum == 5) {
				wordCRUD.updateWord();
			}
			else if(menunum == 6) {
				wordCRUD.deleteWord();
			}
			else if(menunum == 7) {
				wordCRUD.saveFile();
			}
		}
	}
}
