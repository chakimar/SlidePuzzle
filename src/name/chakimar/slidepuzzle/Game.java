package name.chakimar.slidepuzzle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Game {
	
	private static final Game singleton = new Game();
	
	int maxLeftCount;
	int maxRightCount;
	int maxUpCount;
	int maxDownCount;
	
	int totalBoardCount;

	private Iterator<Board> boardIterator;

	private ArrayList<Board> boardList;
	
	private Game() {
	}
	
	public static Game getInstance() {
		return singleton;
	}

	public void load(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		_load(scanner);
	}
	
	private void _load(Scanner scanner) {
		this.maxLeftCount = scanner.nextInt();
		this.maxRightCount = scanner.nextInt();
		this.maxUpCount = scanner.nextInt();
		this.maxDownCount = scanner.nextInt();
		
		this.totalBoardCount = scanner.nextInt();

		this.boardList = new ArrayList<Board>();
		while(scanner.hasNext()) {
			Board board = new Board(scanner.next());
			boardList.add(board);
		}
		this.boardIterator = boardList.iterator();
	}

	public Board nextBoard() {
		return boardIterator.next();
	}

	public boolean hasNextBoard() {
		return boardIterator.hasNext();
	}

	public void backToTopBoard() {
		this.boardIterator = boardList.iterator();
	}
	
	
}
