package luyentm.uet.object;

import java.util.ArrayList;

import luyentm.uet.input.MyInputState;
import luyentm.uet.utils.Asserts;
import luyentm.uet.utils.Configs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.batches.BillboardParticleBatch.Config;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class LonTon extends GameObject {
	private float mTimerAnimation;
	private Animation mAnimation;
	private final float mVelocity = 100f;
	private TextureRegion mLontonImage;
	private ArrayList<Bullet> mBullets;
	private Body mBody;

	private Boolean isFlip = false;
	private Boolean isJumpping = false;
	private float oldTime = 0;

	public LonTon() {
		super();
		mTimerAnimation = 0;
		mAnimation = Asserts.source.animationLonton;
		mLontonImage = mAnimation.getKeyFrame(mTimerAnimation);
		mBullets = new ArrayList<Bullet>();

	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(mLontonImage,
				mBody.getPosition().x * 100f - mLontonImage.getRegionWidth()
						/ 2f,
				mBody.getPosition().y * 100f - mLontonImage.getRegionHeight()
						/ 2f);
		for (Bullet bullet : mBullets) {
			bullet.render(batch);
		}

	}

	@Override
	public void update(float deltalTime) {
		mTimerAnimation += deltalTime;
		oldTime += deltalTime;
		mLontonImage = mAnimation.getKeyFrame(mTimerAnimation);
		if (MyInputState.state.isPress(MyInputState.UP) && !isJumpping) {
			isJumpping = true;
			mBody.applyForceToCenter(0, 25, true);
		}
		if (MyInputState.state.isPress(MyInputState.LEFT)) {
			mBody.setLinearVelocity(-2, mBody.getLinearVelocity().y);
		}
		if (MyInputState.state.isPress(MyInputState.RIGHT)) {
			mBody.setLinearVelocity(2, mBody.getLinearVelocity().y);

		}


		if (MyInputState.state.isPress(MyInputState.SPACE) && oldTime > 0.1f) {
			oldTime = 0;
			Asserts.source.sound.play();
			Bullet bullet = new Bullet();
			bullet.setPosition(mBody.getPosition().x * 100f,
					mBody.getPosition().y * 100f);
			bullet.start();
			mBullets.add(bullet);
		}
		for (Bullet bullet : mBullets) {
			bullet.update(deltalTime);
		}

	}

	public Vector2 getBounds() {
		return new Vector2(mLontonImage.getRegionWidth(),
				mLontonImage.getRegionHeight());
	}

	public void setBody(Body body) {
		mBody = body;
	}

	public void setJump(Boolean b) {
		isJumpping = b;
	}

}
