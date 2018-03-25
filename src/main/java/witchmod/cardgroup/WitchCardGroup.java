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
			if (witchCard.reshuffleOnUse) {
				super.moveToDeck(witchCard, true);
				return;
			}
		}
		super.moveToDiscardPile(c);
    }
	
}
