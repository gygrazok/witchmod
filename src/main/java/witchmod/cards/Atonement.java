package witchmod.cards;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Atonement extends AbstractWitchCard{
	public static final String ID = "Atonement";
	public static final	String NAME = "Atonement";
	public static final	String IMG = "cards/atonement.png";
	public static final	String DESCRIPTION = "Exhaust a random Curse or Status card in your draw pile. NL Gain !B! Block.";
	
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	
	private static final int COST = 1;
	private static final int POWER = 7;
	private static final int POWER_UPGRADED = 4;

	
	public Atonement() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseBlock = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractCard toExhaust = getRandomCurseOrStatusFromDeck();
		if (toExhaust != null) {
			toExhaust.current_y = -200.0f * Settings.scale;
			toExhaust.target_x = Settings.WIDTH / 2.0f;
			toExhaust.target_y = Settings.HEIGHT / 2.0f;
			toExhaust.targetAngle = 0.0f;
			toExhaust.lighten(false);
			toExhaust.drawScale = 0.12f;
			toExhaust.targetDrawScale = 0.75f;
			AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(toExhaust, p.drawPile));
		}
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p,p,block));
	}
	
	
	public AbstractCard makeCopy() {
		return new Atonement();
	}
	
	private AbstractCard getRandomCurseOrStatusFromDeck() {
		List<AbstractCard> candidates = new ArrayList<>();
		for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
			if (c.type == CardType.CURSE || c.type == CardType.STATUS) {
				candidates.add(c);
			}
		}
		if (candidates.size() == 0) {
			return null;
		}
		return candidates.get(MathUtils.random(candidates.size() - 1));
		
	}
	
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBlock(POWER_UPGRADED);
		}
	}
	
}
