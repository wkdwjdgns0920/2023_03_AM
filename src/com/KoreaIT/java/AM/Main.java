package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	List<Post> posts = new ArrayList<>();
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int lastId = 0;
		List<Post> posts = new ArrayList<>();

		System.out.println("==프로그램 시작==");

		while (true) {
			System.out.print("명령어 )");
			String command = sc.nextLine().trim();
			
			if (command.equals("exit")) {
				break;
			}
			
			if(command.equals("post list")) {
				if(posts.size() == 0 ) {
					System.out.println("게시물이 없습니다");
					continue;
				}
				System.out.println("번호|  제목");
				for (int i = posts.size() - 1; i >= 0; i-- ) {
					Post post = posts.get(i);
					System.out.printf("%d|  %s\n",post.id,post.title);
				}
			
			} else if(command.equals("post write")) {
				int id = lastId + 1;
				System.out.print("제목 : ");
				String title = sc.nextLine();
				String regDate = Util.getNowDateTimeStr();
				System.out.print("내용 : ");
				String body = sc.nextLine();
				Post post = new Post(title, body, id, regDate, regDate);
				posts.add(post);
				
				
				System.out.printf("%d번 글이 생성되었습니다\n",id);
				lastId++;
			} else if (command.startsWith("post detail")) {
				String[] cmdDiv = command.split(" ");
				
				if (cmdDiv.length < 3) {
					System.out.println("명령어를 다시 입력해주세요");
				}
				
				int id = Integer.parseInt(cmdDiv[2]);
				Post foundpost = null;
				
				for(Post post : posts) {
					if (post.id == id) {
						foundpost = post;
					}
				}
				if(foundpost == null) {
					System.out.printf("%n번 게시글은 없습니다\n",id);
					continue;
				}
				System.out.printf("번호 : %s\n",foundpost.id);
				System.out.printf("제목 : %s\n",foundpost.title);
				System.out.printf("날짜 : %s\n",foundpost.regDate);
				System.out.printf("내용 : %s\n",foundpost.body);
				
				
			} else if (command.startsWith("post modify")) {
				String[] cmdDiv = command.split(" ");
				
				if (cmdDiv.length < 3) {
					System.out.println("명령어를 다시 입력해주세요");
				}
				
				int id = Integer.parseInt(cmdDiv[2]);
				Post foundpost = null;
				
				for(Post post : posts) {
					if (post.id == id) {
						foundpost = post;
					}
				}
				if(foundpost == null) {
					System.out.printf("%n번 게시글은 없습니다\n",id);
					continue;
				}
				System.out.println("새 제목 : ");
				String newTitle = sc.nextLine();
				String update = Util.getNowDateTimeStr();
				System.out.println("새 내용 : ");
				String newBody = sc.nextLine();
				foundpost.title = newTitle;
				foundpost.body = newBody;
				foundpost.update = update;
				
			} else {
				System.out.println("존재하지 않는 명령어입니다");
			}
		}

	}

//	private static void makeTestData() {
//		System.out.println("테스트를 위한 게시물 데이터를 생성합니다.");
//		add(posts(new Post("제목 1", "내용 1", 1));
//		
//	}
}
class Post {
	String title;
	String body;
	int id;
	String regDate;
	String update;
	
	Post(String title, String body, int id, String regDate, String update){
		this.title = title;
		this.body = body;
		this.id = id;
		this.regDate = regDate;
		this.update = update;
		
	}
}