package witchmod.characters;

import java.util.ArrayList;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.abstracts.CustomPlayer;
import witchmod.WitchMod;
import witchmod.cardgroup.WitchCardGroup;
import witchmod.patches.WitchEnum;
import witchmod.powers.AbstractWitchPower;




public class WitchCharacter extends CustomPlayer{
	
    public static final int ENERGY_PER_TURN = 3;

	public static final String[] orbTextures = {
			"images/char/orb/layer1.png",
			"images/char/orb/layer2.png",
			"images/char/orb/layer3.png",
			"images/char/orb/layer4.png",
			"images/char/orb/layer5.png",
			"images/char/orb/layer6.png",
			"images/char/orb/layer1d.png",
			"images/char/orb/layer2d.png",
			"images/char/orb/layer3d.png",
			"images/char/orb/layer4d.png",
			"images/char/orb/layer5d.png"
	};

	public int cardsDrawnTotal = 0;
	public int cardsDrawnThisTurn = 0;
	public int cursesDrawnTotal = 0;
	
	public WitchCharacter(String name, PlayerClass setClass) {
		super(name, setClass, orbTextures, "images/char/orb/vfx.png", null, WitchMod.getResourcePath(WitchMod.CHAR_SKELETON_JSON));
		hand = new WitchCardGroup(CardGroup.CardGroupType.HAND);
		
		this.dialogX = this.drawX + 0.0f * Settings.scale;
		this.dialogY = this.drawY + 220.0f * Settings.scale;
		
		initializeClass(null, WitchMod.getResourcePath(WitchMod.CHAR_SHOULDER_2),
				WitchMod.getResourcePath(WitchMod.CHAR_SHOULDER_1),
				WitchMod.getResourcePath(WitchMod.CHAR_CORPSE), 
				getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));
		loadAnimation(WitchMod.getResourcePath(WitchMod.CHAR_SKELETON_ATLAS), WitchMod.getResourcePath(WitchMod.CHAR_SKELETON_JSON), 1.0F); 

        AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
	}
	
	@Override
	public void applyEndOfTurnTriggers() {
		for (AbstractPower p : this.powers) {
			p.atEndOfTurn(true);
		}
	}

	public static ArrayList<String> getStartingDeck() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add("Strike_Witch");
		retVal.add("Strike_Witch");
		retVal.add("Strike_Witch");
		retVal.add("Strike_Witch");
		retVal.add("Defend_Witch");
		retVal.add("Defend_Witch");
		retVal.add("Defend_Witch");
		retVal.add("Defend_Witch");
		retVal.add("Hexdarts");
		/*retVal.add("Bewitch");
		retVal.add("Athame");
		retVal.add("CursedBlade");
		retVal.add("Demonfyre");
		retVal.add("DoomBlade");
		retVal.add("FamiliarProtection");
		retVal.add("Foresight");
		retVal.add("Hexguard");
		retVal.add("PainBolt");
		retVal.add("UnluckySeven");
		retVal.add("UnnaturalEnergy");
		retVal.add("BleedOut");
		retVal.add("Schadenfreude");
		retVal.add("Intelligence");
		retVal.add("KarmaDrain");
		retVal.add("NighInvulnerability");
		retVal.add("RitualOfDecay");
		retVal.add("Shrooms");
		retVal.add("TrollsBlood");
		retVal.add("BroomstickSmash");
		retVal.add("EnfeeblingStrike");
		retVal.add("Graveburst");
		retVal.add("MysticUnburial");
		retVal.add("Remembrance");
		retVal.add("Malady");
		retVal.add("EternalThirst");
		retVal.add("BlackShield");
		retVal.add("BlackBolt");
		retVal.add("MercurialRod");*/
		return retVal;
	}
	
	public static ArrayList<String> getStartingRelics() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add("BlackCat");
		UnlockTracker.markRelicAsSeen("BlackCat");
		return retVal;
	}
	
	public static CharSelectInfo getLoadout() {
		return new CharSelectInfo("The Witch", "A cackling sorceress specialized in dealing with curses.",
				70, 70, 99, 5,
			WitchEnum.WITCH, getStartingRelics(), getStartingDeck(), false);
	}
	
	@Override
	public void draw(int numCards) {
        for (int i = 0; i < numCards; ++i) {
            if (!this.drawPile.isEmpty()) {
                int newCost;
                AbstractCard c = this.drawPile.getTopCard();
                c.current_x = CardGroup.DRAW_PILE_X;
                c.current_y = CardGroup.DRAW_PILE_Y;
                c.setAngle(0.0f, true);
                c.lighten(false);
                c.drawScale = 0.12f;
                c.targetDrawScale = 0.75f;
                if (AbstractDungeon.player.hasPower("Confusion") && c.cost > -1 && c.color != AbstractCard.CardColor.CURSE && c.type != AbstractCard.CardType.STATUS && c.cost != (newCost = AbstractDungeon.cardRandomRng.random(3))) {
                    c.costForTurn = c.cost = newCost;
                    c.isCostModified = true;
                }
                c.triggerWhenDrawn();
                this.hand.addToHand(c);
                this.drawPile.removeTopCard();
                if (AbstractDungeon.player.hasPower("Corruption") && c.type == AbstractCard.CardType.SKILL) {
                    c.setCostForTurn(-9);
                }
                for (AbstractRelic r : this.relics) {
                    r.onCardDraw(c);
                }
                for (AbstractPower p : powers) {
                	if (p instanceof AbstractWitchPower) {
                		((AbstractWitchPower)p).onCardDraw(c);
                	}
                }
                cardsDrawnThisTurn++;
                cardsDrawnTotal++;
                if (c.type == CardType.CURSE) {
                	cursesDrawnTotal++;
                }
                continue;
            }
        }
	}
	
	@Override
	public void applyStartOfTurnCards() {
		super.applyStartOfTurnCards();
		cardsDrawnThisTurn = 0;
	}
	

}
