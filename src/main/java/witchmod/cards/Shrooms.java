package witchmod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;

import witchmod.actions.ShroomsAction;

public class Shrooms extends AbstractWitchCard {
	public static final String ID = "Shrooms";
	public static final	String NAME = "'Shrooms";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Draw !M! cards. They have random costs for this turn.";
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	private static final int COST = 0;
	private static final int POWER = 2;
	private static final int UPGRADED_BONUS = 2;


	
	public Shrooms() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.magicNumber = this.baseMagicNumber = POWER;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		for (int i = 0; i < this.magicNumber; i++) {
			AbstractDungeon.actionManager.addToBottom(new ShroomsAction());
		}
		AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new VerticalAuraEffect(Color.PINK, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), (float)0.1*this.magicNumber));

	}
	
	public AbstractCard makeCopy() {
		return new Shrooms();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADED_BONUS);
			upgradedMagicNumber = true;
		}
	}
}
