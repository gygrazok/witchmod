package witchmod.orbs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.defect.EvokeWithoutRemovingOrbAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputAction;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import witchmod.actions.EvokeFamiliarAction;

public abstract class FamiliarOrb extends AbstractOrb{
	public int cost;
	public boolean used;
	public boolean upgraded;
	public String rawDescription;
	public String descriptionUpgraded;
	private Color whiteColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
	private Color redColor = new Color(0.8f, 0.0f, 0.0f, 1.0f);
	private Texture costBg;
	private Texture activateBg;
	public int magicNumber;
	public int baseMagicNumber;

	public FamiliarOrb(String ID, boolean upgraded, int cost, String name, String description, String descriptionUpgraded) {
		this.ID = ID;
		this.img = new Texture("images/familiars/"+ID+".png");
		this.costBg = new Texture("images/familiars/orb_cost.png");
		this.activateBg = new Texture("images/familiars/orb_activate.png");
		this.cost = cost;
		this.used = false;
		this.upgraded = upgraded;
		this.name = name;
		this.description = description;
		this.descriptionUpgraded = descriptionUpgraded;
		this.rawDescription = upgraded?descriptionUpgraded:description;
		updateDescription();
	}

	@Override
	public void updateDescription() {
		applyFocus();
		calcMagicNumber();

		String useMessage;
		if (used) {
			useMessage = "#rAlready #rused #rthis #rturn.";
		} else {
			useMessage = "#gLeft #gclick #gto #gcast.";
		}
		
		description = useMessage+" NL "+rawDescription;


		description = description.replaceAll("!M!","#y"+magicNumber);
	}

	@Override
	public void onStartOfTurn() {
		super.onStartOfTurn();
		used = false;
		updateDescription();
	}

	@Override
	public void update() {
		super.update();
		if (hb.hovered && (InputHelper.justReleasedClickLeft || CInputActionSet.select.isJustPressed())) {
			if (canUse()) {
				AbstractDungeon.effectsQueue.add(new DarkOrbActivateEffect(cX,cY));
				AbstractDungeon.actionManager.addToBottom(new EvokeFamiliarAction(this));
			}
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setColor(c);
		boolean canCast = canUse();
		if (hb.hovered && canCast) {
			sb.draw(activateBg, cX - 48.0F, cY - 48.0F + bobEffect.y / 4.0F, 48.0F, 48.0F, 96.0F, 96.0F, scale*1.2f, scale*1.2f, 0.0F, 0, 0, 96, 96, false, false);
		}
		sb.draw(img, cX - 48.0F, cY - 48.0F + bobEffect.y / 4.0F, 48.0F, 48.0F, 96.0F, 96.0F, scale, scale, 0.0F, 0, 0, 96, 96, false, false);
		sb.draw(costBg, cX - (79.0F), cY + (30.0F) + bobEffect.y / 3.0F, 32.0F, 32.0F, 64.0F, 64.0F, scale, scale, 0.0F, 0, 0, 64, 64, false, false);
		
		FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, cost+"", cX + NUM_X_OFFSET - 67f, cY + bobEffect.y / 3.0f + NUM_Y_OFFSET + 72f, (canCast?whiteColor:redColor), fontScale);

		hb.render(sb);
	}

	@Override
	protected void renderText(SpriteBatch sb) {
	}


	@Override
	public void playChannelSFX() {
		// TODO Auto-generated method stub
	}

	public boolean hasEnoughEnergy(){
		return cost <= EnergyPanel.totalCount;
	}

	public boolean canUse() {
		return hasEnoughEnergy() && !AbstractDungeon.actionManager.turnHasEnded && !used;
	}

	public void calcMagicNumber() {

	}

}
