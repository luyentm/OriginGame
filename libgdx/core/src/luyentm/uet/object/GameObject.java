package luyentm.uet.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
	private Vector2 mPosition;

	public GameObject() {
		mPosition = new Vector2();
	}

	public Vector2 getPosition() {
		return mPosition;
	}

	public void setPosition(Vector2 mPosition) {
		this.mPosition = mPosition;
	}

	public void setPosition(float x, float y) {
		mPosition.set(x, y);
	}

	public abstract void render(SpriteBatch batch); // tu ve chinh minh

	public abstract void update(float deltalTime); // cap nhat cac logic

}
