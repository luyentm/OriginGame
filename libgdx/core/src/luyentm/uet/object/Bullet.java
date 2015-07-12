package luyentm.uet.object;

import luyentm.uet.utils.Asserts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public class Bullet extends GameObject {
	private Body mBody;
	private Boolean isLife = false;
	private float velocity = 25;

	@Override
	public void render(SpriteBatch batch) {
		if (isLife) {
			batch.draw(Asserts.source.bullet, getPosition().x
					- Asserts.source.bullet.getRegionWidth() / 2f,
					getPosition().y - Asserts.source.bullet.getRegionHeight());

		}
	}

	@Override
	public void update(float deltalTime) {
		if (isLife) {
			setPosition(getPosition().x, getPosition().y + velocity);
			if (getPosition().x > Gdx.graphics.getWidth()
					|| getPosition().y > Gdx.graphics.getWidth()) {
				isLife = false;
			}
		}
	}

	public void setBody(Body body) {
		mBody = body;
	}

	public void start() {
		isLife = true;
	}

}
