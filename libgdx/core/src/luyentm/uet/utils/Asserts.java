package luyentm.uet.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Asserts {
	// tu tao ra chinh no
	public static final Asserts source = new Asserts();

	public final TextureRegion animation0;
	public final TextureRegion animation1;
	public final TextureRegion animation2;
	public final TextureRegion animation3;
	public final TextureRegion animation4;
	public final TextureRegion animation5;
	public final TextureRegion animation6;
	public final TextureRegion animation7;
	public final TextureRegion animation8;
	public final TextureRegion animation9;
	public final TextureRegion bullet;
	public final Sound sound;
	public Animation animationLonton;

	// singleton
	private Asserts() {
		// chi tao ra duy nhat 1 doi tuong nay
		// ===================Lonton==============================
		animation0 = new TextureRegion(
				loadTexture("images/animation0.png"));
		animation1 = new TextureRegion(
				loadTexture("images/animation1.png"));
		animation2 = new TextureRegion(
				loadTexture("images/animation2.png"));
		animation3 = new TextureRegion(
				loadTexture("images/animation3.png"));
		animation4 = new TextureRegion(
				loadTexture("images/animation4.png"));
		animation5 = new TextureRegion(
				loadTexture("images/animation5.png"));
		animation6 = new TextureRegion(
				loadTexture("images/animation6.png"));
		animation7 = new TextureRegion(
				loadTexture("images/animation7.png"));
		animation8 = new TextureRegion(
				loadTexture("images/animation8.png"));
		animation9 = new TextureRegion(
				loadTexture("images/animation9.png"));
		bullet = new TextureRegion(
				loadTexture("images/bullet.png"));
		sound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet2.mp3"));
		// tao ra animation
		Array<TextureRegion> array = new Array<TextureRegion>();
		array.add(animation0);
		array.add(animation1);
		array.add(animation2);
		array.add(animation3);
		array.add(animation4);
		array.add(animation5);
		array.add(animation6);
		array.add(animation7);
		array.add(animation8);
		array.add(animation9);
		animationLonton = new Animation(0.05f, array, PlayMode.LOOP);

	}

	private static Texture loadTexture(String path) {
		return new Texture(Gdx.files.internal(path));
	}

	public void dispose() {
		animation0.getTexture().dispose();
		animation1.getTexture().dispose();
		animation2.getTexture().dispose();
		animation3.getTexture().dispose();
		animation4.getTexture().dispose();
		animation5.getTexture().dispose();
		animation6.getTexture().dispose();
		animation7.getTexture().dispose();
		animation8.getTexture().dispose();
		animation9.getTexture().dispose();
	}

}
