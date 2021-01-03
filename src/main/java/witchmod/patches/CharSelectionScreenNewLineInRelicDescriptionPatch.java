package witchmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

@SpirePatch(
        clz = CharacterOption.class,
        method = "renderRelics"
)
public class CharSelectionScreenNewLineInRelicDescriptionPatch {
    public static ExprEditor Instrument() {
        return new ExprEditor() {
            public void edit(MethodCall m) throws CannotCompileException {
                if (m.getMethodName().equals("renderSmartText")) {
                    float lineSpacing = 30.0f;
                    m.replace("{ $7 = " + lineSpacing + "; $_ = $proceed($$); }");
                }
            }
        };
    }
}

