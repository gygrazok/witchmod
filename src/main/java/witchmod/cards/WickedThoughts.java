package witchmod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;

public class WickedThoughts extends AbstractWitchCard {
	public static final String ID = "WickedThoughts";
	public static final	String NAME = "Wicked Thoughts";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Retain. Draw !M! card for each curse in your hand.";
	public static final	String DESCRIPTION_UPGRADED = "Retain. NL Draw !M! cards for each curse in your hand.";

	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;

	private static final int POOL = 1;
	private static final int COST = 0;
	private static final int POWER = 1;
	private static final int UPGRADED_BONUS = 1;

	public WickedThoughts() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.magicNumber = this.baseMagicNumber = POWER;
		this.retain = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		int effect = 0;
		for (AbstractCard c : p.hand.group) {
			if (c.type == CardType.CURSE) {
				effect++;
				if (upgraded) {
					effect++;
				}
			}
		}
		if (effect > 0) {
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(m, effect));
			AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new VerticalAuraEffect(Color.BLACK, p.hb.cX, p.hb.cY), 0.2f));
		}
	}

	public AbstractCard makeCopy() {
		return new WickedThoughts();
	}

	@Override
	public void atTurnStart() {
		super.atTurnStart();
		retain = true;
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADED_BONUS);
			rawDescription = DESCRIPTION_UPGRADED;
			initializeDescription();
			retain = true;
		}
	}
}
