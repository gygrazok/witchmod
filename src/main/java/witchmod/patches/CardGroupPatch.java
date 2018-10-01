package witchmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

import witchmod.cards.AbstractWitchCard;


@SpirePatch(
		cls="com.megacrit.cardcrawl.cards.CardGroup",
		method="moveToDiscardPile"
)
public class CardGroupPatch {
	public static SpireReturn Prefix(CardGroup __instance, AbstractCard c) {
		if (c instanceof AbstractWitchCard) {
			AbstractWitchCard witchCard = (AbstractWitchCard) c;
			if (witchCard.reshuffleOnUse) { //this is set by the "use" method of the card
				__instance.moveToDeck(witchCard, true);
				witchCard.reshuffleOnUse = false;
				return SpireReturn.Return(null);
			} else if (witchCard.reshuffleOnDiscardFromHand) {
				__instance.moveToDeck(witchCard, true);
			}
		}
		return SpireReturn.Continue();
	}
}
