package witchmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import basemod.BaseMod;

public class ShinyModPatch {
	@SpirePatch(cls="com.megacrit.cardcrawl.helpers.CardLibrary",method="getEachRare")
	public static class GetEachRare{
		public static CardGroup Postfix(CardGroup __result, AbstractPlayer.PlayerClass chosenClass) {
			if (BaseMod.playerColorMap.containsKey(chosenClass.toString())) {
				__result = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for (AbstractCard c : BaseMod.getCustomCardsToAdd()) {
                    if (c.color.toString() != BaseMod.playerColorMap.get(chosenClass.toString()) || c.rarity != AbstractCard.CardRarity.RARE) continue;
                    __result.addToBottom(c.makeCopy());
                }
			}
			return __result;

		}
	}

}
