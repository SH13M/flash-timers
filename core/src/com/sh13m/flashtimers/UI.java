package com.sh13m.flashtimers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import org.jetbrains.annotations.NotNull;

public class UI implements Screen, NativeKeyListener {
    private final FlashTimers app;


    private final Texture TOP;
    private final Texture JGL;
    private final Texture MID;
    private final Texture ADC;
    private final Texture SUP;

    private int TOP_TIME;
    private int JGL_TIME;
    private int MID_TIME;
    private int ADC_TIME;
    private int SUP_TIME;

    private float timeBuffer;
    private final BitmapFont font;

    public UI (FlashTimers app) {
        this.app = app;

        TOP = new Texture(Gdx.files.internal("TOP.png"));
        JGL = new Texture(Gdx.files.internal("JUNGLE.png"));
        MID = new Texture(Gdx.files.internal("MIDDLE.png"));
        ADC = new Texture(Gdx.files.internal("ADC.png"));
        SUP = new Texture(Gdx.files.internal("SUPPORT.png"));

        TOP_TIME = 0;
        JGL_TIME = 0;
        MID_TIME = 0;
        ADC_TIME = 0;
        SUP_TIME = 0;

        timeBuffer = 0;
        font = new BitmapFont(Gdx.files.internal(
                "ubuntu.fnt"),
                Gdx.files.internal("ubuntu_0.png"),
                false);
        NativeKeyListener listener = new NativeKeyListener() {
            @Override
            public void nativeKeyPressed(@NotNull NativeKeyEvent e) {
                if (e.getKeyCode() == NativeKeyEvent.VC_F6) {
                    TOP_TIME = 300;
                }
                if (e.getKeyCode() == NativeKeyEvent.VC_F7) {
                    JGL_TIME = 300;
                }
                if (e.getKeyCode() == NativeKeyEvent.VC_F8) {
                    MID_TIME = 300;
                }
                if (e.getKeyCode() == NativeKeyEvent.VC_F9) {
                    ADC_TIME = 300;
                }
                if (e.getKeyCode() == NativeKeyEvent.VC_F10) {
                    SUP_TIME = 300;
                }
            }
        };

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            System.err.println(e.getMessage());
        }
        GlobalScreen.addNativeKeyListener(listener);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update();

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        app.batch.setProjectionMatrix(app.cam.combined);
        app.batch.begin();
        app.batch.draw(TOP,255,365, 64,64);
        app.batch.draw(JGL,255,285, 64,64);
        app.batch.draw(MID,255,205, 64,64);
        app.batch.draw(ADC,255,125, 64,64);
        app.batch.draw(SUP,255,45, 64,64);
        font.getData().setScale(1f);
        font.draw(app.batch, Time.formatTime(TOP_TIME), 335, 412);
        font.draw(app.batch, Time.formatTime(JGL_TIME), 335, 332);
        font.draw(app.batch, Time.formatTime(MID_TIME), 335, 252);
        font.draw(app.batch, Time.formatTime(ADC_TIME), 335, 172);
        font.draw(app.batch, Time.formatTime(SUP_TIME), 335, 92);
        font.getData().setScale(0.4f);
        font.draw(app.batch, "F6", 225, 408);
        font.draw(app.batch, "F7", 225, 328);
        font.draw(app.batch, "F8", 225, 248);
        font.draw(app.batch, "F9", 225, 168);
        font.draw(app.batch, "F10", 215, 88);
        app.batch.end();
    }

    private void update() {
        timeBuffer += Gdx.graphics.getDeltaTime();
        if (timeBuffer >= 1f) {
            TOP_TIME -= 1;
            if (TOP_TIME < 0) TOP_TIME = 0;
            JGL_TIME -= 1;
            if (JGL_TIME < 0) JGL_TIME = 0;
            MID_TIME -= 1;
            if (MID_TIME < 0) MID_TIME = 0;
            ADC_TIME -= 1;
            if (ADC_TIME < 0) ADC_TIME = 0;
            SUP_TIME -= 1;
            if (SUP_TIME < 0) SUP_TIME = 0;

            timeBuffer -= 1f;
        }
    }

    @Override
    public void resize(int width, int height) {
        app.viewport.update(width, height);
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
        TOP.dispose();
        JGL.dispose();
        MID.dispose();
        ADC.dispose();
        SUP.dispose();
        font.dispose();

        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            System.err.println(e.getMessage());
        }
    }
}
