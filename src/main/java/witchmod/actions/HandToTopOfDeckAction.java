/*
 * Decompiled with CFR.
 */
package witchmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class HandToTopOfDeckAction extends AbstractGameAction {
    public static final String TEXT = "Select a card to put on top of your draw pile";
    private AbstractPlayer p = AbstractDungeon.player;

    public HandToTopOfDeckAction(AbstractCreature source, int amount) {
        this.setValues(null, source, amount);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
            isDone = true;
            return;
        }
        if (this.duration == Settings.ACTION_DUR_FASTER) {
            if (p.discardPile.isEmpty()) {
                isDone = true;
                return;
            }
            if (p.hand.size() == 1) {
                AbstractCard tmp = p.hand.getTopCard();
                p.hand.removeCard(tmp);
                p.hand.moveToDeck(tmp, false);
            }
            if (p.hand.group.size() > amount) {
                AbstractDungeon.gridSelectScreen.open(p.hand, amount, TEXT, false, false, false, false);
                tickDuration();
                return;
            }
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                p.hand.removeCard(c);
                p.hand.moveToDeck(c, false);
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
        }
        tickDuration();
    }
}

