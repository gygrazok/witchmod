package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Harmlessness extends AbstractWitchCleansableCurse {
    public static final String ID = "Harmlessness";
    public static final String NAME = "Harmlessness";
    public static final String NAME_CLEANSED = "\"Harmlessness\"";
    public static final String IMG = "cards/harmlessness.png";
    public static final String DESCRIPTION = "Unplayable. NL Cleanse: play 3 skills this turn.";
    public static final String DESCRIPTION_CLEANSED = "Deal !D! damage to a random enemy !M! times.";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int COST = 1;
    private static final int THRESHOLD = 3;

    private static final int DAMAGE = 5;
    private static final int MAGIC = 5;

    public Harmlessness(boolean hasCardPreview) {
        super(ID, IMG, RARITY);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = MAGIC;
        this.checkAtTurnStart = false;
        if (hasCardPreview) {
            Harmlessness tmp = new Harmlessness(false);
            tmp.cleanse(false);
            cardPreviewTooltip = tmp;
        }
    }

    public Harmlessness() {
        this(true);
    }

    @Override
    public void cleanse(boolean applyPowers) {
        type = TYPE;
        cost = COST;
        costForTurn = COST;
        isCostModified = false;
        target = TARGET;
        name = cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
        super.cleanse(applyPowers);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!dontTriggerOnUseCard && p.hasRelic("Blue Candle") && !cleansed) {
            useBlueCandle(p);
        } else {
            for (int i = 0; i < baseMagicNumber; i++) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.getRandomMonster(), new DamageInfo(p, damage, damageTypeForTurn), AttackEffect.SLASH_DIAGONAL, true));
            }
        }
    }

    @Override
    protected boolean cleanseCheck() {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (c.type == CardType.SKILL) {
                count++;
            }
        }
        return count >= THRESHOLD;
    }


    public AbstractCard makeCopy() {
        return new Harmlessness();
    }

    public void upgrade() {

    }
}
