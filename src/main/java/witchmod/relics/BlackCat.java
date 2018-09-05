package witchmod.relics;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.ReflectionHelper;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.curses.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BlackCat extends AbstractWitchRelic {
	public static final String ID = "BlackCat";
	private static final RelicTier TIER = RelicTier.STARTER;
	private static final String IMG = "relics/blackcat.png";
	private static final LandingSound SOUND = LandingSound.FLAT;

	public BlackCat() {
		super(ID, IMG, TIER, SOUND);
	}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}

	@Override
	public void atBattleStartPreDraw() {
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(getCurse(),1,true,true));
		AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
		flash();
	}

	@Override
	public void onCardDraw(AbstractCard drawnCard) {
		if (drawnCard.type == CardType.CURSE){
			AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
			flash();
		}

	}

	protected AbstractCard getCurse() {
		//we don't want to spawn pain or regret as they are far too punishing in the early game
		//maybe add them in higher floors?
		HashMap<String, AbstractCard> curses = (HashMap<String, AbstractCard>) ReflectionHacks.getPrivateStatic(CardLibrary.class, "curses");
		ArrayList<String> tmp = new ArrayList<>();
		ArrayList<String> prohibitedCards = new ArrayList<>();
		prohibitedCards.add(Necronomicurse.ID);
		prohibitedCards.add(AscendersBane.ID);
		prohibitedCards.add(Normality.ID);
		prohibitedCards.add(Pain.ID);
		prohibitedCards.add(Regret.ID);
		for (Map.Entry<String, AbstractCard> c : curses.entrySet()) {
			boolean ok = true;
			for (String prohibitedCard : prohibitedCards) {
				if (prohibitedCard.equals(c.getValue().cardID)) {
					ok = false;
					break;
				}
			}
			if (ok) {
				tmp.add(c.getKey());
			}
		}
		return curses.get(tmp.get(AbstractDungeon.cardRng.random(0, tmp.size() - 1)));
	}

	@Override
	public AbstractRelic makeCopy() {
		return new BlackCat();
	}
}

