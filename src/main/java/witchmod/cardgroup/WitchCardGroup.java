package witchmod.cardgroup;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

import witchmod.cards.AbstractWitchCard;

public class WitchCardGroup extends CardGroup{

	public WitchCardGroup(CardGroupType type) {
		super(type);
	}

	@Override
    public void moveToDiscardPile(AbstractCard c) {
		if (c instanceof AbstractWitchCard) {
			AbstractWitchCard witchCard = (AbstractWitchCard) c;
			if (witchCard.reshuffleOnUse) { //this is set by the "use" method of the card
				super.moveToDeck(witchCard, true);
				witchCard.reshuffleOnUse = false;
				return;
			} else if (witchCard.reshuffleOnDiscardFromHand) {
				super.moveToDeck(witchCard, true);
			}
		}
		super.moveToDiscardPile(c);
    }
	
}
