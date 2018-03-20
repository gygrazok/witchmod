package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class WitchHex extends AbstractWitchCard {
	public static final String ID = "WitchHex";
	public static final	String NAME = "Witch Hex";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Applies 1 Weak, 1 Vulnerability and 1 Frail to an enemy.";
	public static final	String DESCRIPTION_UPGRADED = "Applies 1 Weak, 1 Vulnerability and 1 Frail to all enemies.";
	
	private static final CardRarity RARITY = CardRarity.BASIC;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 0;
	private static final int COST = 1;


	
	public WitchHex() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (upgraded) {
	        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new WeakPower(mo, 1, false),1, true, AbstractGameAction.AttackEffect.NONE));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new VulnerablePower(mo, 1, false),1, true, AbstractGameAction.AttackEffect.NONE));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new FrailPower(mo, 1, false),1, true, AbstractGameAction.AttackEffect.NONE));
	        }
		} else {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, 1, false),1, true, AbstractGameAction.AttackEffect.NONE));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, 1, false),1, true, AbstractGameAction.AttackEffect.NONE));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new FrailPower(m, 1, false),1, true, AbstractGameAction.AttackEffect.NONE));
		}
	}
	
	public AbstractCard makeCopy() {
		return new WitchHex();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			target = CardTarget.ENEMY;
			rawDescription = DESCRIPTION_UPGRADED;
			this.initializeDescription();
			
		}
	}
}
