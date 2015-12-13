package com.mdeiml.ld34.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.mdeiml.ld34.LD34Game;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(384*2, 288*2);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new LD34Game();
        }
}