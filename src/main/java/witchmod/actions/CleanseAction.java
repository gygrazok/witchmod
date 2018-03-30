package witchmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.ExhaustBlurEffect;
import com.megacrit.cardcrawl.vfx.ExhaustEmberEffect;

import witchmod.cards.AbstractWitchCleansableCurse;

public class CleanseAction extends AbstractGameAction{
	private AbstractWitchCleansableCurse card;
	public CleanseAction(AbstractWitchCleansableCurse card) {
		this.card = card;
		this.duration = Settings.ACTION_DUR_FAST;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
	}

	@Override
	public void update() {
		if (duration == Settings.ACTION_DUR_FAST) {
            int i;
            CardCrawlGame.sound.play("CARD_EXHAUST", 0.2f);
            for (i = 0; i < 90; ++i) {
                AbstractDungeon.effectsQueue.add(new ExhaustBlurEffect(card.current_x, card.current_y));
            }
            for (i = 0; i < 50; ++i) {
                AbstractDungeon.effectsQueue.add(new ExhaustEmberEffect(card.current_x, card.current_y));
            }
		}
		tickDuration();
		if (isDone) {
			card.cleanse();
		}
	}
}