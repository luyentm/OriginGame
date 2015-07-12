package luyentm.uet;

import luyentm.uet.utils.Configs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class MainGameXXX extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Animation animation;
	float stateTime;
	TextureRegion pic;
	TextureRegion i0;
	Animation ani;

	// position :D
	int posX = 100;
	int posY = 0;
	int go = 0;

	@Override
	public void create() {
		Configs.logger.info("create");
		batch = new SpriteBatch();
		i0 = new TextureRegion(new Texture(
				Gdx.files.internal("out_animation/animation0.png")));
		TextureRegion i1 = new TextureRegion(new Texture(
				Gdx.files.internal("out_animation/animation1.png")));
		TextureRegion i2 = new TextureRegion(new Texture(
				Gdx.files.internal("out_animation/animation2.png")));
		TextureRegion i3 = new TextureRegion(new Texture(
				Gdx.files.internal("out_animation/animation3.png")));
		TextureRegion i4 = new TextureRegion(new Texture(
				Gdx.files.internal("out_animation/animation4.png")));
		TextureRegion i5 = new TextureRegion(new Texture(
				Gdx.files.internal("out_animation/animation5.png")));
		TextureRegion i6 = new TextureRegion(new Texture(
				Gdx.files.internal("out_animation/animation6.png")));
		TextureRegion i7 = new TextureRegion(new Texture(
				Gdx.files.internal("out_animation/animation7.png")));
		TextureRegion i8 = new TextureRegion(new Texture(
				Gdx.files.internal("out_animation/animation8.png")));
		TextureRegion i9 = new TextureRegion(new Texture(
				Gdx.files.internal("out_animation/animation9.png")));
		Array<TextureRegion> array = new Array<TextureRegion>();
		array.add(i0);
		array.add(i1);
		array.add(i2);
		array.add(i3);
		array.add(i4);
		array.add(i5);
		array.add(i6);
		array.add(i7);
		array.add(i8);
		array.add(i9);
		stateTime = 0;
		animation = new Animation(0.05f, array, PlayMode.LOOP);
		Gdx.input.setInputProcessor(this);
		pic = i0;
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateTime += 2 * Gdx.graphics.getDeltaTime();
		update();
		batch.begin();

		// batch.draw(img, 0, 0);
		batch.draw(pic, posX, 10);
		batch.end();
	}

	private void update() {
		if (go == 1) {
			posX +=2;
			pic = animation.getKeyFrame(stateTime);
		} else if (go == 2) {
			posX -=2;
			pic = animation.getKeyFrame(stateTime);
		}

	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.LEFT) {
			// di sang trai
			go=2;
		} else if (keycode == Input.Keys.RIGHT) {
			go=1;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		go=0;
		pic = i0;
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
