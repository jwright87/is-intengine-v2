package uk.intenso.intenginev2.views;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.github.czyzby.lml.parser.LmlView;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisWindow;

public class DefaultView implements LmlView {

    private Stage stage;

    public DefaultView() {
        VisUI.setSkipGdxVersionCheck(true);
        VisUI.load(VisUI.SkinScale.X2);

        stage = new Stage(new ScreenViewport());

        VisTable root = new VisTable();
        root.setFillParent(true);
        stage.addActor(root);

        final VisTextButton textButton = new VisTextButton("click me!");
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                textButton.setText("clicked");
                Dialogs.showOKDialog(stage, "message", "good job!");
            }
        });

        VisWindow window = new VisWindow("example window");
        window.add("this is a simple VisUI window").padTop(5f).row();
        window.add(textButton).pad(10f);
        window.pack();
        window.centerWindow();
        stage.addActor(window.fadeIn());
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public String getViewId() {
        return "0";
    }
}
