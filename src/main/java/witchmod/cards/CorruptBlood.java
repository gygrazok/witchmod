package witchmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.actions.CorruptBloodAction;

public class CorruptBlood extends AbstractWitchCard{
	public static final String ID = "CorruptBlood";
	public static final	String NAME = "Corrupt Blood";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "If the target is Poisoned transform all Poison into Rot, otherwise apply !M! Poison. NL Recurrent.";

	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.SKILL;

	private static final int POOL = 1;

	private static final int COST = 1;
	private static final int POWER = 2;
	private static final int UPGRADE_BONUS = 2;

	public CorruptBlood() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.magicNumber = this.baseMagicNumber = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new CorruptBloodAction(m,p,magicNumber));
	}


	public AbstractCard makeCopy() {
		return new CorruptBlood();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_BONUS);
		}
	}
}
