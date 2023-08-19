package uk.intenso.intenginev2.lmlpatch;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.function.Consumer;

public class TouchDownListener extends ClickListener {

    private Consumer<Widget> widgetConsumer;
    private Widget target;

    public TouchDownListener(Consumer<Widget> widgetConsumer, Widget target) {
        this.widgetConsumer = widgetConsumer;
        this.target = target;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
       widgetConsumer.accept(target);
       return super.touchDown(event,x,y,pointer,button);
    }
}
