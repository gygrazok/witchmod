package witchmod.cards;

import java.util.HashMap;
import java.util.Map;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.actions.RemembranceAction;

public class BitterMemories extends AbstractWitchCard{
	public static final String ID = "Remembrance";
	public static final	String NAME = "Remembrance";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Shuffle a random card of each different type from your discard pile into your draw pile and gain !B! block for each of them.";
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	
	private static final int COST = 1;
	private static final int POWER = 2;
	private static final int POWER_UPGRADED = 3;

	
	public BitterMemories() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseBlock = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new RemembranceAction(block*countCardTypes()));      
	}
	
	
	public AbstractCard makeCopy() {
		return new BitterMemories();
	}
	
	private int countCardTypes() {
        Map<CardType, Boolean> types = new HashMap<>();
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
        	if (!types.containsKey(c.type)) {
        		types.put(c.type, true);
        	}
        }
        return types.size();
	}
	

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(POWER_UPGRADED);
		}
	}
	
}
