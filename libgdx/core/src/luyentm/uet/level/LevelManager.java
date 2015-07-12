package luyentm.uet.level;

public class LevelManager {
	/*
	 * Quan ly tat ca cac logic lienn quan den level cua game
	 */
	private int mCurrentLevel;

	public int getCurrentLevel() {
		return mCurrentLevel;
	}

	public int getNextLevel() {
		return mCurrentLevel;
	}

	public void updateLevel() {
		mCurrentLevel++;

	}
}
