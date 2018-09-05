package witchmod.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.ReflectionHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;

@SpirePatch(clz = CharacterOption.class, method = "renderRelics")
public class CharSelectionScreenNewLineInRelicDescriptionPatch {
    public static void Replace(CharacterOption instance, SpriteBatch sb) {
        CharSelectInfo charInfo = (CharSelectInfo)ReflectionHacks.getPrivate(instance,CharacterOption.class,"charInfo");
        float infoX = (float)ReflectionHacks.getPrivate(instance,CharacterOption.class,"infoX");
        float infoY = (float)ReflectionHacks.getPrivate(instance,CharacterOption.class,"infoY");
        if (charInfo.relics.size() == 1) {
            sb.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
            sb.draw(RelicLibrary.getRelic(charInfo.relics.get(0)).outlineImg, infoX - 64.0f, infoY - 60.0f * Settings.scale - 64.0f, 64.0f, 64.0f, 128.0f, 128.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 128, 128, false, false);
            sb.setColor(Color.WHITE);
            sb.draw(RelicLibrary.getRelic(charInfo.relics.get(0)).img, infoX - 64.0f, infoY - 60.0f * Settings.scale - 64.0f, 64.0f, 64.0f, 128.0f, 128.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 128, 128, false, false);
            FontHelper.renderSmartText(sb, FontHelper.tipHeaderFont, RelicLibrary.getRelic(charInfo.relics.get(0)).name, infoX + 44.0f * Settings.scale, infoY - 40.0f * Settings.scale, 10000.0f, 10000.0f, Settings.GOLD_COLOR);
            String relicString = RelicLibrary.getRelic(charInfo.relics.get(0)).description;
            if (charInfo.name.equals(CharacterOption.TEXT[7])) {
                relicString = CharacterOption.TEXT[8];
            }
            //the patch is in the following line
            FontHelper.renderSmartText(sb, FontHelper.tipBodyFont, relicString, infoX + 44.0f * Settings.scale, infoY - 66.0f * Settings.scale, 10000.0f, /* PATCHED TO CHANGE THE LINE SPACING*/30.0f * Settings.scale, Settings.CREAM_COLOR);
        } else {
            for (int i = 0; i < charInfo.relics.size(); ++i) {
                AbstractRelic r = RelicLibrary.getRelic(charInfo.relics.get(i));
                r.updateDescription(charInfo.color);
                Hitbox relicHitbox = new Hitbox(80.0f * Settings.scale * (0.01f + (1.0f - 0.019f * (float)charInfo.relics.size())), 80.0f * Settings.scale);
                relicHitbox.move(infoX + (float)i * 72.0f * Settings.scale * (0.01f + (1.0f - 0.019f * (float)charInfo.relics.size())), infoY - 60.0f * Settings.scale);
                relicHitbox.render(sb);
                relicHitbox.update();
                if (relicHitbox.hovered) {
                    if ((float)InputHelper.mX < 1400.0f * Settings.scale) {
                        TipHelper.queuePowerTips((float)InputHelper.mX + 60.0f * Settings.scale, (float)InputHelper.mY - 50.0f * Settings.scale, r.tips);
                    } else {
                        TipHelper.queuePowerTips((float)InputHelper.mX - 350.0f * Settings.scale, (float)InputHelper.mY - 50.0f * Settings.scale, r.tips);
                    }
                }
                sb.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
                sb.draw(r.outlineImg, infoX - 64.0f + (float)i * 72.0f * Settings.scale * (0.01f + (1.0f - 0.019f * (float)charInfo.relics.size())), infoY - 60.0f * Settings.scale - 64.0f, 64.0f, 64.0f, 128.0f, 128.0f, Settings.scale * (0.01f + (1.0f - 0.019f * (float)charInfo.relics.size())), Settings.scale * (0.01f + (1.0f - 0.019f * (float)charInfo.relics.size())), 0.0f, 0, 0, 128, 128, false, false);
                sb.setColor(Color.WHITE);
                sb.draw(r.img, infoX - 64.0f + (float)i * 72.0f * Settings.scale * (0.01f + (1.0f - 0.019f * (float)charInfo.relics.size())), infoY - 60.0f * Settings.scale - 64.0f, 64.0f, 64.0f, 128.0f, 128.0f, Settings.scale * (0.01f + (1.0f - 0.019f * (float)charInfo.relics.size())), Settings.scale * (0.01f + (1.0f - 0.019f * (float)charInfo.relics.size())), 0.0f, 0, 0, 128, 128, false, false);
            }
        }
    }
}
