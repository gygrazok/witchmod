package witchmod.cards;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.helpers.TooltipInfo;

public class UnluckySeven extends AbstractWitchCleansableCurse {
	public static final String ID = "UnluckySeven";
	public static final	String NAME = "Unlucky Seven";
	public static final	String NAME_CLEANSED = "Lucky Seven";
	public static final	String IMG = "cards/unluckyseven.png";
	public static final	String DESCRIPTION = "Unplayable. NL Cleanse: have your current health ending with 7.";
	public static final	String DESCRIPTION_CLEANSED = "Gain !M! [B]. NL Draw !M! cards. NL Exhaust.";
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;

	private static final int COST = 0;
	private static final int POWER = 7;


	public UnluckySeven() {
		super(ID, NAME, IMG, DESCRIPTION, RARITY);
		this.baseMagicNumber = this.magicNumber = POWER;
		this.exhaust = true;
	}

	@Override
	public List<TooltipInfo> getCustomTooltips() {
		List<TooltipInfo> out = new ArrayList<>();
		out.add(new TooltipInfo("Cleansed", "Skill, cost 0, gain 7 energy and draw 7 cards, exhaust."));
		return out;
	}

	@Override
	public void cleanse() {
		super.cleanse();
		type = TYPE;
		cost = COST;
		costForTurn = COST;
		isCostModified = false;
		target = TARGET;
		name = NAME_CLEANSED;
		rawDescription = DESCRIPTION_CLEANSED;
		initializeDescription();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (!this.dontTriggerOnUseCard && p.hasRelic("Blue Candle")) {
			this.useBlueCandle(p);
		} else {
			AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(magicNumber));
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
		}
	}

	@Override
	protected boolean cleanseCheck() {
		int lastDigit = AbstractDungeon.player.currentHealth%10;
		if (lastDigit == 7) {
			return true;
		}
		return false;
	}


	public AbstractCard makeCopy() {
		return new UnluckySeven();
	}

	public void upgrade() {

	}
}
