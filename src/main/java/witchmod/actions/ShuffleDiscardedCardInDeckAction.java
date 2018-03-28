package witchmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ShuffleDiscardedCardInDeckAction extends AbstractGameAction {
	private AbstractCard card;
	private AbstractPlayer player;
	public ShuffleDiscardedCardInDeckAction(AbstractPlayer player, AbstractCard card) {
		this.player = player;
		this.card = card;
		this.duration = Settings.ACTION_DUR_FAST;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
	}


    @Override
    public void update() {
    	if (this.duration == Settings.ACTION_DUR_FAST) {
            player.discardPile.removeCard(card);
        	player.hand.moveToDeck(card, true);
        	AbstractDungeon.player.hand.refreshHandLayout();
        	this.isDone = true;
    	}

    }
}

