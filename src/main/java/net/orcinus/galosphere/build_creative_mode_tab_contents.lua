local LOCAL_NAMESPACE <const> = 'galosphere'
local function handleResourceLocation (r)
	if type(r) == 'table' then
		for i = 1, #r do
			r[i] = handleResourceLocation(r[i])
		end
		return r
	end
	if type(r) ~= 'string' then
		r = tostring(r)
	end

	if r:sub(1, 1) == ':' then
		return LOCAL_NAMESPACE..r
	end
	return r
end
local function handleArgzorz (r)
	if type(r) == 'string' then
		r = { r }
	end
	return handleResourceLocation(r)
end

local function __MK_MAJIGGER (typemode, onestep)
	return function (arg1)
		local outs = {
			type = typemode,
		}
		if onestep then
			outs.things = handleArgzorz(arg1)
			return outs
		else
			outs.target = arg1
			return function (thingz)
				outs.things = handleArgzorz(thingz)
				return outs
			end
		end
	end
end

local before <const> = __MK_MAJIGGER('insert before', false)
local after <const> = __MK_MAJIGGER('insert after', false)
local accept <const> = __MK_MAJIGGER('accept', true)


return {
	tabs = {
		{
			name = 'galosphere',
			icon = ':icon_item',
			title = 'itemGroup.galosphere.galosphere',
			items = {
				':silver_upgrade_smithing_template',
				':preserved_template',
				':saltbound_tablet',
				':berserker_spawn_egg',
				':preserved_spawn_egg',
				':allurite_shard',
				':lumiere_shard',
				':pink_salt_shard',
				':raw_silver',
				':silver_ingot',
				':silver_nugget',
				':barometer',
				':sterling_helmet',
				':sterling_chestplate',
				':sterling_leggings',
				':sterling_boots',
				':sterling_horse_armor',
				':lichen_cordyceps',
				':golden_lichen_cordyceps',
				':salted_jerky',
				':preserved_flesh',
				':cured_membrane',
				':chandelier',
				':gilded_beads',
				':monstrometer',
				':warped_anchor',
				':silver_balance',
				':silver_tiles',
				':silver_tiles_stairs',
				':silver_tiles_slab',
				':silver_panel',
				':silver_panel_stairs',
				':silver_panel_slab',
				':silver_lattice',
				':silver_ore',
				':deepslate_silver_ore',
				':silver_block',
				':raw_silver_block',
				':allurite_block',
				':lumiere_block',
				':charged_lumiere_block',
				':allurite_cluster',
				':lumiere_cluster',
				':glinted_allurite_cluster',
				':glinted_lumiere_cluster',
				':glinted_amethyst_cluster',
				':amethyst_stairs',
				':amethyst_slab',
				':allurite_stairs',
				':allurite_slab',
				':lumiere_stairs',
				':lumiere_slab',
				':smooth_amethyst',
				':smooth_amethyst_stairs',
				':smooth_amethyst_slab',
				':smooth_allurite',
				':smooth_allurite_stairs',
				':smooth_allurite_slab',
				':smooth_lumiere',
				':smooth_lumiere_stairs',
				':smooth_lumiere_slab',
				':amethyst_bricks',
				':amethyst_brick_stairs',
				':amethyst_brick_slab',
				':allurite_bricks',
				':allurite_brick_stairs',
				':allurite_brick_slab',
				':lumiere_bricks',
				':lumiere_brick_stairs',
				':lumiere_brick_slab',
				':chiseled_amethyst',
				':chiseled_allurite',
				':chiseled_lumiere',
				':amethyst_lamp',
				':allurite_lamp',
				':lumiere_lamp',
				':lichen_moss',
				':lichen_roots',
				':bowl_lichen',
				':lichen_shelf',
				':glow_ink_clumps',
				':pink_salt',
				':rose_pink_salt',
				':pastel_pink_salt',
				':pink_salt_stairs',
				':rose_pink_salt_stairs',
				':pastel_pink_salt_stairs',
				':pink_salt_slab',
				':rose_pink_salt_slab',
				':pastel_pink_salt_slab',
				':pink_salt_wall',
				':rose_pink_salt_wall',
				':pastel_pink_salt_wall',
				':polished_pink_salt',
				':polished_rose_pink_salt',
				':polished_pastel_pink_salt',
				':polished_pink_salt_stairs',
				':polished_rose_pink_salt_stairs',
				':polished_pastel_pink_salt_stairs',
				':polished_pink_salt_slab',
				':polished_rose_pink_salt_slab',
				':polished_pastel_pink_salt_slab',
				':polished_pink_salt_wall',
				':polished_rose_pink_salt_wall',
				':polished_pastel_pink_salt_wall',
				':pink_salt_bricks',
				':rose_pink_salt_bricks',
				':pastel_pink_salt_bricks',
				':pink_salt_brick_stairs',
				':rose_pink_salt_brick_stairs',
				':pastel_pink_salt_brick_stairs',
				':pink_salt_brick_slab',
				':rose_pink_salt_brick_slab',
				':pastel_pink_salt_brick_slab',
				':pink_salt_brick_wall',
				':rose_pink_salt_brick_wall',
				':pastel_pink_salt_brick_wall',
				':chiseled_pink_salt',
				':chiseled_rose_pink_salt',
				':chiseled_pastel_pink_salt',
				':pink_salt_chamber',
				':pink_salt_lamp',
				':pink_salt_straw',
				':pink_salt_cluster',
				':pink_salt_cluster',
				':cured_membrane_block',
				':stranded_membrane_block',
			},
		},
	},
	appends = {
		['minecraft:building_blocks'] = {
			after 'minecraft:amethyst_block' {
				':amethyst_stairs',
				':amethyst_slab',
				':chiseled_amethyst',
				':amethyst_lamp',
				':smooth_amethyst',
				':smooth_amethyst_stairs',
				':smooth_amethyst_slab',
				':amethyst_bricks',
				':amethyst_brick_stairs',
				':amethyst_brick_slab',
				':allurite_block',
				':allurite_stairs',
				':allurite_slab',
				':chiseled_allurite',
				':allurite_lamp',
				':smooth_allurite',
				':smooth_allurite_stairs',
				':smooth_allurite_slab',
				':allurite_bricks',
				':allurite_brick_stairs',
				':allurite_brick_slab',
				':lumiere_block',
				':lumiere_stairs',
				':lumiere_slab',
				':chiseled_lumiere',
				':lumiere_lamp',
				':smooth_lumiere',
				':smooth_lumiere_stairs',
				':smooth_lumiere_slab',
				':lumiere_bricks',
				':lumiere_brick_stairs',
				':lumiere_brick_slab',
			},
			accept {
				':silver_block',
				':silver_panel',
				':silver_panel_stairs',
				':silver_panel_slab',
				':silver_tiles',
				':silver_tiles_stairs',
				':silver_tiles_slab',
				':silver_lattice',
			},
		},
		['minecraft:natural_blocks'] = {
			after 'minecraft:deepslate_iron_ore' {
				':silver_ore',
				':deepslate_silver_ore',
			},
			after 'minecraft:raw_gold_block' ':raw_silver_block',
			after 'minecraft:amethyst_cluster' {
				':glinted_amethyst_cluster',
				':allurite_block',
				':allurite_cluster',
				':glinted_allurite_cluster',
				':lumiere_block',
				':lumiere_cluster',
				':glinted_lumiere_cluster',
			},
			after 'minecraft:glow_lichen' ':glow_ink_clumps',
			after 'minecraft:pearlescent_froglight' {
				':amethyst_lamp',
				':allurite_lamp',
				':lumiere_lamp',
			},
			-- this was split into two separate if blocks. why??
			after 'minecraft:snow' ':lichen_moss',
			after 'minecraft:red_mushroom' ':bowl_lichen',
			after 'minecraft:cactus' {
				':lichen_shelf',
				':lichen_roots',
			},
		},
		['minecraft:ingredients'] = {
			after 'minecraft:raw_gold' ':raw_silver',
			after 'minecraft:gold_ingot' ':silver_ingot',
			after 'minecraft:gold_nugget' ':silver_nugget',
			after 'minecraft:amethyst_shard' {
				':allurite_shard',
				':lumiere_shard',
			},
		},
		['minecraft:functional_blocks'] = {
			after 'minecraft:end_rod' ':chandelier',
			after 'minecraft:smithing_table' ':combustion_table',
			after 'minecraft:respawn_anchor' {
				':monstrometer',
				':warped_anchor',
			},
		},
		['minecraft:food_and_drinks'] = {
			after 'minecraft:glow_berries' {
				':lichen_cordyceps',
				':golden_lichen_cordyceps',
			},
		},
		['minecraft:spawn_eggs'] = {
			after 'minecraft:sheep_spawn_egg' {
				':sparkle_spawn_egg',
				':spectre_spawn_egg',
				':specterpillar_spawn_egg',
			},
		},
		['minecraft:tools_and_utilities'] = {
			after 'minecrafT:clock'  ':barometer',
			before 'minecraft:saddle' {
				':glow_flare',
				':spectre_flare',
			},
		},
		['minecraft:combat'] = {
			after 'minecraft:chainmail_boots' {
				':sterling_helmet',
				':sterling_chestplate',
				':sterling_leggings',
				':sterling_boots',
			},
			after 'minecraft:leather_horse_armor' ':sterling_horse_armor',
			after 'minecraft:tnt' ':silver_bomb',
		},
	},
}
