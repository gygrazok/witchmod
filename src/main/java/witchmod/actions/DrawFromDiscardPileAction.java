package witchmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DrawFromDiscardPileAction extends AbstractGameAction {
    public static final String TEXT = "Select a card to draw";
    private AbstractPlayer p = AbstractDungeon.player;
    private Boolean ignoreCost = false;
    private Boolean random = false;

    public DrawFromDiscardPileAction(int cards, Boolean random, Boolean ignoreCost) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.random = random;
        this.ignoreCost = ignoreCost;
        this.amount = cards;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
            isDone = true;
            return;
        }
        if (duration == Settings.ACTION_DUR_FASTER) {
            if (p.discardPile.isEmpty()) {
                isDone = true;
                return;
            }
            if (p.discardPile.size() == 1) {
                AbstractCard tmp = p.discardPile.getTopCard();
                drawCard(tmp);
            } else if (random == false) {
            	if (p.discardPile.group.isEmpty()) {
            		isDone = true;
            	} else {
            		AbstractDungeon.gridSelectScreen.open(p.discardPile, 1, TEXT, false, false, true, false);
            	}
            } else {
            	for (int i = 0; i < Math.min(amount, p.discardPile.size()); i++) {
            		AbstractCard card = p.discardPile.getRandomCard(true);
            		drawCard(card);
            	}
            }
            this.tickDuration();
            return;
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
           		drawCard(c);
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            isDone = true;
        }
        p.discardPile.refreshHandLayout();
        this.tickDuration();
    }
    
    private void drawCard(AbstractCard card) {
    	if (ignoreCost) {
            card.freeToPlayOnce = true;
    	}
    	card.applyPowers();
		AbstractDungeon.player.discardPile.moveToDeck(card, false);
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
    }
}

