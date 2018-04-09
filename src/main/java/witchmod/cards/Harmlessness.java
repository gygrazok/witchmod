package witchmod.cards;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.helpers.TooltipInfo;

public class Harmlessness extends AbstractWitchCleansableCurse {
	public static final String ID = "Harmlessness";
	public static final	String NAME = "Harmlessness";
	public static final	String NAME_CLEANSED = "\"Harmlessness\"";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "Unplayable. NL Cleanse: play 3 skills this turn.";
	public static final	String DESCRIPTION_CLEANSED = "Deal !D! damage to a random enemy 6 times.";

	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
	private static final CardType TYPE = CardType.ATTACK;

	private static final int COST = 1;
	private static final int THRESHOLD = 3;

	private static final int DAMAGE = 4;
	public Harmlessness() {
		super(ID,NAME,IMG,DESCRIPTION,RARITY);
		this.baseDamage = DAMAGE;
		this.checkAtTurnStart = false;
	}

	@Override
	public List<TooltipInfo> getCustomTooltips() {
		List<TooltipInfo> out = new ArrayList<>();
		out.add(new TooltipInfo("Cleansed", "Attack, cost 1, deal "+DAMAGE+" damage to a random enemy 6 times."));
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
		if (!dontTriggerOnUseCard && p.hasRelic("Blue Candle")) {
			useBlueCandle(p);
		} else {
			for (int i = 0; i < 6; i++) {
				AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.getRandomMonster(), new DamageInfo(p, damage, damageTypeForTurn), AttackEffect.SLASH_DIAGONAL, true));
			}
		}
	}

	@Override
	protected boolean cleanseCheck() {
		int count = 0;
		for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
			if (c.type == CardType.SKILL) {
				count++;
			}
		}
		return count >= THRESHOLD;
	}





	public AbstractCard makeCopy() {
		return new Harmlessness();
	}

	public void upgrade() {

	}
}
