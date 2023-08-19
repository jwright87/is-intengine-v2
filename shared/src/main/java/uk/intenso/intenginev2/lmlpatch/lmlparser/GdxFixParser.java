package uk.intenso.intenginev2.lmlpatch.lmlparser;

import com.github.czyzby.kiwi.util.common.Exceptions;
import com.github.czyzby.lml.parser.LmlData;
import com.github.czyzby.lml.parser.LmlStyleSheet;
import com.github.czyzby.lml.parser.LmlSyntax;
import com.github.czyzby.lml.parser.LmlTemplateReader;
import com.github.czyzby.lml.parser.impl.DefaultLmlParser;

public class GdxFixParser extends DefaultLmlParser {
    public GdxFixParser(LmlData data) {
        super(data);
    }

    public GdxFixParser(LmlData data, LmlSyntax syntax) {
        super(data, syntax);
    }

    public GdxFixParser(LmlData data, LmlSyntax syntax, LmlTemplateReader templateReader) {
        super(data, syntax, templateReader);
    }

    public GdxFixParser(LmlData data, LmlSyntax syntax, LmlTemplateReader templateReader, LmlStyleSheet styleSheet) {
        super(data, syntax, templateReader, styleSheet);
    }

    public GdxFixParser(LmlData data, LmlSyntax syntax, LmlTemplateReader templateReader, boolean strict) {
        super(data, syntax, templateReader, strict);
    }

    public GdxFixParser(LmlData data, LmlSyntax syntax, LmlTemplateReader templateReader, LmlStyleSheet styleSheet, boolean strict) {
        super(data, syntax, templateReader, styleSheet, strict);
    }


    @Override
    public boolean parseBoolean(String rawLmlData) {
        try {
            return super.parseBoolean(rawLmlData);
        }catch (Error t) {
            Exceptions.ignore(t);
            System.out.println("Errror: " +t.getMessage());
            return false;
        }
    }

    @Override
    public boolean parseBoolean(String rawLmlData, Object forActor) {
        try {
            return super.parseBoolean(rawLmlData);
        }catch (Error t) {
            Exceptions.ignore(t);
            System.out.println("Errror: " +t.getMessage());
            return false;
        }
    }
}
