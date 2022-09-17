package word;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
	Scanner s = new Scanner(System.in);
	ArrayList<Word> list;
	final String fname = "Dictionary.txt";
	
	WordCRUD(Scanner s){
		list = new ArrayList<>();
		this.s = s;
	}
	
	@Override
	public Object add() {
		System.out.print("=> 난이도(1,2,3) & 새 단어 입력 : ");
		int level = s.nextInt();
		String word = s.nextLine();
		
		System.out.print("뜻 입력 : ");
		String meaning = s.nextLine();
		
		return new Word(0, level, word, meaning);
	}
	
	public void addWord() {
		Word one = (Word)add();
		list.add(one);
		System.out.println("\n새 단어가 추가되었습니다.\n");
	}

	@Override
	public int update(Object obj) {
		return 0;
	}

	@Override
	public int delete(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void selectOne(int id) {
		// TODO Auto-generated method stub
		
	}
	
	public void listAll() {
		System.out.println("--------------------------------");
		for(int i = 0; i < list.size(); i++) {
			String word = list.get(i).getWord();
			System.out.print((i+1) + " ");
			System.out.println(list.get(i).toString());
		}
		System.out.println("--------------------------------");
	}
	
	public ArrayList<Integer> listAll(String keyword) {
		ArrayList<Integer> idlist = new ArrayList<>();
		int j = 0;
		System.out.println("--------------------------------");
		for(int i = 0; i < list.size(); i++) {
			String word = list.get(i).getWord();
			if(!word.contains(keyword)) continue;
			System.out.print((j+1) + " ");
			System.out.println(list.get(i).toString());
			idlist.add(i);
			j++;
		}
		System.out.println("--------------------------------");
		return idlist;
	}
	
	public void listAll(int level) {
		int j = 0;
		System.out.println("--------------------------------");
		for(int i = 0; i < list.size(); i++) {
			int level_n = list.get(i).getLevel();
			if(level_n != level) continue;
			System.out.print((j+1) + " ");
			System.out.println(list.get(i).toString());
			j++;
		}
		System.out.println("--------------------------------");
	}

	public void updateWord() {
		System.out.print("=> 수정할 단어 검색 : ");
		String modword = s.next();
		ArrayList<Integer> idlist = this.listAll(modword);
		System.out.print("=> 수정할 번호 입력 : ");
		int id = s.nextInt();
		s.nextLine();
		
		System.out.print("=> 뜻 입력 : ");
		String meaning = s.nextLine();
		
		Word word = list.get(idlist.get(id-1));
		word.setMeaning(meaning);
		System.out.println("수정이 완료되었습니다!");
		}

	public void deleteWord() {
		System.out.print("=> 삭제할 단어 검색 : ");
		String modword = s.next();
		ArrayList<Integer> idlist = this.listAll(modword);
		System.out.print("=> 삭제할 번호 입력 : ");
		int id = s.nextInt();
		s.nextLine();
		
		System.out.print("=> 정말로 삭제하시겠습니까?(Y/N) : ");
		String yorn = s.next();
		
		if(yorn.equalsIgnoreCase("y")) {
			list.remove((int)idlist.get(id-1));
			System.out.println("단어가 삭제되었습니다.");
		}
		else
			System.out.println("취소되었습니다.");
	}

	public void loadFile() {
		String line;
		int count = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fname));
			
			while(true) {
				line = br.readLine();
				if(line == null) break;
				
				String data[] = line.split("-");
				int level = Integer.parseInt(data[0]);
				String word = data[1];
				String meaning = data[2];
				
				list.add(new Word(0, level, word, meaning));
				count++;
			}
			br.close();
			System.out.println("=> " + count + "개 로딩 완료!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void saveFile() {
		try {
			PrintWriter pr = new PrintWriter(new FileWriter(fname));
			for(Word one : list) {
				pr.write(one.toFileString() + "\n");
			}
			pr.close();
			System.out.println("=> 데이터 저장 완료!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void findLevel() {
		System.out.print("찾으시는 레벨을 입력하세요(1~3) : ");
		int level = s.nextInt();
		
		if(level < 1 || level > 3) {
			System.out.println("해당 레벨은 없습니다!");
			return;
		}
		else listAll(level);
	}

	public void findWord() {
		System.out.print("찾으시는 단어를 입력하세요 : ");
		 String keyword = s.next();
		 
		listAll(keyword);
	}

}
