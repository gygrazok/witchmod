package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.unique.VampireDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EternalThirst extends AbstractWitchCleansableCurse {
	public static final String ID = "EternalThirst";
	public static final	String NAME = "Eternal Thirst";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Unplayable. NL Cleanse: suffer at least 20 damage in this fight.";
	public static final	String DESCRIPTION_CLEANSED = "Deal !D! damage, then heal for the unblocked damage dealt.";

	public static final	String[] EXTENDED_DESCRIPTION = new String[] {" NL You have suffered ", " damage."};

	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;

	private static final int COST = 1;
	private static final int POWER = 10;


	public EternalThirst() {
		super(ID, NAME, IMG, DESCRIPTION, RARITY);
		this.baseDamage = POWER;
		this.exhaust = true;
	}

	@Override
	public void cleanse() {
		super.cleanse();
		type = TYPE;
		cost = COST;
		costForTurn = COST;
		isCostModified = false;
		target = TARGET;
		rawDescription = DESCRIPTION_CLEANSED;
		initializeDescription();
	}


	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (!this.dontTriggerOnUseCard && p.hasRelic("Blue Candle")) {
			this.useBlueCandle(p);
		} else {
			AbstractDungeon.actionManager.addToBottom(new VampireDamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AttackEffect.SLASH_DIAGONAL));   
		}
	}

	@Override
	protected boolean cleanseCheck() {
		if (GameActionManager.damageReceivedThisCombat >= 20) {
			return true;
		}
		return false;
	}


	@Override
	public void tookDamage(){
		rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0]+GameActionManager.damageReceivedThisCombat+EXTENDED_DESCRIPTION[1];
		initializeDescription();
	}



	public AbstractCard makeCopy() {
		return new EternalThirst();
	}

	public void upgrade() {

	}
}
