package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class KarmaDrain extends AbstractWitchCard{
	public static final String ID = "KarmaDrain";
	public static final	String NAME = "Karma Drain";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Applies X Weak. Gain !B! block X times.";
	
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	
	private static final int COST = -1;
	private static final int POWER = 4;
	private static final int UPGRADE_BONUS = 2;

	public KarmaDrain() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseBlock = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		int effect = EnergyPanel.getCurrentEnergy();
        if (effect > 0) {
        	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, effect, false), effect));
        	for (int i = 0; i < effect; i++) {
        		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        	}
        }
		p.energy.use(effect);
	}
	

	public AbstractCard makeCopy() {
		return new KarmaDrain();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_BONUS);
		}
	}
}
