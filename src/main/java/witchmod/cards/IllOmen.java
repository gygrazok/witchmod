package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.powers.IllOmenPower;

public class IllOmen extends AbstractWitchCard{
	public static final String ID = "IllOmen";
	public static final	String NAME = "Ill Omen";
	public static final	String IMG = "cards/illomen.png";
	public static final	String DESCRIPTION = "Shuffle a random Curse in your draw pile. The next time you draw a Curse deal !D! Damage to ALL enemies. Exhaust.";
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.NONE;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	
	private static final int COST = 1;
	private static final int POWER = 14;
	private static final int UPGRADE_BONUS = 4;

	public IllOmen() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IllOmenPower(p,damage),damage));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(p, p, AbstractDungeon.returnRandomCurse(), 1, true, true));
		
	}

	public AbstractCard makeCopy() {
		return new IllOmen();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_BONUS);
		}
	}
}
