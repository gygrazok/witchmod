package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Harmlessness extends AbstractWitchCleansableCurse {
	public static final String ID = "Harmlessness";
	public static final	String NAME = "Harmlessness";
	public static final	String NAME_CLEANSED = "\"Harmlessness\"";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "Unplayable. NL Cleanse: have no Attack cards in hand and in discard pile.";
	public static final	String DESCRIPTION_CLEANSED = "Deal !D! damage to a random enemy 6 times.";

	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
	private static final CardType TYPE = CardType.ATTACK;

	private static final int COST = 3;

	private static final int DAMAGE = 6;
	public Harmlessness() {
		super(ID,NAME,IMG,DESCRIPTION,RARITY);
		this.baseDamage = DAMAGE;
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
			for (int i = 0; i < 6; i++) {
				AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.getRandomMonster(), new DamageInfo(p, damage, damageTypeForTurn), AttackEffect.SLASH_DIAGONAL, true));
			}
		}
	}

	@Override
	protected boolean cleanseCheck() {
		AbstractPlayer p = AbstractDungeon.player;
		for (AbstractCard c : p.hand.group) {
			if (c.type == CardType.ATTACK) {
				return false;
			}
		}
		for (AbstractCard c : p.discardPile.group) {
			if (c.type == CardType.ATTACK) {
				return false;
			}
		}
		return true;
	}





	public AbstractCard makeCopy() {
		return new Harmlessness();
	}

	public void upgrade() {

	}
}
