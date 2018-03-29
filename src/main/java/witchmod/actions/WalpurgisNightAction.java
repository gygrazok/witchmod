package witchmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class WalpurgisNightAction extends AbstractGameAction {
	public WalpurgisNightAction() {
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
	}


    @Override
    public void update() {
    	AbstractPlayer player = AbstractDungeon.player;
    	for (AbstractCard card : player.exhaustPile.group) {
    		if (card.type != CardType.STATUS) {
    	    	player.exhaustPile.moveToDeck(card, true);
    		}
    	}
    	this.isDone = true;

    }
}

