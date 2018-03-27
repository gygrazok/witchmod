package witchmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.QueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MysticUnburialAction extends AbstractGameAction {
    public static final String TEXT = "Select a card to play";
    private AbstractPlayer p = AbstractDungeon.player;

    public MysticUnburialAction(AbstractMonster target) {
    	this.target = target;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
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
                AbstractCard tmp = this.p.discardPile.getTopCard();
                playCard(tmp);
            } else {
            	CardGroup playableCards = new CardGroup(CardGroupType.UNSPECIFIED);
            	for (AbstractCard card : p.discardPile.group) {
            		if (card.canUse(p,(AbstractMonster) target)) {
                    	playableCards.group.add(card);
            		}
            	}
            	if (playableCards.isEmpty()) {
            		isDone = true;
            	} else {
            		AbstractDungeon.gridSelectScreen.open(playableCards, 1, TEXT, false, false, true, false);
            	}
            }
            this.tickDuration();
            return;
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
            	if (c.canUse(p, (AbstractMonster) target) ) {
            		playCard(c);
            	} else {
            		AbstractDungeon.actionManager.addToTop(new MysticUnburialAction(AbstractDungeon.getRandomMonster()));
            	}
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            isDone = true;
        } else if (AbstractDungeon.gridSelectScreen.cancelWasOn) {
        	isDone = true;
        }
        p.discardPile.refreshHandLayout();
        this.tickDuration();
    }
    
    private void playCard(AbstractCard card) {
    	card.applyPowers();
    	AbstractDungeon.player.discardPile.removeCard(card);
        AbstractDungeon.actionManager.addToTop(new QueueCardAction(card, target));
    	
    }
}

