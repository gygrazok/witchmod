package witchmod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;

public class WickedInspiration extends AbstractWitchCard {
	public static final String ID = "WickedInspiration";
	public static final	String NAME = "WickedInspiration";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Draw a card for each curse in your hand.";
	public static final	String DESCRIPTION_UPGRADED = "Draw a card for each curse in your hand plus !M!.";
	
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	private static final int COST = 0;
	private static final int POWER = 0;
	private static final int UPGRADED_BONUS = 1;

	public WickedInspiration() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.magicNumber = this.baseMagicNumber = POWER;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		int effect = 0;
		for (AbstractCard c : p.hand.group) {
			if (c.type == CardType.CURSE) {
				effect++;
			}
		}
		effect += magicNumber;
		if (effect > 0) {
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(m, effect));
			AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new VerticalAuraEffect(Color.BLACK, p.hb.cX, p.hb.cY), 0.2f));
		}
	}
	
	public AbstractCard makeCopy() {
		return new WickedInspiration();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADED_BONUS);
			rawDescription = DESCRIPTION_UPGRADED;
			initializeDescription();
		}
	}
}
