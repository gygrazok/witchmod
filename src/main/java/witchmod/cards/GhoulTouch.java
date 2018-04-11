package witchmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.actions.GhoulTouchAction;

public class GhoulTouch extends AbstractWitchCard{
	public static final String ID = "GhoulTouch";
	public static final	String NAME = "Ghoul Touch";
	public static final	String IMG = "cards/ghoultouch.png";
	public static final	String DESCRIPTION = "Deal !D! damage, then enemy loses Strength for 1 turn equal to the unblocked damage dealt.";

	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;

	private static final int POOL = 1;

	private static final int COST = 2;
	private static final int POWER = 9;
	private static final int UPGRADE_BONUS = 3;

	public GhoulTouch() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GhoulTouchAction(m,new DamageInfo(p, damage)));      
	}

	public AbstractCard makeCopy() {
		return new GhoulTouch();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_BONUS);
		}
	}
}
