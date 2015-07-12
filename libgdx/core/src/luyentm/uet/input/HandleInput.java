package luyentm.uet.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class HandleInput implements InputProcessor {

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Input.Keys.DOWN:
			MyInputState.state.update(MyInputState.DOWN, true);
			break;
		case Input.Keys.LEFT:
			MyInputState.state.update(MyInputState.LEFT, true);
			break;
		case Input.Keys.RIGHT:
			MyInputState.state.update(MyInputState.RIGHT, true);
			break;

		case Input.Keys.UP:
			MyInputState.state.update(MyInputState.UP, true);
			break;
		case Input.Keys.SPACE:
			MyInputState.state.update(MyInputState.SPACE, true);
			break;
		case Input.Keys.Q:
			MyInputState.state.update(MyInputState.Q, true);
			break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Input.Keys.DOWN:
			MyInputState.state.update(MyInputState.DOWN, false);
			break;
		case Input.Keys.LEFT:
			MyInputState.state.update(MyInputState.LEFT, false);
			break;
		case Input.Keys.RIGHT:
			MyInputState.state.update(MyInputState.RIGHT, false);
			break;

		case Input.Keys.UP:
			MyInputState.state.update(MyInputState.UP, false);
			break;
		case Input.Keys.SPACE:
			MyInputState.state.update(MyInputState.SPACE, false);
			break;
		case Input.Keys.Q:
			MyInputState.state.update(MyInputState.Q, false);
			break;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		MyInputState.state.zoom(amount);
		return false;
	}

}
