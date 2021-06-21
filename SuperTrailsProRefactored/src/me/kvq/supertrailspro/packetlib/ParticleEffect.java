package me.kvq.supertrailspro.packetlib;

public enum ParticleEffect {
	
	EXPLOSION_NORMAL("explode", 0, -1),
	
	EXPLOSION_LARGE("largeexplode", 1, -1),
	
	EXPLOSION_HUGE("hugeexplosion", 2, -1),
	
	FIREWORKS_SPARK("fireworksSpark", 3, -1),
	
	WATER_BUBBLE("bubble", 4, -1),
	
	WATER_SPLASH("splash", 5, -1),
	
	WATER_WAKE("wake", 6, 7),
	
	SUSPENDED("suspended", 7, -1),
	
	SUSPENDED_DEPTH("depthSuspend", 8, -1),
	
	CRIT("crit", 9, -1),
	
	CRIT_MAGIC("magicCrit", 10, -1),
	
	SMOKE_NORMAL("smoke", 11, -1),
	
	SMOKE_LARGE("largesmoke", 12, -1),

	SPELL("spell", 13, -1),
	
	SPELL_INSTANT("instantSpell", 14, -1),
	
	SPELL_MOB("mobSpell", 15, -1),

	SPELL_MOB_AMBIENT("mobSpellAmbient", 16, -1),
	
	SPELL_WITCH("witchMagic", 17, -1),
	
	DRIP_WATER("dripWater", 18, -1),
	
	DRIP_LAVA("dripLava", 19, -1),
	
	VILLAGER_ANGRY("angryVillager", 20, -1),
	
	VILLAGER_HAPPY("happyVillager", 21, -1),
	
	TOWN_AURA("townaura", 22, -1),
	
	NOTE("note", 23, -1),
	
	PORTAL("portal", 24, -1),
	
	ENCHANTMENT_TABLE("enchantmenttable", 25, -1),
	
	FLAME("flame", 26, -1),
	
	LAVA("lava", 27, -1),
	
	FOOTSTEP("footstep", 28, -1),
	
	CLOUD("cloud", 29, -1),
	
	REDSTONE("reddust", 30, -1),
	
	SNOWBALL("snowballpoof", 31, -1),
	
	SNOW_SHOVEL("snowshovel", 32, -1),
	
	SLIME("slime", 33, -1),
	
	HEART("heart", 34, -1),
	
	BARRIER("barrier", 35, 8),
	
	ITEM_CRACK("iconcrack", 36, -1),
	
	BLOCK_CRACK("blockcrack", 37, -1),
	
	BLOCK_DUST("blockdust", 38, 7),
	
	WATER_DROP("droplet", 39, 8),
	
	ITEM_TAKE("take", 40, 8),
	
	MOB_APPEARANCE("mobappearance", 41, 8),
	
	DRAGON_BREATH("dragonbreath", 42, -1),
	
	END_ROD("endrod", 43, -1),
	
	DAMAGE("damageindicator", 44, -1),
	
	FALLING_DUST("fallingdust", 46, -1),
	
	SPIT("spit", 47, -1),
	
	TOTEM("totem", 47, -1),
	
	SOUL("soul", 47, -1),
	
	SOUL_FIRE("soul_fire", 47, -1),
	
	NAUTILUS("NAUTILUS",47,-1);
	
	
	
	ParticleEffect(String name, int id,int version) {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return null;
	}
	
	public int getID() {
		return 0;
	}
	
	public String getAquaticName() {
		return null;
	}
	
	public String getPostAcaticName() {
		return null;
	}
	
	public String getCopperName() {
		return null;
	}
	
}