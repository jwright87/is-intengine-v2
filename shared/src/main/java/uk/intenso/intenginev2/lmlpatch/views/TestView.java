package uk.intenso.intenginev2.lmlpatch.views;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.github.czyzby.lml.annotation.LmlActor;
import com.github.czyzby.lml.parser.impl.AbstractLmlView;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TestView extends AbstractLmlView {

    private Stage stage;

    public TestView() {
        super(new Stage(new ScreenViewport()));
        setup();
    }

//    private Set<String> eventTypes = new HashSet<>();
    private final List<String> messages = new ArrayList<>();

    public void setup() {
        VisUI.load(VisUI.SkinScale.X2);
        testTable = new VisTable();
        testTable.setFillParent(true);

        testTitleLabel = new VisLabel();
        testTitleLabel.setText("Welcome to Intenso Test UI!");
        testTable.add(testTitleLabel);//.pad(20);
        testTable.row();
        testUpdateLabel = new VisLabel();
        testTable.add(testUpdateLabel);
        testTable.row();
        testTextField = new VisTextField();
        testTextField.setWidth(20f);
        testTextField.setMaxLength(100);
        testTable.add(testTextField);
        testTextButton = new VisTextButton("Send");
        addTextButtonTouchDownListener();
        testTable.add(testTextButton);
        testTable.pack();
        stage.addActor(testTable);
    }

    class IsTouchDownListener extends InputListener {
        private VisTextField textField;

        public IsTouchDownListener(VisTextField textField) {
            this.textField = textField;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            moveTextFromTextFieldToLabel(testTextField.getText());
            return super.touchDown(event, x, y, pointer, button);
        }
    }

    private void addTextButtonTouchDownListener() {
        testTextButton.addListener(new IsTouchDownListener(testTextField));
    }

    @LmlActor("testTable")
    private VisTable testTable;

    @LmlActor("testTextField")
    private VisTextField testTextField;

    @LmlActor("testTitleLabel")
    private VisLabel testTitleLabel;
    @LmlActor("testUpdateLabel")
    private VisLabel testUpdateLabel;

    @LmlActor("testTextButton")
    private VisTextButton testTextButton;

    public Stage getStage() {
        return stage;
    }

    @Override
    public String getViewId() {
        //FIXME gen from ID System?
        return UUID.randomUUID().toString();
    }

    private void addEventToMessages(Event event) {
        messages.add("Received Event: Targrt: " + event.getTarget().getName() +
            "Listener: " + event.getListenerActor().getName());
    }

    private boolean moveTextFromTextFieldToLabel(String text) {
        if (!text.isBlank()) {
            messages.add(text.trim());
            testTextField.setText("");
            testUpdateLabel.setText(messages.stream().collect(Collectors.joining("\n")));
            return true;
        }
        return false;
    }

    //    private VisLmlParserBuilder lmlParserBuilder;
//
//    private LmlParser lmlParser;
}
