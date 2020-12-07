package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import witchmod.powers.BalefulWardPower;

public class BalefulWard extends AbstractWitchCard {
    public static final String ID = "BalefulWard";
    public static final String NAME = "Baleful Ward";
    public static final String IMG = "cards/balefulward.png";
    public static final String DESCRIPTION = "Gain !B! Block. NL If your Block is broken this turn, add a copy of this card to your hand.";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int POWER = 8;
    private static final int UPGRADE_BONUS = 3;


    public BalefulWard() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseBlock = POWER;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BalefulWardPower(this), 1));
    }

    public AbstractCard makeCopy() {
        return new BalefulWard();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_BONUS);
        }
    }
}
