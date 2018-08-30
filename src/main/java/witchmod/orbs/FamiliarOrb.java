package witchmod.orbs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public abstract class FamiliarOrb extends AbstractOrb{
	public int cost;
	public boolean used;
	public boolean upgraded;
	public String descriptionUpgraded;
	private Color whiteColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
	private Color redColor = new Color(0.8f, 0.0f, 0.0f, 1.0f);
	private Texture costBg;
	private Texture activateBg;
	public int magicNumber;

	public FamiliarOrb(String ID, boolean upgraded, int cost, String name, String description, String descriptionUpgraded) {
		this.ID = ID;
		this.img = new Texture("images/familiar/familiar_orb.png");
		this.costBg = new Texture("images/familiar/orb_cost.png");
		this.activateBg = new Texture("images/familiar/orb_activate.png");
		this.cost = cost;
		this.used = false;
		this.upgraded = upgraded;
		this.name = name;
		this.description = description;
		this.descriptionUpgraded = descriptionUpgraded;
		updateDescription();
	}

	@Override
	public void updateDescription() {
		applyFocus();

		String useMessage;
		if (used) {
			useMessage = "[#efc851]Already used this turn.[]";
		} else {
			useMessage = "[#7fff00]Left click to cast.[]";
		}
		
		description = useMessage+" NL "+(upgraded?descriptionUpgraded:description);
		description = description.replaceAll("!M!","#y"+magicNumber);
	}

	@Override
	public void onStartOfTurn() {
		super.onStartOfTurn();
		used = false;
	}

	@Override
	public void update() {
		super.update();
		if (hb.hovered && hb.clicked) {
			hb.clicked = false;
			if (canUse()) {
				onEvoke();
				AbstractDungeon.player.energy.use(cost);
				used = true;
			}
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setColor(c);
		boolean canCast = canUse();
		if (hb.hovered && canCast) {
			sb.draw(activateBg, cX - 48.0F + bobEffect.y / 4.0F, cY - 48.0F + bobEffect.y / 4.0F, 48.0F, 48.0F, 96.0F, 96.0F, scale, scale, 0.0F, 0, 0, 96, 96, false, false);
		}
		sb.draw(img, cX - 48.0F + bobEffect.y / 4.0F, cY - 48.0F + bobEffect.y / 4.0F, 48.0F, 48.0F, 96.0F, 96.0F, scale, scale, 0.0F, 0, 0, 96, 96, false, false);
		sb.draw(costBg, cX - (48.0F + 32f) + bobEffect.y / 4.0F, cY - (48.0F + 32f) + bobEffect.y / 4.0F, 32.0F, 32.0F, 64.0F, 64.0F, scale, scale, 0.0F, 0, 0, 64, 64, false, false);
		
		FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, cost+"", cX + NUM_X_OFFSET, cY + bobEffect.y / 2.0f + NUM_Y_OFFSET, (canCast?whiteColor:redColor), fontScale);

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
		return hasEnoughEnergy() && !AbstractDungeon.actionManager.turnHasEnded;
	}

}
