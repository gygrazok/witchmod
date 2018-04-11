package witchmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.NlothsGift;

import witchmod.powers.AthamePower;


@SpirePatch(cls="com.megacrit.cardcrawl.rooms.MonsterRoom", method="getCardRarity")
public class MonsterRoomPatch {
	public static Object Replace(int roll) {
		boolean hasNlothsGift = AbstractDungeon.player.hasRelic(NlothsGift.ID);
        int rareRate = 3;
		if (AbstractDungeon.player.hasPower(AthamePower.POWER_ID) && AbstractDungeon.player.getPower(AthamePower.POWER_ID).amount > 0){
			rareRate += AbstractDungeon.player.getPower(AthamePower.POWER_ID).amount;
		}
		int baseRate = rareRate;
		if (hasNlothsGift) {
			rareRate *= 3;
		}
        if (roll < rareRate) {
            if (hasNlothsGift && roll > baseRate) {
                AbstractDungeon.player.getRelic(NlothsGift.ID).flash();
            }
            return AbstractCard.CardRarity.RARE;
        }
        if (roll < 40) {
            return AbstractCard.CardRarity.UNCOMMON;
        }
        return AbstractCard.CardRarity.COMMON;
    }
}
