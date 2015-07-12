package luyentm.uet.screen;

import luyentm.uet.body.MapBodyBuilder;
import luyentm.uet.input.HandleInput;
import luyentm.uet.input.MyInputState;
import luyentm.uet.object.LonTon;
import luyentm.uet.utils.Configs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.batches.BillboardParticleBatch.Config;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class MenuGameScreen implements Screen {
	private Game mGame;
	private SpriteBatch mSpriteBatch;
	LonTon lonTon;

	private final float WORLD_GRAVITY_X = 0;
	private final float WORLD_GRAVITY_Y = -11f;

	// bien cho viec load map len
	Matrix4 debugMatrix;

	private TiledMap mTileMap;// de luu cai map load len
	private OrthographicCamera mCamera;

	private OrthogonalTiledMapRenderer mMapRenderer;
	// private LonTon mLonton;
	private Body mLontonBody;
	// Cac bien cho cac tuong tac

	private World mWorld;
	private Box2DDebugRenderer debugRenderer;

	public MenuGameScreen(Game game) {
		mGame = game;
		mCamera = new OrthographicCamera(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

		mSpriteBatch = new SpriteBatch();

		mTileMap = new TmxMapLoader().load("maps/level1.tmx");
		mMapRenderer = new OrthogonalTiledMapRenderer(mTileMap);
		//
		mWorld = new World(new Vector2(WORLD_GRAVITY_X, WORLD_GRAVITY_Y), true);
		debugRenderer = new Box2DDebugRenderer();
		MapBodyBuilder.buildShapes(mTileMap, 8, mWorld);
		debugMatrix = new Matrix4();

	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(new HandleInput());
		// cho nay khoi tao cho cac bien
		// mLonton = new LonTon();
		lonTon = new LonTon();
		BodyDef mLontonBodyDef = new BodyDef();
		mLontonBodyDef.type = BodyDef.BodyType.DynamicBody;
		mLontonBodyDef.position.set(1, 3);

		mLontonBody = mWorld.createBody(mLontonBodyDef);
		mLontonBody.setFixedRotation(true);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(lonTon.getBounds().x / 2 / 100f,
				lonTon.getBounds().y / 2 / 100f);

		FixtureDef mLontonFixture = new FixtureDef();
		mLontonFixture.density = 0.5f;
		mLontonFixture.shape = shape;
		mLontonFixture.friction = 1f;
		mLontonFixture.filter.categoryBits = Configs.PLAYER_BITS;
		mLontonFixture.filter.maskBits = Configs.WALL_DIE_BITS
				| Configs.PEACE_BITS;
		// mLontonFixture.restitution = 0.2f;

		mLontonBody.createFixture(mLontonFixture);
		lonTon.setBody(mLontonBody);
		// mLonton.setBody(mLontonBody);
		shape.dispose();
		// tao ra bon map

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mWorld.step(1 / 45f, 6, 2);

		// chi dinh xem la thuon camera nao
		mSpriteBatch.setProjectionMatrix(mCamera.combined);
		mMapRenderer.setView(mCamera);

		mMapRenderer.render();// map render truoc
		mSpriteBatch.begin();
		lonTon.render(mSpriteBatch);
		mSpriteBatch.end();
		debugMatrix = mSpriteBatch.getProjectionMatrix().cpy()
				.scale(100, 100, 0);
		debugRenderer.render(mWorld, debugMatrix);
		mWorld.setContactListener(new ContactListener() {

			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {

			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {

			}

			@Override
			public void endContact(Contact contact) {

			}

			@Override
			public void beginContact(Contact contact) {
				if ((contact.getFixtureA().getFilterData().categoryBits == Configs.PLAYER_BITS || contact
						.getFixtureB().getFilterData().categoryBits == Configs.PLAYER_BITS)
						&& (contact.getFixtureA().getFilterData().categoryBits == Configs.WALL_DIE_BITS || contact
								.getFixtureB().getFilterData().categoryBits == Configs.WALL_DIE_BITS)) {
					lonTon.setJump(false);

				}
			}
		});
		mCamera.update();
		update(delta);

	}

	private void update(float deltalTime) {
		mCamera.zoom = MyInputState.state.getZoom();
		lonTon.update(deltalTime);

		if (MyInputState.state.isPress(MyInputState.Q)) {
			if (!isShaking) {
				isShaking = true;
				stateShark = 1;
			}
		}
		// startSharkScreen();

	}

	private int stateShark = 0;
	private boolean isShaking = false;
	private float rateOfSharkinh = 10;
	private int delay = 50;
	private long oldTime = 0l;

	private void startSharkScreen() {
		switch (stateShark) {
		case 0:
			// ko lam gi ca
			oldTime = System.currentTimeMillis();
			break;
		case 1:

			if (System.currentTimeMillis() - oldTime > delay) {
				oldTime = System.currentTimeMillis();
				stateShark = 2;
				mCamera.position.set(mCamera.position.x - 1 * rateOfSharkinh,
						mCamera.position.y - 0 * rateOfSharkinh, 0);
			}
			break;
		case 2:
			if (System.currentTimeMillis() - oldTime > delay) {
				oldTime = System.currentTimeMillis();
				stateShark = 3;
				mCamera.position.set(mCamera.position.x + 2 * rateOfSharkinh,
						mCamera.position.y + 0 * rateOfSharkinh, 0);
			}
			break;
		case 3:
			if (System.currentTimeMillis() - oldTime > delay) {
				oldTime = System.currentTimeMillis();
				stateShark = 4;
				mCamera.position.set(mCamera.position.x - 1 * rateOfSharkinh,
						mCamera.position.y - 0 * rateOfSharkinh, 0);
			}
			break;
		case 4:
			if (System.currentTimeMillis() - oldTime > delay) {
				oldTime = System.currentTimeMillis();
				stateShark = 5;
				mCamera.position.set(mCamera.position.x - 2 * rateOfSharkinh,
						mCamera.position.y - 0 * rateOfSharkinh, 0);
			}
			break;
		case 5:
			if (System.currentTimeMillis() - oldTime > delay) {
				oldTime = System.currentTimeMillis();
				stateShark = 6;
				mCamera.position.set(mCamera.position.x + 4 * rateOfSharkinh,
						mCamera.position.y + 0 * rateOfSharkinh, 0);
			}
			break;
		case 6:
			if (System.currentTimeMillis() - oldTime > delay) {
				oldTime = System.currentTimeMillis();
				stateShark = 7;
				mCamera.position.set(mCamera.position.x - 2 * rateOfSharkinh,
						mCamera.position.y - 0 * rateOfSharkinh, 0);
			}
			break;
		case 7:
			stateShark = 8;
			// mCamera.position.set(mCamera.position.x, mCamera.position.y, 0);
			break;
		case 8:
			isShaking = false;
			stateShark = 0;// bao la ko cap nhat nua
			break;
		default:
			break;
		}

	}

	@Override
	public void resize(int width, int height) {
		mCamera.viewportWidth = width;
		mCamera.viewportHeight = height;
		mCamera.position.x = width / 2;
		mCamera.position.y = height / 2;
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		mSpriteBatch.dispose();

	}

}
