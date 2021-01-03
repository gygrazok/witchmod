package witchmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.NlothsGift;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;

import witchmod.powers.AthamePower;

import java.util.Iterator;


@SpirePatch(cls="com.megacrit.cardcrawl.rooms.AbstractRoom", method="alterCardRarityProbabilities")
public class MonsterRoomPatch {
	public static void Postfix(AbstractRoom __instance) {
        //PATCH FOR ATHAME RARE BONUS
        if (AbstractDungeon.player.hasPower(AthamePower.POWER_ID) && AbstractDungeon.player.getPower(AthamePower.POWER_ID).amount > 0){
            __instance.rareCardChance += AbstractDungeon.player.getPower(AthamePower.POWER_ID).amount;
        }
    }

}
