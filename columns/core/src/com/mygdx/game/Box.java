package com.mygdx.game;

import columns.View;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Box extends Actor {
	static Texture[] textures = new Texture[9];
    static Color Colors[] = {Color.BLACK,Color.CYAN,Color.BLUE,Color.RED,Color.GREEN,
    Color.YELLOW,Color.PINK,Color.MAGENTA,Color.BLACK};

    static {
    	for (int i=0; i<Colors.length; i++)
    		textures[i] = createTexture(i);
    }

	private Texture _texture;
	public Box(int colorIndex) {
		_texture = textures[colorIndex];
		
	}
	private static Texture createTexture(int c) {
		Texture texture = null;
		
		Pixmap image = new Pixmap (View.SL, View.SL, Pixmap.Format.RGBA8888);
		image.setColor(Colors[c]);
		image.fill();
		
		image.setColor(c==8 ? Color.WHITE : Color.BLACK);
		image.drawRectangle(0, 0, View.SL, View.SL);

		texture = new Texture(image);
		return texture;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(_texture, this.getX(), this.getY(), this.getOriginX(), this.getOriginY(),
				this.getWidth()/*_texture.getWidth()*/, this.getHeight(), this.getScaleX(), this.getScaleY(), 
				this.getRotation(), 0, 0, _texture.getWidth(), _texture.getHeight(),
				false, false);
	}
	public void setColor(int c) {
		_texture = textures[c];
		
	}
}
