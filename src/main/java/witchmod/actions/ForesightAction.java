package witchmod.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;

public class ForesightAction extends AbstractGameAction{
	public ForesightAction() {
		this.duration = 0.0f;
		this.actionType = AbstractGameAction.ActionType.WAIT;
	}

	@Override
	public void update() {
		if (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() == 0) {
			this.isDone = true;
			return;
		}
		if (AbstractDungeon.player.drawPile.isEmpty()) {
			AbstractDungeon.actionManager.addToTop(new ForesightAction());
			AbstractDungeon.actionManager.addToTop(new EmptyDeckShuffleAction());
			this.isDone = true;
			return;
		}
		AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
		if (card.type == CardType.CURSE || card.type == CardType.STATUS) {
			card.current_y = -200.0f * Settings.scale;
			card.target_x = Settings.WIDTH / 2.0f + 200;
			card.target_y = Settings.HEIGHT / 2.0f;
			card.targetAngle = 0.0f;
			card.lighten(false);
			card.drawScale = 0.12f;
			card.targetDrawScale = 0.75f;
			AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
		} else {
			AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new VerticalAuraEffect(Color.PURPLE, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 0.0f));
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(card.makeStatEquivalentCopy(), 1));
		}
		this.isDone = true;
	}
}