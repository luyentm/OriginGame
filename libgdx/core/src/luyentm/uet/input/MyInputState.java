package luyentm.uet.input;

import java.util.HashMap;

public class MyInputState {
	private HashMap<String, Boolean> mHashMap;

	public static final String RIGHT = "right";
	public static final String LEFT = "left";
	public static final String UP = "up";
	public static final String DOWN = "down";
	public static final String SPACE = "space";
	public static final String ZOOM = "zoom";
	public static final String Q = "q";

	private float zoom;

	public static MyInputState state = new MyInputState();

	private MyInputState() {
		mHashMap = new HashMap<String, Boolean>();
		mHashMap.put(RIGHT, false);
		mHashMap.put(LEFT, false);
		mHashMap.put(DOWN, false);
		mHashMap.put(UP, false);
		mHashMap.put(SPACE, false);
		mHashMap.put(Q, false);
		zoom = 1;

	}

	public void update(String key, Boolean bool) {
		mHashMap.put(key, bool);
	}

	public boolean isPress(String key) {
		return (boolean) mHashMap.get(key);
	}

	public void zoom(int x) {
		if (x == 1) {
			zoom += 0.02f;
		} else {
			zoom -= 0.02f;
		}
	}

	public float getZoom() {
		return zoom;
	}
}
