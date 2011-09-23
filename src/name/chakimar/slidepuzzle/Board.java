package name.chakimar.slidepuzzle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import name.chakimar.slidepuzzle.logic.Command;

public class Board {

	public static final String SPACE = "0";
	public static final String WALL = "=";
	/**
	 * 最大３６文字
	 */
	private static final String CORRECT_ANSWER = "123456789abcdefghijklmnopqrstuvwxyz0";
	
	private int width;
	private int height;
	private Map<Point, String> map;
	
	public Board(String src) {
		String[] strs = src.split(",");
		this.width = Integer.parseInt(strs[0]);
		this.height = Integer.parseInt(strs[1]);
		this.map = new HashMap<Point, String>();
		mapping(width, height, strs[2]);
	}
	
	public Board(int width, int height, Map<Point, String> map) {
		super();
		this.width = width;
		this.height = height;
		this.map = map;
	}

	private void mapping(int width, int height, String data) {
		for (int i=0;i<data.length();i++) {
			String value = String.valueOf(data.charAt(i));
			int x = i % width;
			int y = i / width;
			
			Point point = new Point(x, y);
			map.put(point, value);
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public boolean canMoveToLeft() {
		Point spacePoint = getSpacePoint();
		if (spacePoint.getX() > 0) {
			Point movePoint = new Point(spacePoint.getX()-1, spacePoint.getY());
			if(isWallAtMovePoint(movePoint)){
				return false;
			}
			return true;
		}
		return false;
	}

	private boolean isWallAtMovePoint(Point movePoint) {
		String movePointValue = map.get(movePoint);
		if (WALL.equals(movePointValue)) {
			return true;
		}
		return false;
	}

	public boolean canMoveToRight() {
		Point spacePoint = getSpacePoint();
		if (spacePoint.getX() < (this.width - 1)) {
			Point movePoint = new Point(spacePoint.getX()+1, spacePoint.getY());
			if(isWallAtMovePoint(movePoint)){
				return false;
			}
			return true;
		}
		return false;
	}

	public boolean canMoveToUp() {
		Point spacePoint = getSpacePoint();
		if (spacePoint.getY() > 0) {
			Point movePoint = new Point(spacePoint.getX(), spacePoint.getY()-1);
			if(isWallAtMovePoint(movePoint)){
				return false;
			}
			return true;
		}
		return false;
	}

	public boolean canMoveToDown() {
		Point spacePoint = getSpacePoint();
		if (spacePoint.getY() < (this.height - 1)) {
			Point movePoint = new Point(spacePoint.getX(), spacePoint.getY()+1);
			if(isWallAtMovePoint(movePoint)){
				return false;
			}
			return true;
		}
		return false;
	}

	public boolean isGameClear() {
		String answer = populateMapToStringSortedByPoint();
		for(int i=0;i<answer.length();i++) {
			String myAnswer = String.valueOf(answer.charAt(i));
			if (i==answer.length()-1 && SPACE.equals(myAnswer)) {
				return true;
			}
			if (WALL.equals(myAnswer)) {
				continue;
			}
			String cAnswer = String.valueOf(CORRECT_ANSWER.charAt(i));
			if (!cAnswer.equals(myAnswer)) {
				return false;
			}
		}
		throw new RuntimeException("何かがおかしい");
	}

	public String populateMapToStringSortedByPoint() {
		StringBuilder sb = new StringBuilder();
		for (int y = 0;y<height;y++) {
			for (int x = 0;x<width;x++) {
				Point key = new Point(x,y);
				String value = map.get(key);
				if (value == null) {
					throw new NullPointerException("key["+key+"]");
				}
				sb.append(value);
			}
		}
		return sb.toString();
	}
	
	public void execute(Command cmd) {
		if ("LEFT".equals(cmd.getType())){
			moveToLeft();
		} else if ("RIGHT".equals(cmd.getType())) {
			moveToRight();
		} else if ("UP".equals(cmd.getType())) {
			moveToUp();
		} else if ("DOWN".equals(cmd.getType())) {
			moveToDown();
		} else {
			throw new IllegalArgumentException("not support command:" + cmd.getType());
		}
		
	}

	private void moveToDown() {
		if (canMoveToDown()) {
			Point space = getSpacePoint();
			Point movePoint = new Point(space.getX(), space.getY()+1);
			move(space, movePoint);
		}
	}

	private void moveToUp() {

		if (canMoveToUp()) {
			Point space = getSpacePoint();
			Point movePoint = new Point(space.getX(), space.getY()-1);
			move(space, movePoint);
		}
	}

	private void moveToRight() {
		if (canMoveToRight()) {
			Point space = getSpacePoint();
			Point movePoint = new Point(space.getX()+1, space.getY());
			move(space, movePoint);
		}
		
	}

	private void moveToLeft() {
		if (canMoveToLeft()) {
			Point space = getSpacePoint();
			Point movePoint = new Point(space.getX()-1, space.getY());
			move(space, movePoint);
		}
	}
	
	private void move(Point space, Point movePoint) {
		String movePointValue = map.get(movePoint);
		map.put(movePoint, SPACE);
		map.put(space, movePointValue);
		
	}

	public Point getPoint(String value) {
		String mapStr = populateMapToStringSortedByPoint();
		int index = mapStr.indexOf(value);
		int x = index % width;
		int y = index / width;
		Point p = new Point(x, y);
		return p;
	}

	public Board copy() {
		Map<Point, String> copyMap = new HashMap<Point, String>();
		Set<Point> keySet = map.keySet();
		Iterator<Point> ite = keySet.iterator();
		while(ite.hasNext()) {
			Point key = ite.next();
			String value = map.get(key);
			copyMap.put(key, value);
		}
		
		Board copy = new Board(this.width, this.height, copyMap);
		return copy;
	}
	
	private Point getSpacePoint() {
		return getPoint(SPACE);
	}

	public boolean isLastDestination() {
		Point space = getSpacePoint();
		if (space.getX() == width -1 && space.getY() == height-1) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String mapStr = populateMapToStringSortedByPoint();
		StringBuilder sb = new StringBuilder();
		
		for (int i=0;i<mapStr.length();i++) {
			String str = String.valueOf(mapStr.charAt(i));
			sb.append(str);
			if (i%width==width-1) {
				sb.append("\n");
			}
		}
		
		return sb.toString();
	}

}
